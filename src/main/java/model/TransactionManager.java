package model;
import java.util.ArrayList;

public class TransactionManager {
    //Atributos
    private Account account;
    private Atm atm;
    private ArrayList<Account> accounts;


    //Constructor
    //Asegura que se cree la lista necesaria para almacenar todas las cuentas.
    public TransactionManager(){
        accounts = new ArrayList();
    }
    //Metodos setter y Getter
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

    //Realiza un retiro de dinero a la cuenta activa
    public void withdraw(double money) {
        if (account.balance < money || account.balance < 0) {
            System.out.println("No tienes dinero suficiente para realizar la operacion");
        }else {
            if (money > atm.getWithdrawLimit()) {
                System.out.println("El cajero no permite sacar mas de: " + "$" + atm.getWithdrawLimit());
            } else {
                account.balance -= money;
                System.out.println("Se han retirado: " + "$" + money + "de la cuenta: " + account.numberAccount);
            }
        }
    }

    //agrega una cuenta al arraylist de cuentas
    public void addAccount(Account account){
        accounts.add(account);
    }

    /*Busca mediante el numero de cuenta una cuenta la se encuentra en un ArrayList donde se guardan todas las cuentas creadas
     * Si la encuentra retorna la cuenta*/

    public Account findAccountAndNip(String accountNumber,int nip){
        for (Account account : accounts) {
            if(account.getNumberAccount().equals(accountNumber) && account.getNip() == nip){
                return account;
            }
        }
        return null;
    }

    public Account findAccount(String accountNumber){
        for (Account account : accounts) {
            if(account.getNumberAccount().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }




    /*
     *@Param money pide una cantidad de dinero la cual se usara para la transaccion en este caso deposito a cuenta propia
     */

    public void deposit(double money) {
        account.balance += money;
        System.out.println("Se han depositado: " + "$"+ money + " a su cuenta: " + account.numberAccount);
    }

    /*
     *@Param money pide una cantidad de dinero la cual se usara para la transaccion en este caso transferencia
     *@Param beneficiaryAccount pide la cuenta a la cual se transferira el dinero*/
    public void transfer(double money,String beneficiaryAccount) {
        if (account.balance > money || account.balance > 0) {
            Account beneficiary = findAccount(beneficiaryAccount);
            if (beneficiary == null) {
                System.out.println("No se ha encontrado la cuenta");
            }else {
                account.balance -= money;
                beneficiary.balance += money;
                System.out.println("Transferencia de " + "$" + money + " a la cuenta: " + beneficiary.numberAccount +
                        " Ha sido exitoso. ");
            }
        }
    }
}
