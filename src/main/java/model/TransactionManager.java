package model;

import java.util.ArrayList;

public class TransactionManager {
    private Account account;
    private Atm atm;
    private ArrayList<Account> accounts;
    private Boolean isDepositSuccesfull = false;
    private Boolean iswithdrawSuccesfull = false;
    private Boolean isTransferSuccesfull = false;
    private Boolean HaveMoney = true;
    private Boolean AtmError = false;

    public TransactionManager(){
        accounts = new ArrayList<>();
    }

    // Métodos setter y getter
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public Boolean getDepositSuccesfull() {
        return isDepositSuccesfull;
    }

    public void setDepositSuccesfull(Boolean depositSuccesfull) {
        isDepositSuccesfull = depositSuccesfull;
    }

    public Boolean getIswithdrawSuccesfull() {
        return iswithdrawSuccesfull;
    }

    public void setIswithdrawSuccesfull(Boolean iswithdrawSuccesfull) {
        this.iswithdrawSuccesfull = iswithdrawSuccesfull;
    }

    public Boolean getTransferSuccesfull() {
        return isTransferSuccesfull;
    }

    public void setTransferSuccesfull(Boolean transferSuccesfull) {
        isTransferSuccesfull = transferSuccesfull;
    }

    public Boolean getHaveMoney() {
        return HaveMoney;
    }

    public void setHaveMoney(Boolean haveMoney) {
        HaveMoney = haveMoney;
    }

    public Boolean getAtmError() {
        return AtmError;
    }

    public void setAtmError(Boolean atmError) {
        AtmError = atmError;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    // Buscar cuenta por número de cuenta y NIP
    public Account findAccountAndNip(String accountNumber, int nip){
        for (Account account : accounts) {
            if(account.getNumberAccount().equals(accountNumber) && account.getNip() == nip){
                return account;
            }
        }
        return null;
    }

    // Buscar cuenta solo por número de cuenta
    public Account findAccount(String accountNumber){
        for (Account account : accounts) {
            if(account.getNumberAccount().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    //todo Corregir errores de logica
    // Realizar un retiro
    public void withdraw(double money) {
        // Validación en función del balance y el límite de retiro del cajero
        if (account.getBalance() < money || account.getBalance() <= 0) {
            System.out.println("No tienes dinero suficiente para realizar la operación.");
            setHaveMoney(false);
        } else {
            if (money > atm.getWithdrawLimit()) {
                System.out.println("El cajero no permite sacar más de: $" + atm.getWithdrawLimit());
                setAtmError(true);
            } else {
                account.setBalance(account.getBalance() - money);
                System.out.println("Se han retirado: $" + money + " de la cuenta: " + account.getNumberAccount());
                setIswithdrawSuccesfull(true);
            }
        }
    }

    // Realizar un depósito
    public void deposit(double money) {
        if (money > 0) {
            account.setBalance(account.getBalance() + money);
            System.out.println("Se han depositado: $" + money + " a su cuenta: " + account.getNumberAccount());
            setDepositSuccesfull(true);
        }else {
            System.out.println("Debe ser una cantidad mayor a 0");
        }
    }

    //todo realizar el agregado de errores y mensajes de satisfactorio
    // Realizar una transferencia
    public void transfer(double money, String beneficiaryAccount) {
        if (account.getBalance() >= money) {
            Account beneficiary = findAccount(beneficiaryAccount);
            if (beneficiary == null) {
                System.out.println("No se ha encontrado la cuenta beneficiaria.");
            } else {
                account.setBalance(account.getBalance() - money);
                beneficiary.setBalance(beneficiary.getBalance() + money);
                System.out.println("Transferencia de $" + money + " a la cuenta: " + beneficiary.getNumberAccount() + " ha sido exitosa.");
                setTransferSuccesfull(true);
            }
        } else {
            System.out.println("Saldo insuficiente para la transferencia.");
            setHaveMoney(false);
        }
    }
}
