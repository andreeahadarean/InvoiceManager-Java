package Model;

public class Company {
    private int id;
    private String name;
    private String phoneNr;

    public Company() {

    }

    public Company(int id, String name, String phoneNr){
        this.id = id;
        this.name = name;
        this.phoneNr = phoneNr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public String toString() {
        return name + " with phone number: " + phoneNr + "\n";
    }
}
