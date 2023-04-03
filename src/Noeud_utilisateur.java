import java.util.List;
import java.util.ArrayList;
public class Noeud_utilisateur extends Noeud {

    private Noeud_systeme noeud_accesible;
    private int distance;

    public void setListe_donnees(List<Donnee> liste_donnees) {
        this.liste_donnees = liste_donnees;
    }

    private List<Donnee> liste_donnees;
    public Noeud_systeme getNoeud_accesible() {
        return noeud_accesible;
    }

    public void setNoeud_accesible(Noeud_systeme noeud_accesible) {
        this.noeud_accesible = noeud_accesible;
    }


    public List<Donnee> getListe_donnees() {
        return liste_donnees;
    }









    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Noeud_utilisateur(int id,Noeud_systeme noeud_accesible,int distance) {
        super(id);
        this.liste_donnees= new ArrayList<Donnee>();
        this.distance=distance;
        this.noeud_accesible=noeud_accesible;
    }
    public void add_donnee(Donnee donnee){
        this.liste_donnees.add(donnee);

    }
}
