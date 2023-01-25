import PSchoolClass.SchoolClass;
import PStudent.Student;

import java.nio.file.Path;

public class TP3_1 {


    public static void main(String[] args) {
        SchoolClass schoolClass = new SchoolClass("B2 Info");

        Student axel = new Student("Axel", "Sevenet", "M");
        schoolClass.setStudent(axel);

        axel.setGrade("Java", "TP1", 15.5);
        axel.setGrade("Java", "TP2", 17.5);
        axel.setGrade("Java", "TP3", 18.5);

        Student guillaume = new Student("Guillaume", "Pham", "M");
        schoolClass.setStudent(guillaume);

        guillaume.setGrade("Java", "TP1", 13);
        guillaume.setGrade("Java", "TP2", 12.5);
        guillaume.setGrade("Java", "TP3", 18.5);

        schoolClass.saveToFile(Path.of("test.json"));

        SchoolClass class2 = SchoolClass.loadFromFile(Path.of("test.json"));
        class2.name = "B3 Info";


        schoolClass.display();
        class2.display();
    }
}
