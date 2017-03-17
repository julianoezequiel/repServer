(function() {
	'use strict';

	angular.module('webapp').controller('MainController', MainController);

	/** @ngInject */
	function MainController(RepService, $timeout) {
		var vm = this;
		vm.listaRep = [];

		var listarRep = function() {
			
			RepService.repStatus.query(function(response) {

				vm.listaRep = response;

				$timeout(function() {
					listarRep();
				}, 1000);
			}, function() {
				$timeout(function() {
					listarRep();
				}, 1000);
			});
		}

		listarRep();
	}
})();
