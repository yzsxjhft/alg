import java.util.Map;

public class PackMutile {
    public static void main(String[] args) {
        int[] n = {4, 2, 4, 3, 4, 5, 1};
        int[] c = {2, 4, 2, 4, 5, 3, 1};
        int[] w = {10, 11, 24, 13, 24, 34,45};
        int V = 100;
        System.out.println(new PackMutile().charge3(n, c, w, V));
    }

    public int charge1(int[] n, int[] c, int[] w, int V) {
        int value = 0;
        if (V <= 0) return value;
        for (int i=0; i<c.length; i++) {
            if (n[i] > 0 && c[i]<=V) {
                n[i]--;
                value = Math.max(value, charge1(n, c, w, V-c[i])+w[i]);
            }
        }
        return value;
    }

    public int charge2(int[] n, int[] c, int[] w, int V) {
        int[] dp = new int[V+1];
        for (int i=0; i<c.length; i++) {
            for (int j=0; j<n[i]; j++) {
                for (int k=V; k>=c[i]; k--) {
                    dp[k] = Math.max(dp[k], dp[k-c[i]]+w[i]);
                }
            }
        }
        return dp[V];
    }

    public int charge3(int[] n, int[] c, int[] w, int V) {
        int[] dp = new int[V+1];
        for (int i=0; i<c.length; i++) {
            if (c[i]*n[i] >= V) {
                // 化为完全背包问题
                for (int k=c[i]; k<=V; k++) {
                    dp[k] = Math.max(dp[k], dp[k-c[i]] + w[i]);
                }
            } else {
                // 化为0-1背包问题，并通过二进制倍增优化
                int count = 1;
                int num = n[i];
                while(count <= num) {
                    for (int k=V; k>=count*c[i]; k--)
                        dp[k] = Math.max(dp[k], dp[k-count*c[i]] + count*w[i]);
                    num -= count;
                    count *= 2;

                }
                for (int k=V; k>=num*c[i]; k--)
                    dp[k] = Math.max(dp[k], dp[k-num*c[i]] + num*w[i]);
            }
        }
        return dp[V];
    }
}
