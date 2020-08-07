package com.liferay.search.ranking.processor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.hits.HitsProcessor;
import com.liferay.portal.kernel.util.ArrayUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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

		if (hits.getLength() <= 1) {
			return true;
		}

		SortedMap<Float, Document> sortedDocumentMap =
			new TreeMap<>(Collections.reverseOrder());
		SortedMap<Float, Float> sortedScoreMap =
			new TreeMap<>(Collections.reverseOrder());

		for (int i = 0; i < hits.getLength(); i++) {
			Document doc = hits.doc(i);
			Float score = hits.score(i);

			String[] assetTagNames = doc.getValues("assetTagNames");

			int matchScore = 0;
			for (String assetTagName : assetTagNames) {
				if (_tagPriority.containsKey(assetTagName)) {
					matchScore += _tagPriority.get(assetTagName);
				}
			}

			float key = score + matchScore;
			sortedDocumentMap.put(key, doc);
			sortedScoreMap.put(key, score);
		}

		Document[] newDocs =
			sortedDocumentMap.values().toArray(new Document[hits.getLength()]);
		float[] newScores = ArrayUtil.toFloatArray(sortedScoreMap.values());

		hits.setDocs(newDocs);
		hits.setScores(newScores);

		return true;
	}

	private Map<String, Integer> _tagPriority = new HashMap<String, Integer>() {
		{
			put("product", 200);
			put("parts", 100);
		}
	};

	Log _log = LogFactoryUtil.getLog(SearchRankingHitsProcessor.class.getName());

}