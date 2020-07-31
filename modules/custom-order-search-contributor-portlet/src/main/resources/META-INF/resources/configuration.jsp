<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="custom.order.search.contributor.portlet.CustomOrderPortletPreferences" %>
<%@ page import="custom.order.search.contributor.portlet.CustomOrderPortletPreferencesImpl" %>
<%@ page import="com.liferay.portal.kernel.settings.ParameterMapSettings" %>
<%@ page import="com.liferay.petra.string.StringPool" %>

<portlet:defineObjects />

<%
	CustomOrderPortletPreferences customOrderPortletPreferences = new CustomOrderPortletPreferencesImpl(java.util.Optional.ofNullable(portletPreferences));

	JSONArray fieldsJSONArray = customOrderPortletPreferences.getFieldsJSONArray();
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
	onSubmit='<%="event.preventDefault();" + renderResponse.getNamespace() + "_submitForm();" %>'
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<aui:fieldset id='<%= renderResponse.getNamespace() + "fieldsId" %>'>

				<%
					int[] fieldsIndexes = new int[fieldsJSONArray.length()];

					for (int i = 0; i < fieldsJSONArray.length(); i++) {
						fieldsIndexes[i] = i;

						JSONObject jsonObject = fieldsJSONArray.getJSONObject(i);
				%>

						<div class="field-form-row lfr-form-row lfr-form-row-inline">
							<div class="row-fields">
								<aui:input cssClass="tag-name-input"
										   label="tag-name"
										   name='<%= "tagName_" + i %>'
										   value='<%= jsonObject.getString("tagName") %>'
								/>

								<aui:input cssClass="weight-field-input"
										   label="weight"
										   name='<%= "weight_" + i %>'
										   value='<%= jsonObject.getString("weight") %>'
								/>
							</div>
						</div>

				<%
					}
				%>

				<aui:input cssClass="fields-input"
						   name="<%= ParameterMapSettings.PREFERENCES_PREFIX + CustomOrderPortletPreferences.PREFERENCE_KEY_FIELDS + StringPool.DOUBLE_DASH %>"
						   type="hidden"
						   value="<%= customOrderPortletPreferences.getFieldsString() %>"
				/>

				<aui:input name="fieldsIndexes" type="hidden" value="<%= StringUtil.merge(fieldsIndexes) %>" />
			</aui:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script use="liferay-auto-fields">
	var autoFields = new Liferay.AutoFields({
		contentBox: 'fieldset#<portlet:namespace />fieldsId',
		fieldIndexes: '<portlet:namespace />fieldsIndexes',
		namespace: '<portlet:namespace />'
	}).render();
</aui:script>

<aui:script use="aui-node">

	<portlet:namespace/>_submitForm = function() {
		var A = AUI();

		var form = A.one(document.<portlet:namespace />fm);

		var fields = [];

		var fieldFormRows = A.all('.field-form-row').filter(item => {
			return !item.get('hidden');
		});

		fieldFormRows.each(item => {
			var tagName = item.one('.tag-name-input').val();

			var weight = item.one('.weight-field-input').val();

			fields.push({
				weight,
				tagName
			});
		});

		form.one('.fields-input').val(JSON.stringify(fields));

		submitForm(form);
	}
</aui:script>