(function() {
	'use strict';

	angular.module('webapp').config(interceptorConfig);

	function interceptorConfig($httpProvider) {

		$httpProvider.interceptors.push('tokenInterceptor');
		$httpProvider.defaults.useXDomain = true;
		$httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
		$httpProvider.defaults.headers.common['Access-Control-Allow-Methods'] = 'POST, GET, OPTIONS, PUT, DELETE, HEAD, PUT';
		$httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = 'X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept,*';
		$httpProvider.defaults.headers.common['Access-Control-Max-Age'] = '1728000';

	}

})();