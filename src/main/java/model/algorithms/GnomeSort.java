package model.algorithms;

import java.util.List;

public class GnomeSort extends SortAlgorithm {

    public GnomeSort() {
        super("Gnome Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        for (int i = 0; i < array.size();) {
            if (i == 0 || array.get(i - 1) <= array.get(i)) {
                i++;
            } else {
                exch(array, i, i - 1);
                i--;
            }
        }
    }
}
