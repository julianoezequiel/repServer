(function() {
	'use strict';

	angular.module('webapp').run(runBlock);

	/** @ngInject */
	function runBlock($log, $rootScope, $state, $timeout,toastr) {
		$rootScope.$state = $state;
		$rootScope.toastr = toastr;
		$log.debug('app running');

		$rootScope.cancelarMonitoramento = function() {
			$timeout.cancel($rootScope.monitorar);
		}
	}

})();
