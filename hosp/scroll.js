	var appModule = angular.module('scrollerTestApp', ['ui.scroll'])
	.controller('ScrollerController', ['$scope', function($scope) {
    
         
		$scope.movieDataSource = {
            
			get : function (index, count, callback) {
				var i, items = [], item;
                
                var min = 1;
                var max = 100;
	
				for (i=index ; i<index + count ; i++) {
                    if(i < min || i > max) { 
                        continue;
                    }
					item = {
						description: "Item : " + i,
						imageURL: "http://placehold.it/96x96&text=" + i
					};
					items.push (item);
				}
				callback (items);
			}
		}
	}]);
