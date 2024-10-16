package customer;

import java.util.ArrayList;

public class Customer implements NewsletterObserver {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<String> gifts;
    private String certificate;

    public Customer(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCertificate() {
        return ("You have resolved the escape room! Here is your certificate: " + certificate);
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public ArrayList<String> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<String> gifts) {
        this.gifts = gifts;
    }

    @Override
    public void update(String message) {
        System.out.println("Welcome to Escape Room Newsletter! We have news for you " +
                this.name + ": " + message);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gifts=" + gifts +
                ", certificate='" + certificate + '\'' +
                '}';
    }


}
