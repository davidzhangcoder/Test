package testketin

fun main(args: Array<String>) {
//    var demo1 = testketin.Demo1()
//    demo1.testFun("content")
//
//    demo1.main( arrayOf() );

    //同时初始化二维数组的两个维数
    var b=Array<IntArray>(3,{IntArray(4,{0})})

//    var c=Array<IntArray>(3, {arrayOf(1,2)} ); //error

//    var arr = arrayOf(arrayOfNulls<String>(3), arrayOf("hello"))
//    System.out.println( arr );

    //20200526
    var s:String = "aaa"
    println(s[1]);

    //20200527
    var a = charArrayOf('A','B');
    var a1 : Array<Char> = Array(4) {'A'};
//    a1 = a.toTypedArray();
    a = a1.toCharArray();
    println( a )

    val c : Char = 'A';
    c.isLetter()
    c.isDigit()
}

class Demo1 {
    var name: String = ""

    fun testFun(name: String) {
        this.name = name
        println(this.name)

        if( 1.toLong() == 1L ) {

        }

        val a : Int = 1;
        val b : Long = 2;
        if( a.toLong() == b ) {

        }
    }

    fun main(args: Array<String>) {

        val array: Array<Int> = Array( 2 , {it -> it.inc()} );
        val array0 : Array<Int> = arrayOf( 1 , 2 , 3 , 4 ,5 )
        val array1 : Array<Int?> = arrayOfNulls<Int>(1);
        val array2 = intArrayOf( 2 );

//        for( array.inde) {
//
//        }

        //List

        val list : List<Int> = mutableListOf<Int>( 1 );
//        list.add

        val list1 : MutableList<Int> = mutableListOf<Int>();
        list1.add( 1 );

        val list2 : List<Int> = listOf<Int>();
//        list2.add

        val list3 : List<Int> = arrayListOf();
//        list3.add

        val list4 : ArrayList<Int> = arrayListOf();
        list4.add( 1 );

        System.out.println("1. Iterator");
        val it : Iterator<Int> = list.iterator();
        while( it.hasNext() ) {
            val value : Int = it.next();
            System.out.println( value )
        }

        System.out.println("2. indices");
        val i:Int  = 7;
        for( i in list.indices ) {
            System.out.println( list.get(i) );
        }

        System.out.println("3. For Each");
        list.forEach {
            System.out.println( it )
        }

        System.out.println("4. withIndex");
        for( ( index , value ) in list1.withIndex() ) {
            System.out.println( "$index" + " : " + "$value" );
        }

        System.out.println("5. For Loop");
        for( i in 0 .. list1.size - 1 ) {
            System.out.println( list1.get( i ) )
        }

//        System.out.println("6. For Loop in");
//        for( value in list ) {
//            System.out.println( list1.get( value ) )
//        }

        //Array

        System.out.println("1. Iterator - Array");
        val it1 : Iterator<Int> = array0.iterator();
        while( it1.hasNext() ) {
            val value : Int  = it1.next();
            System.out.println( value )
        }

        System.out.println("2. indices - Array");
        for( i in array0.indices ) {
            System.out.println( array0[i] );
        }

        System.out.println("3. For Each - Array");
        array0.forEach {
            System.out.println( it )
        }

        System.out.println("4. withIndex - Array");
        for( ( index , value ) in array0.withIndex() ) {
            System.out.println( "$index" + " : " + "$value" );
        }

        System.out.println("5. For Loop - Array");
        for( i in 0 .. array0.size - 1 ) {
            System.out.println( array0[i] )
        }

        System.out.println("6. For Loop in - Array");
        for( value in array0 ) {
            System.out.println( value )
        }

        //Map
        val map : Map<Int,Int> = HashMap<Int,Int>();
//        map.put();//error - no put
        val map1 = map.toMutableMap();
        map1.put(1,1);

        //String
        val a = """
            hello
            worls
            'yes'
            "yes"
            /ok/
        """.trimIndent()
        print(a);

        //Class
        val name = Person().address?.name;

    }

    class Person{
        val address : Address = Address();
        class Address {
            val name:String? = "";
        }
    }


}