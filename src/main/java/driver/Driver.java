package driver;
import manager.DataManager;
import model.Player;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = DataManager.getInstance();


        while (true) {

            System.out.println("                   ==Player Market Analyzer App==");
            System.out.println("          Choose the number of action below for continue app:");
            System.out.println("1 - Add player                         ||     2 - View all players");
            System.out.println("3 - View players by name               ||     4 - View players by age");
            System.out.println("5 - View players by team               ||     6 - Edit player's team");
            System.out.println("7 - Edit player's market value         ||     8 - Delete player");
            System.out.println("9 - Display mean of age                ||     10 - Display Median of age");
            System.out.println("11 - Display mean of market value      ||     12 - Display median of market value");
            System.out.println("13 - Display players ordered           ||     14 - exit");
            System.out.print("Enter your choice: ");





            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter player name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter player surname: ");
                    String surname = scanner.nextLine();

                    System.out.print("Enter player team: ");
                    String team = scanner.nextLine();

                    System.out.print("Enter player age: ");
                    int age = scanner.nextInt();

                    System.out.print("Enter player market value: ");
                    double value = scanner.nextDouble();

                    Player newPlayer = new Player(name, surname, age, team, value);
                    dataManager.insert(newPlayer);

                    System.out.println("Player added successfully!");
                    break;
                case 2:
                    List<Player> allPlayers = dataManager.getAllPlayers();

                    if (allPlayers.isEmpty()) {
                        System.out.println("No players found.");
                    } else {
                        System.out.println("All Players:");
                        for (Player player : allPlayers) {
                            System.out.println(player.toString());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter player name to search: ");
                    String playerNameToSearch = scanner.nextLine();

                    List<Player> playersByName = dataManager.getPlayersByName(playerNameToSearch);

                    if (playersByName.isEmpty()) {
                        System.out.println("No players found with the given name.");
                    } else {
                        System.out.println("Players with the given name:");
                        for (Player player : playersByName) {
                            System.out.println(player.toString());
                        }
                    }
                    break;


                case 4:
                    System.out.print("Enter maximum age: ");
                    int playerAge = scanner.nextInt();
                    scanner.nextLine();
                    List<Player> playersByAge = dataManager.getPlayersByAgeLessThan(playerAge);

                    if (playersByAge.isEmpty()) {
                        System.out.println("No players found with age less than " + playerAge + ".");
                    } else {
                        System.out.println("Players with age less than " + playerAge + ":");
                        for (Player player : playersByAge) {
                            System.out.println(player.toString());
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter team name: ");
                    String teamName = scanner.nextLine();

                    List<Player> playersByTeam = dataManager.getPlayersByTeam(teamName);

                    if (playersByTeam.isEmpty()) {
                        System.out.println("No players found for the team: " + teamName);
                    } else {
                        System.out.println("Players for the team " + teamName + ":");
                        for (Player player : playersByTeam) {
                            System.out.println(player.toString());
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter player ID to edit team: ");
                    int playerIdForTeamEdit = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new team: ");
                    String newTeam = scanner.nextLine();

                    dataManager.updatePlayerTeamById(playerIdForTeamEdit, newTeam);
                    break;


                case 7:
                    System.out.print("Enter player ID to edit market value: ");
                    int playerIdForMarketValueEdit = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new market value: ");
                    double newMarketValue = scanner.nextDouble();

                    dataManager.updatePlayerMarketValueById(playerIdForMarketValueEdit, newMarketValue);
                    break;

                case 8:
                    System.out.print("Enter player ID to delete: ");
                    int playerIdToDelete = scanner.nextInt();
                    scanner.nextLine();

                    dataManager.deletePlayer(playerIdToDelete);
                    break;
                case 9:
                    double meanAge = dataManager.calculateMeanAge();
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String formattedMeanAge = decimalFormat.format(meanAge);

                    System.out.println("Mean age of players: " + formattedMeanAge);
                    break;
                case 10:
                    double medianAge = dataManager.calculateMedianAge();
                    DecimalFormat decimalFormat1 = new DecimalFormat("#.##");
                    String formattedMedianAge = decimalFormat1.format(medianAge);

                    System.out.println("Median age of players: " + formattedMedianAge);
                    break;
                case 11:
                    double meanMarketValue = dataManager.calculateMeanMarketValue();
                    DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
                    String formattedMeanValue = decimalFormat2.format(meanMarketValue);
                    System.out.println("Mean market value of players: " + formattedMeanValue);
                    break;
                case 12:
                    double medianMarketValue = dataManager.calculateMedianMarketValue();
                    DecimalFormat decimalFormat3 = new DecimalFormat("#.##");
                    String formattedMedianValue = decimalFormat3.format(medianMarketValue);
                    System.out.println("Median maket value of players: " + formattedMedianValue);
                    break;
                case 13:
                    System.out.println("Choose an option to order by:");
                    System.out.println("1 - Name         ||     2 - Surname");
                    System.out.println("3 - Team         ||     4 - Age");
                    System.out.println("5 - Market value        '");
                    System.out.print("          Enter your choice: ");

                    int orderChoice = scanner.nextInt();
                    scanner.nextLine();

                    List<Player> orderedPlayers = null;

                    switch (orderChoice) {
                        case 1:
                            orderedPlayers = dataManager.getPlayersOrderedByName();
                            break;
                        case 2:

                            orderedPlayers = dataManager.getPlayersOrderedBySurname();
                            break;
                        case 3:
                            orderedPlayers = dataManager.getPlayersOrderedByTeam();
                            break;
                        case 4:
                            orderedPlayers = dataManager.getPlayersOrderedByAge();
                            break;
                        case 5:
                            orderedPlayers = dataManager.getPlayersOrderedByValue();
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }

                    if (orderedPlayers != null) {
                        if (orderedPlayers.isEmpty()) {
                            System.out.println("No players found.");
                        } else {
                            System.out.println("Ordered Players:");
                            for (Player player : orderedPlayers) {
                                System.out.println(player.toString());
                            }
                        }
                    }
                    break;

                case 14:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
