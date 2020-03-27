import java.util.Date;
import java.util.List;

public class Doctor extends Person {
    private String profession;
    private float seniority;
    private String education;
    private String workPlace;
    private Date employDate;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public float getSeniority() {
        return seniority;
    }

    public void setSeniority(float seniority) {
        this.seniority = seniority;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Date getEmployDate() {
        return employDate;
    }

    public void setEmployDate(Date employDate) {
        this.employDate = employDate;
    }
}
