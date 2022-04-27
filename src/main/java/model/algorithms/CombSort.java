package model.algorithms;

import java.util.List;

public class CombSort extends SortAlgorithm {

    public CombSort() {
        super("Comb Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        int interval = 10 * array.size() / 12;
        while (interval > 0) {
            for (int i = 0; i + interval < array.size(); i++) {
                if (array.get(i) > array.get(i + interval)) {
                    exch(array, i, i + interval);
                }
            }
            interval = 10 * interval / 12;
        }
    }
}
