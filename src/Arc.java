public class Arc {
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    private Integer distance;

    public Noeud_systeme getNdpart() {
        return ndpart;
    }

    public void setNdpart(Noeud_systeme ndpart) {
        this.ndpart = ndpart;
    }

    private Noeud_systeme ndpart;

    public Noeud_systeme getNfin() {
        return nfin;
    }

    public void setNfin(Noeud_systeme nfin) {
        this.nfin = nfin;
    }

    private Noeud_systeme nfin;





    public Arc(Integer distance,Noeud_systeme ndpart, Noeud_systeme nfin){
        this.distance=distance;
        this.ndpart=ndpart;
        this.nfin=nfin;



    }
}
