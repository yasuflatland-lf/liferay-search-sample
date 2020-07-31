package custom.order.search.contributor;

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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

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

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getSearchRequestBuilder();

		BooleanQuery booleanQuery = _queries.booleanQuery();

		FunctionScoreQuery functionScoreQuery =
			_queries.functionScore(booleanQuery);

		TermQuery termQuery = _queries.term(Field.ASSET_TAG_NAMES, "parts");
		TermQuery termQuery2 = _queries.term(Field.ASSET_TAG_NAMES, "product");

		WeightScoreFunction scoreFunction = _scoreFunctions.weight(100);
		WeightScoreFunction scoreFunction2 = _scoreFunctions.weight(1);

		functionScoreQuery.addFilterQueryScoreFunctionHolder(
			termQuery, scoreFunction);

		functionScoreQuery.addFilterQueryScoreFunctionHolder(
			termQuery2, scoreFunction2);

		searchRequestBuilder.query(functionScoreQuery);
	}

	@Reference
	protected Queries _queries;

	@Reference
	protected ScoreFunctions _scoreFunctions;

	private List<FunctionScoreQuery.FilterQueryScoreFunctionHolder>
		_filterQueryScoreFunctionHolders = new ArrayList<>();
}
