
import java.util.*

/**
@author David
@date 2020-05-08 - 2:19 a.m.
 */
fun main(args: Array<String>) {
    val rKotlin = RKotlin();
//    val grades = arrayOf<Int>( 73 , 67 , 38 , 33);
//    rKotlin.gradingStudents( grades );

//    var a = Array( 5 , {it});
//    System.out.println(a);
//
//    a = Array( 7 , { v -> v } );
//    System.out.println(a);

//    val n =100000;
//    var astronaut : Array<Array<Int>> = Array( 2 ,  {Array( 2 , {0} )}  );
//    astronaut[0] = arrayOf(1,2);
//    astronaut[1] = arrayOf(3,4);
//    rKotlin.journeyToMoon( n , astronaut );

    var arr : Array<Array<Int>> = arrayOf(
            arrayOf(-1 ,-1 ,0 ,-9 ,-2 ,-2),
            arrayOf(-2 ,-1 ,-6 ,-8 ,-2 ,-5),
            arrayOf(-1 ,-1 ,-1 ,-2 ,-3 ,-4),
            arrayOf(-1 ,-9 ,-2 ,-4 ,-4 ,-5),
            arrayOf(-7 ,-3 ,-3 ,-2 ,-9 ,-9),
            arrayOf(-1 ,-3 ,-1 ,-2 ,-4 ,-5)
    );
    rKotlin.hourglassSum( arr );
}

class RKotlin {

    fun main(args: Array<String>) {
        val grades = arrayOf<Int>( 73 , 67 , 38 , 33);
        gradingStudents( grades );
    }

    fun gradingStudents(grades: Array<Int>): Array<Int> {
        val result = Array( grades.size , {0} );
        for( i: Int in grades.indices ) {
            if( grades[i] > 0 ) {
                val mod = 5 - grades[i] % 5;
                if (grades[i] >= 38 && mod < 3) {
                    grades[i] = grades[i] + mod;
                }
            }
        }
        return grades;
    }

    fun superReducedString(s: String): String {
        val stack = Stack<String>();
        for( i in s.indices ) {
            if( stack.isEmpty() )
                stack.push( s.substring(i, i+1) );
            else {
                val temp:String = s.substring(i, i+1);
                if( temp.equals(stack.peek() ) )
                    stack.pop();
                else
                    stack.push( temp );
            }
        }

        val result = stack.stream().reduce( "",{ a, b  -> a + b });
        if( result == null || result.trim().equals("") )
            return "Empty String";
        else
            return result;
    }

    fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {
        var a1: Int = 0;
        var b1: Int = 0 ;
        for( i in a.indices ) {
            if( a[i] > b[i] )
                a1++;
            else if ( b[i] > a[i] )
                b1++;
        }
        return arrayOf(a1,b1);
    }

    fun simpleArraySum(ar: Array<Int>): Int {
        var sum : Int = 0;

//        for( i in ar.indices ) {
//            sum += ar[i];
//        }

//        for( i in 0 .. ar.size - 1 ) {
//            sum += ar[i];
//        }

//        ar.forEach {
//            sum += it;
//        }

//        val it:Iterator<Int> = ar.iterator();
//        while( it.hasNext() ) {
//            sum += it.next();
//        }

        for( ( i , v ) in ar.withIndex() ) {
            sum += v;
        }

        return sum;
    }

    fun test() : Any? {
        var str :String? = null;

        str?.let { test1( it ) }

        if( str != null ) {
            test1(str);
        }

        return null;
    }

    fun test1( parameter : Any ) {

    }

    fun reverseArray(a: Array<Int>): Array<Int> {
        var b : Int = 0;

        for(  i : Int in 0 .. a.size/2 - 1 ){
            b = a[i];
            a[i] = a[a.size -1 - i];
            a[a.size -1 - i] = b;
        }
        return a;
    }

    class UnionSearch constructor( val n : Int ) {

        var nArray : Array<Int> = Array(n) { it };

        fun merge( e0 : Int , e1 : Int ) {
            val a : Int = find(e0);
            val b : Int = find(e1);

            if( a != b ) {
                for( i in nArray.indices ) {
                    if( nArray[i] == b )
                        nArray[i] = a ;
                }
            }
        }

        fun find( e : Int ) : Int {
            return nArray[e];
        }

    }

