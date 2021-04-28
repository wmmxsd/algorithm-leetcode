import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wangmingming160328
 * @Description
 * @date @2020/8/15 21:19
 */
public class StackDemo {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if ("..".equals(item)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.isEmpty() && !".".equals(item)) {
                stack.push(item);
            }
        }
        String res = "";
        for (String d : stack) {
            res = "/" + d + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    public static void main(String[] args) {
        System.out.println(new StackDemo().simplifyPath("/a/./b/../../c/"));
    }
}
