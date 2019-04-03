package com.hematite.predictive.search.mock;

import org.apache.commons.text.RandomStringGenerator;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public static List<String> generateData(final List<String> data) {
        final List<String> result = new ArrayList<>();
        final int resultSize = data.size() / 3;

        // Add full value
        for (int i = 0; i < data.size(); i += 10) {
            result.add(data.get(i));
        }

        final RandomStringGenerator stringGenerator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();

        final int resultWithValidQueries = (int) (result.size() * 2.5);

        // Add valid query
        while (result.size() < resultWithValidQueries) {
            final String value = stringGenerator.generate(1, 4);
            if (isContain(value, data)) {
                result.add(value);
            }
        }

        // Add not valid query
        while (result.size() < resultSize) {
            final String value = stringGenerator.generate(1, 4);
            if (!isContain(value, data)) {
                result.add(value);
            }
        }

        return result;
    }

    private static boolean isContain(final String searchValue,
                                     final List<String> list) {
        for (final String value : list) {
            if (value.contains(searchValue)) {
                return true;
            }
        }
        return false;
    }
}
