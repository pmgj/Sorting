package model.algorithms;

import java.util.List;

public class ShellSort extends SortAlgorithm {

    public ShellSort() {
        super("Shell Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        int increment = array.size() / 2;
        while (increment > 0) {
            for (int i = increment; i < array.size(); i++) {
                int temp = array.get(i), j;
                for (j = i; j - increment >= 0 && array.get(j - increment) > temp; j = j - increment) {
                    array.set(j, array.get(j - increment));
                }
                array.set(j, temp);
            }
            increment = increment / 2;
        }
    }
}
