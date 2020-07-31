
(function() {
	AUI().applyConfig({
		groups: {
			search: {
				base: MODULE_PATH + '/js/',
				combine: Liferay.AUI.getCombine(),
				filter: Liferay.AUI.getFilterConfig(),
				modules: {
					'liferay-search-custom-order-configuration': {
						path: 'custom_order_configuration.js',
						requires: ['aui-node']
					}
				},
				root: MODULE_PATH + '/js/'
			}
		}
	});
})();
