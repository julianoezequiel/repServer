(function() {
	'use strict';

	angular.module('webapp').factory('tokenInterceptor', tokenInterceptor);

	function tokenInterceptor($q, $rootScope,$log) {
		return {
			'request' : function($config) {
				$log.debug('tokenInterceptor');
				if (angular.isDefined($rootScope.authToken)) {
					var authToken = $rootScope.authToken;
					// if (TopPontoWebApp.useAuthTokenHeader) {
					$config.headers['X-Auth-Token'] = authToken;
				}
				return $config || $q.when($config);
			}
		};
	}

})();