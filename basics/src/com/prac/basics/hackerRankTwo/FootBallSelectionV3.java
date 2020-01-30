package com.prac.basics.arcesium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

enum ApplicationStatus {
    SELECTED("SELECT"), REJECTED("REJECT");
    String status;

    ApplicationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}

class Application {
    private String applicantsName;
    private BigDecimal applicantsHeight;
    private BigDecimal applicantsBmi;
    private Integer applicantsScore;
    private Integer applicantsDefends;
    private ApplicationStatus status;
    private String selectedAs;

    public String getApplicantsName() {
        return applicantsName;
    }

    public void setApplicantsName(String applicantsName) {
        this.applicantsName = applicantsName;
    }

    public BigDecimal getApplicantsHeight() {
        return applicantsHeight;
    }

    public void setApplicantsHeight(BigDecimal applicantsHeight) {
        this.applicantsHeight = applicantsHeight;
    }

    public BigDecimal getApplicantsBmi() {
        return applicantsBmi;
    }

    public void setApplicantsBmi(BigDecimal applicantsBmi) {
        this.applicantsBmi = applicantsBmi;
    }

    public void setApplicantsScore(Integer applicantsScore) {
        this.applicantsScore = applicantsScore;
    }

    public void setApplicantsDefends(Integer applicantsDefends) {
        this.applicantsDefends = applicantsDefends;
    }

    public int getApplicantsScore() {
        return applicantsScore;
    }

    public void setApplicantsScore(int applicantsScore) {
        this.applicantsScore = applicantsScore;
    }

    public int getApplicantsDefends() {
        return applicantsDefends;
    }

    public void setApplicantsDefends(int applicantsDefends) {
        this.applicantsDefends = applicantsDefends;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getSelectedAs() {
        return selectedAs;
    }

    public void setSelectedAs(String selectedAs) {
        this.selectedAs = selectedAs;
    }
}

class SelectionCriteriaConstants {
    public static final BigDecimal minimumHeight = new BigDecimal(5.8);
    public static final BigDecimal maximumBMI = new BigDecimal(23);
    public static final Integer minGoalsScored = 50;
    public static final Integer minGoalsDefended = 30;
}

class Result2 {

    /*
     * Complete the 'getSelectionStatus' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts 2D_STRING_ARRAY applications as parameter.
     *
     * [[Boateng, 6.1, 22, 24, 45], [Kaka, 6, 22, 1, 1], [Ramos, 6.3, 22, 67, 70]]
     *
     * 3
     *
     */


    private static Application createApplication(List<String> applicationData) {
        Application application = new Application();
        application.setApplicantsName(applicationData.get(0));
        application.setApplicantsHeight(new BigDecimal(applicationData.get(1)));
        application.setApplicantsBmi(new BigDecimal(applicationData.get(2)));
        application.setApplicantsScore(Integer.parseInt(applicationData.get(3)));
        application.setApplicantsDefends(Integer.parseInt(applicationData.get(4)));
        return application;
    }

    private static String derivePlayerType(Application application) {
        Integer goalsScored = application.getApplicantsScore();
        Integer goalsDefended = application.getApplicantsDefends();
        if (goalsScored.compareTo(SelectionCriteriaConstants.minGoalsScored) >= 0 && goalsDefended.compareTo(SelectionCriteriaConstants.minGoalsDefended) >= 0) {
            return "ALLROUNDER";
        } else if (goalsScored.compareTo(SelectionCriteriaConstants.minGoalsScored) >= 0) {
            return "STRIKER";
        } else
            return "DEFENDER";

    }

