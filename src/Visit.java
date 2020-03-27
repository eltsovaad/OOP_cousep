import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Visit {
    private Date dateOfVisit;
    private String pat;
    private String doc;
    private String diagnosis;
    private String recommends;
    private String region;

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.dateOfVisit = formatter.parse(dateOfVisit);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommends() {
        return recommends;
    }

    public void setRecommends(String recommends) {
        this.recommends = recommends;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
