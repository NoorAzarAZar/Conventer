package com.example.conventer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller  {




    /////////////////////////length part




    @FXML
    protected void onButtonClickCurrency() throws IOException{
       FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("Currency.fxml"));
       Stage stage = new Stage(); stage.setTitle("");
       Scene scene = new Scene(fxmlLoader.load());
       stage.setScene(scene);
       stage.show();

    }


    @FXML
    protected void onButtonClickLength() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("Length.fxml"));
        Stage stage = new Stage(); stage.setTitle("");
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    protected void onButtonClickWeight() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("Weight.fxml"));
        Stage stage = new Stage(); stage.setTitle("");
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    protected void onButtonClickArea() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("Area.fxml"));
        Stage stage = new Stage(); stage.setTitle("");
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }

















}