package com.prac.basics.arcesium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'getSelectionStatus' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts 2D_STRING_ARRAY applications as parameter.
     */
    public static final BigDecimal minimumHeight = new BigDecimal(5.8);
    public static final BigDecimal maximumBMI = new BigDecimal(23);
    public static final Integer minGoalsScored = 50;
    public static final Integer minGoalsDefended = 30;

    public static List<List<String>> getSelectionStatus(List<List<String>> applications) {
        int totalApplications = applications.size();
        int strikerCount = 0;
        int defenderCount = 0;
        int j = 0;
        boolean[] visited = new boolean[totalApplications];

        List<List<String>> result = new ArrayList<>();
        List<String> strikerApplication = null;
        List<String> defenderApplication = null;
        for (int i = 0; i < totalApplications; i++) {
            List<String> application = applications.get(i);
            j = i;
            if (checkIfRejected(application)) {
                result.add(selectOrRejectApplicant(application, "REJECT", "NA"));
                visited[i] = true;
                continue;
            }
            if (isStriker(application.get(3))) {
                strikerCount++;
                strikerApplication = application;
                visited[i] = true;
            } else if (isDefender(application.get(4))) {
                defenderCount++;
                defenderApplication = application;
                visited[i] = true;
            }
            while (strikerCount <= defenderCount && !visited[j] && j < totalApplications - 1) {
                j++;
                application = applications.get(j);
                if (checkIfRejected(application)) {
                    result.add(selectOrRejectApplicant(application, "REJECT", "NA"));
                    visited[i] = true;
                    continue;
                }
                if (isStriker(application.get(3))) {
                    strikerCount++;
                    strikerApplication = application;
                    visited[j] = true;
                }
            }
            while (strikerCount > defenderCount && !visited[j] && j < totalApplications - 1) {
                j++;
                application = applications.get(j);
                if (checkIfRejected(application)) {
                    result.add(selectOrRejectApplicant(application, "REJECT", "NA"));
                    visited[i] = true;
                    continue;
                }
                if (isDefender(application.get(4))) {
                    defenderCount++;
                    defenderApplication = application;
                    visited[j] = true;
                }
            }
            if (strikerCount == defenderCount && strikerCount > 0) {
                result.add(selectOrRejectApplicant(strikerApplication, "SELECT", "STRIKER"));
                result.add(selectOrRejectApplicant(defenderApplication, "SELECT", "DEFENDER"));
            }
        }
        if (strikerCount != defenderCount) {
            IntStream.range(strikerCount + defenderCount, totalApplications).forEach(i -> {
                result.add(selectOrRejectApplicant(applications.get(i), "REJECT", "NA"));
            });
        }
        result.sort(Comparator.comparing((List<String> a) -> a.get(0)));
        return result;
    }

    private static List<String> selectOrRejectApplicant(List<String> originalApplication,
                                                        String status, String playerGroup) {
        List<String> processedApplication = new ArrayList<>();
        processedApplication.add(originalApplication.get(0));
        processedApplication.add(status);
        processedApplication.add(playerGroup);
        return processedApplication;
    }

    private static boolean checkIfRejected(List<String> application) {
        BigDecimal height = new BigDecimal(application.get(1));
        BigDecimal bmi = new BigDecimal(application.get(2));
        Integer goalsScored = Integer.parseInt(application.get(3));
        Integer goalsDefended = Integer.parseInt(application.get(4));
        return height.compareTo(minimumHeight) < 0 || bmi.compareTo(maximumBMI) > 0
                || (goalsScored.compareTo(minGoalsScored) < 0 && goalsDefended.compareTo(minGoalsDefended) < 0);
    }

    private static boolean isStriker(String goalsScored) {
        Integer goals = Integer.parseInt(goalsScored);
        return goals.compareTo(minGoalsScored) >= 0;
    }

    private static boolean isDefender(String goalsDefended) {
        Integer goals = Integer.parseInt(goalsDefended);
        return goals.compareTo(minGoalsDefended) >= 0;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int applicationsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int applicationsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> applications = new ArrayList<>();

        IntStream.range(0, applicationsRows).forEach(i -> {
            try {
                applications.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<String>> result = Result.getSelectionStatus(applications);

        result.stream()
                .map(
                        r -> r.stream()
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    //try {
                    System.out.println(e);
                   /* } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }*/
                });

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
