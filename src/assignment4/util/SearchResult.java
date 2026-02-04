package assignment4.util;

import java.util.List;

public final class SearchResult<T> {
    private final List<T> items;

    public SearchResult(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }
}
