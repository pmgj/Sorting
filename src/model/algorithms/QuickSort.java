package model.algorithms;

import java.util.List;

public class QuickSort extends SortAlgorithm {

    public QuickSort() {
        super("Quick Sort");
    }

    @Override
    public void sort(List<Integer> a) {
        quicksort(a, 0, a.size() - 1);
    }

    public void quicksort(List<Integer> a, int left, int right) {
        if (right <= left) {
            return;
        }
        int i = partition(a, left, right);
        quicksort(a, left, i - 1);
        quicksort(a, i + 1, right);
    }

    public int partition(List<Integer> a, int left, int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            while (a.get(++i) < a.get(right));
            while (a.get(right) < a.get(--j)) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, i, right);
        return i;
    }
}
