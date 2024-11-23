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

public class transferController implements AccountAwareController{
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
    private Button btnTransfer;

    @FXML
    private Label lblError;

    @FXML
    private TextField txtBeneficiary;

    @FXML
    private TextField txtTransferMoney;

    private Account activeAccount;
    private TransactionManager transactionManager;

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

    @FXML
   void  TransferMoney(ActionEvent event){
        transferToAccount();
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

    public void transferToAccount(){
        String accountNumber = txtBeneficiary.getText();
        double money = Double.parseDouble(txtTransferMoney.getText());
        try{
            transactionManager.transfer(money, accountNumber);
            lblError.setTextFill(Color.GREEN);
            lblError.setText("Transferencia exitosa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
