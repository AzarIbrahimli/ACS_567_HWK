package manager;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private Connection connection;

    private DataManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://postgresql-azar98.alwaysdata.net:5432/azar98_playermarket", "azar98", "Alwaysdatacode777");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }




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
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information";

            getAll(players, query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }

    public List<Player> getPlayersByName(String playerName) {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information WHERE name = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, playerName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    mapResultSet(players, resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }



    public List<Player> getPlayersByAgeLessThan(int maxAge) {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information WHERE age < ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, maxAge);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    mapResultSet(players, resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }

    public List<Player> getPlayersByTeam(String teamName) {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information WHERE team = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, teamName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    mapResultSet(players, resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }


    public void updatePlayerTeamById(int id, String newTeam) {
        try {
            String query = "UPDATE information SET team = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newTeam);
                preparedStatement.setInt(2, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Player's team updated successfully!");
                } else {
                    System.out.println("Player not found or team update failed.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void updatePlayerMarketValueById(int id, double newMarketValue) {
        try {
            String query = "UPDATE information SET value = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, newMarketValue);
                preparedStatement.setInt(2, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Player's market value updated successfully!");
                } else {
                    System.out.println("Player not found or market value update failed.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deletePlayer(int playerId) {
        try {
            String query = "DELETE FROM information WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, playerId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Player deleted successfully!");
                } else {
                    System.out.println("Player not found or deletion failed.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public double calculateMeanAge() {
        double meanAge = 0;

        try {
            String query = "SELECT AVG(age) AS mean_age FROM information";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (resultSet.next()) {
                    meanAge = resultSet.getDouble("mean_age");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return meanAge;
    }



    public double calculateMedianAge() {
        List<Integer> ages = new ArrayList<>();

        try {
            String query = "SELECT age FROM information";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    int age = resultSet.getInt("age");
                    ages.add(age);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Collections.sort(ages);

        int size = ages.size();
        double medianAge;


        if (size % 2 == 0) {
            int mid1 = ages.get(size / 2 - 1);
            int mid2 = ages.get(size / 2);
            medianAge = (mid1 + mid2) / 2.0;
        } else {
            medianAge = ages.get(size / 2);
        }

        return medianAge;
    }

    public double calculateMeanMarketValue() {
        double meanMarketValue = 0;

        try {
            String query = "SELECT AVG(value) AS mean_market_value FROM information";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (resultSet.next()) {
                    meanMarketValue = resultSet.getDouble("mean_market_value");

                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return meanMarketValue;
    }


    public double calculateMedianMarketValue() {
        List<Double> marketValues = new ArrayList<>();

        try {
            String query = "SELECT value FROM information";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    double marketValue = resultSet.getDouble("value");
                    marketValues.add(marketValue);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        Collections.sort(marketValues);

        int size = marketValues.size();
        double medianMarketValue;


        if (size % 2 == 0) {
            double mid1 = marketValues.get(size / 2 - 1);
            double mid2 = marketValues.get(size / 2);
            medianMarketValue = (mid1 + mid2) / 2.0;
        } else {
            medianMarketValue = marketValues.get(size / 2);
        }

        return medianMarketValue;
    }


    public List<Player> getPlayersOrderedByName() {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information ORDER BY name";

            getAll(players, query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }

    private void getAll(List<Player> players, String query) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            mapResultSet(players, resultSet);
        }
    }

    private void mapResultSet(List<Player> players, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String team = resultSet.getString("team");
            int age = resultSet.getInt("age");
            double marketValue = resultSet.getDouble("value");

            Player player = new Player(id, name, surname, age, team, marketValue);
            players.add(player);
        }
    }

    public List<Player> getPlayersOrderedBySurname() {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information ORDER BY surname";

            getAll(players, query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }


    public List<Player> getPlayersOrderedByAge() {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information ORDER BY age";

            getAll(players, query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }


    public List<Player> getPlayersOrderedByTeam() {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information ORDER BY team";

            getAll(players, query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }



    public List<Player> getPlayersOrderedByValue() {
        List<Player> players = new ArrayList<>();

        try {
            String query = "SELECT * FROM information ORDER BY value";

            getAll(players, query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return players;
    }

}
