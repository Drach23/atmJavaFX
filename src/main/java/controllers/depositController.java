package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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

    @FXML
    private Label lblDepositError;

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

    @Override
    public void setCuentaActiva(Account activeAccount) {
        this.activeAccount = activeAccount;
        // Actualiza la interfaz con la información de la cuenta activa
        System.out.println("Cuenta recibida: " + activeAccount.getNumberAccount());

    }

    @Override
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void DepositMoney(){
        try {

            double amount = Double.parseDouble(txtDeposit.getText());
            transactionManager.deposit(amount);
            if(transactionManager.getDepositSuccesfull() == true){
                lblDepositError.setTextFill(Color.GREEN);
                lblDepositError.setText("DEPOSITO DE: $" + amount + " " + "HA SIDO REALIZADO CON EXITO");
                txtDeposit.clear();
            }else {
                lblDepositError.setTextFill(Color.RED);
                lblDepositError.setText("LA CANTIDAD A DEPOSITAR DEBE SER MAYOR A 0");
                txtDeposit.clear();
            }
        } catch (NumberFormatException e) {
            // Maneja errores si el texto no es un número válido
            System.err.println("Error: El valor ingresado no es un número válido.");
        }
    }


    }
