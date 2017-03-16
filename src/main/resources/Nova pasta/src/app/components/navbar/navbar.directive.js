(function() {
	'use strict';

	angular.module('webapp').directive('acmeNavbar', acmeNavbar);

	/** @ngInject */
	function acmeNavbar() {
		var directive = {
			restrict : 'E',
			templateUrl : 'app/components/navbar/navbar.html',
			scope : {
				creationDate : '='
			},
			controller : NavbarController,
			controllerAs : 'vm',
			bindToController : true
		};

		return directive;

		/** @ngInject */
		function NavbarController(moment, $rootScope) {
			var vm = this;
			vm.irParaRep = function() {
				$rootScope.$state.go('repListar');
			}
			vm.irParaHome = function() {
				$rootScope.$state.go('home');
			}
			// "vm.creationDate" is available by directive option
			// "bindToController: true"
			vm.relativeDate = moment(vm.creationDate).fromNow();
		}
	}

})();
