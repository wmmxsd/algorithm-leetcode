package ExcelColumNumber171;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @Description
 * @date @2021/4/11 22:23
 */
public class ExcelColumnNumber {
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.trim().isEmpty()) throw new IllegalArgumentException();
        int result = 0;
        char[] columnTitleArray = columnTitle.toUpperCase().toCharArray();
        for (int index = columnTitleArray.length - 1; index >= 0; index--) {
            result+= (columnTitleArray[index] - 64) * Math.pow(26, columnTitleArray.length - 1 - index);
        }
        return result;
    }

    public static void main(String[] args) {
        ExcelColumnNumber excelColumnNumber = new ExcelColumnNumber();
        System.out.println(excelColumnNumber.titleToNumber("A"));
        System.out.println(excelColumnNumber.titleToNumber("Z"));
        System.out.println(excelColumnNumber.titleToNumber("AA"));
        System.out.println(excelColumnNumber.titleToNumber("AB"));
        System.out.println(excelColumnNumber.titleToNumber("ZY"));
        System.out.println(excelColumnNumber.titleToNumber("AAA"));
    }

}
