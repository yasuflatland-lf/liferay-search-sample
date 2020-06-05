package com.liferay.search.ranking.processor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.hits.HitsProcessor;

import org.osgi.service.component.annotations.Component;

/**
 * @author gabriel
 */
@Component(
	immediate = true,
	property = {
		"sort.order=4"
	},
	service = HitsProcessor.class
)
public class SearchRankingHitsProcessor implements HitsProcessor {

	@Override
	public boolean process(
		SearchContext searchContext, Hits hits) throws SearchException {

		Document[] docs = hits.getDocs();

		for (Document doc : docs) {
			String tagName = doc.get("assetTagNames");
		}

		_log.fatal("Search Ranking Hits");

		return false;
	}

	Log _log = LogFactoryUtil.getLog(SearchRankingHitsProcessor.class.getName());

}