package model.algorithms;

import java.util.List;

public class OddEvenSort extends SortAlgorithm {

    public OddEvenSort() {
        super("Odd-Even Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        boolean sorted;
        do {
            sorted = true;
            for (int i = 1; i < array.size() - 1; i += 2) {
                if (array.get(i) > array.get(i + 1)) {
                    exch(array, i, i + 1);
                    sorted = false;
                }
            }
            for (int i = 0; i < array.size() - 1; i += 2) {
                if (array.get(i) > array.get(i + 1)) {
                    exch(array, i, i + 1);
                    sorted = false;
                }
            }
        } while (!sorted);
    }
}
