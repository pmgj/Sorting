package model.algorithms;

import java.util.List;

public class CocktailSort extends SortAlgorithm {

    public CocktailSort() {
        super("Cocktail Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        boolean is_Sorted = true;
        int maxIndex = array.size(), minIndex = 0;
        while (is_Sorted) {
            int newIndex = 0;
            is_Sorted = false;
            for (int i = minIndex; i < maxIndex - 1; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    exch(array, i, i + 1);
                    is_Sorted = true;
                    newIndex = i + 1;
                }
            }
            maxIndex = newIndex;
            if (!is_Sorted) {
                break;
            }
            is_Sorted = false;
            for (int j = maxIndex; j > minIndex; j--) {
                if (array.get(j - 1) > array.get(j)) {
                    exch(array, j, j - 1);
                    is_Sorted = true;
                    newIndex = j;
                }
            }
            minIndex = newIndex;
        }
    }
}
