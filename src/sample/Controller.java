package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Controller implements Initializable{

   
    private LController wybranyMalarz;
    private int licznikObrazow;
    int counter = 1;
    private Main malarzeMain;
    private Stage stage;



    @FXML
    private ComboBox<String> cb;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Label Label;
    @FXML
    private ImageView imageViever;

    @FXML
    private void handleNextButton(ActionEvent event) {
        if(wybranyMalarz != null && (licznikObrazow < wybranyMalarz.getListaObrazow().size() - 1) )
        {
            licznikObrazow++;
            Label.setText((wybranyMalarz.getListaOpisowObrazow().get(licznikObrazow)));
            File file = new File("src/malarze/"+wybranyMalarz.getListaObrazow().get(licznikObrazow));
            Image image = new Image(file.toURI().toString());
            imageViever.setImage(image);
           
        }
  
    }

    @FXML
    private void handlePrevButton(ActionEvent event) {
        if(wybranyMalarz != null && (licznikObrazow > 0))
        {
            licznikObrazow--;
            Label.setText((wybranyMalarz.getListaOpisowObrazow().get(licznikObrazow)));
            File file = new File("src/malarze/"+wybranyMalarz.getListaObrazow().get(licznikObrazow));
            Image image = new Image(file.toURI().toString());
            imageViever.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb.setOnAction(action ->{
            Label.setText(cb.getValue() + "");
            for(LController malarz : malarzeMain.getListaMalarzy())
            {
                if(malarz.getDaneMalarza().equals(cb.getValue()))
                {
                    licznikObrazow = 0;
                    wybranyMalarz = malarz;
                    Label.setText((malarz.getListaOpisowObrazow().get(licznikObrazow)));
                    File file = new File("src/malarze/"+malarz.getListaObrazow().get(licznikObrazow));
                    Image image = new Image(file.toURI().toString());
                    imageViever.setImage(image);
                    imageViever.setPreserveRatio(true);
                }
            }

        });

    }

    public void setMalarze(Main malarze){
        this.malarzeMain = malarze;
        this.stage = malarze.getPrimaryStage();

        for(LController malarz : malarze.getListaMalarzy()) {
            cb.getItems().add(malarz.getDaneMalarza());
        }
    }


}
