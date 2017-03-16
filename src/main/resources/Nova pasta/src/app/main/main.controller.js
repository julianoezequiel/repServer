(function() {
  'use strict';

  angular
    .module('webapp')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController() {
    
    var vm = this;

    vm.listaRep = [{
      ip:'10.133.13.33',
      numSerie:'000000923338888',
      status:'ON-LINE'
    }];
    
  }
})();
