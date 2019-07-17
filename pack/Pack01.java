public class Pack01 {
    public static void main(String[] args) {
        int V = 68;
        int[] c1 = {69, 60, 8};
        int[] w1 = {10, 1, 3};
    }

    public int charge1(int[] c1, int[] w1, int V, int value, boolean[] visit) {
        if (V <= 0) return value;
        int res = value;
        for(int i = 0; i<c1.length; i++) {
            if (visit[i]) continue;
            if (c1[i] <= V) {
                visit[i] = true;
                res = Math.max(res, charge1(c1, w1, V-c1[i], value+w1[i], visit));
                visit[i] = false;
            }
        }
        return res;
    }

    public int charge2(int[] c1, int[] w1, int V) {
        int[] dp = new int[V+1];
        for (int i=0; i<c1.length; i++) {
            //倒序，并且放在内层循环，是为了保证每个物品只考虑一次
            for (int j=V; j>=0; j--) {
                if (j >= c1[i]) {
                    dp[j] = Math.max(dp[j], dp[j-c1[i]] + w1[i]);
                }
            }
        }

        return dp[V];
    }
}
