import java.util.Date;

public class Patient extends Person {

    private Date registrationDate;
    private int polisNum;
    private int passportNum;
    private String address;

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPolisNum() {
        return polisNum;
    }

    public void setPolisNum(int polisNum) {
        this.polisNum = polisNum;
    }

    public int getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(int passportNum) {
        this.passportNum = passportNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
