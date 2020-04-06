package net.luohuasheng.bee.jdbc.common.utils;

 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luohuasheng
 * @date 2020/4/5 20:57
 */
public class ColumnUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String toSnakeCase(String input) {
        if (input == null) {
            return null;
        } else {
            int length = input.length();
            StringBuilder result = new StringBuilder(length * 2);
            int resultLength = 0;
            boolean wasPrevTranslated = false;

            for (int i = 0; i < length; ++i) {
                char c = input.charAt(i);
                if (i > 0 || c != '_') {
                    if (Character.isUpperCase(c)) {
                        if (!wasPrevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != '_') {
                            result.append('_');
                            ++resultLength;
                        }

                        c = Character.toLowerCase(c);
                        wasPrevTranslated = true;
                    } else {
                        wasPrevTranslated = false;
                    }

                    result.append(c);
                    ++resultLength;
                }
            }

            return resultLength > 0 ? result.toString().toUpperCase() : input;
        }
    }

    public static String toCamelCase(String input) {
        input = input.toLowerCase();
        Matcher matcher = linePattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
