import java.util.ArrayList;
import java.util.List;

public class Noeud_systeme extends Noeud {
    // class noeud systeme sous class de noeud

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
        super(id); // id du noeud
        this.memoire = capa_memoire;// capacité de memoire restante dans le noeud
        this.liste_donnees= new ArrayList<Donnee>();// liste de donnees que le noeud contient
        this.arc_sortant= new ArrayList<Arc>();// liste des arc sortant du noeud vers les autres noeuds

    }
    public void add_donnee(Donnee donnee){ // methode qui permet d'ajouter une donnee et de par consequent mettre a jour la memoire restante
        this.liste_donnees.add(donnee);
        this.memoire=memoire- donnee.getTaille();


    }





}