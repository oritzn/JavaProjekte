package javafx.project.template;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainController implements Initializable {

    @FXML
    private Button btnHinzufügen;
    @FXML
    private Button btnAnzeigen;
    @FXML
    private Button btnLöschen;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addAddress(ActionEvent event) throws IOException {
        Stage main = (Stage) btnHinzufügen.getScene().getWindow(); 
        main.close();

        Stage primaryStage = new Stage(); 

        Parent root = FXMLLoader.load(getClass().getResource("/AddAddressFolder/AddAddress.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @FXML
    private void showAdress(ActionEvent event) throws IOException {
        Stage main = (Stage) btnAnzeigen.getScene().getWindow(); 
        main.close();

        Stage primaryStage = new Stage(); 

        Parent root = FXMLLoader.load(getClass().getResource("/ShowAddressFolder/ShowAddress.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void deletAdress(ActionEvent event) throws IOException {
        Stage main = (Stage) btnLöschen.getScene().getWindow(); 
        main.close();

        Stage primaryStage = new Stage(); 

        Parent root = FXMLLoader.load(getClass().getResource("/DeletAddressFolder/DeletAddress.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
