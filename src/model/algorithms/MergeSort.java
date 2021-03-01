package model.algorithms;

import java.util.ArrayList;
import java.util.List;

public class MergeSort extends SortAlgorithm {

    public MergeSort() {
        super("Merge Sort");
    }

    private void merge(List<Integer> array, int begin, int middle, int end) {
        List<Integer> resultArray = new ArrayList();
        int leftIndex = 0, rightIndex = 0;
        List<Integer> left = array.subList(begin, middle + 1);
        List<Integer> right = array.subList(middle + 1, end + 1);
        // We will concatenate values into the resultArray in order
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                resultArray.add(left.get(leftIndex));
                leftIndex++; // move left array cursor
            } else {
                resultArray.add(right.get(rightIndex));
                rightIndex++; // move right array cursor
            }
        }
        // We need to concat to the resultArray because there will be one element left over after the while loop
        resultArray.addAll(left.subList(leftIndex, left.size()));
        resultArray.addAll(right.subList(rightIndex, right.size()));
        for (int i = begin, j = 0; i <= end; i++, j++) {
            array.set(i, resultArray.get(j));
        }
    }

    private void mergeSort(List<Integer> array, int begin, int end) {
        // No need to sort the array if the array only has one element or empty
        if (end - begin == 1) {
            if (array.get(begin) > array.get(end)) {
                exch(array, begin, end);
            }
        } else if (begin != end) {
            // In order to divide the array in half, we need to figure out the middle
            int middle = (int) Math.floor((begin + end) / 2);
            // Using recursion to combine the left and right
            mergeSort(array, begin, middle);
            mergeSort(array, middle + 1, end);
            merge(array, begin, middle, end);
        }
    }
    
    @Override
    public void sort(List<Integer> array) {
        this.mergeSort(array, 0, array.size() - 1);
    }    
}
