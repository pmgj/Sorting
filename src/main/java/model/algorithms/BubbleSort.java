package model.algorithms;

import java.util.List;

public class BubbleSort extends SortAlgorithm {

    public BubbleSort() {
        super("Bubble Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        int len = array.size() - 1;
        do {
            int newn = 0;
            for (int i = 0; i < len; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    exch(array, i, i + 1);
                    newn = i;
                }
            }
            len = newn;
        } while (len > 0);
    }
}
