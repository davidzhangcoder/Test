package leetcode;

import java.util.*;

public class L20201030_743_NetworkDelayTime {

    //status: code done 20201030
    //status: success 20201030

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, HashMap<Integer, Integer>> hashMap = buildMap(times);
        Integer node = Integer.valueOf(K);
        int count = 0;
        Map<Integer,Integer> visited = new HashMap<Integer, Integer>();
        dfs(hashMap, node , count , visited);

        if( visited.keySet().size()==N ) {
            TreeSet<Integer> sortSet = new TreeSet<Integer>((o1, o2) -> o2.compareTo(o1));
            sortSet.addAll(new HashSet(visited.values()));
            return sortSet.first().intValue();
        }
        else
            return -1;
    }

    private Map<Integer, HashMap<Integer,Integer>> buildMap(int[][] times) {
        Map<Integer, HashMap<Integer,Integer>> map = new HashMap<Integer,HashMap<Integer,Integer>>();
        for (int[] time : times) {
            if(time != null) {
                int source = time[0];
                int target = time[1];
                int weight = time[2];
                HashMap<Integer,Integer> innerMap = map.get(Integer.valueOf(source));
                if (innerMap == null) {
                    innerMap = new HashMap<Integer,Integer>();
                    map.put(Integer.valueOf(source),innerMap);
                }
                innerMap.put(Integer.valueOf(target),Integer.valueOf(weight));
            }
        }
        return map;
    }

    private void dfs(Map<Integer, HashMap<Integer,Integer>> map , Integer node , int count, Map<Integer,Integer> visited){
        if( visited.get(node) == null || count < visited.get(node).intValue()){
            visited.put(node, Integer.valueOf(count));
        }
        else {
                return ;
        }
        if (map.get(node) != null && map.get(node).keySet()!=null && map.get(node).keySet().size()>0) {
            for (Integer innerNode : map.get(node).keySet()) {
                Integer weight = map.get(node).get(innerNode);
                dfs(map, innerNode, count+weight.intValue(), visited);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,1,1};
        int[] b = new int[]{2,3,1};
        int[] c = new int[]{3,4,1};

        int[][] d = new int[][]{a,b,c};

        L20201030_743_NetworkDelayTime test = new L20201030_743_NetworkDelayTime();
        test.networkDelayTime(d,3,1);
    }


}
