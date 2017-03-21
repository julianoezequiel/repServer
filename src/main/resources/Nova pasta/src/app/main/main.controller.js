(function() {
	'use strict';

	angular.module('webapp').controller('MainController', MainController);

	/** @ngInject */
	function MainController(RepService, $timeout, $rootScope) {
		var vm = this;
		vm.listaRep = [];

		(function listarRep() {
			RepService.repStatus.query(function(response) {
				vm.listaRep = response;
				$rootScope.monitorar = $timeout(listarRep, 1000);
			}, function() {
				$rootScope.monitorar = $timeout(listarRep, 1000);
			});
		})();

		$rootScope.$on('$destroy', function() {
			$timeout.cancel($rootScope.monitorar);
		});
	}

})();
