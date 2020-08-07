
AUI.add(
	'liferay-search-custom-order-configuration',
	A => {
		var CustomOrderConfiguration = function(form) {
			var instance = this;

			instance.form = form;

			instance.form.on('submit', A.bind(instance._onSubmit, instance));
		};

		A.mix(CustomOrderConfiguration.prototype, {
			_onSubmit(event) {
				var instance = this;

				event.preventDefault();

				var fields = [];

				var fieldFormRows = A.all('.field-form-row').filter(item => {
					return !item.get('hidden');
				});

				fieldFormRows.each(item => {
					var tagName = item.one('.tag-name-input').val();

					var weight = item.one('.weight-field-input').val();

					fields.push({
						tagName,
						weight
					});
				});

				instance.form.one('.fields-input').val(JSON.stringify(fields));

				submitForm(instance.form);
			}
		});

		Liferay.namespace('Search').CustomOrderConfiguration = CustomOrderConfiguration;
	},
	'',
	{
		requires: ['aui-node']
	}
);
