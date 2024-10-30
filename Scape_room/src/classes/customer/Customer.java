package classes.customer;

import java.util.UUID;

public class Customer implements NewsletterObserver {

    private String id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String gifts;
    private String certificate;

    public Customer(){
    }

    public Customer(String name, String surname, String email, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return "You have resolved the escape room " + certificate + " ! Here is your certificate.";
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getGifts() {
        return "The customer receive a " + gifts + " as a gift!";
    }

    public void setGifts(String gifts) {
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
                "Id: " + id + '\'' +
                "Name: " + name + '\'' +
                "Surname: " + surname +
                "Email: " + email + '\'' +
                "Phone Number: " + phoneNumber + '\'' +
                "Gifts " + gifts +
                "Certificate: " + certificate;
    }

}
