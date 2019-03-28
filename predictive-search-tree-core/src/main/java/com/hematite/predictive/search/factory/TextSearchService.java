package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.tree.NodeData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@Service
public class TextSearchService {

    public List<NodeData<String>> search(final List<NodeData<String>> startedList,
                                         final String text) {
        final List<NodeData<String>> matchedList = new ArrayList<>();
        for (final NodeData<String> value : startedList) {
            if (compareWithKey(value.getData().toLowerCase(), text)) {
                matchedList.add(value);
            }
        }
        return matchedList;
    }

    private boolean compareWithKey(final String data, final String text) {
        final List<Integer> prefix = prefixFunction(text);

        int j = 0;
        for (int i = 0; i < data.length(); i++) {
            while (j > 0 && data.charAt(i) != text.charAt(j)) {
                j = prefix.get(j - 1);
            }
            if (data.charAt(i) == text.charAt(j)) {
                j++;
                if (j == text.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    private List<Integer> prefixFunction(final String text) {
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
}
