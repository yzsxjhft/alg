package problem2;


public class Main {
    public static void main(String[] args) {
    }
}

class Solution {
    public String minWindow(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
        if(lenS < lenT) return "";
        int[] dict = new int[128];
        for(int i=0; i<lenT; i++) {
            dict[T.charAt(i)]++;
        }
        int count = lenT;
        int left = 0;
        int right = 0;
        int minW = lenS + 1;
        int head = 0;
        while(left <=right && right < lenS) {
            if(dict[S.charAt(right++)]-- > 0) count--;
            while(count == 0) {
                if(right-left < minW) {
                    minW = right-(head = left);
                }
                if(dict[S.charAt(left++)]++ >= 0) {
                    count++;
                }
            }

        }
        return minW > lenS?"":S.substring(head, head+minW);
    }
}