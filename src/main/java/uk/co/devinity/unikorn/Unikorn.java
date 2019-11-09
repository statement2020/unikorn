package uk.co.devinity.unikorn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import uk.co.devinity.unikorn.exceptions.CouldNotExecuteQueryException;

public class Unikorn {

    private final UnikornConfiguration configuration;

    public Unikorn(final UnikornConfiguration configuration) {
        this.configuration = configuration;
    }

    public ValidatableResponse executeQuery(final String query) {
        try (final Connection connection = configuration.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return new ValidatableResponse(preparedStatement.getResultSet());
        } catch (final SQLException ex) {
            throw new CouldNotExecuteQueryException("Failed to execute query", ex);
        }
    }
}
