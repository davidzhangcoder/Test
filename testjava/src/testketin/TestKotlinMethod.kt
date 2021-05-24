package testketin

fun main(args: Array<String>) {

    doMyMethod { a : String, b : String ->
        var c = a + "1";
        c + b;
    };

    doMyMethod { a , b  ->
        var c = a + "1";
        c + b;
    };

    doMyMethod { a : String, b : String ->
        var c = a + "1";
        return@doMyMethod c + b;
    };

    doMyMethod1 { -> "11111" }

    doMyMethod1 { -> return@doMyMethod1 "22222" }

    doMyMethod1(null)

    val items = listOf(1, 2, 3, 4, 5)
    val product = items.fold(1, Int::times)

}

fun doMyMethod( t : (String , String) -> String ) {
    println(t("a","b"))
}

fun doMyMethod1( t : (() -> String)? ) {
    println( t!!() )

//    html(
//            {       // 带接收者的 lambda 由此开始
//                -> body()   // 调用该接收者对象的一个方法
//            }
//    )
}

interface MyClass{
    fun myMethod( a:String , b:String ) : String;
}

class HTML {
    fun body() { }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // 创建接收者对象
    html.init()        // 将该接收者对象传给该 lambda
    return html
}

