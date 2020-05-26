package com.liferay.search.ranking.processor;

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
		// TODO enter required service properties
		"sort.order=1"
	},
	service = HitsProcessor.class
)
public class SearchRankingHitsProcessor implements HitsProcessor {


	@Override
	public boolean process(
		SearchContext searchContext, Hits hits) throws SearchException {
		return false;
	}

}