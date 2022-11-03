package Tema1Hom;

import java.sql.*;
import java.util.Scanner;

public class RemoveVillain {

    private static final String NO_SUCH_VILLAIN = "No such villain was found";
    private static final String GET_NAME_VILLAIN = "SELECT `name` FROM villains WHERE id = ? ";
    private static final String DELETE_VM = "DELETE FROM minions_villains WHERE villain_id = ?";
    private static final String DELETE_M = "DELETE FROM villains WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "presi", "100108002Pp");

        int villainid = Integer.parseInt(scanner.nextLine());

        PreparedStatement preparedStatement = conn.prepareStatement(GET_NAME_VILLAIN);
        preparedStatement.setInt(1, villainid);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.println(NO_SUCH_VILLAIN);
            conn.close();
        }

        conn.setAutoCommit(false);

        try {
            String villain = resultSet.getString("name");

            PreparedStatement freeMinStat = conn.prepareStatement( DELETE_VM);
            freeMinStat.setInt(1, villainid);
            int freeMinions = freeMinStat.executeUpdate();

            PreparedStatement statement = conn.prepareStatement(DELETE_M);
            statement.setInt(1, villainid);
            statement.executeUpdate();

            System.out.printf("%s was deleted%n", villain);
            System.out.printf("%d minions released%n", freeMinions);

        } catch (SQLException exception) {
            exception.printStackTrace();
            conn.rollback();
        }

        conn.close();
    }
}