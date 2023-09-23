package Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Father extends User{
    private List<Student> sons = new ArrayList<>();

    public Father(String name, String bornDate, String ID) {
        super(name, bornDate, ID);
    }
    public String getId(){
        return ID;
    }
    public void addSon(Student student){
        sons.add(student);
    }
    @Override
    public void Menu() {
        sons.forEach(s -> System.out.println("Nombre: " + s.name
                + "\nDia de nacimiento: " + s.bornDate
                + "\nIdentificacion: " + s.ID + "===================="));
        System.out.println("Ver notas de su hijo, digite el numero de identificacion de su hijo, por favor: ");
        Student son = sons.stream().filter(s -> s.getId().equals(new Scanner(System.in).nextLine())).findFirst().orElse(null);
        if(son != null){
            son.printNotes();
        }
    }
}
