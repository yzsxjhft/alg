import java.util.Arrays;

public class ChargeCoinTwo {
    public static void main(String[] args) {
        int[] coins = {2,4};
        System.out.println(new ChargeCoinTwo().charge2(3, coins));
    }

    public int charge1(int amount, int[] coins) {
        return charge(coins, amount, 0);
    }

    public int charge(int[] coins, int amount, int index) {
        int count = 0;
        for(int i=index; i<coins.length; i++) {
            int coin = coins[i];
            if (amount < coin) continue;
            if (amount == coin) {
                count++;
                continue;
            }
            int n = charge(coins, amount-coin, i);
            count += n;
        }
        return count;
    }

    public int charge2(int amount, int[] coins) {
        if (amount == 0) return 1;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i=coin; i<=amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];

    }
}
