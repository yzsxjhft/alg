package str;

public class JudgeRangeStr {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bbbca";
        System.out.println(new JudgeRangeStr().checkInclusion(s1, s2));
    }

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int[] window = new int[26];
        int[] source = new int[26];
        for(int i=0; i<len1; i++) {
            window[s1.charAt(i) - 'a'] ++;
            source[s2.charAt(i) - 'a'] ++;
        }
        int match = 0;
        for(int i=0; i<26; i++) {
            if(window[i] == source[i]) match++;
        }
        if(match == 26) return true;
        for(int i=len1; i<s2.length(); i++) {
            int right = s2.charAt(i) - 'a';
            int left = s2.charAt(i-len1) - 'a';
            if(window[right] == source[right]) {
                match --;
                source[right] ++;
            }
            else {
                source[right] ++;
                if(window[right] == source[right]) {
                    match ++;
                }
            }
            if(window[left] == source[left]) {
                match--;
                source[left] --;
            } else {
                source[left] --;
                if(window[left] == source[left]) {
                    match ++;
                }
            }
            if(match == 26) return true;
        }
        return false;
    }
}
