package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.TransactionManager;
import utilities.Paths;

import java.io.IOException;

public class depositController implements AccountAwareController {
    @FXML
    private Button btnAccountStatement;

    @FXML
    private Button btnDeposit;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMenuDeposit;

    @FXML
    private Button btnMenuTransfer;

    @FXML
    private Button btnMenuWithdraw;

    @FXML
    private TextField txtDeposit;

    private Account activeAccount;

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

    @FXML
    void Deposit(ActionEvent event) {
        DepositMoney();
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

    @Override
    public void setCuentaActiva(Account activeAccount) {
        this.activeAccount = activeAccount;
        // Actualiza la interfaz con la información de la cuenta activa
        System.out.println("Cuenta recibida: " + activeAccount.getNumberAccount());

    }

    public void DepositMoney(){
        try {
            // Recupera el texto y conviértelo a un double
            double value = Double.parseDouble(txtDeposit.getText());
            System.out.println("Valor ingresado: " + value);
            activeAccount.setBalance(activeAccount.getBalance() + value);
        } catch (NumberFormatException e) {
            // Maneja errores si el texto no es un número válido
            System.err.println("Error: El valor ingresado no es un número válido.");
        }
    }


    }
