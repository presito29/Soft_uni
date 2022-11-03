package Tema1Hom;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    private static final String COLUM_NAME = "name";
    private static  final String UPDATE_TOWN_NAME = "UPDATE towns SET name = upper(name) where country = ?";
    private static  final String GET_TOWN_ALL = "SELECT t.name FROM towns t where t.country = ?";
    private static final String NO_TOWN_MASS = "No town names were affected.%n";
    private static final String TOWN_MASS = "%d town names were affected.%n";

    public static void main(String[] args) throws SQLException {

        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "presi", "100108002Pp");
       final String town_name = new Scanner(System.in).nextLine();

      final PreparedStatement statement = conn.prepareStatement(UPDATE_TOWN_NAME);
statement.setString(1, town_name);
final int updatedCount = statement.executeUpdate();

if (updatedCount == 0){
    System.out.println(NO_TOWN_MASS);
    return;
}
        System.out.printf(TOWN_MASS, updatedCount);
final PreparedStatement getAllTowns = conn.prepareStatement(GET_TOWN_ALL);
getAllTowns.setString(1, town_name);

final ResultSet resultSet = getAllTowns.executeQuery();

ArrayList<String> towns = new ArrayList<>();

        while (resultSet.next()){
            towns.add(resultSet.getString(COLUM_NAME));
        }
        System.out.println(towns);
    }
}
