class LKotin {

    fun numOfBurgers(tomatoSlices: Int, cheeseSlices: Int): List<Int> {
        val result : MutableList<Int> = mutableListOf();

        if( tomatoSlices == 0 || cheeseSlices == 0 ) {
            result.add( 0 );
            result.add( 0 );
            return result;
        }

        val possibleBurgers : Int = tomatoSlices / 2;

        for( i in 0 .. possibleBurgers ) {
            

        }

        return result;
    }

    fun isBadVersion( n : Int ) : Boolean{
        if( n >= 1 )
            return true;
        return false;
    }

    fun firstBadVersion(n: Int) : Int {
        var s : Int = 1;
        var e : Int = n;
        if(isBadVersion(n)) {
            while( s < e ) {
                if (isBadVersion((s + e) / 2))
                    e = ((s + e) / 2);
                else
                    s = ((s + e) / 2) + 1;
            }
        }
        return s;
    }

    //toCharArray()
    //sort()
    fun numJewelsInStones(j: String, s: String): Int {
        var sArray = s.toCharArray();
        sArray.sort();

        var jArray = j.toCharArray();
        jArray.sort();

        var result = 0;
        for( a in jArray ) {
            result += sArray.count { it == a }
        }
        return result;
    }

    //split
    //?. ?:
    //MutableMap.MutableEntry<String,Int>
    fun mostCommonWord(paragraph: String, banned: Array<String>): String {

        var list = paragraph.split("!","?","'",",",";","." , " ");
        var countMap = mutableMapOf<String,Int>();

        for( i in list ) {
            if( !i.trim().equals("") && !banned.contains( i.toLowerCase() )) {
                if( countMap.get(i.toLowerCase()) == null ) {
                    countMap.put(i.toLowerCase(),0);
                }
                countMap.put(i.toLowerCase(), countMap[i.toLowerCase()]?.plus(1) ?: 0 );
            }
        }

        var max = 0;
        var result: String = "";
        for( i in countMap.entries ) {
            if( i.value > max ) {
                max = i.value;
                result = i.key;
            }
        }

        return result;
    }

    //Map get() would a nullable variable
    fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        var result = mutableListOf<List<String>>( );

        //build up tree
        var root : MutableMap<Char,Node> = mutableMapOf<Char,Node>();
        for( str in products ) {
            var firstchar = str.toCharArray().first();
            if( root.get(firstchar) == null ) {
                root.put(firstchar, Node( firstchar , mutableListOf<Node>() ) );
            }
            var noderef = root.get( firstchar );
            for( index in 1 .. str.toCharArray().size - 1 ) {
                var element = str[index];
                var node = Node( element , mutableListOf() )
                if( !noderef!!.conentList.contains(node) ) {
                    noderef!!.conentList.add( node );
                } else {
                    for( innere in noderef.conentList ) {
                        if( innere.equals( element )) {
                            node = innere;
                            break;
                        }
                    }
                }
                noderef = node;
            }
            noderef!!.conentList.add( Node(' ', mutableListOf() ) )
        }

        for( element in searchWord ) {

        }

        return result;
    }

    //Comparable
    class Node constructor( var value:Char , var conentList : MutableList<Node> ) : Comparable<Node>{
        override fun compareTo(other: Node): Int {
            return this.value.compareTo( other.value )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Node

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

    }

    fun partitionLabels(s: String): List<Int> {
        var result = mutableListOf<Int>();
        var letter = Array(26 , {0} );

        for( element in s ) {
            letter[element.toInt() - 'a'.toInt()]++;
        }

        var zeroDetectorPosition = 0;
        var start : Int = 0 ;
        var end : Int = 0;
        for( element in s ) {
            var index = element.toInt() - 'a'.toInt();
            letter[index]--;
            if( letter[index] == 0 && start == end) {
                start++;
                end = start;
                continue;
            }
            else {
                end++;
            }

            while(letter[zeroDetectorPosition]==0){
                zeroDetectorPosition++;
            }
        }

        return result;
    }

    fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {

        var list = mutableListOf<Array<Int>>();
        var visited = Array(matrix.size,{ Array(matrix[0].size,{0})});
        for( i in 0 until matrix.size ) {
            for( j in 0 until matrix[0].size ) {
                if( matrix[i][j] == 0 ) {
                    var array = arrayOf( i , j );
                    list.add( array );
                    visited[i][j]=1;
                }
            }
        }

        updateMatrixbfs( matrix , list , matrix.size , matrix[0].size ,visited);
        return matrix;
    }

    private var direction = arrayOf( arrayOf(1,0),
            arrayOf(-1,0),
            arrayOf(0,1),
            arrayOf(0,-1) );

    fun updateMatrixbfs( matrix: Array<IntArray> ,list : MutableList<Array<Int>> ,
                         h:Int , w:Int , visited:Array<Array<Int>>) {
        var zerolist = mutableListOf<Array<Int>>();
        for( a in list ) {
            var x = a[0];
            var y = a[1];
            for( b in direction ) {
                var x1 = x + b[0];
                var y1 = y + b[1];
                if( x1 >=0 && x1 < h && y1>=0 && y1 < w ) {
                    if( matrix[x1][y1] != 0 &&
                            (visited[x1][y1] == 0
                            ||
                            (visited[x1][y1] == 1 && matrix[x][y]+1 < matrix[x1][y1])) )
                        matrix[x1][y1] = matrix[x][y]+1;
                    if( visited[x1][y1] != 1 ) {
                        zerolist.add(arrayOf(x1, y1));
                        visited[x1][y1] = 1;
                    }
                }
            }
        }

        if( zerolist.size > 0 )
            updateMatrixbfs(matrix,zerolist,h,w,visited);
    }

}

fun main() {
    val lKotin : LKotin = LKotin();
    lKotin.firstBadVersion(3);
}

