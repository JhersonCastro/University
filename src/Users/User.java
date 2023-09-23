package Users;

public abstract class User {
    String name, bornDate, ID;
    public User(String name, String bornDate, String ID){
        this.name= name; this.bornDate = bornDate; this.ID = ID;
    }
    public abstract void Menu();
}