    fun journeyToMoon(n: Int, astronaut: Array<Array<Int>>): Long {
        val unionSearch : UnionSearch = UnionSearch( n );
        for( i : Int in astronaut.indices ) {
            val element0 : Int = astronaut[i][0];
            val element1 : Int = astronaut[i][1];
            unionSearch.merge( element0 , element1 );
        }

//        val distinctMap : (Mutable)Map<Any?, Long!>! = unionSearch.nArray.distinct().toMutableList()
//                .stream()
//                .collect( Collectors.groupingBy( { a -> a } , Collectors.counting()) );

        val distinctMap = unionSearch.nArray.toMutableList().groupBy { it }.mapValues{
            a : Map.Entry<Int, List<Int>> -> a.value.count()
        };
//                .mapValues { it.value.count() }

        var result:Long = 0;
//        for( i in distinctMap.values ) {
//            result *= (if (i.toInt() != 1) i.toInt() else 2);
//        }

        val intArray = distinctMap.values.toIntArray();
        for( i in 0 until intArray.size) {
            for( j in i+1 until intArray.size) {
                result += intArray[i] * intArray[j];
            }
            //System.out.println(result);
        }
        
        return result;
    }

    fun getSum( arr: Array<Array<Int>> , i : Int , j : Int ) : Int {
        return (arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                + arr[i + 1][j + 1]
                + arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2]);
    }

    fun hourglassSum(arr: Array<Array<Int>>): Int {
        if( arr == null || arr.size < 3 || arr[0].size < 3 )
            return 0;
        var max = Int.MIN_VALUE;
        for( i:Int in 0 .. arr.size - 3 ) {
            for( j:Int in 0 .. arr[i].size - 3 ) {
                max = Math.max( max , getSum(arr,i,j) );
            }
        }
        return max;
    }

    fun maxScore(a: Array<Int>, m: Int): Int {
        a.sort();
        val remain = a.size%m;

//        for( i:Int = 0 ; i <= a.size - m - remain ; i = i + m ) {
//
//        }

        var sum:Long = 0;
        for( i:Int in 0 .. a.size - m - remain step m ) {
            if( i == a.size - m - remain ) {
                sum += (i/m + 1) * getSubTotal( a , i , m + remain );
            }
            else {
                sum += (i/m + 1) * getSubTotal( a , i , m );
            }
        }
        return (sum % (1000000000+27)).toInt();
    }

    private fun getSubTotal( a: Array<Int> , start:Int , count:Int ) : Long {
        var subTotal:Long = 0;
        for( i:Int in 0 until count) {
            subTotal += a[start+i];
        }
        return subTotal;
    }

    fun dynamicArray(n: Int, queries: Array<Array<Int>>): Array<Int> {
        var nArray = Array( n ) { mutableListOf<Int>() };
        var nArray1 = Array( n) { arrayOf<Int>() };
        var nArray2 = Array( n) { emptyArray<Int>() };

        var result:Array<Int> = emptyArray<Int>();
        for( i in queries) {

        }

        return result;
    }

    private fun doQuery( nArray:Array<MutableList<Int>> , query:Array<Int> , result:Array<Int> ) : Unit {
        var lastAnswer = if ( result == null || result.size == 0 ) 0 else result.last();
        val n = nArray.size;
        if( query.get(0) == 1 ) {
            val index = (query.get(1) + lastAnswer)%n;
            if( nArray.get(index) == null )
                nArray.set(index, mutableListOf<Int>() );
            nArray.get(index).add( query.get(2 ));
        }
        else if( query.get(0) == 2 ) {
            doQuery2(lastAnswer);
        }
    }

    private fun doQuery1() {

    }

    private fun doQuery2( lastAnswer:Int ) : Int {
        return lastAnswer;
    }

    fun cavityMap(grid: Array<String>): Array<String> {
        var aArray = Array<CharArray>(grid.size,{CharArray(grid.size)});
        arrayOfNulls<CharArray>(grid.size)

        for ( i:Int in 0 until grid.size ) {
            aArray.set( i , grid.get(i).toCharArray() );
        }

        for( i:Int in 1 until aArray.size - 1 )
            for( j:Int in 1 until aArray.size - 1 ) {
                if( aArray[i][j].toInt() > aArray[i-1][j].toInt()
                        && aArray[i][j].toInt() > aArray[i][j-1].toInt()
                        && aArray[i][j].toInt() > aArray[i][j+1].toInt()
                        && aArray[i][j].toInt() > aArray[i+1][j].toInt()
                )
                    aArray[i][j] = 'X';
            }

        for( i:Int in 0 until aArray.size ) {
            grid.set(i,aArray.get(i).joinToString(""));
        }

        return grid;
    }


}