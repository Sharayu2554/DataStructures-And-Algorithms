/**
 * Class to Implement Regex
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/25/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

public class Regex {

    public boolean isMatch(String input, String pattern) {

        if (pattern.isEmpty()) {
            return input.isEmpty();
        }

        boolean firstMatch = (!input.isEmpty()) && input.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.';
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (firstMatch && isMatch(input.substring(1), pattern) || (isMatch(input, pattern.substring(2))));
        }
        else {
            return firstMatch && isMatch(input.substring(1) ,pattern.substring(1));
        }

    }

    public boolean isRegex(int i, int j, String input, String pattern) {
        if (j >= pattern.length()) {
            return (i >= input.length());
        }

        boolean firstMatch = (i < input.length()) && (input.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');
        if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*') {
            return (firstMatch && isRegex(i+1, j, input, pattern) || isRegex(i, j+2, input, pattern)) ;
        }
        else {
            return firstMatch && isRegex(i+1, j+1, input, pattern);
        }
    }

    public static void main(String args[]) {

    }
}
