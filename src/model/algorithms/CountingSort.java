package model.algorithms;

import java.util.List;

public class CountingSort extends SortAlgorithm {

    public CountingSort() {
        super("Counting Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        int min = array.stream().mapToInt(Integer::intValue).min().getAsInt();
        int max = array.stream().mapToInt(Integer::intValue).max().getAsInt();
        int[] count = new int[max + 1];
        for (int i = 0; i < array.size(); i++) {
            count[array.get(i)]++;
        }
        for (int i = min, z = 0; i <= max; i++) {
            while (count[i]-- > 0) {
                array.set(z++, i);
            }
        }
    }
}