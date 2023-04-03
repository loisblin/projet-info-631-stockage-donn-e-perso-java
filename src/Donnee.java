public class Donnee {
    private int idd;
    private Integer taille;

    public Donnee(int id_donnees, int taille_donnees) {
        this.idd = id_donnees;
        this.taille = taille_donnees;


    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getId() {
        return idd;
    }

    public void setId(int id) {
        this.idd = id;
    }
}