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

	function repController(RepService) {
		var vm = this;

		vm.listaRep = RepService.rep.query();

	}
})();

(function() {
	'use strict';

	angular.module('rep').factory('RepService', RepService);

	function RepService($resource) {
		var _rep = $resource('rep', {
			id : '@id'
		}, {
			'update' : {
				'method' : 'PUT'
			}
		});

		return {
			rep : _rep
		}

	}
})();

