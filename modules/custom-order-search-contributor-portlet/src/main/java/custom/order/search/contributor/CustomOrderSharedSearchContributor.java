package custom.order.search.contributor;


import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.FunctionScoreQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.query.function.score.ScoreFunctions;
import com.liferay.portal.search.query.function.score.WeightScoreFunction;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;
import custom.order.search.contributor.constants.CustomOrderSearchContributorPortletKeys;
import custom.order.search.contributor.portlet.CustomOrderPortletPreferences;
import custom.order.search.contributor.portlet.CustomOrderPortletPreferencesImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletPreferences;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component(
	immediate = true,
	property = "javax.portlet.name=" + CustomOrderSearchContributorPortletKeys.CUSTOM_ORDER_SEARCH_CONTRIBUTOR_PORTLET,
	service = PortletSharedSearchContributor.class
)
public class CustomOrderSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		Optional<PortletPreferences> preferences =
			portletSharedSearchSettings.getPortletPreferencesOptional();

		CustomOrderPortletPreferences customOrderPortletPreferences =
			new CustomOrderPortletPreferencesImpl(preferences);

		JSONArray fields = customOrderPortletPreferences.getFieldsJSONArray();

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getSearchRequestBuilder();

		BooleanQuery booleanQuery = _queries.booleanQuery();

		FunctionScoreQuery functionScoreQuery =
			_queries.functionScore(booleanQuery);

		for (int i = 0; i < fields.length(); i++) {
			JSONObject field = fields.getJSONObject(i);

			TermQuery termQuery = _queries.term(
				Field.ASSET_TAG_NAMES, field.getString("tagName"));

			WeightScoreFunction scoreFunction = _scoreFunctions.weight(
				field.getInt("weight"));

			functionScoreQuery.addFilterQueryScoreFunctionHolder(
				termQuery, scoreFunction);
		}

		searchRequestBuilder.query(functionScoreQuery);
	}

	@Reference
	protected Queries _queries;

	@Reference
	protected ScoreFunctions _scoreFunctions;

	private List<FunctionScoreQuery.FilterQueryScoreFunctionHolder>
		_filterQueryScoreFunctionHolders = new ArrayList<>();
}
