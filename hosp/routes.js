var app = angular.module("myApp", ["ui.router",'ngAnimate', 'ngSanitize', "ui.bootstrap",'smart-table','infinite-scroll']);

app.run(['$location','$rootScope','AuthService',function($location,$rootScope,AuthService) {
    $rootScope.$on("$locationChangeStart", function(event, next, current) { 
	    //var currentPath = current.split('#/')[1];
		var nextpath = next.split('#/')[1];
	if(nextpath == 'login' || nextpath == 'register'){
		return;
	}else{
		if(AuthService.isvaliduser() !== true){
			$location.path("/login");
		}

	}
	
	// handle route changes  
				// $http({
						// method: 'GET',
						// url : "http://localhost/hospital/validateuser"
					// }).success(function(data, status, headers, config) {
					// alert(data);
					// }).error(function(data, status, headers, config) {
					// });		
    });
}]);

app.service('AuthService', function($http,$location) {
	
    this.isvaliduser = function() {
	alert("22");
	if(sessionStorage.getItem("loggedin") == 'Y'){
		return true;
	}else{
		return false;
	}
			
			// $http({
					// method: 'GET',
					// url : "http://localhost/hospital/validateuser",
				// }).success(function(data, status, headers, config) {
					// alert(data);
					// return data;

				// }).error(function(data, status, headers, config) {
									// alert(data);

				// });	       
    };

	this.logout = function(){
		sessionStorage.clear();
		$location.path("/login");		
	};
    
});

app.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/login');    
	
	$stateProvider
	.state('home', {
            url: '/home',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/templates/patient-info-template.html' ,
					controller: 'loginCtrl'
				},
                'nav@home': { 
					templateUrl: 'http://localhost/hosp/templates/hosp-nav.html',
                    controller: 'loginCtrl'
				}
            }
        })
		.state('newpatient', {
            url: '/newpatient',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/templates/patient-info-template.html' ,
					controller: 'newPatientCtrl'
				},
                'nav@newpatient': { 
					templateUrl: 'http://localhost/hosp/templates/hosp-nav.html',
                    controller: 'newPatientCtrl'
				},
                'hospbody@newpatient': { 
                    templateUrl: 'http://localhost/hosp/newpatient/patient-info.html',
                    controller: 'newPatientCtrl'
                }
            }
        })
		.state('existing', {
            url: '/existing',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/templates/patient-info-template.html' ,
					controller: 'newPatientCtrl'
				},
                'nav@existing': { 
					templateUrl: 'http://localhost/hosp/templates/hosp-nav.html',
                    controller: 'newPatientCtrl'
				},
                'hospbody@existing': { 
                    templateUrl: "http://localhost/hosp/existing/search-form.html",
                    controller: 'existingPatientCtrl'
                }
            }

        })
		.state('queue', {
            url: '/queue',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/templates/patient-info-template.html' ,
					controller: 'newPatientCtrl'
				},
                'nav@queue': { 
					templateUrl: 'http://localhost/hosp/templates/hosp-nav.html',
                    controller: 'newPatientCtrl'
				},
                'hospbody@queue': { 
                    templateUrl: "http://localhost/hosp/patientqueue/queue-info.html",
                    controller: 'queueCtrl'
                }
            }
			
        })
		.state('login', {
            url: '/login',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/login/login-info.html' ,
					controller: 'loginCtrl'
				}
            }
        })
		.state('register', {
            url: '/register',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/registration/register-info.html' ,
					controller: 'registerCtrl'
				}
            }
			
        })
		.state('pharmacyqueue', {
            url: '/pharmacyqueue',
            views: {
                '': { 
					templateUrl: 'http://localhost/hosp/templates/patient-info-template.html' ,
					controller: 'newPatientCtrl'
				},
                'nav@pharmacyqueue': { 
					templateUrl: 'http://localhost/hosp/templates/hosp-nav.html',
                    controller: 'newPatientCtrl'
				},
                'hospbody@pharmacyqueue': { 
                    templateUrl: "http://localhost/hosp/pharmacy/prescription-queue.html",
                    controller: 'pharmacyCtrl'
                }
            }
			
        });
});














	
app.directive('restrict', function($window){
	return{
		restrict: 'A',
		prioriry: 100000,
		scope: false,
		link: function(){
			// alert('ergo sum!');
		},
		compile:  function(element, attr, linker){
			var accessDenied = true;
			var attributes = attr.access.split(" ");
			for(var i in attributes){
				if(sessionStorage.getItem("role") == attributes[i]){
					accessDenied = false;
				}
			}

			if(accessDenied){
				element.children().remove();
				element.remove();			
			}

		}
	}
});








 