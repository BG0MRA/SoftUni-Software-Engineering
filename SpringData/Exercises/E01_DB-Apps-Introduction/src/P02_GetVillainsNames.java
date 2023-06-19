import java.sql.*;
import java.util.Properties;

public class P02_GetVillainsNames {
    private static final String GET_VILLAINS_NAMES =
            "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS minions_count " +
                    "FROM villains AS v " +
                    "JOIN minions_villains AS mv ON v.id = mv.villain_id " +
                    "GROUP BY v.id " +
                    "HAVING minions_count > ? " +
                    "ORDER BY minions_count DESC;";

    private static final String PRINT_FORMAT = "%s %d";
    private static final String COlUMN_LABEL_NAME = "name";
    private static final String COlUMN_LABEL_MINIONS_COUNT = "minions_count";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSqlConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        statement.setInt(1, 15);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            print(resultSet);
        }

        connection.close();
    }

    private static void print(ResultSet resultSet) throws SQLException {
        final String name = resultSet.getString(COlUMN_LABEL_NAME);
        final int minionsCount = resultSet.getInt(COlUMN_LABEL_MINIONS_COUNT);

        System.out.printf(PRINT_FORMAT, name, minionsCount);
    }
}
