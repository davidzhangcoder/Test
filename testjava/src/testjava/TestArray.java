package testjava;

public class TestArray {

    public static void main(String[] args) {
        int[][] a = new int[5][];
        a[0] = new int[1];
        a[1] = new int[2];

        for (int[] ints : a) {
            System.out.println(ints.length);
        }
    }
}
