public class Test2 {

    private static void plusMinus(int[] arr) {
        int length = arr.length;
        double positive = 0;
        double negative = 0;
        double zero = 0;
        for (int j : arr) {
            if (j > 0) {
                positive++;
            } else if (j < 0) {
                negative++;
            } else {
                zero++;
            }
        }
        System.out.println(positive / length);
        System.out.println(negative / length);
        System.out.println(zero / length
        );
    }

    public static void main(String[] args) {
        plusMinus(new int[]{-4, 3, -9, 0, 4, 1});
    }
}
