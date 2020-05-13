
app.controller('pharmacyCtrl', function ($scope,$http,$interval,$uibModal,$log) {

		 
		$scope.refresh  = function() {	
				$http({
						method: 'GET',
						url : "http://localhost/hospital/getPrescriptions",
					}).success(function(data, status, headers, config) {
						$scope.pharmacyqueuelist = data;
					}).error(function(data, status, headers, config) {
					});		
		};
		
		$scope.confirmprescription = function (row) {

						var	modalInstance = $uibModal.open({
							  animation: 'true',
							  ariaLabelledBy: 'modal-title',
							  ariaDescribedBy: 'modal-body',
							  templateUrl: 'http://localhost/hosp/pharmacy/prescription-confirm-modal.html',
							  controller: 'pharmacyConfirmationCtrl',
							  size: 'md',
							  resolve: {
								fulfilledprescription: function () {
									return row;

								}
							  }	
		  
							});
			}; // end of scope.open function
		
		

			  
});

app.controller('pharmacyConfirmationCtrl', function ($uibModalInstance,$http,$scope,fulfilledprescription) {

			$scope.cancel = function () {
                    $uibModalInstance.dismiss('cancel'); 
                };
				
			$scope.confirm = function () {
				var res = $http({
						method: 'POST',
						url : "http://localhost/hospital/fulfillprescription",
						data: fulfilledprescription
					}).success(function(data, status, headers, config) {
						$scope.refresh();
					}).error(function(data, status, headers, config) {
						alert( "failure message: " + JSON.stringify({data: data}));
					});	
				$uibModalInstance.close();
            };
});
