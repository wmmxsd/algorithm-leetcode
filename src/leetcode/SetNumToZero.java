/**
 * @author wangmingming160328
 * @Description
 * @date @2020/8/10 23:20
 */
public class SetNumToZero {
    public int numberOfSteps (int num) {
        int result = 0;
        while(num != 0) {
            if(num % 2 == 0) {
                num /= 2;
            } else {
                num--;
            }
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        SetNumToZero setNumToZero = new SetNumToZero();
        System.out.println(setNumToZero.numberOfSteps(14));
    }
}
