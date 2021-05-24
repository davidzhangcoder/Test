package testketin

fun main(args: Array<String>) {

    val a = arrayOf<Int>()
    a.set(0,1);
    println( a.get(0) );

//    var a = arrayOf( arrayOf(1,2) , arrayOf(3,4) );
//    System.out.println( a );
//
////    var c=Array<IntArray>(3, {arrayOf(1,2)} ); //error

//    //把a当成一维数组进行初始化，初始化a是一个长度为4的数组
//    //a数组的元素又是Array<Int>类型
//    var a = arrayOfNulls<Array<Int>>(4)
//    //把a数组当成一维数组，遍历a数组的每个数组元素
//    for (i in a.indices) {
//        println(a[i])
//    }
//    //初始化a数组的第一个元素
//    a[0]= arrayOf(2,5)
//    //访问a数组的第一个元素所指数组的第二个元素
//    a[0]?.set(1, 6)
//    //a数组的第一个元素是一个一维数组，遍历这个一维数组
//    for (i in a[0]!!.indices) {
//        println(a[0]?.get(i))
//    }

//    //同时初始化二维数组的两个维数
//    var b=Array<IntArray>(3,{IntArray(4,{0})})

//    var arr = arrayOf(arrayOfNulls<String>(3), arrayOf("hello"))
//    System.out.println( arr );

}