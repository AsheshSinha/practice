package com.prac.basics.bnyM;

import java.util.*;

public class MyTests {
    public static void main(String[] args) {
        /*List<Integer> t = new ArrayList<>();
        t.add(4);
        t.add(8);
        t.add(6);
        t.add(22);
        t.add(9);
        t.add(7);
        t.add(50);*/
        countHoles(630);
        System.out.println(countHoles(630));

    }

    public static int countHoles(int number) {
        // Write your code here
        HashMap<Integer, Integer> noOfHolesMap = buildNoOfHolesStaticData();
        int countOfHoles = 0;
        while(number > 0){
            int digit = number % 10;
            countOfHoles = countOfHoles + noOfHolesMap.get(digit);
            number = number / 10;
        }
        return countOfHoles;
    }

    public static HashMap<Integer, Integer> buildNoOfHolesStaticData() {
        HashMap<Integer, Integer> noOfHolesMap = new HashMap<>();
        noOfHolesMap.put(0, 1);
        noOfHolesMap.put(1, 0);
        noOfHolesMap.put(2, 0);
        noOfHolesMap.put(3, 0);
        noOfHolesMap.put(4, 1);
        noOfHolesMap.put(5, 0);
        noOfHolesMap.put(6, 1);
        noOfHolesMap.put(7, 0);
        noOfHolesMap.put(8, 2);
        noOfHolesMap.put(9, 1);
        return noOfHolesMap;
    }


}
