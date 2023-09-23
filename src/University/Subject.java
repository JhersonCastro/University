package University;

public class Subject {
    String nameOfSub, ID;

    public Subject(String nameOfSub, String ID) {
        this.nameOfSub = nameOfSub;
        this.ID = ID;
    }
    public String getID(){
        return ID;
    }
    public String getName(){
        return nameOfSub;
    }
}
