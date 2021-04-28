/**
 * @author wangmingming160328
 * @Description
 * @date @2020/8/12 23:32
 */
public class Demo2 {
    public int myAtoi(String str) {
        int result = 0;
        //非空检测
        if(str == null || str.length() == 0 || str.trim().length() == 0) {
            return 0;
        }
        str = str.trim();
        //第一个非空字符是否为'+'、'-'及数字
        char[] strArray = str.toCharArray();
        char firstNonNullChar = strArray[0];
        if(firstNonNullChar != '+' && firstNonNullChar != '-' && (firstNonNullChar < 48 || firstNonNullChar > 57)) {
            return 0;
        }
        //转换为整数
        int index = 0;
        boolean positive = true;
        if(firstNonNullChar == '-') {
            positive = false;
            index++;
        } else if(firstNonNullChar == '+') {
            index++;
        }

        while(index < strArray.length) {
            char tempChar = strArray[index];
            if(tempChar < 48 ||tempChar > 57) {
                break;
            }
            if(result > (Integer.MAX_VALUE - (tempChar - '0')) / 10) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (tempChar - '0');
            index++;
        }

        return positive ? result : -result;
    }

    public static void main(String[] args) {
        System.out.println(new Demo2().myAtoi("23"));
    }
}
