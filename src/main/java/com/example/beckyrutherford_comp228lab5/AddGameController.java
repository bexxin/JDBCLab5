package com.example.beckyrutherford_comp228lab5;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        addGameForm.setAlignment(Pos.CENTER);
        VBox.setMargin(gameTitleField, new Insets(15,15,15,15));
        VBox.setMargin(addButton, new Insets(0,0,15,0));


        //Styles
        addGameLabel.setStyle("-fx-font-size:28; -fx-text-fill: #FF6347;");
        addButton.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");



        //Add VBox to scene/stage
        Scene addGameScene = new Scene(addGameForm);
        addGameScene.getRoot().setStyle("-fx-background-color: #1F1F1F; ");
        addGameStage.setScene(addGameScene);
        addGameStage.showAndWait();


    }
}