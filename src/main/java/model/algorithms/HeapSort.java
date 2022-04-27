package model.algorithms;

import java.util.List;

public class HeapSort extends SortAlgorithm {

    private int array_length;

    public HeapSort() {
        super("Heap Sort");
    }

    private void heap_root(List<Integer> input, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < array_length && input.get(left) > input.get(max)) {
            max = left;
        }
        if (right < array_length && input.get(right) > input.get(max)) {
            max = right;
        }
        if (max != i) {
            exch(input, i, max);
            heap_root(input, max);
        }
    }

    @Override
    public void sort(List<Integer> array) {
        array_length = array.size();
        for (int i = array_length / 2; i >= 0; i--) {
            heap_root(array, i);
        }
        for (int i = array.size() - 1; i > 0; i--) {
            exch(array, 0, i);
            array_length--;
            heap_root(array, 0);
        }
    }
}
