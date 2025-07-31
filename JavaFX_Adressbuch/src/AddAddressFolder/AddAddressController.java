
package AddAddressFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddAddressController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfStraße;
    @FXML
    private TextField tfTelefonnummer;
    @FXML
    private Button btnSave;
    @FXML
    private Label lblInfo;
    @FXML
    private Label lblSaved;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @FXML
    private void home(MouseEvent event) throws IOException {
        Stage addAddress = (Stage) imgView.getScene().getWindow(); 
        addAddress.hide();

        Stage primaryStage = new Stage(); 

        Parent root = FXMLLoader.load(getClass().getResource("/javafx/project/template/Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    @FXML
    private void saveAddress(ActionEvent event) throws IOException {

        if (tfName.getText().isEmpty() || tfStraße.getText().isEmpty() || tfTelefonnummer.getText().isEmpty()) {
            lblSaved.setText("");
            lblInfo.setText("Please fill out all the information!");
        
        } 
        else {
            addAddress(tfName.getText(), tfStraße.getText(), tfTelefonnummer.getText());
            
            lblInfo.setText("");
            lblSaved.setText("Information was saved!");

            // Felder leeren
            tfName.setText("");
            tfStraße.setText("");
            tfTelefonnummer.setText("");
        }
    
    }
    
    
    private void addAddress(String name, String straße, String telefonnummer) {
        String speicherOrt = "C:\\Users\\morit\\OneDrive\\Desktop\\Programmieren\\Java\\Java Buch\\Java_Buch_GUI\\JavaFX_Adressbuch\\src\\saves";
        File ordner = new File(speicherOrt);
        
        if (!ordner.exists()) {
            ordner.mkdirs(); 
        }
        
        File datei = new File(ordner, "Adressen.txt");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datei, true))) {
            bw.write("\n");
            bw.write(tfName.getText() + "\n");
            bw.write(tfStraße.getText() + "\n");
            bw.write(tfTelefonnummer.getText() + "\n");

        } 
        catch (IOException ex) {
            Logger.getLogger(AddAddressController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
