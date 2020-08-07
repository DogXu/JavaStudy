package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DogXu
 * @date 2020/8/6 14:12
 * @description:
 */
public class ZY_ABC_Test {

    @Test
    public void Test1() {
        String word = "";
        System.err.println(isWordWright(word));
    }

    private boolean isWordWright(String word) {
        int length = word.length();

        if (length == 1) {
            return true;
        }

        char[] chars = word.toCharArray();
        if (length >= 2) {
            boolean flag = true;

            if (isCapital(chars[0])) {
                //全大写：
                if (isCapital(chars[1])) {
                    for (char c : chars) {
                        if (isLower(c)) {
                            flag = false;
                            break;
                        }
                    }
                }
                //首字母大写：
                if (isLower(chars[1])) {
                    for (char c : chars) {
                        if (isCapital(c)) {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if (isLower(chars[0])) {
                for (char c : chars) {
                    if (isCapital(c)) {
                        flag = false;
                        break;
                    }
                }
            }

            return flag;
        }

        return false;
    }

    private boolean isLower(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }

        return false;
    }

    private boolean isCapital(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }

        return false;
    }

    @Test
    public void Test3() {
        String fc = "2x+3x-6x=x+2";
        System.err.println(solution(fc));
    }

    private String solution(String fc) {
        String[] s = fc.split("=");

        Map<String,Integer> leftMap = getResultMap(s[0]);
        Map<String,Integer> rightMap = getResultMap(s[1]);

        int num = leftMap.get("num") - rightMap.get("num");
        int xNum = leftMap.get("xNum") - rightMap.get("xNum");

        if (xNum == 0 && num == 0) {
            return "Infinite solutions";
        }

        if (xNum == 0) {
            return "No Solution";
        }

        return "x="+ String.valueOf(((-num) / xNum));
    }

    @Test
    public void getResultMapTest() {
        String str = "2x-1+6x-x-5+10-2-3x";
        Map<String, Integer> resultMap = getResultMap(str);
        System.err.println(resultMap);
    }

    private Map<String,Integer> getResultMap(String str) {
        Map<String,Integer> resMap = new HashMap<>(2);

        int num = 0;
        int xNum = 0;

        String[] split = str.split("\\+");
        for (String s1 : split) {
            String[] s2 = s1.split("-");

            for (int i = 0; i < s2.length; i++) {
                if (i == 0) {
                    if (s2[i].contains("x")) {
                        String numStr = s2[i].replace("x", "");
                        if (numStr.length() > 0) {
                            xNum += Integer.parseInt(numStr);
                        } else {
                            xNum += 1;
                        }
                    } else {
                        num += Integer.parseInt(s2[i]);
                    }
                } else {
                    if (s2[i].contains("x")) {
                        String numStr = s2[i].replace("x", "");
                        if (numStr.length() > 0) {
                            xNum -= Integer.parseInt(numStr);
                        } else {
                            xNum -= 1;
                        }
                    } else {
                        num -= Integer.parseInt(s2[i]);
                    }
                }
            }
        }

        resMap.put("num", num);
        resMap.put("xNum", xNum);

        return resMap;
    }
}
