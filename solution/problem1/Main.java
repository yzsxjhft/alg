package problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        char[][] boards = {{'a', 'a'}};
        int[] S = {1, 2, 3};
        System.out.println(new Solution().subsets(S));

    }

}

class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        ArrayList<Integer> list = new ArrayList();
        dfs(res, list, S, 0);
        Collections.sort(res, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.size() != o2.size()) return o1.size() < o2.size()?-1:1;
                for (int i=0; i<o1.size(); i++) {
                    if (o1.get(i) < o2.get(i)) return -1;
                }
                return 1;
            }
        });
        return res;

    }

    public void dfs(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int[] S, int index) {
        if(index >= S.length) {
            res.add(new ArrayList(list));
            return;
        }
        list.add(S[index]);
        dfs(res, list, S, index+1);
        list.remove(Integer.valueOf(S[index]));
        dfs(res, list, S, index+1);
    }
}