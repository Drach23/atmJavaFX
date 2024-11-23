package model;

public class Atm {
    private double cantMoney;
    private String location;
    private Boolean isOperative;
    private double withdrawLimit;
    private TransactionManager transactionManager;

    // Constructor
    public Atm(TransactionManager transactionManager) {
        this.cantMoney = 100000;
        this.withdrawLimit = 10000;
        this.transactionManager = transactionManager;
    }

    // Constructor alternativo
    public Atm(String location, Boolean isOperative, double withdrawLimit) {
        cantMoney = 100000;
        this.withdrawLimit = 10000;
        this.location = location;
        this.isOperative = isOperative;
        this.withdrawLimit = withdrawLimit;
        this.transactionManager = transactionManager;
    }

    // Método para iniciar sesión con número de cuenta y NIP
    public void login(String numberAccount, int nip) {
        Account account = transactionManager.findAccountAndNip(numberAccount, nip);
        if (account != null) {
            transactionManager.setAccount(account);
            account.setIsLogued(true);
            System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + account.getName());
        } else {
            System.out.println("No se encontró la cuenta o el NIP es incorrecto.");
        }
    }

    // Validar si hay dinero suficiente en el cajero
    public boolean isSufficientFunds(double amount) {
        if (cantMoney >= amount) {
            return true;
        } else {
            System.out.println("El cajero no tiene suficiente dinero para realizar esta operación.");
            return false;
        }
    }

    // Realizar un retiro
    public void withdraw(double amount) {
        if (isSufficientFunds(amount)) {
            transactionManager.withdraw(amount);
            cantMoney -= amount;  // Restar dinero del cajero
        }
    }

    // Getters y Setters
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
