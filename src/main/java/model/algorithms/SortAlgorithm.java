package model.algorithms;

import java.util.List;

public abstract class SortAlgorithm {

    private final String name;

    public SortAlgorithm(String name) {
        this.name = name;
    }

    public abstract void sort(List<Integer> array);

    @Override
    public String toString() {
        return name;
    }

    public void exch(List<Integer> a, int i, int j) {
        int swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
    }
}
