public class Test3 {
        private static void timeConversion(String str) {
            String[] split = str.split(":");
            String hour = split[0];
            String minute = split[1];
            String second = split[2].substring(0, 2);
            String ampm = split[2].substring(2, 4);
            if (ampm.equals("AM")) {
                if (hour.equals("12")) {
                    hour = "00";
                }
            } else {
                if (!hour.equals("12")) {
                    hour = String.valueOf(Integer.parseInt(hour) + 12);
                }
            }
            System.out.println(hour + ":" + minute + ":" + second);
        }

        public static void main(String[] args) {
            timeConversion("07:05:45PM");
        }
}
