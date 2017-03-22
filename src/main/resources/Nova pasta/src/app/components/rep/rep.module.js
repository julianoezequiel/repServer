(function() {
	'use strict';

	angular
		.module('rep', [])
			.config(routerConfig)
			.controller('RepController',repController);

	/** @ngInject */
	routerConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

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
			url : '/cadastro/rep/:id',
			templateUrl : 'app/components/rep/rep.template.html',
			controller : 'RepController',
			controllerAs : 'vm'
		});

		$urlRouterProvider.otherwise('/');
	}

	repController.$inject = ['RepService', '$stateParams', '$rootScope', '$log'];

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
			});
		} else {
			vm.listaRep = RepService.rep.query();	
		}

		vm.salvar = function(rep) {
			RepService.rep.save(rep, function(response) {
				vm.rep = response;
				$rootScope.toastr.success('Rep cadstrado com sucesso!');
			},function(err){
				$rootScope.toastr.warning(err.data.message);
			});
		}
	}

})();

(function() {
	'use strict';

	angular.module('rep').factory('RepService', RepService);

	RepService.$inject = ['$resource'];

	function RepService($resource) {

		var _rep = $resource('rep/:id', {
			id : '@id'
		}, {
            'update': {
                'method': 'PUT'
            }
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
