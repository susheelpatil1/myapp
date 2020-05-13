app.controller('queueCtrl', function ($scope,$http,$interval,$uibModal,$log) {

	// $interval(function() {
				// $http({
						// method: 'GET',
						// url : "http://localhost/hospital/getqueuelist",
					// }).success(function(data, status, headers, config) {
						// $scope.queuelist = data;
					// }).error(function(data, status, headers, config) {
					// });
			  // }, 100000);
			 
			 
		$scope.refresh  = function() {	
				$http({
						method: 'GET',
						url : "http://localhost/hospital/getqueuelist",
					}).success(function(data, status, headers, config) {
						$scope.queuelist = data;
					}).error(function(data, status, headers, config) {
					});		
		};
		
		$scope.openPatient  = function(row) {
		
		//show modal if get results
			$http({
						method: 'GET',
						url : "http://localhost/hospital/getFullDiagnosisData/"+row.patientID
					}).success(function(data, status, headers, config) {


						var	modalInstance = $uibModal.open({
							  animation: 'true',
							  ariaLabelledBy: 'modal-title',
							  ariaDescribedBy: 'modal-body',
							  templateUrl: 'http://localhost/hosp/diagnosis/diagnosis-info.html',
							  controller: 'diagnosisCtrl',
							  size: 'lg',
							  resolve: {
								selectedpatient: function () {
									return data;

								}
							  }			  
							});
					}).error(function(data, status, headers, config) {
					});	
			  
			  

			
			// modalInstance.result.then(function () {

			// }, function () {
			  // $log.info('Modal dismissed at: ' + new Date());
			// });
		};
			  
});
