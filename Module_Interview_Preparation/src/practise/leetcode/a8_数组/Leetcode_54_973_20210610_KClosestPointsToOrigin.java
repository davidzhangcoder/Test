package practise.leetcode.a8_数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class Leetcode_54_973_20210610_KClosestPointsToOrigin {

    static class Node {
        int x;
        int y;
        double value;

        public Node(int x, int y, double value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y &&
                    Double.compare(node.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, value);
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        Comparator<Node> c = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.value, o2.value);
            }
        };
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (int i = 0; i < points.length; i++) {
            int i0 = points[i][0];
            int i1 = points[i][1];
            double sqrt = Math.sqrt(i0 * i0 + i1 * i1);
            pq.offer(new Node(i0,i1,sqrt));
        }

        int[][] answer = new int[k][2];
        for (int i = 0; i < k; i++) {
            Node node = pq.remove();
            answer[i][0] = node.x;
            answer[i][1] = node.y;
        }
        return answer;
    }

    public int[][] kClosest1(int[][] points, int k) {
        double[] a = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            int i0 = points[i][0];
            int i1 = points[i][1];
            double sqrt = Math.sqrt(i0 * i0 + i1 * i1);
            a[i]=sqrt;
        }
        Arrays.sort(a);
        for (int i = 0; i < k; i++) {

        }

        return null;
    }

    public int[][] kClosest2(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int i0 = o1[0];
                int i1 = o1[1];
                double sqrt1 = Math.sqrt(i0 * i0 + i1 * i1);
                int j0 = o2[0];
                int j1 = o2[1];
                double sqrt2 = Math.sqrt(j0 * j0 + j1 * j1);
                return Double.compare(sqrt1,sqrt2);
            }
        });
        for (int i = 0; i < points.length; i++) {
            int i0 = points[i][0];
            int i1 = points[i][1];
            double sqrt = Math.sqrt(i0 * i0 + i1 * i1);
            pq.offer(points[i]);
        }

        int[][] answer = new int[k][];
        for (int i = 0; i < k; i++){
           answer[i] = pq.remove();
        }
        return answer;
    }


}
