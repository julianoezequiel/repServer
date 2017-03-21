(function() {
	'use strict';

	angular.module('webapp').run(runBlock);

	/** @ngInject */
	function runBlock($log, $rootScope, $state, $timeout) {
		$rootScope.$state = $state;
		$log.debug('app running');

		$rootScope.cancelarMonitoramento = function() {
			$timeout.cancel($rootScope.monitorar);
		}
	}

})();
