import java.util.Stack;

public class MaxRectangleInHistogram {
    public static void main(String[] args) {
        int[] gram = {2, 1, 5, 6, 2, 3};
        System.out.println(new MaxRectangleInHistogram().largest3(gram));
    }

    public int largest1(int[] heights) {
        int num = heights.length;
        int res = 0;
        for (int i=0; i<num; i++) {
            int h = heights[i];
            for (int j=i; j<num; j++) {
                h = Math.min(h, heights[j]);
                res = Math.max(res, (j-i+1)*h);
            }
        }
        return res;
    }

    public int largest2(int[] heights) {
        int len = heights.length;
        int[] leftMin = new int[len];
        int[] rightMin = new int[len];
        for (int i=0; i<len; i++) {
            int j = i-1;
            while (j>=0 && heights[j] >= heights[i]) j--;
            leftMin[i] = j;
            j = i+1;
            while (j<len && heights[j] >= heights[i]) j++;
            rightMin[i] = j;
        }
        int area = 0;
        for (int i=0; i<len; i++) {
            int tmp = (rightMin[i] - leftMin[i] - 1) * heights[i];
            area = Math.max(area, tmp);
        }
        return area;
    }

    public int largest3(int[] heights) {
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        int area = 0;
        while (p < len) {
            if (stack.isEmpty() || heights[p] >= heights[stack.peek()]) {
                stack.push(p++);
            } else {
                int topIndex = stack.pop();
                int leftMin = stack.isEmpty()?-1:stack.peek();
                int rightMin = p;
                area = Math.max(area, (rightMin-leftMin-1)*heights[topIndex]);
            }
        }
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            int leftMin = stack.isEmpty()?-1:stack.peek();
            int rightMin = len;
            area = Math.max(area, (rightMin-leftMin-1)*heights[topIndex]);
        }
        return area;
    }
}
