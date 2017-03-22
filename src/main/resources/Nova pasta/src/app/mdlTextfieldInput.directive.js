(function() {
	'use strict';

	angular.module('webapp').directive("mdlTextfieldInput", mdlTfFix);

	function mdlTfFix() {
		return {
			restrict : "C",
			require : "ngModel",
			scope:{
				ngModel:'=?'
			},
			link : function($scope, $element) {

				$scope.$watch('ngModel',
						function(newVal, oldVal) {
							if (typeof newVal !== "undefined" && newVal !== ""
									&& newVal !== oldVal) {
								$element.parent().addClass("is-dirty");
							} else {
								$element.parent().removeClass("is-dirty");
							}
						});
			}
		}
	}
})();