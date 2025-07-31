
package ShowAddressFolder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ShowAddressController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private ListView<String> lvAdressen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\morit\\OneDrive\\Desktop\\Programmieren\\Java\\Java Buch\\Java_Buch_GUI\\JavaFX_Adressbuch\\src\\saves\\Adressen.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String linie;

            // Solange es Zeilen gibt, lese sie ein
            boolean firstLine = true; // Flag, um zu prüfen, ob es sich um die erste Zeile handelt

            while ((linie = br.readLine()) != null) {
                if (linie.trim().isEmpty() && !firstLine) {
                    // Wenn eine Leerzeile vorliegt, die nicht die erste Zeile ist, füge sie hinzu
                    lvAdressen.getItems().add(""); 
                } else if (!linie.trim().isEmpty()) {
                    // Wenn es sich um eine nicht-leere Zeile handelt, füge diese in die Liste ein
                    lvAdressen.getItems().add(linie);
                }
                firstLine = false;
            }

            br.close();
            
        } 
        catch (IOException ioAusnahme) {
            System.out.println("Datei konnte nicht ausgelesen werden: " + ioAusnahme.getMessage());
        }
        
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
    
}
