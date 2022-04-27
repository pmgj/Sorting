package model.algorithms;

import java.util.List;

public class BinaryInsertionSort extends SortAlgorithm {

    public BinaryInsertionSort() {
        super("Binary Insertion Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        int n = array.size();
        for (int i = 1; i < n; i++) {
            int v = array.get(i);
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (v < array.get(mid)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            for (int j = i; j > lo; --j) {
                array.set(j, array.get(j - 1));
            }
            array.set(lo, v);
        }
    }
}
