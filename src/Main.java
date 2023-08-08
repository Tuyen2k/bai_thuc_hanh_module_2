import javax.xml.bind.SchemaOutputResolver;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        menu(scanner,choice);
    }
    public static void menu(Scanner scanner, int choice){
        StudentManager studentManager = new StudentManager();
        do {
            System.out.println("----STUDENT MANAGEMENT PROGRAM----");
            System.out.println("Select function by number (to continue):");
            System.out.println("1. See list of students");
            System.out.println("2. Add new student");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Sort");
            System.out.println("6. Read from file");
            System.out.println("7. Write to file");
            System.out.println("8. Exit");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        studentManager.display();
                        break;
                    case 2:
                        studentManager.create();
                        break;
                    case 3:
                        studentManager.update();
                        break;
                    case 4:
                        studentManager.delete();
                        break;
                    case 5:
                        do {
                            System.out.println("----Sort students by grade point average----");
                            System.out.println("Select function by number (to continue):");
                            System.out.println("1. Sort medium score by increase.");
                            System.out.println("2. Sort medium score by decrease.");
                            System.out.println("3. Exit.");
                            choice = Integer.parseInt(scanner.nextLine());
                            switch (choice){
                                case 1:
                                    studentManager.sortByScoreIncrease();
                                    break;
                                case 2:
                                    studentManager.sortByScoreDecrease();
                                    break;
                            }
                        }while (choice != 3);
                        break;
                    case 6:
                        studentManager.readFromFile();
                        break;
                    case 7:
                        studentManager.writeToFile();
                        break;
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }while (choice != 8);
    }
}
