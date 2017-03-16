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
    },{
      ip:'243.2.6.31',
      numSerie:'09233577638888',
      status:'ON-LINE'
    },{
      ip:'17.1.19.33',
      numSerie:'055956523336665888',
      status:'ON-LINE'
    },{
      ip:'10.133.13.33',
      numSerie:'096565623338888',
      status:'ON-LINE'
    },{
      ip:'243.2.6.31',
      numSerie:'09233577638888',
      status:'ON-LINE'
    },{
      ip:'17.1.19.33',
      numSerie:'055956523336665888',
      status:'ON-LINE'
    },{
      ip:'10.133.13.33',
      numSerie:'096565623338888',
      status:'ON-LINE'
    }];
    
  }
})();
