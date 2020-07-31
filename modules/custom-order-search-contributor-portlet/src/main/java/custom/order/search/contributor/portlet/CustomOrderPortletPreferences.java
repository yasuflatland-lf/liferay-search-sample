package custom.order.search.contributor.portlet;

import com.liferay.portal.kernel.json.JSONArray;

public interface CustomOrderPortletPreferences {

	public static final String PREFERENCE_KEY_FIELDS = "fields";

	public JSONArray getFieldsJSONArray();

	public String getFieldsString();

	public String getParameterName();
}
