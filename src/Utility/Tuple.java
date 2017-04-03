package Utility;

public class Tuple {
    private final String Nume;
    private final int ID;
    
    public Tuple(String Name,int ID){
        this.Nume = Name;
        this.ID = ID;
    }

    public String getName() {
        return Nume;
    }

    public int getID() {
        return ID;
    }
    
    @Override
    public String toString() {
        return Nume;
    }
    
}
