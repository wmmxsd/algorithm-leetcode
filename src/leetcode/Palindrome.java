/**
 * @author wangmingming160328
 * @Description
 * @date @2020/8/13 22:00
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(new Palindrome().convert("LEETCODEISHIRINGF", 3));
    }

    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0 || numRows == 0) {
            return "";
        }
        StringBuilder[] resultArray = new StringBuilder[numRows];
        for (int index = 0; index < numRows; index++) {
            resultArray[index] = new StringBuilder();
        }
        char[] charArray = s.toCharArray();

        int index = 0;
        boolean order = true;
        while(index < charArray.length) {
            if(order) {
                for(StringBuilder result : resultArray) {
                    if (index < charArray.length) {
                        result.append(charArray[index++]);
                    }
                }
                order = false;
            } else {
                for(int count = numRows - 2; count > 0; count--) {
                    if (index < charArray.length) {
                        resultArray[count].append(charArray[index++]);
                    }
                }
                order = true;
            }
        }

        StringBuilder stringBuilder = new StringBuilder(charArray.length);
        for(StringBuilder result : resultArray){
            stringBuilder.append(result.toString());
        }

        return stringBuilder.toString();
    }
}
