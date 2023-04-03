import java.util.ArrayList;
import java.util.List;

public class Noeud_systeme extends Noeud {

    private List<Donnee> liste_donnees;
    private int memoire;

    private List<Arc> arc_sortant;

    public List<Arc> getArc_sortant() {
        return arc_sortant;
    }

    public void setArc_sortant(List<Arc> arc_sortant) {
        this.arc_sortant = arc_sortant;
    }







    public int getMemoire() {
        return memoire;
    }

    public void setMemoire(int memoire) {
        this.memoire = memoire;
    }



    public List<Donnee> getListe_donnees() {
        return liste_donnees;
    }

    public void setListe_donnees(List<Donnee> liste_donnees) {
        this.liste_donnees = liste_donnees;
    }







    public Noeud_systeme(int id, int capa_memoire) {
        super(id);
        this.memoire = capa_memoire;
        this.liste_donnees= new ArrayList<Donnee>();
        this.arc_sortant= new ArrayList<Arc>();

    }
    public void add_donnee(Donnee donnee){
        this.liste_donnees.add(donnee);
        this.memoire=memoire- donnee.getTaille();


    }





}