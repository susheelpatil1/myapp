app.controller("existingPatientCtrl", function($scope,$http,$uibModal, $log) {


	$scope.patientstoselect = null;

	$scope.searchPatient  = function() {
	
					var dataObj = ({
					id : $scope.patientid, 
					firstName : $scope.firstname,
					lastName : $scope.lastname,
					phone : $scope.phone
					
				});


			var res = $http({
					method: 'POST',
					url : "http://localhost/hospital/searchPatient",
					data: dataObj
				}).success(function(data, status, headers, config) {
					$scope.patientstoselect = data;
	
				}).error(function(data, status, headers, config) {
					alert( "failure message: " + JSON.stringify({data: data}));
				});	
	
	//show modal if get results
	
  var $ctrl = this;
  $ctrl.items = $scope.patientstoselect;
  

			var modalInstance = $uibModal.open({
			  animation: 'true',
			  ariaLabelledBy: 'modal-title',
			  ariaDescribedBy: 'modal-body',
			  templateUrl: 'http://localhost/hosp/existing/modal.html',
			  controller: 'ModalInstanceCtrl',
			  controllerAs: '$ctrl',
			  size: 'lg',
			  resolve: {
				items: function () {
				  return $ctrl.items;
				}
			  }
			});

			modalInstance.result.then(function (selectedItem) {
			  $ctrl.selected = selectedItem;
			  $ctrl.selected.queueName = "CHECK_IN";
			  $http({
					method: 'POST',
					url : "http://localhost/hospital/updatepatientinfo",
					data: $ctrl.selected
				}).success(function(data, status, headers, config) {
					
				}).error(function(data, status, headers, config) {
					alert( "failure message: " + JSON.stringify({data: data}));
				});	
			}, function () {
			  $log.info('Modal dismissed at: ' + new Date());
			});


	
	};

});