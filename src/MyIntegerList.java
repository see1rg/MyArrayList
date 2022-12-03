import java.util.Arrays;
import java.util.MissingFormatArgumentException;

public class MyIntegerList implements IntegerList {

    private final Integer[] values;
    private int size;

    public MyIntegerList(int initSize) {
        values = new Integer[initSize];
    }

    public MyIntegerList() {
        values = new Integer[10];
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
            values[size++]=item;
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
    public boolean contains(Integer item) { // todo  осуществлена сортировка и вызван метод бинарного поиска.
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(values[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(values[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size-1; i >= 0; i--) {
            if (item.equals(values[i])){
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
        return Arrays.copyOf(values,size);
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

    void validateSize(){
        if (size == values.length) {
            throw new IndexOutOfBoundsException();
        }
    }







}
