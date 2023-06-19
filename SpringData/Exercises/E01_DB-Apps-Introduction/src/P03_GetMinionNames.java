import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P03_GetMinionNames {

    private static final String COlUMN_LABEL_NAME = "name";
    private static final String COlUMN_LABEL_AGE = "age";
    private static final String GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID =
            "SELECT m.name, m.age\n" +
                    "FROM minions AS m\n" +
                    "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                    "WHERE mv.villain_id = ?;";
    private static final String GET_VILLAIN_NAME_BY_ID =
            "SELECT v.name FROM villains AS v WHERE v.id = ?";
    private static final String NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSqlConnection();

        final int villainId = new Scanner(System.in).nextInt();


        final PreparedStatement statementForVillain = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        statementForVillain.setInt(1, villainId);

        final ResultSet villainResultSet = statementForVillain.executeQuery();

        if (!villainResultSet.next()) {
            System.out.printf(NO_VILLAIN_FORMAT,villainId);

            connection.close();
            return;
        }

        final PreparedStatement statementForMinions = connection.prepareStatement(GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID);
        statementForMinions.setInt(1, villainId);

        final ResultSet minionsResultSet = statementForMinions.executeQuery();

        print(villainResultSet, minionsResultSet);

        connection.close();
    }

    private static void print(ResultSet villains, ResultSet minions) throws SQLException {
        final String villainName = villains.getString(COlUMN_LABEL_NAME);
        System.out.printf(VILLAIN_FORMAT, villainName);

        for (int index = 1; minions.next(); index++) {
            final String minionName = minions.getString(COlUMN_LABEL_NAME);
            final int minionAge = minions.getInt(COlUMN_LABEL_AGE);

            System.out.printf(MINION_FORMAT, index, minionName, minionAge);
        }
    }
}
