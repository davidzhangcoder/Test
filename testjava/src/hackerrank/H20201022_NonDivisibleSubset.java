package hackerrank;

public class H20201022_NonDivisibleSubset {
//    public static int nonDivisibleSubset(int k, List<Integer> s) {
//        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<Integer,Integer>();
//        Set<Integer> needToRemove = new HashSet<Integer>();
//        Collections.sort(s);
//        for (Integer integer : s) {
//            linkedHashMap.put(integer,integer);
//        }
//
//        for (Iterator<Map.Entry<Integer, Integer>> it = linkedHashMap.entrySet().iterator(); it.hasNext();) {
//            Map.Entry<Integer, Integer> next = it.next();
//            Integer integer = next.getKey();
//            if(integer.intValue()==0&&linkedHashMap.get(Integer.valueOf(k))!=null){
//                needToRemove.add(Integer.valueOf(0));
//                needToRemove.add(Integer.valueOf(k));
//            }
//            else if(integer.intValue()<k&&linkedHashMap.get(Integer.valueOf(k-integer.intValue()))!=null) {
//                needToRemove.add(Integer.valueOf(k-integer.intValue()));
//                needToRemove.add(Integer.valueOf(integer.intValue()));
//            }
//            else if(integer.intValue()==k&&linkedHashMap.get(Integer.valueOf(0))!=null) {
//                needToRemove.add(Integer.valueOf(0));
//                needToRemove.add(Integer.valueOf(k));
//            }
//            else if(integer.intValue()>k&&linkedHashMap.get(Integer.valueOf(integer.intValue()%k))!=null) {
//                needToRemove.add(Integer.valueOf(integer.intValue()%k));
//                needToRemove.add(Integer.valueOf(integer.intValue()));
//            }
//        }
//        s.removeAll(needToRemove);
//        return s.size();
//    }

    public static void main(String[] args) {
//        List<Integer> integers = new ArrayList(Arrays.asList(1, 7, 2, 4));
//        System.out.println("nonDivisibleSubset(3,integers) = " + nonDivisibleSubset(3, integers));

    }
}
