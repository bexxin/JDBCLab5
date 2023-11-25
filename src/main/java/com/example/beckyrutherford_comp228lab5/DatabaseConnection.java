package com.example.beckyrutherford_comp228lab5;


import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static Connection connection;
    //CONNECT METHOD
    public static Connection connect(String user, String password) {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", user, password);
            System.out.println("Connection established successfully!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //Add Game to Game table
    public static List<String> addGameToList(String gameName){
        List<String> gamesList;
        try{
         //Establish Connection
            Connection myConnection = DatabaseConnection.connect("COMP228_F23_dah_27","password");
          //Create statement
          Statement stGames = myConnection.createStatement();
          //Build SQL statement
            String insertGame = "insert into Game (GAME_TITLE) VALUES ('"+ gameName +"')";
          stGames.executeUpdate(insertGame);
          //Refresh games list
            gamesList = getGamesList();

          //Close st and connection
          stGames.close();
          myConnection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gamesList;
    }




    //GET GAMES TABLE METHOD
    public static List<String> getGamesList() {
         List<String> gamesList = new ArrayList<>();
        try {
            //Establish Connection
            Connection myConnection = DatabaseConnection.connect("COMP228_F23_dah_27","password");
            //Create statement
            Statement stGames = myConnection.createStatement();
            //get results from Games table
            ResultSet gamesResultSet = stGames.executeQuery("SELECT GAME_TITLE FROM GAME");
            //Debugging code
            ResultSetMetaData gamesMetaData = gamesResultSet.getMetaData();
            System.out.println(gamesMetaData.getColumnName(1));


            //Add game_title column to gameslist array
            try{
                while(gamesResultSet.next()){
                System.out.println("Inside the while loop.");
                    String game = gamesResultSet.getString(1);
                    System.out.println("The game name:"+ game);
                gamesList.add(game);

            }}
            catch(SQLException ex){
                ex.printStackTrace();
            }

            gamesResultSet.close();
            stGames.close();
            myConnection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return gamesList;
    }
}


