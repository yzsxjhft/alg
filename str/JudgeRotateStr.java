package str;

public class JudgeRotateStr {
    public static void main(String[] args) {
        String A = "abcdef";
        String B = "cdefab";
        System.out.println(new JudgeRotateStr().judge(A, B));
    }

    public boolean judge(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
