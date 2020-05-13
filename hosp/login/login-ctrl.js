app.controller('loginCtrl', function ($window,$location,$scope,$http,AuthService) {

		$scope.login  = function() {
		
				var dataObj = ({
								userId : $scope.vm.user.username,
								pwd : $scope.vm.user.password
							});
		$http({
				method: 'POST',
				url : "http://localhost/hospital/login",
				data: dataObj
			}).success(function(data, status, headers, config) {
				$scope.regresult = data;
				$http.defaults.headers.common.jwttoken = headers()['jwttoken'];
				sessionStorage.role = headers()['role'];
				sessionStorage.loggedin = headers()['loggedin'];
				$location.path("/home");
			}).error(function(data, status, headers, config) {

			});		
		};
		
		$scope.logout = function(){
			AuthService.logout();
		};

});