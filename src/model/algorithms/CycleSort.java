package model.algorithms;

import java.util.List;

public class CycleSort extends SortAlgorithm {

    public CycleSort() {
        super("Cycle Sort");
    }

    @Override
    public void sort(List<Integer> array) {
        // loop from the beginning of the array to the second to last item
        for (int currentIndex = 0; currentIndex < array.size() - 1; currentIndex++) {
            // save the value of the item at the currentIndex
            int item = array.get(currentIndex);

            int currentIndexCopy = currentIndex;
            // loop through all indexes that proceed the currentIndex
            for (int i = currentIndex + 1; i < array.size(); i++) {
                if (array.get(i) < item) {
                    currentIndexCopy++;
                }
            }

            // if currentIndexCopy has not changed, the item at the currentIndex is already in the correct currentIndexCopy
            if (currentIndexCopy == currentIndex) {
                continue;
            }

            // skip duplicates
            while (item == array.get(currentIndexCopy)) {
                currentIndexCopy++;
            }
            // swap
            int temp = array.get(currentIndexCopy);
            array.set(currentIndexCopy, item);
            item = temp;

            // repeat above steps as long as we can find values to swap
            while (currentIndexCopy != currentIndex) {
                currentIndexCopy = currentIndex;
                // loop through all indexes that proceed the currentIndex
                for (int i = currentIndex + 1; i < array.size(); i++) {
                    if (array.get(i) < item) {
                        currentIndexCopy++;
                    }
                }

                // skip duplicates
                while (item == array.get(currentIndexCopy)) {
                    currentIndexCopy++;
                }
                // swap
                temp = array.get(currentIndexCopy);
                array.set(currentIndexCopy, item);
                item = temp;
            }
        }
    }
}
