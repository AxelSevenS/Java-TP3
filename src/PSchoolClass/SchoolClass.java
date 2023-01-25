package PSchoolClass;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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

        try {
            Gson gson = new Gson();
            FileWriter file = new FileWriter( path.toString() );
            String json = gson.toJson(this);
            System.out.println(json);
            file.write(json);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param path
     */
    public static SchoolClass loadFromFile(Path path) {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            FileReader file = new FileReader( path.toString() );
            return gson.fromJson(file, SchoolClass.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
