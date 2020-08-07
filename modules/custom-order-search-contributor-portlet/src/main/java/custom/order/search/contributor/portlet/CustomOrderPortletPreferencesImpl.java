package custom.order.search.contributor.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletPreferences;
import java.util.Optional;

public class CustomOrderPortletPreferencesImpl
	implements CustomOrderPortletPreferences {

	public CustomOrderPortletPreferencesImpl(
		Optional<PortletPreferences> portletPreferences) {

		_portletPreferencesOptional = portletPreferences;
	}

	@Override
	public JSONArray getFieldsJSONArray() {
		String fieldsString = getFieldsString();

		if (Validator.isBlank(fieldsString)) {
			return getDefaultFieldsJSONArray();
		}

		try {
			return JSONFactoryUtil.createJSONArray(fieldsString);
		}
		catch (JSONException jsone) {
			_log.error(
				"Unable to create a JSON array from: " + fieldsString, jsone);

			return getDefaultFieldsJSONArray();
		}
	}

	@Override
	public String getFieldsString() {
		Optional<String> value =
			_portletPreferencesOptional.flatMap(portletPreferences ->
				Optional.of(portletPreferences.getValue(
					CustomOrderPortletPreferences.PREFERENCE_KEY_FIELDS,
					StringPool.BLANK)));

		return value.orElse(StringPool.BLANK);
	}

	@Override
	public String getParameterName() {
		return "customorder";
	}

	protected JSONArray getDefaultFieldsJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject object =
			JSONUtil.put("tagName", "").put("weight", "");

		jsonArray.put(object);

		return jsonArray;
	}

	private final Optional<PortletPreferences> _portletPreferencesOptional;

	private static final Log _log = LogFactoryUtil.getLog(
		CustomOrderPortletPreferencesImpl.class);
}
