package Users;

public class Teacher extends User{
    private double avgGrade;
    public Teacher(String name, String bornDate, String ID) {
        super(name, bornDate, ID);
    }
    public String getId(){
        return ID;
    }
    public double getAvgGrade(){
        return avgGrade;
    }
    public void setAvgGrade(double avgGrade){
        this.avgGrade = avgGrade;
    }
    @Override
    public void Menu() {

    }

}
