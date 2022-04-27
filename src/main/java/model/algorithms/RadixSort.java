package model.algorithms;

import java.util.ArrayList;
import java.util.List;

public class RadixSort extends SortAlgorithm {

    public RadixSort() {
        super("Radix Sort");
    }

    private int getMax(List<Integer> array) {
        int max = 0;
        for (int i = 0; i < array.size(); i++) {
            int num = array.get(i);
            String t = "" + num;
            if (max < t.length()) {
                max = t.length();
            }
        }
        return max;
    }

    private int getPosition(int num, int place) {
        return (int) Math.floor(Math.abs(num) / Math.pow(10, place)) % 10;
    }

    @Override
    public void sort(List<Integer> array) {
        int max = getMax(array);
        for (int i = 0; i < max; i++) {
            List<List<Integer>> buckets = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                buckets.add(new ArrayList<>());
            }
            for (int j = 0; j < array.size(); j++) {
                buckets.get(getPosition(array.get(j), i)).add(array.get(j));
            }
            Integer[] temp = buckets.stream().flatMap(m -> m.stream()).toArray(Integer[]::new);
            for (int j = 0; j < array.size(); j++) {
                array.set(j, temp[j]);
            }
        }
    }
}
