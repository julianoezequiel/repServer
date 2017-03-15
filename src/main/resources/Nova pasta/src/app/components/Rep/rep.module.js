(function () {
  'use strict';

  angular
    .module('rep', [])
    .config(routerConfig)
    .controller('RepController', repController);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('rep', {
        url: '/rep/:id',
        templateUrl: 'app/components/rep.html',
        controller: 'RepController',
        controllerAs: 'vm'
      });

    $urlRouterProvider.otherwise('/');
  }

  function repController() {

    var vm = this;

  }
})();
