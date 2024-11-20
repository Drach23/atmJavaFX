package model;
public class User {
    //Atributos
    private String name;
    private String lastname;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String phone;

    //Constructor
    public User(){}

    public User(String name, String lastname, String street, String streetNumber, String postalCode, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    //Setters y Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new  StringBuilder();
        sb.append("Titular de la cuenta: ").append(this.name).append(" ").append(this.lastname).append("\n");
        sb.append("Direccion del titular: ").append(this.street).append(" #").append(this.streetNumber).append(" ").append(this.postalCode).append("\n");
        sb.append("Numero de contacto: ").append(" ").append(this.phone);
        return sb.toString();
    }
}
