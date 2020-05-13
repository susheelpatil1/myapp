app.controller("newPatientCtrl", function($scope,$http,$location,$uibModal) {
	
		$scope.states = ["Maharashtra", "Karnataka", "Tamilnadu","Orissa"];    
		var patient = $scope;
		
		$scope.clear  = function() {
			$scope.firstName = '';
			$scope.lastName = '';
			$scope.phone = '';
			$scope.addressLine1 = '';
			$scope.addressLine2 = '';
			$scope.city = '';
			$scope.state = '';
			$scope.email = '';
			$scope.comment = '';
		};
		
		$scope.saveData  = function() {
				var dataObj = ({
					firstName : patient.firstName,
					lastName : patient.lastName,
					phone : patient.phone,
					addressLine1 : patient.addressLine1,
					addressLine2 : patient.addressLine2,
					city : patient.city,
					state : patient.state,
					email : patient.email,
					comments : patient.comment
				});


			var res = $http({
					method: 'POST',
					url : "http://localhost/hospital/savepatientinfo",
					data: dataObj
				});
				res.success(function(data, status, headers, config) {
					$scope.message = data;
						$scope.clear();
						var	modalInstance = $uibModal.open({
							  animation: 'true',
							  ariaLabelledBy: 'modal-title',
							  ariaDescribedBy: 'modal-body',
							  templateUrl: 'http://localhost/hosp/newpatient/savedata-confirm-modal.html',
							  controller: 'newPatientSaveCtrl',
							  size: 'md'

		  
							});					
				})
				
				res.error(function(data, status, headers, config) {
					alert( "failure message: " + JSON.stringify({data: data}));
				});		
			
		};
		

	});
	
	
	app.controller('newPatientSaveCtrl', function ($uibModalInstance,$http,$scope) {
			$scope.cancel = function () {
                    $uibModalInstance.dismiss('cancel'); 
                };
	});
