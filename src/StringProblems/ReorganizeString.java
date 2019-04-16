/**
 * Class to Implement ReorganizeString
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/14/19
 * Project : StringProblems
 **/

package StringProblems;

import java.util.*;

public class ReorganizeString {

    class MultiChar {
        int count;
        char letter;
        MultiChar(int ct, char ch) {
            count = ct;
            letter = ch;
        }
    }

    public String reorganizeString(String S) {
        int N = S.length();
        int[] count = new int[26];
        for (char c: S.toCharArray()) count[c-'a']++;
        PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
                a.count == b.count ? a.letter - b.letter : b.count - a.count);

        for (int i = 0; i < 26; ++i) if (count[i] > 0) {
            if (count[i] > (N + 1) / 2) return "";
            pq.add(new MultiChar(count[i], (char) ('a' + i)));
        }

        StringBuilder ans = new StringBuilder();
        while (pq.size() >= 2) {
            MultiChar mc1 = pq.poll();
            MultiChar mc2 = pq.poll();

            ans.append(mc1.letter);
            ans.append(mc2.letter);
            if (--mc1.count > 0) pq.add(mc1);
            if (--mc2.count > 0) pq.add(mc2);
        }


        if (pq.size() > 0) ans.append(pq.poll().letter);
        return ans.toString();
    }

    public String splitLoopedString(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            String rev = new StringBuilder(strs[i]).reverse().toString();
            if (strs[i].compareTo(rev) < 0)
                strs[i] = rev;
        }
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            String rev = new StringBuilder(strs[i]).reverse().toString();
            for (String st : new String[]{strs[i], rev}) {
                for (int k = 0; k < st.length(); k++) {
                    StringBuilder t = new StringBuilder(st.substring(k));
                    for (int j = i + 1; j < strs.length; j++)
                        t.append(strs[j]);
                    for (int j = 0; j < i; j++)
                        t.append(strs[j]);
                    t.append(st.substring(0, k));
                    if (t.toString().compareTo(res) > 0)
                        res = t.toString();
                }
            }
        }
        return res;
    }

    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i =0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        int i =0;
        int start = 0;
        int count = 0;
        HashMap<Character, Integer> r = new HashMap<>();
        //abc, cccbbbababa , ab, eiboabo,   bbc, aabccbcc
        while (i < s2.length()) {
            Character c = s2.charAt(i);
            if (map.containsKey(c)) {
                //System.out.println(c +  " " + r.getOrDefault(c, 0));
                if (r.getOrDefault(c, 0) + 1 > map.get(c)) {
                    r.put(s2.charAt(start), r.get(s2.charAt(start))-1);
                    start++;
                    count--;
                }
                else {
                    r.put(c, r.getOrDefault(c, 0) +1);
                    count++;
                    i++;
                }
            }
            else {
                i++;
                start = i;
                count = 0;
                r = new HashMap<>();
            }
            if (map.size() == r.size() && count == s1.length()) {
                return true;
            }
        }
        return false;
    }


    enum OPERATOR {
        NUM,
        CHAR,
        OPEN,
        CLOSE

   }

    public OPERATOR check(Character c) {

        switch(c) {
            case '[' : return OPERATOR.OPEN;
            case ']' : return OPERATOR.CLOSE;
            default:
                int ch = c;
                if (ch >= '0' && ch <= '9') {
                    return OPERATOR.NUM;
                }
                else {
                    return OPERATOR.CHAR;
                }
        }
    }

    public String decodeString(String s) {

        Stack<String> st = new Stack<>();
        int i =0;
        Stack<OPERATOR> stOp = new Stack<>();
        String res = "";
        while (i < s.length()) {
            Character c = s.charAt(i);
            OPERATOR or = check(c);
            System.out.println(c);
            switch(or.ordinal()) {
                case 0: //NUM
                    st.push(c + "");
                    stOp.push(OPERATOR.NUM);
                    break;
                case 1: //CHAR
                    st.push(c + "");
                    stOp.push(OPERATOR.CHAR);
                    break;
                case 2:  //OPEN
                    st.push(c + "");
                    stOp.push(OPERATOR.OPEN);
                    break;
                case 3: //CLOSE
                    String r =  close(st, stOp);
                    if (st.empty()) {
                        res += r;
                    }
                    else {
                        st.push(r);
                        stOp.push(OPERATOR.CHAR);
                    }
                    break;
            }
            i++;
        }

        String r = "";
        while (!st.empty()) {
            r = st.pop() + r;
            stOp.pop();
        }

        res += r;
        return res;
    }

    public String close(Stack<String> st, Stack<OPERATOR> stOp){

        String s = "";
        while(!stOp.empty() && stOp.peek() == OPERATOR.CHAR) {
            stOp.pop();
            s = st.pop() + s;
        }
        System.out.println("poped : " + s);

        if (!stOp.empty() && stOp.peek() == OPERATOR.OPEN) {
            System.out.println("poped : " + OPERATOR.OPEN);
            stOp.pop();
            st.pop();
        }

        String num = "";
        while(!stOp.empty() && stOp.peek() == OPERATOR.NUM) {
            stOp.pop();
            num = st.pop() + num;
        }
        System.out.println("poped : " + num);

        if (!stOp.empty() && stOp.peek() == OPERATOR.OPEN) {
            System.out.println("poped : " + OPERATOR.OPEN);
            stOp.pop();
            st.pop();
        }

        StringBuilder sb = new StringBuilder();
        int n = num == "" ? 1: Integer.parseInt(num);
        while (n > 0) {
            sb.append(s);
            n--;
        }
        return sb.toString();
    }

    public String decodeString(String s, int i) {
        StringBuilder sb = new StringBuilder();
        int num = 0;

        for (; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '[') {
                i++;
                String str = decodeString(s, i);
                for (int k = 0; k < num; k++) {
                    sb.append(str);
                }
                num = 0;
            } else if (c == ']') {
                return sb.toString();
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String args[]) {
        String s = "aaabbcc";
        ReorganizeString r = new ReorganizeString();
        System.out.println(r.reorganizeString(s));

        System.out.println("abc, cccbbbababa : " + r.checkInclusion("abc", "cccbbbababa" ));
        System.out.println("ab, eiboabo : " + r.checkInclusion("ab", "eiboabo"));
        System.out.println("bbc, aabccbcc : " + r.checkInclusion("bbc", "aabccbcc "));
        System.out.println("abc, cccbababa : " + r.checkInclusion("abc", "cccbababa" ));

//        System.out.println("3[a] : " + r.decodeString("3[a]"));
//        System.out.println("3[a]2[b]ef : " + r.decodeString("3[a]2[b]ef"));
//        System.out.println("3[a2[c]] : " + r.decodeString("3[a2[c]]") ) ;
//        System.out.println("3[a2[c3[d]2[e]f]]2[abc3[cd]]ef : " + r.decodeString("3[a2[c3[d]2[e]f]]2[abc3[cd]]ef"));
       // System.out.println("3[z]2[2[y]pq4[2[jk]e1[f]]]]ef : " + r.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]]ef"));
//        System.out.println("2[2[y]pq4[2[jk]e1[f]]] : " + r.decodeString("2[2[y]pq4[2[jk]e1[f]]]"));
        System.out.println("100[leetcode] : " + r.decodeString("100[leetcode]", 0));
    }
}
