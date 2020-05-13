app.controller('ModalInstanceCtrl', function ($uibModalInstance,$http, items) {
  var $ctrl = this;
  $ctrl.items = items;


  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.selected.patient);
	$ctrl.selected.patient.queueName = "CHECK_IN";
	var res = $http({
					method: 'POST',
					url : "http://localhost/hospital/checkin",
					data: $ctrl.selected.patient
				}).success(function(data, status, headers, config) {
	
				}).error(function(data, status, headers, config) {
					alert( "failure message: " + JSON.stringify({data: data}));
				});	
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});