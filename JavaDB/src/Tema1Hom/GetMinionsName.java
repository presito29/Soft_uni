package Tema1Hom;

import java.sql.*;
import java.util.Scanner;

public class GetMinionsName {
    public static void main(String[] args) throws SQLException {

         final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "presi", "100108002Pp");
          final  PreparedStatement villainStat = conn.prepareStatement(GET_VILLAIN_NAME);

          final int villainId = new Scanner(System.in).nextInt();

          villainStat.setInt(1,villainId);

         final ResultSet villainSet =  villainStat.executeQuery();

        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN, villainId);
            conn.close();
            return;
        }

      final String name = villainSet.getString(COLUM_NAME);
        System.out.printf(VILLAIN_FORMAT, name);

     final PreparedStatement minionsStat = conn.prepareStatement(GET_MINIONS);
        minionsStat.setInt(1, villainId);

       final ResultSet minionSet = minionsStat.executeQuery();

        for (int i = 1; minionSet.next(); i++) {
            final String mimName = minionSet.getString(COLUM_NAME);
            final int mimAge = minionSet.getInt(COLUM_AGE);

            System.out.printf(MINION_FORMAT, i, mimName, mimAge);
        }
        conn.close();
    }

    private static final String GET_MINIONS = "SELECT m.name, m.age FROM minions AS m" +
    " JOIN minions_villains mv on m.id = mv.minion_id" +
    " WHERE mv.villain_id = ?;";

    private static final String NO_VILLAIN = "No villain with ID %d exists in the database.";

    private static final String GET_VILLAIN_NAME = "SELECT * FROM villains v " +
            "WHERE v.id = ?;";

    private static final String COLUM_NAME = "name";

    private static final String VILLAIN_FORMAT = "Villain: %s%n";

    private static final String MINION_FORMAT = "%d. %s %d %n";

    private static final  String COLUM_AGE = "age";
}
