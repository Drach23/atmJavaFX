package model;


// Modificación de la clase Atm
public class Atm {
    private double cantMoney;
    private String location;
    private Boolean isOperative;
    private double withdrawLimit;
    private TransactionManager transactionManager;

    //Constructor
    public Atm(TransactionManager transactionManager) {
        this.cantMoney = 100000;
        this.transactionManager = transactionManager;
    }

    public Atm(String location, Boolean isOperative, double withdrawLimit) {
        cantMoney = 100000;
        this.location = location;
        this.isOperative = isOperative;
        this.withdrawLimit = withdrawLimit;
        this.transactionManager = transactionManager;
    }

    //Método para iniciar sesión recibiendo los datos como parámetros
    public void login(String numberAccount, int nip) {
        // Buscar la cuenta
        Account account = transactionManager.findAccountAndNip(numberAccount, nip);
        if (account != null) {
            transactionManager.setAccount(account);
            account.setIsLogued(true);
            System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + account.getName());
            // Aquí puedes invocar el menú o acciones adicionales
        } else {
            System.out.println("No se encontró la cuenta ingresada o el NIP es incorrecto.");
        }
    }

    //Getters y Setters
    public double getCantMoney() {
        return cantMoney;
    }

    public void setCantMoney(double cantMoney) {
        this.cantMoney = cantMoney;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getOperative() {
        return isOperative;
    }

    public void setOperative(Boolean operative) {
        isOperative = operative;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }
}
