public class Test1 {

    private static void miniMaxSum(int[] arr) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (int j : arr) {
            sum += j;
            if (j <= min) {
                min = j;
            }
            if (j > max) {
                max = j;
            }
        }
        System.out.println((sum - max) + " " + (sum - min));
    }

    public static void main(String[] args) {
        miniMaxSum(new int[]{1, 3, 5, 7, 9});
    }
}