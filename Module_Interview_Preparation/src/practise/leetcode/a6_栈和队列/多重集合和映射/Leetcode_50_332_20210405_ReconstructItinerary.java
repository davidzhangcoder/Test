package practise.leetcode.a6_栈和队列.多重集合和映射;

import java.util.*;

public class Leetcode_50_332_20210405_ReconstructItinerary {

    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            PriorityQueue<String> pq = map.getOrDefault(from, new PriorityQueue<String>());
            pq.add(to);
            map.put(from,pq);
        }

        List<String> result = new ArrayList<String>();
        String from = "JFK";
        result.add(from);
        while(!map.isEmpty()) {
            PriorityQueue<String> to = map.get(from);
            String temp = to.poll();
            result.add(temp);
            if( to.isEmpty() )
                map.remove( from );
            from = temp;
        }

        return result;
    }

    public static void main(String[] args) {
//        [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//        [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]

//        List l1 = new ArrayList();
//        l1.add("MUC");
//        l1.add("LHR");
//        List l2 = new ArrayList();
//        l2.add("JFK");
//        l2.add("MUC");
//        List l3 = new ArrayList();
//        l3.add("SFO");
//        l3.add("SJC");
//        List l4 = new ArrayList();
//        l4.add("LHR");
//        l4.add("SFO");
//
//        List l5 = new ArrayList();
//        l5.add(l1);
//        l5.add(l2);
//        l5.add(l3);
//        l5.add(l4);

//        System.out.println(findItinerary(l5));

        List l1 = new ArrayList();
        l1.add("JFK");
        l1.add("KUL");
        List l2 = new ArrayList();
        l2.add("JFK");
        l2.add("NRT");
        List l3 = new ArrayList();
        l3.add("NRT");
        l3.add("JFK");

        List l5 = new ArrayList();
        l5.add(l1);
        l5.add(l2);
        l5.add(l3);

        System.out.println(findItinerary(l5));

//        Queue temp = new LinkedList<>();
//        temp.offer();
//        temp.poll(); //Retrieves and removes the head of this queue
//        temp.add();
//        temp.remove(); //exception - Retrieves and removes the head of this queue

    }

}
