package leetcode;

import java.util.Collections;
import java.util.List;

public class L20201022_539_MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size()-1; i++) {
            String s1 = timePoints.get(i);
            String s2 = timePoints.get(i+1);

            String[] s1Split = s1.split(":");
            String[] s2Split = s2.split(":");

            min = Math.min(min, getMinutes(s1Split,s2Split));
        }
        min = Math.min(min, getMinutes(timePoints.get(0).split(":"),timePoints.get(timePoints.size()-1).split(":")));
        return min;
    }

    private int getMinutes(String[] s1Split , String[] s2Split) {
        if(s1Split[0].equals(s2Split[0]) && s1Split[1].equals(s2Split[1]))
            return 0;
        if (Integer.parseInt(s1Split[0])==0) {
            if((24-Integer.parseInt(s2Split[0]))<(Integer.parseInt(s2Split[0]) - Integer.parseInt(s1Split[0]))){
                s1Split[0]="24";
                String[] temp = s1Split;
                s1Split = s2Split;
                s2Split = temp;
            }
        }

        int hourDiffInminutes = 0;
        if( (Integer.parseInt(s2Split[0]) - Integer.parseInt(s1Split[0])) > 1 ){
            hourDiffInminutes = (Integer.parseInt(s2Split[0]) - Integer.parseInt(s1Split[0])) * 60;
        }
        int minutesDiff = 0;
        if( (Integer.parseInt(s2Split[0]) - Integer.parseInt(s1Split[0])) == 0)
            minutesDiff = Integer.parseInt(s1Split[1]) - Integer.parseInt(s2Split[1]);
        else
            minutesDiff = 60 - Integer.parseInt(s1Split[1]) + Integer.parseInt(s2Split[1]);

//        int hourDiffInminutes = (Integer.parseInt(s2Split[0]) - Integer.parseInt(s1Split[0])) * 60;
//        int minutesDiff = 0;
//        if (hourDiffInminutes == 0)
//            minutesDiff = Integer.parseInt(s1Split[1]) - Integer.parseInt(s2Split[1]);
//        else
//            minutesDiff = 60 - Integer.parseInt(s1Split[1]) + Integer.parseInt(s2Split[1]);



        return hourDiffInminutes + minutesDiff;
    }

    public static void main(String[] args) {
//        System.out.println(1);
//        System.out.println(Integer.parseInt("00"));
//        System.out.println(Integer.parseInt("01"));
    }
}
