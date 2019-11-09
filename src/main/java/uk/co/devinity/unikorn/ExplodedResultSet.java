package uk.co.devinity.unikorn;

import java.util.List;
import java.util.Map;

public class ExplodedResultSet {
    private final int size;
    private final List<Map<String, Object>> results;

    public ExplodedResultSet(final int size,
                             final List<Map<String, Object>> results) {
        this.size = size;
        this.results = results;
    }

    public int getSize() {
        return size;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }
}
