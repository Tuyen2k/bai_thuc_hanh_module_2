
import io.FileIO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentManager implements IStudentService {
    private List<Student> students = new ArrayList<>();;
    private final Scanner scanner;
    private Pattern pattern;
    private boolean flag;
    private final FileIO<Student> file = new FileIO<Student>();
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Bai_Thuc_Hanh_Module_2\\src\\data\\student.csv";

    public StudentManager() {
        scanner = new Scanner(System.in);
        readFromFile();
    }

    @Override
    public void display() {
        if (!students.isEmpty()) {
            System.out.printf("%-10S %-20s %-20s %-20s %-20s %-20s\n",
                    "Student Id", "Name", "Age", "Gender", "Address", "Medium Score");
            for (Student student : students) {
                System.out.println(student);
            }
        } else {
            System.out.println("No student!!!");
        }
    }

    private void getStudents() {
        students.add(new Student(320324, "Nguyễn Đức Tuyên", 23, "Male", "Hà Nội", 7.5));
        students.add(new Student(320424, "Nguyễn Ngọc Anh", 20, "Female", "Hà Nội", 8));
        students.add(new Student(321254, "Nguyễn Đức Mạnh", 21, "Male", "Hà Nội", 8.5));
        students.add(new Student(320158, "Nguyễn Văn Hoan", 22, "Other", "Hà Nội", 9));
        students.add(new Student(320678, "Nguyễn Văn Đức", 23, "Other", "Hà Nội", 7.5));
    }

    private String inputStudentCode() {
        pattern = Pattern.compile("^\\d{6}$");
        System.out.println("Input student code: ");
        return getString();
    }

    private String inputName() {
        pattern = Pattern.compile("^.$");
        System.out.println("Input student name: ");
        return getString();
    }

    private String inputAge() {
        pattern = Pattern.compile("^\\d{2}$");
        System.out.println("Input student age: ");
        return getString();
    }

    private String inputAddress() {
        pattern = Pattern.compile("^.$");
        System.out.println("Input student address: ");
        return getString();
    }

    private String inputMediumScore() {
        pattern = Pattern.compile("^\\d{1,2}(\\.\\d{1,2})?$");
        System.out.println("Input student medium score: ");
        return getString();
    }

    private String getString() {
        flag = true;
        int count = 0;
        String str = scanner.nextLine();
        while (count < 3 && flag) {
            try {
                if (pattern.matcher(str).matches()) {
                    flag = false;
                } else {
                    count++;
                    System.out.println("You entered the wrong " + count + " times");
                    str = scanner.nextLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    private String inputGender() {
        System.out.println("choose the student's gender:");
        String gender = "";
        String choice = "";
        System.out.printf("%-20d %-20d %-20d\n", 1, 2, 3);
        System.out.printf("%-20s %-20s %-20s\n", "Male", "Female", "Other");
        pattern = Pattern.compile("^(123)&");
        if (pattern.matcher(choice).matches()) {
            switch (Integer.parseInt(choice)) {
                case 1:
                    gender = "Male";
                    break;
                case 2:
                    gender = "Female";
                    break;
                case 3:
                    gender = "Other";
                    break;
            }
        } else {
            System.out.println("Please choose the correct one!!!");
        }
        return gender;
    }

    private boolean checkStudentId(int studentCore) {
        flag = true;
        for (Student student : students) {
            if (studentCore == student.getStudentId()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public void create() {
        int studentCore = Integer.parseInt(inputStudentCode());
        String name = inputName();
        int age = Integer.parseInt(inputAge());
        String gender = inputGender();
        String address = inputAddress();
        double mediumScore = Double.parseDouble(inputMediumScore());
        if (checkStudentId(studentCore)) {
            if (!name.isEmpty()) {
                if (18 <= age && age <= 25) {
                    if (!gender.isEmpty()) {
                        if (0 <= mediumScore && mediumScore <= 10) {
                            students.add(new Student(studentCore, name, age, gender, address, mediumScore));
                            System.out.println("Add success!!!");
                        } else {
                            System.out.println("Invalid score");
                        }
                    } else {
                        System.out.println("Gender cannot be empty!!!");
                    }
                } else {
                    System.out.println("Not of the right age!!!");
                }
            } else {
                System.out.println("Name cannot be empty!!!");
            }
        } else {
            System.out.println("Student ID already exists!!!");
        }
        writeToFile();
    }

    public Student findByStudentId() {
        int studentId = Integer.parseInt(inputStudentCode());
        if (!students.isEmpty()) {
            for (Student student : students) {
                if (studentId == student.getStudentId()) {
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public void delete() {
        Student student = findByStudentId();
        if (student != null) {
            System.out.println(student);
            String choice = "";
            System.out.println("1-Y / 2-N");
            pattern = Pattern.compile("^(12)&");
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    students.remove(student);
                }
            } else {
                System.out.println("Please choose the correct one!!!");
            }
        } else {
            System.out.println("The student with the above student code could not be found!!!");
        }
        writeToFile();
    }

    @Override
    public void update() {
        Student student = findByStudentId();
        if (student != null) {
            int studentCore = Integer.parseInt(inputStudentCode());
            String name = inputName();
            int age = Integer.parseInt(inputAge());
            String gender = inputGender();
            String address = inputAddress();
            double mediumScore = Double.parseDouble(inputMediumScore());
            if (checkStudentId(studentCore)) {
                student.setStudentId(studentCore);
                if (!name.isEmpty()) {
                    student.setName(name);
                }
                if (18 <= age && age <= 25) {
                    student.setAge(age);
                }
                if (!gender.isEmpty()) {
                    student.setGender(gender);
                }
                if (!address.isEmpty()) {
                    student.setAddress(address);
                }
                if (0 <= mediumScore && mediumScore <= 10) {
                    student.setMediumScore(mediumScore);
                }
                System.out.println("Update success!!!");
            } else {
                System.out.println("Student ID already exists!!!");
            }
        } else {
            System.out.println("The student with the above student code could not be found!!!");
        }
        writeToFile();
    }

    public void sortByScoreIncrease() {
        if (!students.isEmpty()) {
            students.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Double.compare(o1.getMediumScore(), o2.getMediumScore());
                }
            });
        }
        display();
    }

    public void sortByScoreDecrease() {
        if (!students.isEmpty()) {
            students.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Double.compare(o2.getMediumScore(), o1.getMediumScore());
                }
            });
        }
        display();
    }

    public void writeToFile() {
        file.writeFile(PATH,students);
    }

    public void readFromFile() {
        students = file.readFile(PATH);
    }
}
