import java.util.Arrays;
import java.util.MissingFormatArgumentException;

public class MyStringList implements StringList {

    private final String[] values;
    private int size;

    public MyStringList(int initSize) {
        values = new String[initSize];
    }

    public MyStringList() {
        values = new String[10];
    }

    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        values[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);
        values[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
                String removeString = values[index];
                System.arraycopy(values, index, values, index + 1, size - index);
                size--;
                return removeString;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String removeString = values[index];
        System.arraycopy(values, index, values, index + 1, size - index);
        size--;
        return removeString;
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(values[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(values[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = size-1; i >= 0; i--) {
            if (item.equals(values[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return values[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {

        return Arrays.copyOf(values,size);
    }

    void validateIndex(int index) {
        if (index < 0 || index > values.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    void validateItem(String item) {
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
