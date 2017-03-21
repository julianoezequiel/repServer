(function() {
	'use strict';

	angular.module('rep', []).config(routerConfig).controller('RepController',
			repController);

	/** @ngInject */
	function routerConfig($stateProvider, $urlRouterProvider) {
		$stateProvider.state('repListar', {
			url : '/listaRep',
			templateUrl : 'app/components/rep/repLista.template.html',
			controller : 'RepController',
			controllerAs : 'vm'
		}).state('repAdicionar', {
			url : '/rep',
			templateUrl : 'app/components/rep/rep.template.html',
			controller : 'RepController',
			controllerAs : 'vm'
		}).state('repEditar', {
			url : '/rep/:id',
			templateUrl : 'app/components/rep/rep.template.html',
			controller : 'RepController',
			controllerAs : 'vm'
		});

		$urlRouterProvider.otherwise('/');
	}

	function repController(RepService, $stateParams, $rootScope, $log) {

		var vm = this;

		$rootScope.cancelarMonitoramento();
		$log.debug('Rep controller');

		if ($stateParams.id) {
			RepService.rep.get({
				id : $stateParams.id
			}, function(response) {
				vm.rep = response;
				$log.debug(response);
				MaterialTextfield.checkDirty();
			});
		} else {
			vm.listaRep = RepService.rep.query();
		}

		vm.salvar = function(rep) {
			RepService.rep.save(rep, function(response) {
				rep = response;
			});
		}
	}

})();

(function() {
	'use strict';

	angular.module('rep').factory('RepService', RepService);

	function RepService($resource) {

		var _rep = $resource('rep/:id', {
			id : '@id'
		});

		var _repStatus = $resource('rep/monitoramento', {
			id : '@id'
		});

		return {
			rep : _rep,
			repStatus : _repStatus
		}

	}
})();
