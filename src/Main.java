import University.University;
import Users.Admin;
import Users.User;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        University uni = new University();
        net.DataBase.Create(uni);

        User user;
        int opc;
        user = new Admin(uni, "admin", "admin", "admin");
        user.Menu();
        do{
            System.out.println("1- Estudiante\n2-Profesor\n3-Padre de familia\n4-Admin\n5-Salir");
            opc = new Scanner(System.in).nextInt();
            System.out.println("Digite su numero de identificacion");

            String ID = new Scanner(System.in).nextLine();
            switch (opc) {
                case 1 -> {
                    user = uni.searchStudentById(ID);
                    if (user != null) {
                        user.Menu();
                        break;
                    }
                    System.out.println("No sé encontró el estudiante " +
                            "con ese numero de identificacion intentalo de nuevo");
                }
                case 2 -> {
                    user = uni.searchTeacherById(ID);
                    if (user != null) {
                        user.Menu();
                        break;
                    }
                    System.out.println("No sé encontró el profesor " +
                            "con ese numero de identificacion intentalo de nuevo");
                }
                case 3 -> {
                    user = uni.searchFatherById(ID);
                    if (user != null) {
                        user.Menu();
                        break;
                    }
                    System.out.println("No sé encontró el profesor " +
                            "con ese numero de identificacion intentalo de nuevo");
                }
                case 4 -> {
                    if (ID.equalsIgnoreCase("admin admin")) {
                        user = new Admin(uni, "", "", "");
                        user.Menu();
                    }
                }
                default -> System.out.println("Opcion incorrecta");
            }
        }while(opc != 5);
    }

}