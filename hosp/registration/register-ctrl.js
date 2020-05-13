

app.controller('registerCtrl', function ($scope,$http) {


		$scope.registerUser  = function() {	
				var dataObj = ({
								firstName : $scope.vm.user.firstName, 
								lastName : $scope.vm.user.lastName,
								userId : $scope.vm.user.username,
								pwd : $scope.vm.user.password,
								role : $scope.vm.user.role
							});
				$http({
						method: 'POST',
						url : "http://localhost/hospital/registerUser",
						data: dataObj
					}).success(function(data, status, headers, config) {
						$scope.regresult = data;
						alert("11 "+$scope.regresult.success);
					}).error(function(data, status, headers, config) {

					});		
		};

});
