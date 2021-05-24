import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FirstJava {
    public static void main(String[] args) {
        System.out.println("test");

        String enter = "ENTER aaa 3.75 1";
        String served = "SERVED";
        List<String> stringList = new ArrayList<>();
        stringList.add( enter );
        stringList.add( served );
        Priorities priorities = new Priorities();
        priorities.getStudents( stringList );

        int[] arrays1 = { 100 ,100 ,50 ,40 ,40 ,20 ,10 };
        Arrays.stream( arrays1 ).map( intelement -> new Integer( intelement) ).boxed().collect( Collectors.toList() );

        Set set1 = new LinkedHashSet<>();
        for(int a : arrays1) {
            set1.add( a );
        }
        System.out.println( set1 );

        String[] arr = {"11oneone","22twotwo"};
        Set set = new HashSet<>(Arrays.asList(arr));
        Set set2 = new HashSet<>(Arrays.asList(arrays1));
        System.out.println("set.toString()--->"+set.toString());

        appendAndDelete("hackerhappy","hackerrank",9);

        Function a = new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        };
        method( a );

        method( (Object a1) -> a1.toString() );
        method( a1 -> a1.toString() );

        List<String> list = new ArrayList<String>();
        list.stream().map( a1 -> a1.toString() ).collect( Collectors.toList() );
        list.stream().map( (String a1) -> a1.toString() ).collect( Collectors.toList() );
        list.stream().map( (String a1) -> {return a1.toString();} ).collect( Collectors.toList() );

        isValid("abcdefghhgfedecba" );

        int[][] arr1 = new int[][]{
                { -1 ,-1 ,0 ,-9 ,-2 ,-2 },
                {-2 ,-1 ,-6 ,-8 ,-2 ,-5},
                {-1 ,-1 ,-1 ,-2 ,-3 ,-4},
                {-1 ,-9 ,-2 ,-4 ,-4 ,-5},
                {-7 ,-3 ,-3 ,-2 ,-9 ,-9},
                {-1 ,-3 ,-1 ,-2 ,-4 ,-5}
        };

        hourglassSum( arr1 );

    }

    static void method( Function a ) {

    }

    static int solveMeFirst(int a, int b) {
        // Hint: Type return a+b; below
        if( b <= Integer.MAX_VALUE - a )
            return a + b;
        else
            return 0;
    }

    static int sockMerchant(int n, int[] ar) {
        if( ar == null || ar.length == 1)
            return 0;

        int result = 0;
        int count = 1;
        Arrays.sort( ar );
        for( int i = 1 ; i < ar.length ; i++ ) {
            if( ar[i] != ar[i-1] ){
                result = result + count/2;
                count = 1;
            }
            else {
                count++;
            }
        }
        return result + count/2;
    }

    static void javaDeqeue(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        int max = 0;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();

            deque.addLast( num );
            if( map.get(num) == null )
                map.put( num , 0 );
            map.put( num , map.get(num)+1);
            if( i >= m -1 ) {
                int size = map.size();
                if ( size > max)
                    max = size;
                int first = (int) deque.removeFirst();
                if( map.get(first) > 1 )
                    map.put( first , map.get(first) - 1);
                else
                    map.remove(first);
            }
        }

    }

    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        if( alice == null || alice.length == 0 )
            return new int[0];

        int[] result = new int[alice.length];
        if( scores == null || scores.length == 0 ) {
            for(int i = 0 ; i < alice.length ; i++ )
                result[i] = 1;
            return result;
        }

        Set<Integer> set1 = new LinkedHashSet();
        for( int i = scores.length - 1 ; i>=0 ; i-- )
            set1.add( scores[i] );

        int rank = set1.size();
        List<Integer> list1 = new ArrayList<>(set1);
        int j = 0;
        int i = 0;
        while( i < list1.size() && j < alice.length ) {
            int a = list1.get(i).intValue();
            int aliceScore = alice[j];
            if( aliceScore <= a ) {
                if(aliceScore < a)
                    result[j] = rank + 1;
                else
                    result[j]= rank;
                if(j+1 < alice.length && alice[j+1]!=aliceScore && alice[j+1]> a) {
                    rank--;
                    i++;
                }
                j++;
            } else {
                rank--;
                i++;
            }
        }
        for( ; j < result.length ; j++ )
            result[j] = 1;
        return result;
    }

    static void extraLongFactorials(int n) {
        BigInteger b = new BigInteger( n + "" );
        n--;
        while( n >= 1) {
            b = b.multiply( new BigInteger( n + "" ));
            n--;
        }
        System.out.println(b.toString());
    }

    static String appendAndDelete(String s, String t, int k) {
        int i = s.length()>t.length()?s.length():t.length();

        int j = 0;
        for( j = 0 ; j < i ; j++) {
            if( j < t.length() && j < s.length() ) {
                if( s.charAt(j) != t.charAt(j) ) {
                    break;
                }
            }
            else
                break;
        }

        if( k >= s.length() + t.length() )
            return "Yes";
        else if( k == (s.length() - j) + (t.length() - j) )
            return "Yes";
        else if ( k > (s.length() - j) + (t.length() - j) && ( k - (s.length() - j) - (t.length() - j) ) %2 == 0  )
            return "Yes";
        else
            return "No";
    }

    static String isValid(String s) {
        int[] a = new int[26];

        for( int a1 : a)
            a1 = 0;

        for( int i = 0 ; i < s.length() ; i++ ) {
            char b = s.charAt(i);
            a[ b - 'a']++;
        }

        List<Integer> list = (List<Integer>) Arrays.stream( a ).filter( afilter -> afilter != 0 ).map(aint -> new Integer(aint) ).boxed().collect( Collectors.toList() );
        Map<Integer,Long> map = list.stream().collect( Collectors.groupingBy( Function.identity() , Collectors.counting() ) );
//        Map<Integer,Long> map1 = list.stream().collect( Collectors.groupingBy( atest -> atest , Collectors.counting() ) );

        if( map.size() == 1 )
            return "YES";
        else if( map.size() == 2 ) {

            Set<Map.Entry<Integer,Long>> entrys = map.entrySet();
            boolean found = false;
            int x1 = 0;
            int x2 = 0;
            for( Map.Entry<Integer,Long> entry : entrys){
                if( entry.getValue().longValue() == 1 ) {
                    found = true;
                    x1 = entry.getKey();
                }
                else {
                    x2 = entry.getKey();
                }
            }

            if( found && ( x1 - 1 == x2 || x1 -1 == 0 ) ){
                return "YES";
            }
            else
                return "NO";

        }
        else
            return "NO";
    }

    static class Student {
        private int id;
        private String name;
        private float cgpa;

        public int getId() {
            return id;
        }

        public Student(int id, String name, float cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getCgpa() {
            return cgpa;
        }

        public void setCgpa(float cgpa) {
            this.cgpa = cgpa;
        }
    }

    static class Priorities {
        private Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if( o1.getCgpa() > o2.getCgpa() )
                    return 1;
                else if ( o1.getName().compareTo( o2.getName() ) >= 1 )
                    return 1;
                else if( o1.getId() > o2.getId() )
                    return 1;
                return -1;
            }
        };

        private PriorityQueue<Student> pq = new PriorityQueue<Student>(comparator);

        enum EVENT {
        ENTER,
        SERVED
        };

        public List<Student> getStudents(List<String> events) {
            List<Student> studentList = new ArrayList<Student>();
            for( String event : events ) {
                if( event.indexOf( EVENT.ENTER.name() ) != -1 ){
                    String[] values = event.split(" ");
                    String name = values[1];
                    float cgpa = Float.parseFloat( values[2] );
                    int id = Integer.parseInt( values[3] );
                    Student student = new Student( id , name , cgpa );
                    pq.add( student );
                } else if( event.indexOf( EVENT.SERVED.name() ) != -1 ) {
                    pq.poll();
                }
            }

            while( !pq.isEmpty() ) {
                studentList.add( pq.remove() );
            }

            return studentList;
        }

    }

    class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    class Checker implements Comparator<Player> {
        // complete this method
        public int compare(Player a, Player b) {
            if( a != null && b == null )
                return -1;
            else if( a == null && b != null )
                return 1;
            else if( a == null && b == null )
                return 0;
            else {
                if( a.score > b.score )
                    return -1;
                else if( a.score < b.score )
                    return 1;
                else {
                    return (a.name==null?"":a.name).compareToIgnoreCase( b.name );
                }
            }
        }
    }


    static private int getSum(int[][] arr , int i , int j) {
        return arr[i][j] + arr[i][j+1] + arr[i][j+2]
                +arr[i+1][j+1]
                +arr[i+1][j+1]
                + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
    }

    static int hourglassSum(int[][] arr) {
        if( arr == null || arr.length < 3 || arr[0].length < 3 )
            return 0;
        int max = Integer.MIN_VALUE;
        for( int i = 0 ; i <= arr.length - 3 ; i++) {
            for( int j = 0 ; j <= arr[i].length - 3 ; j++) {
                max = Math.max( max, getSum( arr , i , j ) );
            }
        }
        return max;
    }







}
