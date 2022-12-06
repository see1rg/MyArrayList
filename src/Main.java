import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Integer[] arr1 = new Integer[100_000];
//        MyIntegerList example = new MyIntegerList();
//        example.add(3);
//        example.add(30);
//        example.add(31);
//        example.add(32);
//        example.add(33);
//        example.add(34);
//        example.add(3);
//        example.add(34);
//        example.add(3);
//        example.add(3);
//        example.add(3);
//        example.add(3);
//        example.add(3);
//        example.add(3);
//        System.out.println(example.toString());
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = getRandom();
        }
        Integer[] arr;

        arr = Arrays.copyOf(arr1, arr1.length);
        long start2 = System.currentTimeMillis();
        sortSelection(arr);
        System.out.println("sortSelection - " + (System.currentTimeMillis() - start2));

        arr = Arrays.copyOf(arr1, arr1.length);
        long start1 = System.currentTimeMillis();
        MyIntegerList.sort(arr,0, arr.length-1);
        System.out.println("QuickSort - " + (System.currentTimeMillis() - start1));

        arr = Arrays.copyOf(arr1, arr1.length);
        long start = System.currentTimeMillis();
        sortBubble(arr);
        System.out.println("sortBubble - " + (System.currentTimeMillis() - start));

    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }


    public static int getRandom() {
        Random rand = new Random();
        return rand.nextInt(10_000);
    }

}