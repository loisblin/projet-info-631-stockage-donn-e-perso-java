
import java.util.*;
public class repartition2 {


    public static int trouverDistanceMin(Noeud_systeme depart, Noeud_systeme arrivee) {
        //objectif trouver le chemin le plus court entre 2 noeud

        Noeud_systeme premiernoeud = depart;
        List<List<Noeud_systeme>> listechemin = new ArrayList<>(); //la liste de tout les chemins possible
        List<Noeud_systeme> chemin1 = new ArrayList<>();
        chemin1.add(premiernoeud);
        chemin1.add(arrivee);
        listechemin.add(chemin1); //premier chemin le chemin direct

        List<Arc> noeudaccecible = new ArrayList<>(); //en recuperant les arc sortant on a aussi tout les noeud par lesquel on peut passer  car tout les noeud système sont relié
        List<Noeud_systeme> aparcourir = new ArrayList<>();
        for (Arc arc : noeudaccecible) { //on extrait les noeuds des arcs
            aparcourir.add(arc.getNfin());
        }
        Integer indice = 0;

        Integer longueur = aparcourir.size();
        for (int k = 0; k < longueur; k++) { //double boucle for qui parcours tout les possibilité de chemin possible et qui fait a chaque fois un nouveau chemin qu'on ajoute a liste
            List<Noeud_systeme> nouveauchemin = new ArrayList<>();//nouveau chemin a ajouter
            nouveauchemin.add(premiernoeud);//le chemin part du premier noeud
            for (int i = 0; i < longueur; i++) {
                if (i > k) {
                    nouveauchemin = aparcourir.subList(k, i);//entre le noeud de depart
                    nouveauchemin.add(arrivee);//le chemin fini forcement par le noeud d'arrivé
                    listechemin.add(nouveauchemin);
                }
            }
        }
        List<Integer> listdistance = new ArrayList<>();//fait une liste de distance qui correspond a la longeur de chaque chemin
        for (List<Noeud_systeme> chemin : listechemin) {// pour tout les chemins trouve la distance
            int distance = 0;
            int n = chemin.size();
            for (int i = 0; i < n - 1; i++) {
                List<Arc> listarc = chemin.get(i).getArc_sortant();
                for (Arc arc : listarc) {
                    if (arc.getNdpart() == chemin.get(i) && arc.getNfin() == chemin.get(i + 1)) { //retrouve l'arc correspondant a 2 noeud consecutif du chemin pour ainsi avoir la distance
                        distance = distance + arc.getDistance();
                    }
                }
            }
            listdistance.add(distance);
        }
        int minimum = listdistance.get(0);
        int indexMinimum = 0;
        for (int i = 1; i < listdistance.size(); i++) { // trouve l'indice de la plus petit distance
            if (listdistance.get(i) < minimum) {
                minimum = listdistance.get(i);
                indexMinimum = i;
            }
        }
        return listdistance.get(indexMinimum);//retourne la plus petit distance
    }


    public void repartitionclassique(Noeud_utilisateur ndepar) {//repartition des donne de l'utilisateur dasn les noeud systeme
        Noeud_systeme premiernoeud = ndepar.getNoeud_accesible(); //le noeud de depart est un noued utilisateur mais il na que un noeud systeme accesible donc on part de ce noeud forcement
        List<Donnee> donnees = ndepar.getListe_donnees();//liste des donnees à repartir

        for (Donnee donnee : donnees) {
            Noeud_systeme noeud = ndepar.getNoeud_accesible();
            if (noeud.getMemoire() > donnee.getTaille()) {//teste le premier noeud si il est compatible
                noeud.add_donnee(donnee);
            }
            else {
                List<Noeud_systeme> prochainsnoeud = new ArrayList<>(); //liste des noeud accesibe
                List<Arc> prochainpossible = noeud.getArc_sortant();//
                for (Arc arc : prochainpossible) { //trouve les noeuds avec les arcs
                    prochainsnoeud.add(arc.getNfin());
                }
                List<Integer> distancesmin = new ArrayList<>();//

                for (Noeud_systeme prochainnoeud : prochainsnoeud) {
                    distancesmin.add(trouverDistanceMin(premiernoeud, prochainnoeud));//trouve la distance min pour chaque noeud

                }
                List<Noeud_systeme> noeudtrie = new ArrayList<>();//nouvelle liste dans laquel on trie les noeud par rraport a leur distance

                while (!distancesmin.isEmpty()) { // boucle qui parcours tout les noeud en commencant par le plus proche et les trie dans l'ordre des plus proche
                    int minIndex = 0;
                    int minValue = distancesmin.get(0);

                    for (int i = 1; i < distancesmin.size(); i++) { // trouve la distance minimum
                        if (distancesmin.get(i) < minValue) {
                            minValue = distancesmin.get(i);
                            minIndex = i;
                        }
                    }
                    noeudtrie.add(prochainsnoeud.get(minIndex));
                    prochainsnoeud.remove(minIndex);
                    distancesmin.remove(minIndex);
                }
                while (!noeudtrie.isEmpty()) { // en fonction de leur proximité teste leur possibilite d'acceuillir la donné ou passe au suivant
                    if (noeudtrie.get(0).getMemoire() > donnee.getTaille()) {
                        noeudtrie.get(0).add_donnee(donnee);
                    } else {
                        noeudtrie.remove(0);


                    }
                }
            }
        }
    }

