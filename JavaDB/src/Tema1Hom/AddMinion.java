package Tema1Hom;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class AddMinion {
    private static final String GET_TOWN_BY_NAME = "SELECT t.id FROM towns AS t WHERE t.name = ?";
    private static final String INSERT_INTO_TOWN = "INSERT INTO towns(name) VALUES (?)";
    private static final String TOWN_ADDED_FORMAT = "Town %s was added to the database.%n";
    private static final String COLUM_ID = "id";
    private static final String GET_VILLAIN_BY_NAME = "SELECT v.id FROM villains AS v WHERE v.name = ?";
    private static final String INSERT_INTO_VILLAIN = "INSERT INTO villains(name, evilness_factor) VALUES (?, ?)";
    private static final String EVILNESS_FACTOR = "evil";
    private static final String VILLAIN_ADDED_FORMAT = "Villain %s was added to the database.%n";
    private static final String SUCCESSFULLY_FORMAT = "Successfully added %s to be minion of %s.%n";
    private static final String INSERT_INTO_MINION = "INSERT INTO minions(name, age, town_id) VALUES (?, ?, ?)";
    private static final String GET_LAST_MINION = "SELECT m.id FROM minions AS m ORDER BY m.id DESC LIMIT 1 ";
    private static final String INSERT_INTO_MINIONS_VILLAIN = "INSERT INTO minions_villains(minion_id, villain_id) VALUES (?, ?)";

    private static int getId(Connection conn, List <String> arguments,
                             String selectQuery,
                             String insertQuery,
                             String printFormat) throws SQLException {

        final String name = arguments.get(0);

        final PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            final PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            for (int i = 1; i <= arguments.size() ; i++) {
                insertStatement.setString(i, arguments.get(i - 1));
            }
            insertStatement.executeUpdate();

            final ResultSet newResultSet = preparedStatement.executeQuery();
            newResultSet.next();
            final int id = newResultSet.getInt(COLUM_ID);

            System.out.printf(printFormat, name);

            return id;
        }

        return resultSet.getInt(COLUM_ID);
    }


    public static void main(String[] args) throws SQLException {
        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "presi", "100108002Pp");

        final Scanner scanner = new Scanner(System.in);

        final String[] minionInfo = scanner.nextLine().split(" ");

        final String minionName = minionInfo[1];
        final int minionAge = Integer.parseInt(minionInfo[2]);
        final String minionTown = minionInfo[3];

        final String villainName = scanner.nextLine().split(" ")[1];

       /* final int townId = getId(conn,
                List.of(minionTown),
                GET_TOWN_BY_NAME,
                INSERT_INTO_TOWN,
                TOWN_ADDED_FORMAT );

        final int villainId = getId(conn,
                List.of(villainName, EVILNESS_FACTOR),
                GET_VILLAIN_BY_NAME,
                INSERT_INTO_VILLAIN,
                VILLAIN_ADDED_FORMAT); */


        final int townId = getId(conn,
                List.of(minionTown),
                GET_TOWN_BY_NAME,
                INSERT_INTO_TOWN,
                TOWN_ADDED_FORMAT);

        final int villainId = getId(conn,
                List.of(villainName, EVILNESS_FACTOR),
                GET_VILLAIN_BY_NAME,
                INSERT_INTO_VILLAIN,
                VILLAIN_ADDED_FORMAT);

        final  PreparedStatement insertMinion = conn.prepareStatement(INSERT_INTO_MINION);

        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);

        insertMinion.executeUpdate();

        final PreparedStatement lastMinion = conn.prepareStatement(GET_LAST_MINION);

        final ResultSet resultSet = lastMinion.executeQuery();
        resultSet.next();
         final int lastMinionId = resultSet.getInt(COLUM_ID);

        final PreparedStatement insertIntoMinVill = conn.prepareStatement(INSERT_INTO_MINIONS_VILLAIN);
        insertIntoMinVill.setInt(1, lastMinionId);
        insertIntoMinVill.setInt(2, villainId);

        insertIntoMinVill.executeUpdate();

        System.out.printf(SUCCESSFULLY_FORMAT, minionName, villainName);

        conn.close();

    }

}