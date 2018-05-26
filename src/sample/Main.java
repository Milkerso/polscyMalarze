package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Stage primaryStage;
    private GridPane GUI;

    List<LController> listaMalarzy = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        initGUI();
    }

    void czytajPliki(){
        File pliki = new File("src/malarze");
        File[] listaPlikow = pliki.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        });
        for(int i = 0; i < listaPlikow.length; i++) listaMalarzy.add(new LController(listaPlikow[i].getName()));

    }

    void initGUI(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GUI.fxml"));
            GUI = loader.load();
            czytajPliki();
            Controller guiController = loader.getController();
            guiController.setMalarze(this);

            Scene scene = new Scene(GUI);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Polscy Malarze");
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    List<LController> getListaMalarzy(){
        return  listaMalarzy;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
