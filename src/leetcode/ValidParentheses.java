import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author wangmingming160328
 * @Description 有效的括号
 * @date @2020/5/18 23:47
 */
public class ValidParentheses {
    private final Map<Character, Character> map = new HashMap<>(3);

    public ValidParentheses() {
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        assert s != null;
        assert s.length() > 0;
        for (int index = 0; index < s.length(); index++) {
            char currentChar = s.charAt(index);
            if (map.containsKey(currentChar)) {
                char cr = stack.isEmpty() ? '#' : stack.pop();
                if (cr != map.get(currentChar)) {
                    return false;
                }
            } else {
              stack.push(currentChar);
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        String test = "[]{}()";
        String test1 = "[{()}]";
        String test2 = "[[[]]";
        String test3 = "[";
        String test4 = "";

        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid(test));
        System.out.println(validParentheses.isValid(test1));
        System.out.println(validParentheses.isValid(test2));
        System.out.println(validParentheses.isValid(test3));
        System.out.println(validParentheses.isValid(test4));
    }
}
