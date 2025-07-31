
package DeletAddressFolder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class DeletAddressController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private ListView<String> lvAdressenDelet;
    @FXML
    private ImageView ivDelet;
    @FXML
    private Label lblNotification;
    
    ObservableList<String> toDelet = FXCollections.observableArrayList(); // Kopie für toDelet

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lvAdressenDelet.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        readFile();
        
        //Wird aufgerufen wenn auf ein Item des ListViews geklickt wurde
        lvAdressenDelet.setOnMouseClicked(event -> {
            
            int currentIndex = lvAdressenDelet.getSelectionModel().getSelectedIndex();
            ObservableList<String> items = lvAdressenDelet.getItems();

            lblNotification.setText("");
            lblNotification.setTextFill(Color.BLACK);
            int lastItem = lvAdressenDelet.getItems().size() - 1;
            
            String selectedItem = items.get(currentIndex);
            
            if(selectedItem.trim().isEmpty()) {
                lblNotification.setText("Bitte Valides Feld auswählen");
                lblNotification.setTextFill(Color.RED);
                ivDelet.setDisable(true);
                
            }else {
                
                ivDelet.setDisable(false);


                String nextSelectedItem = (currentIndex + 1 < items.size()) ? items.get(currentIndex + 1) : null;
                String nextNextSelectedItem = (currentIndex + 2 < items.size()) ? items.get(currentIndex + 2) : null;

                String previousSelectedItem = (currentIndex - 1 >= 0) ? items.get(currentIndex - 1) : null;
                String previousPreciousSelectedItem = (currentIndex - 2 >= 0) ? items.get(currentIndex - 2) : null;



                if (currentIndex > 0 && currentIndex < lastItem) {

                    if(!nextSelectedItem.trim().isEmpty() && !previousSelectedItem.trim().isEmpty()) {
                        
                        lvAdressenDelet.getSelectionModel().clearSelection();

                        lvAdressenDelet.getSelectionModel().select(currentIndex);
                        lvAdressenDelet.getSelectionModel().select(currentIndex + 1);
                        lvAdressenDelet.getSelectionModel().select(currentIndex - 1);

                        lblNotification.setText(previousSelectedItem + " löschen?");
                        lblNotification.setTextFill(Color.GREEN);

                    }
                    else if(nextSelectedItem.trim().isEmpty() && !previousSelectedItem.trim().isEmpty()){
                        
                        lvAdressenDelet.getSelectionModel().clearSelection();

                        lvAdressenDelet.getSelectionModel().select(currentIndex);
                        lvAdressenDelet.getSelectionModel().select(currentIndex - 1);
                        lvAdressenDelet.getSelectionModel().select(currentIndex - 2);

                        lblNotification.setText(previousPreciousSelectedItem + " löschen?");
                        lblNotification.setTextFill(Color.GREEN);

                    }
                    else if(!nextSelectedItem.trim().isEmpty() && previousSelectedItem.trim().isEmpty()) {
                        
                        lvAdressenDelet.getSelectionModel().clearSelection();

                        lvAdressenDelet.getSelectionModel().select(currentIndex);
                        lvAdressenDelet.getSelectionModel().select(currentIndex + 1);
                        lvAdressenDelet.getSelectionModel().select(currentIndex + 2);

                        lblNotification.setText(selectedItem + " löschen?");
                        lblNotification.setTextFill(Color.GREEN);

                    }

                } 
                else if(currentIndex == 0) {    //Falls erstes Item wird autmatisch 1,2,3 genommen
                    
                    lvAdressenDelet.getSelectionModel().clearSelection();
                    
                    lvAdressenDelet.getSelectionModel().select(currentIndex);
                    lvAdressenDelet.getSelectionModel().select(currentIndex + 1);
                    lvAdressenDelet.getSelectionModel().select(currentIndex + 2);

                    lblNotification.setText(selectedItem + " löschen?");
                    lblNotification.setTextFill(Color.GREEN);

                }
                else if(currentIndex == lastItem) {     //Falls letztes Item wird automatisch 3,2,1 genommen
                    
                    lvAdressenDelet.getSelectionModel().clearSelection();
                    
                    lvAdressenDelet.getSelectionModel().select(currentIndex);
                    lvAdressenDelet.getSelectionModel().select(currentIndex - 1);
                    lvAdressenDelet.getSelectionModel().select(currentIndex - 2);
                    
                    lblNotification.setText(previousPreciousSelectedItem + " löschen?");
                    lblNotification.setTextFill(Color.GREEN);

                }
                else {
                    lblNotification.setText("Bitte Valides Feld auswählen");
                    lblNotification.setTextFill(Color.RED);
                    ivDelet.setDisable(true);
                }
                
            
            }
            
        });
        
    }   
    
    
    @FXML
    private void delet(MouseEvent event) {
        
        ObservableList<Integer> selectedIndices = lvAdressenDelet.getSelectionModel().getSelectedIndices();
        List<Integer> indices = new ArrayList<>(selectedIndices);
        indices.sort(Comparator.reverseOrder());
        
        for (int index : indices) {
            lvAdressenDelet.getItems().remove(index);
        }
        
        String filePath = "C:\\Users\\morit\\OneDrive\\Desktop\\Programmieren\\Java\\Java Buch\\Java_Buch_GUI\\JavaFX_Adressbuch\\src\\saves\\Adressen.txt";
        File file = new File(filePath);
        
        //neuer listView ohne die gelöschten items, dieser wird nun in das Adressen file geschrieben ohne überflüssige leerzeilen
        ObservableList<String> newListView = lvAdressenDelet.getItems();
        
        String speicherOrt = "C:\\Users\\morit\\OneDrive\\Desktop\\Programmieren\\Java\\Java Buch\\Java_Buch_GUI\\JavaFX_Adressbuch\\src\\saves";
        File ordner = new File(speicherOrt);
        File datei = new File(ordner, "Adressen.txt");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) { // false = überschreiben
            boolean lastLineEmpty = false; // Merkt sich, ob die letzte Zeile leer war

            for (String adresse : newListView) {
                if (adresse.trim().isEmpty()) { // Falls Zeile leer ist
                    if (lastLineEmpty) {
                        continue; // Überspringe diese Zeile, wenn die vorherige auch leer war
                    }
                    lastLineEmpty = true; // Setze Flag, dass eine Leerzeile geschrieben wurde
                } else {
                    lastLineEmpty = false; // Setze das Flag zurück, da eine echte Zeile kommt
                }

                writer.write(adresse);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben der Datei: " + e.getMessage());
        }
        
        lvAdressenDelet.getItems().clear();
        readFile();
        
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
    
    private void readFile() {
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
                    lvAdressenDelet.getItems().add(""); 
                } else if (!linie.trim().isEmpty()) {
                    // Wenn es sich um eine nicht-leere Zeile handelt, füge diese in die Liste ein
                    lvAdressenDelet.getItems().add(linie);
                }
                firstLine = false;
            }

            br.close();
            
        } 
        catch (IOException ioAusnahme) {
            System.out.println("Datei konnte nicht ausgelesen werden: " + ioAusnahme.getMessage());
        }
    }
    
}
