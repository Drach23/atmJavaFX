package applications;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.Account;
import model.Atm;
import model.TransactionManager;
import utilities.Paths;

public class App extends Application {
    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            // Crear el objeto TransactionManager y Atm
            TransactionManager transactionManager = new TransactionManager();
            Atm atm = new Atm(transactionManager);
            transactionManager.setAtm(atm);
            Account account2 = new Account("Diego","Aquino","puerto vallarta","1221","45625","3325165098","218616492",2003,500);
            transactionManager.addAccount(account2);

            // Cargar el FXML y obtener el controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.LOGIN));
            Parent root = loader.load();

            // Inyectar el TransactionManager en el controlador
            LoginController controller = loader.getController();
            controller.setTransactionManager(transactionManager);

            // Crear y mostrar la escena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Mostrar el error en caso de fallo
            System.exit(1); // Salir de la aplicación en caso de error
        }
    }

}
