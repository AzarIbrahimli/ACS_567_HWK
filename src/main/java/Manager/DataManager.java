package Manager;

import Model.Player;

import java.sql.*;

public class DataManager {
    private static DataManager instance;
    private Connection connection;

    private DataManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlayerMarket", "postgres", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

//    public void insert(Connection connection, String name, String surname, int age, String team, double value){
//        Statement statement;
//        try{
//            String query = String.format("insert into information(name, surname, age, team, value) values ('%s', '%s', '%d', '%s', '%f')", name, surname, age, team, value );
//            statement = connection.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Updated");
//        } catch (Exception e){
//            System.out.println(e);
//        }
//
//    }


    public void insert(Player player) {
        try {
            String query = "INSERT INTO information (name, surname, team, age, value) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, player.getName());
                preparedStatement.setString(2, player.getSurname());
                preparedStatement.setString(3, player.getTeam());
                preparedStatement.setInt(4, player.getAge());
                preparedStatement.setDouble(5, player.getValue());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
        }

    }
}
