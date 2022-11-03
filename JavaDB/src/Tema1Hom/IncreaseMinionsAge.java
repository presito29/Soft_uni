package Tema1Hom;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

    Scanner scan = new Scanner(System.in);
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/minions_db", "presi", "100108002Pp");
        System.out.print("Enter minion IDs: ");
    List<String> minionIDs = Arrays.asList(scan.nextLine().split("\\s+").clone());
    Statement upd = conn.createStatement();
        upd.executeUpdate(String.format("update minions set age = age + 1, `name` = lower(`name`) where id in (%s);", String.join(", ", minionIDs)));

    ResultSet rs = conn.createStatement().executeQuery(String.format("select * from minions"));
        while (rs.next()) {
        System.out.printf("%s %d%n", rs.getString(2), rs.getInt(3));
    }
}
}
