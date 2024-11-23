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
            Account account1 = new Account("Claudia Elizabeth","Robles Orozco","San martin","3363","45580","3325165090","218616491",2001,10000);
            Account account2 = new Account("Diego","Aquino","puerto vallarta","1221","45625","3325165098","218616492",2002,500);
            Account account3 = new Account("Guillermo Antonio","Arellano Cervantes","Puerto escondido","1222","45626","3325165091","218616493",2003,5000);
            Account account4 = new Account("Luis Felipe","Aguiñiga Haro","Puerto veracruz","1223","45627","3325165092","218616494",2003,0);
            Account account5 = new Account("Roman Antonio","Arechiga Rojas","Puerto salina cruz","1224","45628","3325165093","218616495",2003,100);

            transactionManager.addAccount(account1);
            transactionManager.addAccount(account2);
            transactionManager.addAccount(account3);
            transactionManager.addAccount(account4);
            transactionManager.addAccount(account5);

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
