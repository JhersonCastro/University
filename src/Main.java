import University.University;
import Users.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Admin> admin = new ArrayList<>();
        University uni = new University();
        User user;
        admin.add(new Admin(uni, "admin", "admin", "admin"));

        int opc;
        user = admin.get(0);
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
                        user = admin.get(0);
                        user.Menu();
                    }
                }
                default -> System.out.println("Opcion incorrecta");
            }
        }while(opc != 5);

    }

}