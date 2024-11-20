package model;
public class Account extends User{
    //Atributos
    public String numberAccount;
    public int nip;
    public double balance;
    public boolean isLogued;
    //Constructors
    public Account(){}
    public Account(String name, String lastname, String street, String streetNumber, String postalCode, String phone, String numberAccount, int nip, double balance) {
        super(name, lastname, street, streetNumber, postalCode, phone);
        this.numberAccount = numberAccount;
        this.nip = nip;
        this.balance = balance;
        this.isLogued = false;
    }



    //Setters y Getters
    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean getIsLogued() {
        return isLogued;
    }

    public void setIsLogued(boolean logued) {
        isLogued = logued;
    }


}