    public static List<List<String>> getSelectionStatus(List<List<String>> applications) {

        List<Application> applicationList = applications.stream().map(data -> createApplication(data)).collect(toList());

        List<Application> nonQualifyingApplicants = new ArrayList<>();
        List<Application> strikerApplicants = new ArrayList<>();
        List<Application> defenderApplicants = new ArrayList<>();
        List<Application> allRounderApplicants = new ArrayList<>();
        List<Application> processedApplications = new ArrayList<>();

        applicationList.stream().forEach(application -> {
            if (checkIfRejected(application)) {
                processedApplications.add(classifyApplicants(application, ApplicationStatus.REJECTED, Optional.empty()));
            } else {
                switch (derivePlayerType(application)) {
                    case "ALLROUNDER":
                        allRounderApplicants.add(application);
                        break;
                    case "STRIKER":
                        strikerApplicants.add(application);
                        break;
                    case "DEFENDER":
                        defenderApplicants.add(application);
                        break;
                }
            }

        });

        IntStream.range(0, allRounderApplicants.size() / 2).forEach(i -> {
            processedApplications.add(classifyApplicants(allRounderApplicants.get(i), ApplicationStatus.SELECTED, Optional.of("STRIKER")));
            processedApplications.add(classifyApplicants(allRounderApplicants.get(i + 1), ApplicationStatus.SELECTED, Optional.of("DEFENDER")));
        });

        int commonCountOfPlayers = Math.min(strikerApplicants.size(), defenderApplicants.size());
        boolean strikersAreMore = strikerApplicants.size() > defenderApplicants.size();

        IntStream.range(0, commonCountOfPlayers).forEach(i -> {
            processedApplications.add(classifyApplicants(strikerApplicants.get(i), ApplicationStatus.SELECTED, Optional.of("STRIKER")));
            processedApplications.add(classifyApplicants(defenderApplicants.get(i), ApplicationStatus.SELECTED, Optional.of("DEFENDER")));
        });

        if (allRounderApplicants.size() % 2 > 0) {
            if (strikerApplicants.size() > defenderApplicants.size()) {
                processedApplications.add(classifyApplicants(allRounderApplicants.get(allRounderApplicants.size() - 1), ApplicationStatus.SELECTED, Optional.of("DEFENDER")));
                processedApplications.add(classifyApplicants(strikerApplicants.get(strikerApplicants.size() - 1), ApplicationStatus.SELECTED, Optional.of("STRIKER")));
            } else if (strikerApplicants.size() < defenderApplicants.size()) {
                processedApplications.add(classifyApplicants(allRounderApplicants.get(allRounderApplicants.size() - 1), ApplicationStatus.SELECTED, Optional.of("STRIKER")));
                processedApplications.add(classifyApplicants(defenderApplicants.get(defenderApplicants.size() - 1), ApplicationStatus.SELECTED, Optional.of("DEFENDER")));
            }
        }

        if (strikersAreMore) {
            IntStream.range(commonCountOfPlayers, strikerApplicants.size()).forEach(i -> {
                if(!strikerApplicants.get(i).getSelectedAs().equals(Optional.empty())){
                    processedApplications.add(classifyApplicants(strikerApplicants.get(i), ApplicationStatus.REJECTED, Optional.empty()));
                }

            });
        } else {
            IntStream.range(commonCountOfPlayers, defenderApplicants.size()).forEach(i -> {
                if(!defenderApplicants.get(i).getSelectedAs().equals(Optional.empty())){
                    processedApplications.add(classifyApplicants(defenderApplicants.get(i), ApplicationStatus.REJECTED, Optional.empty()));
                }

            });
        }

        processedApplications.sort(Comparator.comparing(Application::getApplicantsName));
        return prepareResults(processedApplications);
    }

    private static List<List<String>> prepareResults(List<Application> finalTeam) {
        List<List<String>> applicationList = new ArrayList<>();
        finalTeam.forEach(application -> {
            List<String> applicationString = new ArrayList<>();
            applicationString.add(application.getApplicantsName());
            applicationString.add(application.getStatus().status);
            applicationString.add(application.getSelectedAs());
            applicationList.add(applicationString);
        });
        return applicationList;
    }

    private static boolean checkIfRejected(Application application) {
        BigDecimal height = application.getApplicantsHeight();
        BigDecimal bmi = application.getApplicantsBmi();
        Integer goalsScored = application.getApplicantsScore();
        Integer goalsDefended = application.getApplicantsDefends();
        return height.compareTo(SelectionCriteriaConstants.minimumHeight) < 0 || bmi.compareTo(SelectionCriteriaConstants.maximumBMI) > 0
                || (goalsScored.compareTo(SelectionCriteriaConstants.minGoalsScored) < 0 && goalsDefended.compareTo(SelectionCriteriaConstants.minGoalsDefended) < 0);
    }

    private static Application classifyApplicants(Application application, ApplicationStatus applicationStatus, Optional<String> playerGroup) {
        application.setStatus(ApplicationStatus.SELECTED);
        application.setSelectedAs(playerGroup.orElse("NA"));
        application.setStatus(applicationStatus);
        return application;
    }
}

public class FootBallSelectionV3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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

        List<List<String>> result = Result2.getSelectionStatus(applications);

        result.stream()
                .map(
                        r -> r.stream()
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    System.out.println("e " + e);
                });

        bufferedReader.close();
    }
}

