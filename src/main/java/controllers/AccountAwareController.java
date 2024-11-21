package controllers;

import model.Account;
public interface AccountAwareController {
    void setCuentaActiva(Account account);
}
