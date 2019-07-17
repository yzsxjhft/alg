package str;

public class ReverseWords {
    public static void main(String[] args) {

        String line = " I   am a   people       ";
        System.out.println(new ReverseWords().reverse(line));
    }

    public String reverse(String str) {
        String[] arr = str.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i=arr.length-1; i>=0; i--) {
            if (arr[i].trim().isEmpty()) continue;
            res.append(arr[i]).append(" ");
        }
        return res.toString().trim();
    }

}
