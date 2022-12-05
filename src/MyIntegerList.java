import jdk.internal.util.ArraysSupport;

import java.util.Arrays;
import java.util.MissingFormatArgumentException;

public class MyIntegerList implements IntegerList {

    private static final int DEFAULT_CAPACITY = 10;
    private Integer[] values;
    private int size;

    public MyIntegerList(int initSize) {
        values = new Integer[initSize];
    }

    public MyIntegerList() {
        values = new Integer[10];
    }

//    public static void sort(Integer[] values) {
//        for (int i = 1; i < values.length; i++) {
//            int temp = values[i];
//            int j = i;
//            while (j > 0 && values[j - 1] >= temp) {
//                values[j] = values[j - 1];
//                j--;
//            }
//            values[j] = temp;
//        }
//    }

    public static void sort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        values[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            values[size++] = item;
            return item;
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        size++;
        values[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        values[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        Integer removeInteger = values[index];
        System.arraycopy(values, index, values, index + 1, size - index);
        size--;
        return removeInteger;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer removeInteger = values[index];
        System.arraycopy(values, index, values, index + 1, size - index);
        size--;
        return removeInteger;
    }

    @Override
    public boolean contains(Integer item) { //   осуществлена сортировка и вызван метод бинарного поиска.
        validateItem(item);
        sort(values,0,values.length);

        int min = 0;
        int max = values.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == values[mid]) {
                return true;
            }

            if (item < values[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(values[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(values[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return values[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(values, size);
    }

    void validateIndex(int index) {
        if (index < 0 || index > values.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    void validateItem(Integer item) {
        if (item == null) {
            throw new MissingFormatArgumentException(" not Null");
        }
    }


    private Object[] grow(int minCapacity) {
        int oldCapacity = values.length;
        if (oldCapacity > 0) {
            int newCapacity = values.length + values.length / 2;
            return values = Arrays.copyOf(values, newCapacity);
        } else {
            return values = new Integer[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    void validateSize() {
        if (size == values.length) {
            grow();
        }
    }

    @Override
    public String toString() {
        return "MyIntegerList{" +
                "values=" + Arrays.toString(values) +
                ", size=" + size +
                '}';
    }
}
