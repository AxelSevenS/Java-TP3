package PStudent;

import java.util.HashMap;
import java.util.Map;

public class Student {

    public String firstName = "";
    public String lastName = "";

    public String sex = "";

    public Map<String, Map<String, Double>> grades;

    public Student(String firstName, String lastName, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.grades = new HashMap<>();
    }


    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void display() {
        System.out.printf("%s %s :\n", firstName, lastName);
        displayGrades();
    }
    public void displayGrades() {
        for (Map.Entry<String, Map<String,Double>> schoolSubject : grades.entrySet()) {
            displayGrades(schoolSubject.getKey());
        }
    }
    public void displayGrades(String subject) {
        System.out.printf("- %s :\n", subject);
        for (Map.Entry<String, Double> entry : grades.get(subject).entrySet()) {
            System.out.printf("\t-%s : %.2f\n", entry.getKey(), entry.getValue());
        }

    }


    public int average(String a) {

        int average = 0;
        for (Map<String, Double> subject : grades.values()) {
            int subjectAverage = 0;
            for(Double grade : subject.values()) {
                subjectAverage += grade;
            }
            average += subjectAverage / subject.size();
        }
        return average / grades.size();
    }

    
    public void setGrade(String subject, String name, double grade) {
        setGrade(subject, name, 20, grade);
    }
    public void setGrade(String subject, String name, int max, double grade) {
        Map<String, Double> subjectGrades = grades.get(subject);
        if (subjectGrades == null) {
            subjectGrades = new HashMap<>();
            grades.put(subject, subjectGrades);
        }
        subjectGrades.put(name, grade * max / 20);
    }
}
