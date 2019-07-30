public class PackComplete {
    public static void main(String[] args) {
//        int V = 68;
        int[] c1 = {69, 60, 8};
        int[] w1 = {10, 1, 3};
        int[] c = {2, 3};
        int[] w = {10, 24};
        int V = 100;
        System.out.println(new PackComplete().charge1(c, w, V));
    }

    public int charge1(int[] c1, int[] w1, int V) {
        int value = 0;
        for (int i=0; i<c1.length; i++) {
            if (c1[i]<=V) {
                value = Math.max(value, charge1(c1, w1, V-c1[i]) + w1[i]);
            } else {
            }
        }
        return value;
    }

    public int charge2(int[] c1, int[] w1, int V) {
        int[] dp = new int[V+1];
        for (int i=0; i<c1.length; i++) {
            for (int j=1; j<=V; j++) {
                if (j >= c1[i]) {
                    dp[j] = Math.max(dp[j], dp[j-c1[i]] + w1[i]);
                }
            }
        }

        return dp[V];
    }
}
