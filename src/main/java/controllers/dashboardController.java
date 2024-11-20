package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Account;

public class dashboardController {
    @FXML
    private Label lblAccountName;
    @FXML
    private Button btnAccountStatement;

    @FXML
    private Button btnDeposit;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnTransfer;

    @FXML
    private Button btnWithdraw;
    private Account activeAccount;


    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void openAccountStatementWindow(ActionEvent event) {

    }

    @FXML
    void openDepositWindow(ActionEvent event) {

    }

    @FXML
    void openTransferWindow(ActionEvent event) {

    }

    @FXML
    void openWithdrawWindow(ActionEvent event) {

    }

    public void setCuentaActiva(Account activeAccount) {
        this.activeAccount = activeAccount;

        // Actualiza la UI cuando se establece la cuenta activa
        if (lblAccountName != null) {
            lblAccountName.setText(activeAccount != null ? "Bienvenido " + activeAccount.getName() + " " + activeAccount.getLastname() : "No encontrado");
        }
    }

    @FXML
    public void initialize() {

    }
}
