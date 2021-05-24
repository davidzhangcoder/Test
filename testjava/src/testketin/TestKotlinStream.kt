package testketin

fun main(args: Array<String>) {

    val userList = mutableListOf<User>();
    userList.add( User( "a" , 1 ) );
    userList.add( User( "a" , 1 ) );

    val result0 = userList.map { a -> a.name };

    val result0_1 = userList.map { a -> a };

    val result0_2 = userList.map { a -> a.name }.joinToString { "," };

    val result1 = userList.map { a -> a.name }.joinToString(",");

    val result2 = userList.sumBy { a -> a.age }

    val result3 = userList.groupBy { a -> a.name }

}

