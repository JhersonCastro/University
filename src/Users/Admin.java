package Users;

import University.*;
import java.util.Scanner;

public class Admin extends User{
    University uni;
    public Admin(University uni, String name, String bornDate, String ID){
        super(name, bornDate, ID);
        this.uni = uni;
    }
    @Override
    public void Menu() {
        int opc;
        do{
            System.out.println("Escoje que quieres hacer: ");
            System.out.println("1-Registrar estudiante\n" +
                    "2-Asignar Materia a un estudiante\n" +
                    "3-Actualizar datos del estudiante\n" +
                    "4-Crear una nueva materia\n" +
                    "5-Crear profesor"+
                    "6-Salir");
            opc = new Scanner(System.in).nextInt();
            switch (opc) {
                case 1 -> createStudent();
                case 2 -> associationSubToStd();
                case 3 -> UpdateStudent();
                case 4 -> createSubject();
                case 5 -> createTeacher();
            }
        }while(opc != 6);
    }
    private void createStudent(){
        String[] gen = lblGenericCreateUser("estudiante");
        createUser(new Student(gen[0], gen[1], gen[2]));
        do{
            System.out.println("Usuario registrado correctamente," +
                    "\n¿El estudiante ya tiene parientes creados? Y/N y/n");
            if (new Scanner(System.in).next().trim().equalsIgnoreCase("y")){
                System.out.println("Dime el numero de identificacion del tutor legal del estudiante");
                Father father = uni.searchFatherById(new Scanner(System.in).next());
                if (father != null){
                    father.addSon(uni.searchStudentById(gen[2]));
                    return;
                }
                System.out.println("No se encontro un padre de familia con ese ID");
            }else{
                System.out.println("No se encontro al tutor, " +
                        "¿Fue un error al digitalizar la identificacion?, " +
                        "¿Desea crear la cuenta del tutor? Y/N y/n");
                if (new Scanner(System.in).next().trim().equalsIgnoreCase("y")){
                    String cache = gen[2]; //Basicamente para no perder el ID de su hijo
                    gen = lblGenericCreateUser("tutor");
                    createUser(new Father(gen[0], gen[1], gen[2]));
                    Father father = uni.searchFatherById(gen[2]);
                    father.addSon(uni.searchStudentById(cache));
                    return;
                }
            }
        }while(true);
    }
    private void createTeacher(){
        String[] args = lblGenericCreateUser("profesor");
        createUser(new Teacher(args[0], args[1], args[2]));
    }
    private void createUser(Object type){
        if (type.getClass().equals(Student.class)){
            uni.registerStudents((Student) type);
        }
        else if(type.getClass().equals(Father.class)){
            uni.registerFather((Father) type);
        }
        else if(type.getClass().equals(Teacher.class)){
            uni.registerTeacher((Teacher) type);
        }
    }
    private String[] lblGenericCreateUser(String lbl){
        String[] register = new String[3];
        System.out.println("Dime el nombre del " + lbl);
        register[0] = new Scanner(System.in).nextLine();
        System.out.println("Dime la fecha de nacimiento en formato (DD-MM-AAAA)\nEjemplo: 02-04-2003");
        register[1] = new Scanner(System.in).nextLine();
        System.out.println("Dime el numero de identificacion del " + lbl);
        register[2] = new Scanner(System.in).nextLine();
        return register;
    }
    private void associationSubToStd(){
        System.out.println("Dime el numero de identificacion del estudiante: ");
        Student student = uni.searchStudentById(new Scanner(System.in).next());
        System.out.println("Dime el numero de identificacion de la materia: ");
        Subject subject = uni.searchSubjectById(new Scanner(System.in).next());
        if (student != null && subject != null){
            student.setValueToSubject(subject, "0");
            return;
        }
        System.out.println("Registra un estudiante o crea la materia, para empezar");
    }
    private void UpdateStudent(){
        System.out.println("Dime el numero de identificacion del estudiante");
        Student student = uni.searchStudentById(new Scanner(System.in).next());
        if (student != null) {
            System.out.println("Dime el nombre del estudiante");
            student.setName(new Scanner(System.in).next());
            System.out.println("Dime la fecha de nacimiento en formato (DD-MM-AAAA)\n" +
                    "Ejemplo: 02-04-2003");
            student.setBornDate(new Scanner(System.in).next());
        }else{
            System.out.println("No se encontro al estudiante");
        }
    }
    private void createSubject(){
        String name, ID;
        System.out.println("Dime el nombre de la materia: ");
        name = new Scanner(System.in).nextLine();
        System.out.println("Dime el ID: ");
        ID = new Scanner(System.in).nextLine();
        uni.CreateSubject(name, ID);
    }
}