    public void repartitionentre2(Noeud_utilisateur depar1, Noeud_utilisateur depar2, Donnee donnee) {
        Noeud_systeme premiernoeud1 = depar1.getNoeud_accesible();
        Noeud_systeme premiernoeud2 = depar2.getNoeud_accesible();

        List<Noeud_systeme> possibles = new ArrayList<>();
        possibles.add(premiernoeud1);
        for (Arc possibilite : premiernoeud1.getArc_sortant()) {
            possibles.add(possibilite.getNfin());
        }
        List<Integer> distancesmin1 = new ArrayList<>();
        List<Integer> distancesmin2 = new ArrayList<>();
        for (Noeud_systeme prochain : possibles) {
            distancesmin1.add(trouverDistanceMin(premiernoeud1, prochain));// liste distance minimum entre premier noeud systeme et les autre noeud pour utilisateur 1
            distancesmin2.add(trouverDistanceMin(premiernoeud2, prochain));
        }
        List<Integer> distancemoyenne = new ArrayList<>();// distance moyenne entre le noeud et les 2 utilisateurs
        for (int i = 1; i < distancesmin1.size(); i++) {
            distancemoyenne.add(distancesmin1.get(i) + distancesmin2.get(i));

        }
        List<Integer> distancemoyennetri = new ArrayList<>();
        List<Integer> distancetrie1 = new ArrayList<>();
        List<Integer> distancetrie2 = new ArrayList<>();
        List<Noeud_systeme> noeudtrie = new ArrayList<>();
        while (!distancemoyenne.isEmpty()) {// trie en fonction de la distance moyenne
            int minIndex = 0;
            int minValue = distancemoyenne.get(0);

            for (int i = 1; i < distancemoyenne.size(); i++) {
                if (distancemoyenne.get(i) < minValue) {
                    minValue = distancemoyenne.get(i);
                    minIndex = i;
                }
            }
            distancemoyennetri.add(distancemoyenne.get(minIndex));
            distancetrie1.add(distancesmin1.get(minIndex));
            distancetrie2.add(distancesmin2.get(minIndex));
            noeudtrie.add(possibles.get(minIndex));
            distancemoyenne.remove(minIndex);
            distancesmin1.remove(minIndex);
            distancesmin2.remove(minIndex);
            possibles.remove(minIndex);

        }
        while (!distancemoyennetri.isEmpty()) {// regarde les terme ayant la meme distance moyenne
            List<Integer> memedistancemoyenne = new ArrayList<>();
            Integer indice = 0;
            while (distancemoyennetri.get(indice) == distancemoyennetri.get(0)) {
                memedistancemoyenne.add(indice);
            }
            List<Integer> differencedistance = new ArrayList<>();
            for (Integer i : memedistancemoyenne) {
                differencedistance.add(Math.abs(distancetrie1.get(i) - distancetrie2.get(i)));// pour les terme ayant la meme distance moyenne on prend leur ecart type
            }

            while (!differencedistance.isEmpty()) {//on regarde les noeud avec le plus faible ecart type
                int minindice = 0;
                int minValue = differencedistance.get(0);

                for (int i = 1; i < differencedistance.size(); i++) { //trouve le plus petit ecart type
                    if (differencedistance.get(i) < minValue) {
                        minValue = differencedistance.get(i);
                        minindice = i;
                    }
                }
                if(noeudtrie.get(minindice).getMemoire()>donnee.getTaille()){//regarde si le noeud trouver peut prendre la donnée
                    noeudtrie.get(minindice).add_donnee(donnee);
                    break;
                }
                else{//sinon le supprime des liste et recommence
                    differencedistance.remove(minindice);
                    noeudtrie.remove(minindice);
                    distancetrie1.remove(minindice);
                    distancetrie2.remove(minindice);
                    distancemoyennetri.remove(minindice);



                }



            }
        }

    }
}

















