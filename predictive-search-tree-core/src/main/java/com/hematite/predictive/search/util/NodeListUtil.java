package com.hematite.predictive.search.util;

import com.hematite.predictive.search.tree.NodeData;

import java.util.ArrayList;
import java.util.List;

public class NodeListUtil {

    public static List<NodeData<String>> filterList(final List<NodeData<String>> startedList,
                                              final String key) {
        final List<NodeData<String>> matchedList = new ArrayList<>();
        for (final NodeData<String> value : startedList) {
            if (compareWithKey(value.getData().toLowerCase(), key)) {
                matchedList.add(value);
            }
        }
        return matchedList;
    }

    private static boolean compareWithKey(final String data, final String key) {
        final List<Integer> prefix = prefixFunction(key);

        int j = 0;
        for (int i = 0; i < data.length(); i++) {
            while (j > 0 && data.charAt(i) != key.charAt(j)) {
                j = prefix.get(j - 1);
            }
            if (data.charAt(i) == key.charAt(j)) {
                j++;
                if (j == key.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    private static List<Integer> prefixFunction(final String key) {
        final List<Integer> prefix = new ArrayList<>();
        prefix.add(0);

        int k = 0;
        for (int i = 1; i < key.length(); ++i) {
            while ((k > 0) && (key.charAt(k) != key.charAt(i))) {
                k = prefix.get(k - 1);
            }
            if (key.charAt(k) == key.charAt(i)) {
                ++k;
            }
            prefix.add(k);
        }
        return prefix;
    }

    public static List<NodeData<String>> getSubList(final List<NodeData<String>> list, final int itemsCount) {
        final List<NodeData<String>> subList = new ArrayList<>();
        int i = 0;
        while (i < itemsCount && i < list.size()) {
            subList.add(list.get(i));
            i++;
        }
        return subList;
    }
}
