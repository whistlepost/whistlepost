package org.whistlepost.model.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Support for shuffling items contained in an {@link Iterable} collection.
 * @param <T>
 */
public class ShuffleTransformer<T> {
    List<T> toList(Iterable<T> items) {
        List<T> result = new ArrayList<>();
        items.forEach(result::add);
        return result;
    }

    public Iterable<T> transform(Iterable<T> items) {
        List<T> list = toList(items);
        Collections.shuffle(list);
        return list;
    }
}
