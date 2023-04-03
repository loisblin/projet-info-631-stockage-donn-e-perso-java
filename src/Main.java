import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Noeud_systeme noeud1=new Noeud_systeme(1,100);
        Noeud_systeme noeud2=new Noeud_systeme(2,10);
        Noeud_systeme noeud3=new Noeud_systeme(3,200);
        Noeud_systeme noeud4=new Noeud_systeme(4,100);
        Noeud_systeme noeud5=new Noeud_systeme(5,10);

        Arc a12=new Arc(1,noeud1,noeud2);
        Arc a13=new Arc(3,noeud1,noeud3);
        Arc a14=new Arc(3,noeud1,noeud4);
        Arc a15=new Arc(1,noeud1,noeud5);

        Arc a21=new Arc(1,noeud2,noeud1);
        Arc a23=new Arc(1,noeud2,noeud3);
        Arc a24=new Arc(3,noeud2,noeud4);
        Arc a25=new Arc(3,noeud2,noeud5);

        Arc a31=new Arc(3,noeud3,noeud1);
        Arc a32=new Arc(1,noeud3,noeud2);
        Arc a34=new Arc(1,noeud3,noeud4);
        Arc a35=new Arc(3,noeud3,noeud5);

        Arc a41=new Arc(3,noeud4,noeud1);
        Arc a42=new Arc(3,noeud4,noeud2);
        Arc a43=new Arc(1,noeud4,noeud3);
        Arc a45=new Arc(1,noeud4,noeud5);

        Arc a51=new Arc(1,noeud5,noeud1);
        Arc a52=new Arc(3,noeud5,noeud2);
        Arc a53=new Arc(3,noeud5,noeud3);
        Arc a54=new Arc(1,noeud5,noeud4);




        ArrayList<Arc> noeudaccecible1=new ArrayList<Arc>();
        noeudaccecible1.add(a12);
        noeudaccecible1.add(a13);
        noeudaccecible1.add(a14);
        noeudaccecible1.add(a15);

        noeud1.setArc_sortant(noeudaccecible1);

        ArrayList<Arc> noeudaccecible2=new ArrayList<Arc>();
        noeudaccecible1.add(a21);
        noeudaccecible1.add(a23);
        noeudaccecible1.add(a24);
        noeudaccecible1.add(a25);

        noeud1.setArc_sortant(noeudaccecible2);


        ArrayList<Arc> noeudaccecible3=new ArrayList<Arc>();
        noeudaccecible1.add(a31);
        noeudaccecible1.add(a32);
        noeudaccecible1.add(a34);
        noeudaccecible1.add(a35);

        noeud1.setArc_sortant(noeudaccecible3);


        ArrayList<Arc> noeudaccecible4=new ArrayList<Arc>();
        noeudaccecible1.add(a41);
        noeudaccecible1.add(a42);
        noeudaccecible1.add(a43);
        noeudaccecible1.add(a45);

        noeud1.setArc_sortant(noeudaccecible4);


        ArrayList<Arc> noeudaccecible5=new ArrayList<Arc>();
        noeudaccecible1.add(a51);
        noeudaccecible1.add(a52);
        noeudaccecible1.add(a53);
        noeudaccecible1.add(a54);

        noeud1.setArc_sortant(noeudaccecible5);







        repartition2 test1=new repartition2();





        Noeud_utilisateur noeudUtilisateur1=new Noeud_utilisateur(1,noeud1,1);

        Donnee donnee1=new Donnee(1,90);

        ArrayList<Donnee> donneesutilisateur1=new ArrayList<Donnee>();

        donneesutilisateur1.add(donnee1);


        noeudUtilisateur1.setListe_donnees(donneesutilisateur1);


        test1.repartitionclassique(noeudUtilisateur1);
        System.out.println(noeud1.getListe_donnees());





















    }
}