package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;
import model.TransactionManager;
import utilities.Paths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class LoginController {
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtAccount;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label errorMessage;
    private TransactionManager transactionManager;

    @FXML
    void click(ActionEvent event) {
        handleLogin();
    }

    @FXML
    void eventKey(KeyEvent event) {
        Object source = event.getSource();

        if (source.equals(txtAccount)) {
            if (event.getCode() == KeyCode.SPACE){
                event.consume();
            }
        }else if (source.equals(txtPassword)) {
            if (event.getCode() == KeyCode.SPACE){
                event.consume();
            }
        }
    }

    public void initialize() {
        // Configurar TextFormatter para que solo acepte números
        setNumericFormatter(txtAccount);
        setNumericFormatter(txtPassword);
    }
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * Configura un TextFormatter para restringir el contenido a solo números.
     */
    private void setNumericFormatter(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Solo números
                return change;
            }
            return null; // Rechazar cambios no numéricos
        };
        textField.setTextFormatter(new TextFormatter<>(filter));
    }

    public void handleLogin(){
        String accountNumber = txtAccount.getText().trim();
        String password = txtPassword.getText().trim();
        if (txtAccount.getText().isEmpty() || txtPassword.getText().isEmpty()){
            errorMessage.setText("Completa los campos vacios");
            return;
        }
        try{
            int nip = Integer.parseInt(password);
            Account account = transactionManager.findAccountAndNip(accountNumber,nip);
            if (account != null){
                transactionManager.setAccount(account);
                account.setIsLogued(true);
                errorMessage.setTextFill(Color.GREEN);
                errorMessage.setText("INICIO DE SESION EXITOSO");
                loadDashboardWindow();
            }else{
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Numero de cuenta y/o NIP incorrectos");
            }
        }catch(NumberFormatException e){
            errorMessage.setText("El nip debe ser numerico");
        }

    }
    public void loadDashboardWindow() {
        try {
            // Cargar el archivo FXML del Dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DASHBOARD));
            Parent root = loader.load();

            // Obtener el controlador de Dashboard
            dashboardController dashboardController = loader.getController();

            // Pasar la cuenta activa al controlador de Dashboard
            dashboardController.setCuentaActiva(transactionManager.getAccount());

            // Configurar la nueva escena con el Dashboard
            Scene dashboardScene = new Scene(root);
            Stage stage = (Stage) btnLogin.getScene().getWindow(); // Usar la misma ventana
            stage.setScene(dashboardScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("Error al cargar el Dashboard");
        }
    }

}
