//diagnosis control
app.controller("diagnosisCtrl", function($scope,$http,$uibModalInstance,selectedpatient) {

	//var patient = selectedpatient;
		$scope.patient = selectedpatient;
		
		

					

		$scope.oldDiagnosis = function () {
            
			return patient;
		};

  $scope.saveDiagnosis = function () {

	var dataObj = ({
					patientID : $scope.patient.patientID,
					diagnosisTxt : $scope.patient.diagnosis,
					prescriptionTxt : $scope.patient.prescription
				});
     $uibModalInstance.close();
	//$ctrl.selected.patient.queueName = "CHECK_IN";
	var res = $http({
					method: 'POST',
					url : "http://localhost/hospital/saveDiagnandpresc",
					data: dataObj
				}).success(function(data, status, headers, config) {
	
				}).error(function(data, status, headers, config) {
					alert( "failure message: " + JSON.stringify({data: data}));
				});	
  };

  $scope.getDiagnosisData = function () {
  }; 
  
  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

});
