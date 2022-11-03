package Tema1Hom;

import java.sql.*;


public class main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "presi", "100108002Pp");

        PreparedStatement stat = conn.prepareStatement(GET_VILLAINS_NAME);

        stat.setInt(1, 15);

        ResultSet result = stat.executeQuery();

        while (result.next()){
            System.out.printf("%s %d%n", result.getString("name"), result.getInt("count"));

        }
        conn.close();
    }

    private static String GET_VILLAINS_NAME ="select v.`name`, count(distinct mv.minion_id) as 'count' from villains v" +
            " join minions_villains mv on v.id = mv.villain_id" +
            " group by v.id having `count` > ?" +
            " order by `count` desc;";
}

