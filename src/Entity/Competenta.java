package Entity;

/**
 *
 * @author ynad
 */
public class Competenta {
    private int ID;
    private String Calificativ;
    private String Denumire;

    public Competenta() {
    }

    public Competenta(int ID, String Calificativ, String Denumire) {
        this.ID = ID;
        this.Calificativ = Calificativ;
        this.Denumire = Denumire;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCalificativ(String Calificativ) {
        this.Calificativ = Calificativ;
    }

    public void setDenumire(String Denumire) {
        this.Denumire = Denumire;
    }

    public int getID() {
        return ID;
    }

    public String getCalificativ() {
        return Calificativ;
    }

    public String getDenumire() {
        return Denumire;
    }
    
}
