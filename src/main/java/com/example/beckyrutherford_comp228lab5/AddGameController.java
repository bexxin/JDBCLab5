package com.example.beckyrutherford_comp228lab5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class AddGameController {


    public static void showAddGameForm(ListView<String> gamesListBox) {
        //Create new stage for Add Game Form
        Stage addGameStage = new Stage();
        addGameStage.initModality(Modality.APPLICATION_MODAL);
        addGameStage.setTitle("Add New Game");

        //Form components
        Label addGameLabel= new Label("Game Title");
        TextField gameTitleField = new TextField();
        Button addButton =new Button("Add Game");

        //AddButton Logic
        addButton.setOnAction(actionEvent -> {
            String gameTitle = gameTitleField.getText();
            List<String> updatedGamesList = DatabaseConnection.addGameToList(gameTitle);
            gamesListBox.getItems().clear();
            gamesListBox.getItems().addAll(updatedGamesList);

            addGameStage.close();
        });

        VBox addGameForm = new VBox();
        addGameForm.getChildren().addAll(addGameLabel,gameTitleField,addButton);

        //Add VBox to scene/stage
        Scene addGameScene = new Scene(addGameForm);
        addGameStage.setScene(addGameScene);
        addGameStage.showAndWait();


    }
}