package com.example.beckyrutherford_comp228lab5;


import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnection {
    private static Connection connection;
    //CONNECT TO DATABASE
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

    //
    //CRUD FUNCTIONALITIES
    //

    //CREATE Game-Add game to game table
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


          //Close st and connection
          stGames.close();
          myConnection.close();
          //Refresh games list
            gamesList = getGamesList();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gamesList;
    }


    //RETRIEVE GAMES TABLE METHOD
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

    public static List<String> addPlayerToList(String firstNameData, String lastNameData, String addressData, String postalCodeData, String provinceData, String phoneNumberData) {
        List<String> updatedPlayersList = new ArrayList<>();
        try {//Establish Connection
            Connection myConnection = DatabaseConnection.connect("COMP228_F23_dah_27", "password");
            //Create statement
            Statement stPlayers = myConnection.createStatement();
            //Build SQL statement.
            String insertPlayer = "INSERT INTO Player (first_name, last_name, address, postal_code, province, phone_number) VALUES ('"+ firstNameData +"', '"+ lastNameData +"', '"+ addressData +"', '"+ postalCodeData +"', '"+ provinceData+ "', '"+ phoneNumberData +"') ";
            stPlayers.executeUpdate(insertPlayer);
            //Close st and connection
            stPlayers.close();
            myConnection.close();
            //Refresh players list
            updatedPlayersList=getPlayersList();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return updatedPlayersList;
    }

    public static List<String> getPlayersList() {
        List<String> playersList = new ArrayList<>();
        try {
            //Establish Connection
            Connection myConnection = DatabaseConnection.connect("COMP228_F23_dah_27","password");
            //Create statement
            Statement stPlayers = myConnection.createStatement();
            //get results from Games table
            ResultSet playersResultSet = stPlayers.executeQuery("SELECT first_name, last_name FROM Player");
            //Debugging code
            ResultSetMetaData playersMetaData = playersResultSet.getMetaData();
            System.out.println(playersMetaData.getColumnName(1));


            //Add first_name, last_name column to playersList array
            try{
                while(playersResultSet.next()){
                    System.out.println("Inside the while loop for getting players.");
                    String player = playersResultSet.getString("first_name")+" "+ playersResultSet.getString("last_name");
                    System.out.println("The players name" + player);
                    playersList.add(player);

                }}
            catch(SQLException ex){
                ex.printStackTrace();
            }

           playersResultSet.close();
            stPlayers.close();
            myConnection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playersList;
    }

    public static Map<String, String> getPlayerDemoData(String firstName, String lastName) {
        Map<String, String> playerDemoData = new HashMap<>();
        try{
            //Establish Connection
            Connection myConnection = DatabaseConnection.connect("COMP228_F23_dah_27","password");
            //Create statement
            Statement stPlayersDemoData = myConnection.createStatement();
            //get results from Games table
            ResultSet playersDemoDataResultSet = stPlayersDemoData.executeQuery("SELECT * FROM Player WHERE first_name= '"+ firstName+"' AND last_name= '"+ lastName +"'");
            //Store results of current player demo data in playerDemoData
            while(playersDemoDataResultSet.next()){
                playerDemoData.put("Address",playersDemoDataResultSet.getString("address"));
                playerDemoData.put("Postal Code",playersDemoDataResultSet.getString("postal_code"));
                playerDemoData.put("Province", playersDemoDataResultSet.getString("province"));
                playerDemoData.put("Phone Number", playersDemoDataResultSet.getString("phone_number"));
            }
            playersDemoDataResultSet.close();
            stPlayersDemoData.close();
            myConnection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playerDemoData;
    }
    //Method to update player demographics
    public static void updatePlayerDemo(String firstNameData, String lastNameData, String addressData, String postalCodeData, String provinceData, String phoneNumberData){
        try{
            //Establish connection
            Connection myConnection = DatabaseConnection.connect("COMP228_F23_dah_27", "password");
            //Create statement
            Statement stUpdatePlayerDemo = myConnection.createStatement();
            //Build SQL statement for updating
            String updatePlayerSQLQuery="UPDATE Player SET address = '" + addressData + "',postal_code = '" + postalCodeData + "', province = '" + provinceData + "', phone_number = '" + phoneNumberData + "' WHERE first_name = '"
+firstNameData + "' AND last_name = '" + lastNameData +"'";
            //execute statement
            stUpdatePlayerDemo.executeUpdate(updatePlayerSQLQuery);
            stUpdatePlayerDemo.close();
            myConnection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}


