package controllers;

import model.Account;
import model.TransactionManager;

public interface AccountAwareController {
    void setCuentaActiva(Account account);
    void setTransactionManager(TransactionManager transactionManager);
}
