package Users;
import University.Subject;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student extends User {
    private Map<Subject, String> subjectsAndGrade = new HashMap<>();

    public Student(String name, String bornDate, String ID) {
        super(name, bornDate, ID);
    }

    @Override
    public void Menu() {
        int opc;
        do{
            System.out.println("Escoje que quieres hacer: ");
            System.out.println("1-Revisar las notas\n" +
                    "2-Calificar a profesores\n" +
                    "3-Salir");
            opc = new Scanner(System.in).nextInt();
            switch (opc) {
                case 1 -> printNotes();
                case 2 -> printNotes();
            }
        }while(opc != 3);
    }
    public void printNotes(){
        subjectsAndGrade.forEach( (key, value) -> System.out.println(key.getName() + ": "+ value));
    }

    public void setValueToSubject(University.Subject sub, String grade){
        subjectsAndGrade.put(sub, grade);
    }
    public String getId(){
        return ID;
    }
    public void setBornDate(String bornDate){
        this.bornDate = bornDate;
    }
    public void setName(String name){
        this.name = name;
    }
}
