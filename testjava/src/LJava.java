import java.util.*;

public class LJava {

    public static void main(String[] args) {

    }

    public int maxPower(String s) {
        char[] chars = s.toCharArray();
        char v = chars[0];
        int max = 1;
        int start = 0;
        for (int i = 1; i < chars.length; i++) {
            if( chars[i] != v ) {
                v=chars[i];
                if( i - start > max)
                    max = i - start;
                start = i;
            }
            else if( i == chars.length -1 ){
                if( i - start + 1> max)
                    max = i - start+1;
            }
        }
        return max;
    }


    public int[] sortArray(int[] nums)
    {
        return quicksearch(nums, 0 , nums.length-1);
    }

    public int[] quicksearch(int[] nums , int l , int r) {
        int v = nums[l];

        int x = l+1;
        int y = r;
        while( x < y) {
            if( nums[x] > v && nums[y] < v) {
                nums[y] = nums[y] + nums[x];
                nums[x] = nums[y] - nums[x];
                nums[y] = nums[y] - nums[x];
            }
            if (nums[x] < v)
                x++;

            if (nums[y] > v)
                y--;
        }

        if( l < x )
            quicksearch(nums, l , x-1);
        if( r > x)
            quicksearch(nums, x+1 , r);

        return nums;
    }


    public int[] sortArray1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if(nums[j]<nums[i]) {
                    nums[j] = nums[j] + nums[i];
                    nums[i] = nums[j] - nums[i];
                    nums[j] = nums[j] - nums[i];
                }
            }
        }
        return nums;
    }

    public boolean canArrange(int[] arr, int k) {

        for (int i = 0; i < arr.length; i++) {
            if( Math.abs(arr[i]) > k )
                arr[i] = arr[i]%k;
        }

        Arrays.sort(arr);

        int i = 0;
        int j = arr.length-1;
        while( i < j ) {
            if(arr[i]+arr[j] != k && arr[i]+arr[j]!=0)
                return false;
            i++;
            j--;
        }

        return true;
    }


    public String[] reorderLogFiles(String[] logs) {
        List<String> letterList = new LinkedList<String>();
        List<String> digList = new LinkedList<String>();

        for( String element : logs ) {
            if( isNumber( element.split(" ")[1] ) )
                digList.add( element );
            else letterList.add( element );
        }

        letterList.sort( comparator );
        letterList.addAll(digList);

        return letterList.toArray(new String[letterList.size()]);
    }

    private Comparator comparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            String sr1 = s1.substring(s1.indexOf(" ")+1);
            String sr2 = s2.substring(s2.indexOf(" ")+1);

            int result = sr1.compareTo( sr2 );
            if( result != 0 )
                return result;
            else {
                String sp1 = s1.substring(0,s1.indexOf(" "));
                String sp2 = s2.substring(0,s2.indexOf(" "));
                return sp1.compareTo(sp2);
            }
        }
    };

    private boolean isNumber(String a) {
        boolean result = true;
        try {
            Integer.parseInt(a.substring(0,1));
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public int orangesRotting(int[][] grid) {
        int count = 0;
        List<int[]> rottingList = new ArrayList<int[]>();
        for( int i = 0 ; i < grid.length ; i++ )
            for( int j = 0 ; j < grid[0].length ; j++ ) {
                if( grid[i][j] == 2 ) {
                    int[] rotting = getPosition(i,j);
                    rottingList.add(rotting);
                }
            }

        count = finding(grid,rottingList,0);

        for( int i = 0 ; i < grid.length ; i++ )
            for( int j = 0 ; j < grid[0].length ; j++ ) {
                if( grid[i][j] == 1 )
                    return -1;
            }

        return count;
    }

    private int[] getPosition(int i, int j){
        int[] rotting = new int[2];
        rotting[0]=i;
        rotting[1]=j;
        return rotting;
    }

    private int finding( int[][] grid , List<int[]> rottingList , int count) {
        List<int[]> newRottinglist = new ArrayList<int[]>();
        for( int[] rotting : rottingList ) {
            int i = rotting[0];
            int j = rotting[1];
            grid[i][j] = 0;
            if (i >= 1 && grid[i - 1][j] == 1 ) {
                int[] newrotting = getPosition(i - 1, j);
                newRottinglist.add(newrotting);
                grid[i - 1][j]=0;
            }
            if (j >= 1 && grid[i][j - 1] == 1 ) {
                int[] newrotting = getPosition(i, j - 1);
                newRottinglist.add(newrotting);
                grid[i][j - 1]=0;
            }
            if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                int[] newrotting = getPosition(i, j + 1);
                newRottinglist.add(newrotting);
                grid[i][j + 1]=0;
            }
            if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                int[] newrotting = getPosition(i + 1, j);
                newRottinglist.add(newrotting);
                grid[i + 1][j]=0;
            }
        }

        if( newRottinglist.size() > 0 )
            count = finding(grid, newRottinglist , ++count);

        return count;
    }

}
