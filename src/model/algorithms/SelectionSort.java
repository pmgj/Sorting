package model.algorithms;

import java.util.List;

public class SelectionSort extends SortAlgorithm {

    public SelectionSort() {
        super("Selection Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        for (int i = 0, len = array.size(); i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (array.get(min) > array.get(j)) {
                    min = j;
                }
            }
            if (min != i) {
                exch(array, i, min);
            }
        }
    }
}
