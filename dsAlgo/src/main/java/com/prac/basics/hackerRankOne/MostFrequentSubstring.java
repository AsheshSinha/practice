package com.prac.basics.bnyM;


import java.io.*;
import java.util.*;


class Result {

    /*
     * Complete the 'getMaxOccurrences' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER minLength
     *  3. INTEGER maxLength
     *  4. INTEGER maxUnique
     */

    public static int getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
        int len = s.length();
        Map<String, Integer> strCountMap = new HashMap<>();

        for(int i = minLength; i <= maxLength; i++){
            for(int j = 0; j <= len - i; j++){
                String subString = s.substring(j, j+i);
                if(verifyForMaxUnique(maxUnique,subString)){
                    if(strCountMap.containsKey(subString)){
                        strCountMap.put(subString, strCountMap.get(subString) + 1);
                    }else{
                        strCountMap.put(subString, 1);
                    }
                }
            }
        }
        Iterator<String> strCountMapIter = strCountMap.keySet().iterator();
        int maxOccuranceCount = 0;
        while(strCountMapIter.hasNext()){
            String str = strCountMapIter.next();
            int strOccuranceCount =  strCountMap.get(str);
            if(strOccuranceCount > maxOccuranceCount)
                maxOccuranceCount= strOccuranceCount;
        }
        return  maxOccuranceCount;
    }

    private static boolean verifyForMaxUnique(int maxUnique,String subString){
        char[] charArr = subString.toCharArray();
        HashSet<Character> charSet = new HashSet<>();
        for(int i = 0; i< charArr.length; i++){
            charSet.add(charArr[i]);
        }
        if(charSet.size() <= maxUnique)
            return true;
        else
            return false;
    }


}

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        int minLength = Integer.parseInt(bufferedReader.readLine().trim());

        int maxLength = Integer.parseInt(bufferedReader.readLine().trim());

        int maxUnique = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.getMaxOccurrences(s, minLength, maxLength, maxUnique);
        System.out.println(result);

        bufferedReader.close();
    }
}

