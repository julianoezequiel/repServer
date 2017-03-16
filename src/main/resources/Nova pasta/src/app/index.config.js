(function() {
	'use strict';

	angular.module('webapp').config(config);

	/** @ngInject */
	function config($logProvider, toastrConfig, $httpProvider) {
		// Enable log
		$logProvider.debugEnabled(true);

		// Set options third-party lib
		toastrConfig.allowHtml = true;
		toastrConfig.timeOut = 3000;
		toastrConfig.positionClass = 'toast-top-right';
		toastrConfig.preventDuplicates = true;
		toastrConfig.progressBar = true;

		$httpProvider.defaults.useXDomain = true;
		$httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
		$httpProvider.defaults.headers.common['Access-Control-Allow-Methods'] = 'POST, GET, OPTIONS, PUT, DELETE, HEAD, PUT';
		$httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = 'X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept,*';
		$httpProvider.defaults.headers.common['Access-Control-Max-Age'] = '1728000';
	}

	angular.module('webapp').factory('tokenInterceptor', tokenInterceptor);

	function tokenInterceptor($q, $rootScope, constantesConfig) {
		return {
			'request' : function($config) {
				var isRestCall = $config.url.indexOf('restrict') > -1;
				if (isRestCall && angular.isDefined($rootScope.authToken)) {
					var authToken = $rootScope.authToken;
					// if (TopPontoWebApp.useAuthTokenHeader) {
					$config.headers['Authorization'] = authToken;
					if ($config.url.indexOf(constantesConfig.baseUrl) < 0) {
						$config.url = constantesConfig.baseUrl + $config.url;
					}
				}
				return $config || $q.when($config);
			}
		};
	}

	angular.module('webapp').constant('constantesConfig', {
		baseUrl : ''
	});

})();
