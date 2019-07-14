public class RangeCoin {
    public static void main(String[] args) {
        System.out.println(new RangeCoin().range2(5));
    }

    public int range1(int n) {
        int k = 0;
        while(k <= n) {
            n -= k;
            k += 1;
        }
        return k-1;
    }

    public int range2(int n) {
        return (int)(Math.sqrt(8*(long)n+1)-1)/2;
    }
}