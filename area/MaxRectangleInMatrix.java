import java.util.Stack;

public class MaxRectangleInMatrix {
    public static void main(String[] args) {
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        
        };
        System.out.println(new MaxRectangleInMatrix().largest(matrix));
    }

    public int largest(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] heights = new int[rows][cols];
        int area = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
               if (matrix[i][j] == '0') heights[i][j] = 0;
               else heights[i][j] = i>0?heights[i-1][j]+1:1;
            }
            area = Math.max(area, largestInHIstogram(heights[i]));
        }
        return area;
    }
    public int largestInHIstogram(int[] heights) {
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
