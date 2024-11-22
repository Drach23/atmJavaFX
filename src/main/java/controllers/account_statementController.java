package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import utilities.Paths;

import java.io.IOException;

public class account_statementController implements AccountAwareController{
    @FXML
    private Button btnAccountStatement;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMenuDeposit;

    @FXML
    private Button btnMenuTransfer;

    @FXML
    private Button btnMenuWithdraw;

    @FXML
    private Label lblError;

    @FXML
    private TextField txtAccountHolder;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtBankAccount;
    private Account activeAccount;

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void openAccountStatementWindow(ActionEvent event) {
        navigateToWindow(Paths.ACCOUNT_STATEMENT,event);
    }

    @FXML
    void openDepositWindow(ActionEvent event) {
        navigateToWindow(Paths.DEPOSIT,event);
    }

    @FXML
    void openTransferWindow(ActionEvent event) {
        navigateToWindow(Paths.TRANSFER,event);
    }

    @FXML
    void openWithdrawWindow(ActionEvent event) {
        navigateToWindow(Paths.WITHDRAW,event);
    }

    private void navigateToWindow(String fxmlPath, ActionEvent event) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            Object controller = loader.getController();

            // Pasar la cuenta activa al nuevo controlador
            if (controller instanceof AccountAwareController) {
                ((AccountAwareController) controller).setCuentaActiva(activeAccount);
            }

            // Cambiar la escena
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCuentaActiva(Account activeAccount) {

            this.activeAccount = activeAccount;

            if (activeAccount != null) {
                System.out.println("Cuenta recibida: " + activeAccount.getNumberAccount());
                showAccountStatement();
            } else {
                System.err.println("Error: Se intentó asignar una cuenta nula.");
            }
    }

    private void showAccountStatement() {
        if (activeAccount == null) {
            System.out.println("Error: activeAccount is null. Ensure it's set before opening this window.");
            return;
        }

        txtAccountHolder.setEditable(false);
        txtAccountHolder.appendText(activeAccount.getName() + " " + activeAccount.getLastname());
        txtAccountNumber.setEditable(false);
        txtAccountNumber.appendText(activeAccount.getNumberAccount());
        txtBankAccount.setEditable(false);
        txtBankAccount.appendText("$" + activeAccount.getBalance() + " mxn");
    }


    @FXML
    public void initialize() {
        showAccountStatement();
    }
}
