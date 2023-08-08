import java.io.File;
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1234567;
    private int studentId;
    private String name;
    private int age;
    private String gender;
    private String address;
    private double mediumScore;

    public Student() {
    }

    public Student(int studentId, String name, int age, String gender, String address, double mediumScore) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.mediumScore = mediumScore;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(double mediumScore) {
        this.mediumScore = mediumScore;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-20d %-20s %-20s %-20f", studentId, name, age,gender,address,mediumScore) ;
    }
}
