package PSchoolClass;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import PStudent.Student;

public class SchoolClass {

    public String name;
    public HashMap<String, Student> students;



    public SchoolClass(String name) {
        this.name = name;
        this.students = new HashMap<>();
    }


    public void display() {
        System.out.printf("[%s] :\n", name);
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            entry.getValue().display();
        }
    }


    public Student getStudent(String str) {

        return students.get(str);
    }

    public float classAverage(String str) {
        float sum = 0;
        for (Student student : students.values()) {
            sum += student.average(str);
        }
        return sum / students.size();
    }

    public void setStudent(Student student) {
        students.put(student.getName(), student);
    }


    /**
     * @param path
     */
    public void saveToFile(Path path) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("students", students);

        try {
            FileWriter file = new FileWriter(path.toString());
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param path
     */
    public void loadFromFile(Path path) {
        try {
            JSONObject jsonObject = new JSONObject(path.toString());
            name = jsonObject.getString("name");
            students = (HashMap<String, Student>) jsonObject.get("students");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
