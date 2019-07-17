public class PackComplete {
    public static void main(String[] args) {
        int V = 68;
        int[] c1 = {69, 60, 8};
        int[] w1 = {10, 1, 3};
        System.out.println(new PackComplete().charge(c1, w1, V));
    }

    public int charge(int[] c1, int[] w1, int V) {
        int[] dp = new int[V+1];
        for (int j=1; j<=V; j++) {
            for (int i=0; i<c1.length; i++) {
                if (j >= c1[i]) {
                    dp[j] = Math.max(dp[j], dp[j-c1[i]] + w1[i]);
                }
            }
        }

        return dp[V];
    }
}
