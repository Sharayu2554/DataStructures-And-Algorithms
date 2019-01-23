package SearchPatterns;

public class RabinKarp {

    /**
     * Run Time of Rabin Karp for
     * Best case and Average Case is O(M + N)
     * Worst Case : O(M * N) , example : Pattern : "AAAA" and Text : "AAAAAAAAAAAAA"
     * @param pattern
     * @param txt
     * @param q : Low Prime NUmber for calculating Hash Value
     * @return
     */
    public static int search(String pattern, String txt, int q) {

        /**
         * calculate the hash function of the pattern and first k character of txt, where k is length of pattern
         * if hash function matches, make a secondary check
         * else subtract hash of first character in window and add hash of last character and check again
         * hash( txt[s+1 .. s+m] ) = ( d ( hash( txt[s .. s+m-1]) – txt[s]*h ) + txt[s + m] ) mod q)
         * hash( txt[s .. s+m-1] ) : Hash value at shift s.
         * hash( txt[s+1 .. s+m] ) : Hash value at next shift (or shift s+1)
         * d: Number of characters in the alphabet
         * h: d^(m-1) //m is lenght of pattern
         *
         */

        int m = pattern.length();
        int n = txt.length();
        int d = 256; // d is the number of characters in the input alphabet
        int h = 1;
        int p = 0;
        int t = 0;

        for (int i =0; i < m-1; i++) {
            h = (h * d) % q; //The value of h would be "pow(d, M-1)%q"
        }

        // Calculate the hash value of pattern and first
        // window of text
        //modify this hash( txt[s+1 .. s+m] ) = ( d ( hash( txt[s .. s+m-1]) – txt[s]*h ) + txt[s + m] ) mod q)
        //NewH = (d * oldH + new asci char value) % q
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        //check and keep moving the window by 1
        boolean flag = false;
        for (int i = 0; i < n-m; i++) {
            if (p == t) {
                //secondary check
                for (int j = 0; j < m; j++) {
                    if (pattern.charAt(j) != txt.charAt(i + j)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Pattern match found at index " + i);
                    flag = false;
                }
            }
            t = (d * (t - txt.charAt(i) * h) + txt.charAt(i+ m))%q;

            //we might get negative t value, so convert it to positive
            if (t < 0)
                t += q;

        }
        return -1;
    }

    public static void main(String args[]) {
        String txt = "GEEKS FOR GEEKS";
        String pat = "GEEK";
        int q = 11; // A prime number
        search(pat, txt, q);
    }
}
