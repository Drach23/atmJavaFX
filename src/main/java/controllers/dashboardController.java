package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Account;
import model.TransactionManager;
import utilities.Paths;

import java.io.IOException;

public class dashboardController {
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

    @FXML
    private Label lblAccountName;
    private Account activeAccount;
    private TransactionManager transactionManager;

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void openAccountStatementWindow(ActionEvent event) {
        navigateToWindow(Paths.ACCOUNT_STATEMENT, event);
    }

    @FXML
    void openDepositWindow(ActionEvent event) {
        navigateToWindow(Paths.DEPOSIT, event);
    }

    @FXML
    void openTransferWindow(ActionEvent event) {
        navigateToWindow(Paths.TRANSFER, event);
    }

    @FXML
    void openWithdrawWindow(ActionEvent event) {
        navigateToWindow(Paths.WITHDRAW, event);
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
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
                ((AccountAwareController) controller).setTransactionManager(transactionManager);
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

        // Actualiza la UI cuando se establece la cuenta activa
        if (lblAccountName != null) {
            lblAccountName.setText(activeAccount != null ? "Bienvenido " + activeAccount.getName() + " " + activeAccount.getLastname() : "No encontrado");
        }
    }

    @FXML
    public void initialize() {

    }
}