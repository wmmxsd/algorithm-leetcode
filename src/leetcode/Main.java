import java.util.Scanner;

/**
 * @author wangmingming160328
 * @Description
 * @date @2020/8/15 12:02
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        int i = 2 ^ 2;
        System.out.println(i);
        newString(str1);
        newString(str2);

    }

    private static void newString(String str) {
        int length = str.length();
        if (length < 8){
            outPut(str);
        } else {
            for (int index = 0; index < length / 8; index++) {
                System.out.println(str.substring(index * 8, index * 8 + 8));
            }
            if (length % 8 != 0) {
                outPut(str.substring(length / 8 * 8));
            }
        }
    }

    private static void outPut(String str) {
        StringBuilder strBuilder = new StringBuilder(str);
        for (int index = 0; index < 8 - str.length(); index++) {
            strBuilder.append("0");
        }
        System.out.println(strBuilder.toString());
    }
}
