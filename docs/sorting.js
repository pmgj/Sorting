export class SortingAlgorithm {
    constructor(name) {
        this.name = name;
    }
    toString() {
        return this.name;
    }
}
class BinaryInsertionSort extends SortingAlgorithm {
    constructor() {
        super("Binary Insertion Sort");
    }
    sort(array) {
        let n = array.length;
        for (let i = 1; i < n; i++) {
            let v = array[i];
            let lo = 0, hi = i;
            while (lo < hi) {
                let mid = lo + Math.floor((hi - lo) / 2);
                if (v < array[mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            for (let j = i; j > lo; --j) {
                array[j] = array[j - 1];
            }
            array[lo] = v;
        }
        return array;
    }
}
class BubbleSort extends SortingAlgorithm {
    constructor() {
        super("Bubble Sort");
    }
    sort(array) {
        let len = array.length - 1;
        do {
            let newn = 0;
            for (let i = 0; i < len; i++) {
                if (array[i] > array[i + 1]) {
                    [array[i], array[i + 1]] = [array[i + 1], array[i]];
                    newn = i;
                }
            }
            len = newn;
        } while (len > 0);
        return array;
    }
}
class CocktailSort extends SortingAlgorithm {
    constructor() {
        super("Cocktail Shaker Sort");
    }
    sort(array) {
        let is_Sorted = true, maxIndex = array.length, minIndex = 0;
        while (is_Sorted) {
            let newIndex = 0;
            is_Sorted = false;
            for (let i = minIndex; i < maxIndex - 1; i++) {
                if (array[i] > array[i + 1]) {
                    [array[i], array[i + 1]] = [array[i + 1], array[i]];
                    is_Sorted = true;
                    newIndex = i + 1;
                }
            }
            maxIndex = newIndex;
            if (!is_Sorted)
                break;
            is_Sorted = false;
            for (let j = maxIndex; j > minIndex; j--) {
                if (array[j - 1] > array[j]) {
                    [array[j], array[j - 1]] = [array[j - 1], array[j]];
                    is_Sorted = true;
                    newIndex = j;
                }
            }
            minIndex = newIndex;
        }
        return array;
    }
}
class CombSort extends SortingAlgorithm {
    constructor() {
        super("Comb Sort");
    }
    sort(array) {
        let interval = Math.floor(array.length / 1.2);
        while (interval > 0) {
            for (let i = 0; i + interval < array.length; i++) {
                if (array[i] > array[i + interval]) {
                    [array[i], array[i + interval]] = [array[i + interval], array[i]];
                }
            }
            interval = Math.floor(interval / 1.2);
        }
        return array;
    }
}
class CycleSort extends SortingAlgorithm {
    constructor() {
        super("Cycle Sort");
    }
    sort(array) {
        // loop from the beginning of the array to the second to last item
        for (let currentIndex = 0; currentIndex < array.length - 1; currentIndex++) {
            // save the value of the item at the currentIndex
            let item = array[currentIndex];

            let currentIndexCopy = currentIndex;
            // loop through all indexes that proceed the currentIndex
            for (let i = currentIndex + 1; i < array.length; i++)
                if (array[i] < item)
                    currentIndexCopy++;

            // if currentIndexCopy has not changed, the item at the currentIndex is already in the correct currentIndexCopy
            if (currentIndexCopy === currentIndex)
                continue;

            // skip duplicates
            while (item === array[currentIndexCopy])
                currentIndexCopy++;

            // swap
            [array[currentIndexCopy], item] = [item, array[currentIndexCopy]];

            // repeat above steps as long as we can find values to swap
            while (currentIndexCopy !== currentIndex) {
                currentIndexCopy = currentIndex;
                // loop through all indexes that proceed the currentIndex
                for (let i = currentIndex + 1; i < array.length; i++)
                    if (array[i] < item)
                        currentIndexCopy++;

                // skip duplicates
                while (item === array[currentIndexCopy])
                    currentIndexCopy++;

                // swap
                [array[currentIndexCopy], item] = [item, array[currentIndexCopy]];
            }
        }
        return array;
    }
}
class DoubleSelectionSort extends SortingAlgorithm {
    constructor() {
        super("Double Selection Sort");
    }
    sort(array) {
        for (let i = 0, len = array.length; i < len; i++, len--) {
            let min = i, max = i;
            for (let j = i + 1; j < len; j++) {
                if (array[min] > array[j]) {
                    min = j;
                } else if (array[max] < array[j]) {
                    max = j;
                }
            }
            if (min !== i) {
                [array[i], array[min]] = [array[min], array[i]];
            }
            if (max !== i) {
                [array[len - 1], array[max]] = [array[max], array[len - 1]];
            } else {
                [array[len - 1], array[min]] = [array[min], array[len - 1]];
            }
        }
        return array;
    }
}
class GnomeSort extends SortingAlgorithm {
    constructor() {
        super("Gnome Sort");
    }
    sort(array) {
        for (let i = 0; i < array.length; ) {
            if (i === 0 || array[i - 1] <= array[i]) {
                i++;
            } else {
                [array[i], array[i - 1]] = [array[i - 1], array[i]];
                i--;
            }
        }
        return array;
    }
}
class HeapSort extends SortingAlgorithm {
    constructor() {
        super("Heap Sort");
    }
    sort(array) {
        let array_length = array.length;
        let heap_root = (input, i) => {
            let left = 2 * i + 1;
            let right = 2 * i + 2;
            let max = i;
            if (left < array_length && input[left] > input[max]) {
                max = left;
            }
            if (right < array_length && input[right] > input[max]) {
                max = right;
            }
            if (max !== i) {
                [input[i], input[max]] = [input[max], input[i]];
                heap_root(input, max);
            }
        };
        for (let i = Math.floor(array_length / 2); i >= 0; i--) {
            heap_root(array, i);
        }
        for (let i = array.length - 1; i > 0; i--) {
            [array[0], array[i]] = [array[i], array[0]];
            array_length--;
            heap_root(array, 0);
        }
        return array;
    }
}
class InsertionSort extends SortingAlgorithm {
    constructor() {
        super("Insertion Sort");
    }
    sort(array) {
        for (let i = 1, length = array.length; i < length; i++) {
            let key = array[i], j;
            for (j = i - 1; j >= 0 && array[j] > key; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
        return array;
    }
}
class MergeSort extends SortingAlgorithm {
    constructor() {
        super("Merge Sort");
    }
    sort(array, begin = 0, end = array.length - 1) {
        // Merge the two arrays: left and right
        let merge = (array, begin, middle, end) => {
            let resultArray = [], leftIndex = 0, rightIndex = 0;
            const left = array.slice(begin, middle + 1);
            const right = array.slice(middle + 1, end + 1);
            // We will concatenate values into the resultArray in order
            while (leftIndex < left.length && rightIndex < right.length) {
                if (left[leftIndex] < right[rightIndex]) {
                    resultArray.push(left[leftIndex]);
                    leftIndex++; // move left array cursor
                } else {
                    resultArray.push(right[rightIndex]);
                    rightIndex++; // move right array cursor
                }
            }
            // We need to concat to the resultArray because there will be one element left over after the while loop
            resultArray = resultArray.concat(left.slice(leftIndex)).concat(right.slice(rightIndex));
            for (let i = begin; i <= end; i++) {
                array[i] = resultArray.shift();
            }
        };
        // No need to sort the array if the array only has one element or empty
        if (end - begin === 1) {
            if (array[begin] > array[end]) {
                [array[begin], array[end]] = [array[end], array[begin]];
            }
        } else if (begin !== end) {
            // In order to divide the array in half, we need to figure out the middle
            const middle = Math.floor((begin + end) / 2);
            // Using recursion to combine the left and right
            this.sort(array, begin, middle);
            this.sort(array, middle + 1, end);
            merge(array, begin, middle, end);
        }
        return array;
    }
}
class OddEvenSort extends SortingAlgorithm {
    constructor() {
        super("Odd-Even Sort");
    }
    sort(array) {
        let sorted;
        do {
            sorted = true;
            for (let i = 1; i < array.length - 1; i += 2) {
                if (array[i] > array[i + 1]) {
                    [array[i], array[i + 1]] = [array[i + 1], array[i]];
                    sorted = false;
                }
            }
            for (let i = 0; i < array.length - 1; i += 2) {
                if (array[i] > array[i + 1]) {
                    [array[i], array[i + 1]] = [array[i + 1], array[i]];
                    sorted = false;
                }
            }
        } while (!sorted);
        return array;
    }
}
class QuickSort extends SortingAlgorithm {
    constructor() {
        super("Quick Sort");
    }
    sort(array, left = 0, right = array.length - 1) {
        let partition = (items, left, right) => {
            var pivot = items[Math.floor((right + left) / 2)], //middle element
                    i = left, //left pointer
                    j = right; //right pointer
            while (i <= j) {
                while (items[i] < pivot) {
                    i++;
                }
                while (items[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    [items[i], items[j]] = [items[j], items[i]]; //swapping two elements
                    i++;
                    j--;
                }
            }
            return i;
        };
        if (array.length > 1) {
            let index = partition(array, left, right); //index returned from partition
            if (left < index - 1) { //more elements on the left side of the pivot
                this.sort(array, left, index - 1);
            }
            if (index < right) { //more elements on the right side of the pivot
                this.sort(array, index, right);
            }
        }
        return array;
    }
}
class RadixSort extends SortingAlgorithm {
    constructor() {
        super("Radix Sort");
    }
    sort(array) {
        let getMax = (array) => {
            let max = 0;
            for (let i = 0; i < array.length; i++) {
                let num = array[i];
                if (max < num.toString().length) {
                    max = num.toString().length;
                }
            }
            return max;
        };
        let getPosition = (num, place) => {
            return Math.floor(Math.abs(num) / Math.pow(10, place)) % 10;
        };
        const max = getMax(array);
        for (let i = 0; i < max; i++) {
            let buckets = Array.from({length: 10}, () => []);
            for (let j = 0; j < array.length; j++) {
                buckets[getPosition(array[j], i)].push(array[j]);
            }
            let temp = buckets.flat();
            array.forEach((cv, i) => {
                array[i] = temp[i];
            });
        }
        return array;
    }
}
class SelectionSort extends SortingAlgorithm {
    constructor() {
        super("Selection Sort");
    }
    sort(array) {
        for (let i = 0, len = array.length; i < len; i++) {
            let min = i;
            for (let j = i + 1; j < len; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min !== i) {
                [array[i], array[min]] = [array[min], array[i]];
            }
        }
        return array;
    }
}
class ShellSort extends SortingAlgorithm {
    constructor() {
        super("Shell Sort");
    }
    sort(array) {
        let increment = Math.floor(array.length / 2);
        while (increment > 0) {
            for (let i = increment; i < array.length; i++) {
                let temp = array[i], j;
                for (j = i; j >= 0 && array[j - increment] > temp; j = j - increment) {
                    array[j] = array[j - increment];
                }
                array[j] = temp;
            }
            increment = Math.floor(increment / 2);
        }
        return array;
    }
}
class TimSort extends SortingAlgorithm {
    constructor() {
        super("Tim Sort");
        this.RUN = 32;
    }
    sort(array) {
        this.timSort(array, array.length);
    }
    // this function sorts array from left index to 
    // to right index which is of size atmost RUN 
    insertionSort(arr, left, right) {
        for (let i = left + 1; i <= right; i++) {
            let temp = arr[i];
            let j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    // merge function merges the sorted runs 
    merge(arr, l, m, r) {
        // original array is broken in two parts 
        // left and right array 
        let len1 = m - l + 1, len2 = r - m;
        let left = new Array(len1);
        let right = new Array(len2);

        for (let x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }

        for (let x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }
        let i = 0;
        let j = 0;
        let k = l;

        // after comparing, we merge those two array 
        // in larger sub array 
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        // copy remaining elements of left, if any 
        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }
        // copy remaining element of right, if any 
        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    // iterative Timsort function to sort the 
    // array[0...n-1] (similar to merge sort) 
    timSort(arr, n) {
        // Sort individual subarrays of size RUN 
        for (let i = 0; i < n; i += this.RUN) {
            this.insertionSort(arr, i, Math.min((i + 31), (n - 1)));
        }

        // start merging from size RUN (or 32). It will merge 
        // to form size 64, then 128, 256 and so on .... 
        for (let size = this.RUN; size < n; size = 2 * size) {
            // pick starting point of left sub array. We 
            // are going to merge arr[left..left+size-1] 
            // and arr[left+size, left+2*size-1] 
            // After every merge, we increase left by 2*size 
            for (let left = 0; left < n; left += 2 * size) {
                // find ending point of left sub array 
                // mid+1 is starting point of right sub array 
                let mid = left + size - 1;

                let right = Math.min((left + 2 * size - 1), (n - 1));

                // merge sub array arr[left.....mid] & 
                // arr[mid+1....right] 
                this.merge(arr, left, mid, right);
            }
        }
    }
}
export const algorithms = [new BinaryInsertionSort(), new BubbleSort(), new CocktailSort(), new CombSort(), new CycleSort(), new DoubleSelectionSort(), new GnomeSort(), new HeapSort(), new InsertionSort(), new MergeSort(), new OddEvenSort(), new QuickSort(), new RadixSort(), new SelectionSort(), new ShellSort(), new TimSort()];