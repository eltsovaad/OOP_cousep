public class Stats {

    String diseaseName;
    int numberOf=0;

    public Stats(String diseaseName){
        this.diseaseName=diseaseName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void increaseNumberOf() {
        this.numberOf++;
    }


}
