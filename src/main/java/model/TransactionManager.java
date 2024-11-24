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
    private Boolean isLowerThan0 = false;

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

    public Boolean getLowerThan0() {
        return isLowerThan0;
    }

    public void setLowerThan0(Boolean lowerThan0) {
        isLowerThan0 = lowerThan0;
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
        double balance = account.getBalance();
        double atmLimit = atm.getWithdrawLimit();

        if(balance < money){
            setHaveMoney(false);
            System.out.println("No cuentas con fondos suficientes para realizar la operacion");
            return;
        }
        if(money <= 0){
            setIswithdrawSuccesfull(false);
            System.out.println("No puedes retirar una cantidad menor a 0");
            return;
        }
        if(money >= atmLimit){
            setAtmError(true);
            System.out.println("El cajero no permite retirar mas de $" + atmLimit);
            return;
        }

        account.setBalance(account.getBalance() - money);
        System.out.println("Se han retirado $" + money + " de tu cuenta");
        setIswithdrawSuccesfull(true);
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
                System.out.println("NO SE HA ENCONTRADO LA CUENTA BENEFICIARIA.");
                setTransferSuccesfull(false);
                return;
            }
            if (money <=0){
                System.out.println("NO ES POSIBLE TRANSFERIR UNA CANTIDAD MENOR O IGUAL A 0");
                setLowerThan0(true);
                return;
            }
                account.setBalance(account.getBalance() - money);
                beneficiary.setBalance(beneficiary.getBalance() + money);
                System.out.println("Transferencia de $" + money + " a la cuenta: " + beneficiary.getNumberAccount() + " ha sido exitosa.");
                setTransferSuccesfull(true);
            }else {
            System.out.println("Saldo insuficiente para la transferencia.");
            setHaveMoney(false);
        }
    }
}
