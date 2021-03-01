package model.algorithms;

import java.util.List;

public class DoubleSelectionSort extends SortAlgorithm {

    public DoubleSelectionSort() {
        super("Double Selection Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        for (int i = 0, len = array.size(); i < len; i++, len--) {
            int min = i, max = i;
            for (int j = i + 1; j < len; j++) {
                if (array.get(min) > array.get(j)) {
                    min = j;
                } else {
                    if (array.get(max) < array.get(j)) {
                        max = j;
                    }
                }
            }
            if (min != i) {
                exch(array, i, min);
            }
            if (max != i) {
                exch(array, len - 1, max);
            } else {
                exch(array, len - 1, min);
            }
        }
    }
}
