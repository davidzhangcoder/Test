package practise.leetcode.a11_多线程;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_62_120_20210616_Triangle {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(2);

        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);

        List<Integer> c = new ArrayList<>();
        c.add(6);
        c.add(5);
        c.add(7);

        List<Integer> d = new ArrayList<>();
        d.add(4);
        d.add(1);
        d.add(8);
        d.add(3);

        List<List<Integer>> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(c);
        e.add(d);

        System.out.println(minimumTotal(e));
    }
    
    public static int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> a = triangle.get(i-1);
            List<Integer> b = triangle.get(i);
            for (int j = 0; j < b.size(); j++) {
                if (j == 0) {
                    b.set(j, a.get(j) + b.get(j));
                }
                else if (j < b.size() - 1) {
                    b.set(j, Math.min(a.get(j-1)+b.get(j), a.get(j) + b.get(j)));
                }
                else {
                    b.set(j, a.get(j-1) + b.get(j));
                }
            }
        }

        List<Integer> last = triangle.get(triangle.size() - 1);
        int min = last.get(0);
        for (int i = 0; i < last.size(); i++) {
            if( last.get(i) < min )
                min = last.get(i);
        }
        return min;
    }

}
