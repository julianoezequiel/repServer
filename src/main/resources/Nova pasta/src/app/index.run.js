(function () {
  'use strict';

  angular
    .module('webapp')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope, $state) {
    $rootScope.$state = $state;
    $log.debug('runBlock end');
  }

})();
