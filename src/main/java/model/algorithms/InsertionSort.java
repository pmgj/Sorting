package model.algorithms;

import java.util.List;

public class InsertionSort extends SortAlgorithm {

    public InsertionSort() {
        super("Insertion Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (array.get(j) < array.get(j - 1)) {
                    exch(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
