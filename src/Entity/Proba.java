/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author ynad
 */
public class Proba {
    private int ID;
    private String Denumire;
    private float Nota;

    public int getID() {
        return ID;
    }

    public Proba(){
        
    }
    public Proba(int ID, String Denumire, float Nota) {
        this.ID = ID;
        this.Denumire = Denumire;
        this.Nota = Nota;
    }

    public String getDenumire() {
        return Denumire;
    }

    public float getNota() {
        return Nota;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDenumire(String Denumire) {
        this.Denumire = Denumire;
    }

    public void setNota(float Nota) {
        this.Nota = Nota;
    }
    
    @Override
    public String toString(){
        return Denumire;
    }
    
}
