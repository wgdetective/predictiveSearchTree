package com.hematite.predictive.search.neo4j;

import com.hematite.predictive.search.neo4j.entity.NodeDataNeo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
public class TextSearchServiceNeo {

    public List<Integer> calculatePrefixFunction(final String text) {
        final List<Integer> prefix = new ArrayList<>();
        prefix.add(0);

        int k = 0;
        for (int i = 1; i < text.length(); ++i) {
            while ((k > 0) && (text.charAt(k) != text.charAt(i))) {
                k = prefix.get(k - 1);
            }
            if (text.charAt(k) == text.charAt(i)) {
                ++k;
            }
            prefix.add(k);
        }
        return prefix;
    }

    public List<NodeDataNeo> search(final List<NodeDataNeo> originalData,
                                    final String text) {
        final List<NodeDataNeo> matchedList = new ArrayList<>();

        originalData.stream().forEach(data -> {
            if (compareWithKey(data, text)) {
             matchedList.add(data);
           }
        });
        return matchedList;
    }

    private boolean compareWithKey(final NodeDataNeo data, final String text) {
        final List<Integer> prefix = data.getPrefix();
        final String keyString = data.getKey();

        int j = 0;
        for (int i = 0; i < keyString.length(); i++) {
            while (j > 0 && keyString.charAt(i) != text.charAt(j)) {
                j = prefix.get(j - 1);
            }
            if (keyString.charAt(i) == text.charAt(j)) {
                j++;
                if (j == text.length()) {
                    return true;
                }
            }
        }

        return false;
    }
}
