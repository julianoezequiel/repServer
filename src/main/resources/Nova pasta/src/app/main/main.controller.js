(function() {
	'use strict';

	angular.module('webapp').controller('MainController', MainController);

	/** @ngInject */
	function MainController(RepService, $timeout, $rootScope) {
		var vm = this;
		vm.ismonitorando = true;
		vm.listaRep = [];

		function monitorar() {
			RepService.repStatus.query(function(response) {
				vm.listaRep = response;
				$rootScope.monitorar = $timeout(monitorar, 1000);
			}, function() {
				$rootScope.monitorar = $timeout(monitorar, 1000);
			});
		}

		vm.initStopMon = function(){
			if(vm.ismonitorando){
				$rootScope.cancelarMonitoramento();
				vm.ismonitorando = false;
			}else{
				monitorar();
				vm.ismonitorando = true;
			}
		}

		monitorar();

	}

})();
