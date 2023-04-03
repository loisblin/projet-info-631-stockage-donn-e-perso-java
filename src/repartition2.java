
import java.util.*;
public class repartition2 {


    public static int trouverDistanceMin(Noeud_systeme depart, Noeud_systeme arrivee) {

        Noeud_systeme premiernoeud = depart;
        List<List<Noeud_systeme>> listechemin = new ArrayList<>();
        List<Noeud_systeme> chemin1 = new ArrayList<>();
        chemin1.add(premiernoeud);
        chemin1.add(arrivee);
        listechemin.add(chemin1);

        List<Arc> noeudaccecible = new ArrayList<>();
        List<Noeud_systeme> aparcourir = new ArrayList<>();
        for (Arc arc : noeudaccecible) {
            aparcourir.add(arc.getNfin());
        }
        Integer indice = 0;
        for (Noeud_systeme noeud : aparcourir) {
            indice = indice + 1;
            if (noeud == premiernoeud) {
                aparcourir.remove(indice);
            }
        }
        Integer longueur = aparcourir.size();
        for (int k = 0; k < longueur; k++) {
            List<Noeud_systeme> nouveauchemin = new ArrayList<>();
            nouveauchemin.add(premiernoeud);
            for (int i = 0; i < longueur; i++) {
                if (i > k) {
                    nouveauchemin = aparcourir.subList(k, i);
                    nouveauchemin.add(arrivee);
                    listechemin.add(nouveauchemin);
                }
            }
        }
        List<Integer> listdistance = new ArrayList<>();
        for (List<Noeud_systeme> chemin : listechemin) {
            int distance = 0;
            int n = chemin.size();
            for (int i = 0; i < n - 1; i++) {
                List<Arc> listarc = chemin.get(i).getArc_sortant();
                for (Arc arc : listarc) {
                    if (arc.getNdpart() == chemin.get(i) && arc.getNfin() == chemin.get(i + 1)) {
                        distance = distance + arc.getDistance();
                    }
                }
            }
            listdistance.add(distance);
        }
        int minimum = listdistance.get(0);
        int indexMinimum = 0;
        for (int i = 1; i < listdistance.size(); i++) {
            if (listdistance.get(i) < minimum) {
                minimum = listdistance.get(i);
                indexMinimum = i;
            }
        }
        return listdistance.get(indexMinimum);
    }


    public void repartitionclassique(Noeud_utilisateur ndepar) {
        Noeud_systeme premiernoeud = ndepar.getNoeud_accesible();
        List<Donnee> donnees = ndepar.getListe_donnees();

        for (Donnee donnee : donnees) {
            Noeud_systeme noeud = ndepar.getNoeud_accesible();
            if (noeud.getMemoire() > donnee.getTaille()) {
                noeud.add_donnee(donnee);

            } else {
                List<Noeud_systeme> prochainsnoeud = new ArrayList<>();
                List<Arc> prochainpossible = noeud.getArc_sortant();
                for (Arc arc : prochainpossible) {
                    prochainsnoeud.add(arc.getNfin());
                }
                List<Integer> distancesmin = new ArrayList<>();

                for (Noeud_systeme prochainnoeud : prochainsnoeud) {
                    distancesmin.add(trouverDistanceMin(premiernoeud, prochainnoeud));

                }
                List<Noeud_systeme> noeudtrie = new ArrayList<>();

                while (!distancesmin.isEmpty()) {
                    int minIndex = 0;
                    int minValue = distancesmin.get(0);

                    for (int i = 1; i < distancesmin.size(); i++) {
                        if (distancesmin.get(i) < minValue) {
                            minValue = distancesmin.get(i);
                            minIndex = i;
                        }
                    }
                    noeudtrie.add(prochainsnoeud.get(minIndex));
                    prochainsnoeud.remove(minIndex);
                    distancesmin.remove(minIndex);
                }
                while (!noeudtrie.isEmpty()) {
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
            distancesmin1.add(trouverDistanceMin(premiernoeud1, prochain));
            distancesmin2.add(trouverDistanceMin(premiernoeud2, prochain));
        }
        List<Integer> distancemoyenne = new ArrayList<>();
        for (int i = 1; i < distancesmin1.size(); i++) {
            distancemoyenne.add(distancesmin1.get(i) + distancesmin2.get(i));

        }
        List<Integer> distancemoyennetri = new ArrayList<>();
        List<Integer> distancetrie1 = new ArrayList<>();
        List<Integer> distancetrie2 = new ArrayList<>();
        List<Noeud_systeme> noeudtrie = new ArrayList<>();
        while (!distancemoyenne.isEmpty()) {
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
        while (!distancemoyennetri.isEmpty()) {
            List<Integer> memedistancemoyenne = new ArrayList<>();
            Integer indice = 0;
            while (distancemoyennetri.get(indice) == distancemoyennetri.get(0)) {
                memedistancemoyenne.add(indice);
            }
            List<Integer> differencedistance = new ArrayList<>();
            for (Integer i : memedistancemoyenne) {
                differencedistance.add(Math.abs(distancetrie1.get(i) - distancetrie2.get(i)));
            }

            while (!differencedistance.isEmpty()) {
                int minindice = 0;
                int minValue = differencedistance.get(0);

                for (int i = 1; i < differencedistance.size(); i++) {
                    if (differencedistance.get(i) < minValue) {
                        minValue = differencedistance.get(i);
                        minindice = i;
                    }
                }
                if(noeudtrie.get(minindice).getMemoire()>donnee.getTaille()){
                    noeudtrie.get(minindice).add_donnee(donnee);
                    break;
                }
                else{
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

















