package Users;

import University.Subject;
import University.University;
import net.Subjects;

import java.util.*;

public class Teacher extends User{
    private final List<String> SubjectsID = new ArrayList<>();
    private final Map<String, Double> avgGrade = new HashMap<>();
    University uni;
    public Teacher(String name, String bornDate, String ID, University uni) {
        super(name, bornDate, ID);
        this.uni = uni;
    }
    public void addSubject(String subjectID){
        Subject sub = uni.searchSubjectById(subjectID);
        if (sub != null){
            SubjectsID.add(subjectID);
        }
    }
    public String getId(){
        return ID;
    }
    public void setValueToAvgGrade(String SubjectID, double grade){
        avgGrade.put(SubjectID, grade);
    }
    private void setGradeToStudent(){
        String StudentID, IDSubject, Grade;
        System.out.println("Dime el numero de identificacion del estudiante");
        StudentID = new Scanner(System.in).nextLine();
        System.out.println("Dime el numero de identificacion del materia");
        IDSubject = new Scanner(System.in).nextLine();
        System.out.println("Dime la calificacion del usuario");
        Grade = new Scanner(System.in).nextLine();

        Student std = uni.searchStudentById(StudentID);
        Subject sub = uni.searchSubjectById(IDSubject);

        if(std != null && std.getSubjectsAndGrade().containsKey(sub)) {
            if (SubjectsID.contains(sub.getID())){
                std.setValueToSubject(sub, Grade);
                Subjects.Update(StudentID, IDSubject, Grade);
                System.out.println("Se actualizo la nota correctamente");
            }else{
                System.out.println("No tienes permiso para editar esta materia");
            }
        }else{
            System.out.println("No existe esa materia en el usuario, " +
                    "comunicate con el administrador");
        }
    }
    private void setSubjects(){
        String opc;
        do {
            System.out.println("Asignar materias automaticamente");
            System.out.println("En este momento la universidad tiene estas materias");
            uni.getSubjects().forEach(i -> System.out.println("ID: " + i.getID() + ", Nombre de la materia: " + i.getName()));
            System.out.println("Escriba el codigo de la materia que desea usar, 0 si quiere salir");
            opc = new Scanner(System.in).next();
            Subject sub = uni.searchSubjectById(opc);
            if(sub != null && !SubjectsID.contains(sub.getID())){
                SubjectsID.add(sub.getID());
                net.Subjects.AddTeacherSubjects(this.ID,sub.getID());
            }else{
                System.out.println(opc.equals("0") ? "Eligio salir" : "Ya estaba asignada o no se encuentra la materia en la base de datos");
            }
        }while(opc.equals("0"));
    }
    @Override
    public void Menu() {
        int opc;
        User us = new Admin(uni, "", "", "");
        if(SubjectsID.isEmpty()){
            System.out.println("Creación de sus materias");
            if (uni.getSubjects().isEmpty()){
                System.out.println("La universidad no tiene materias, regresando con el administrador para que cree las materias...");
                us.Menu();
            }else{
                setSubjects();
            }
            return;
        }
        do{
            System.out.println("1-Calificar a un estudiante\n2-Ver nota promedio\n3-Agregar materias\n4-Salir");
            opc = new Scanner(System.in).nextInt();
            switch (opc) {
                case 1 -> setGradeToStudent();
                case 2 -> avgGrade.forEach((i, j) -> System.out.println("En la materia " + uni.searchSubjectById(i) + " los estudiantes te han asignado " + j + " de la nota"));
                case 3 -> setSubjects();
            }
        }while(opc != 4);
    }

}
