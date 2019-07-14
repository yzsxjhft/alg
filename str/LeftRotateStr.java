public class LeftRotateStr {
    public static void main(String[] args) {
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f'};
        new LeftRotateStr().rotate2(str, 2);
        System.out.println(str);
    }

    public void rotate1(char[] str, int n) {
        int len = str.length;
        n %= len;
        int index = len - 1;
        for (int i=0; i<n; i++) {
            int next = index;
            char replace = str[next];
            while(next - n >= 0) {
                next = next - n;
                char tmp = str[next];
                str[next] = replace;
                replace = tmp;
            }
            str[(len + next - n)%len] = replace;
            index --;
        }
    }

    public void rotate2(char[] str, int n) {
        int len = str.length;
        n %= len;
        reverse(str, 0, n-1);
        reverse(str, n, len-1);
        reverse(str, 0, len-1);
    }

    public void reverse(char[] str, int start, int end) {
        while(start < end) {
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
            start ++;
            end --;
        }
    }
}
