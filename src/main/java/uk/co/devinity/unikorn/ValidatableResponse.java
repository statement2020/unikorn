package uk.co.devinity.unikorn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import uk.co.devinity.unikorn.exceptions.NoResultsFoundException;

public class ValidatableResponse {
    private final ResultSet resultSet;
    private final ExplodedResultSet explodedResultSet;

    public ValidatableResponse(final ResultSet resultSet) {
        this.resultSet = resultSet;
        this.explodedResultSet = explodeResultSet(resultSet);
    }

    public ValidatableResponse hasSize(final int expectedSize) {
        Assertions.assertThat(explodedResultSet.getSize())
                  .isEqualTo(expectedSize);
        return this;
    }

    public ValidatableResponse containsExactlyRow(final Map<String, Object> expectedRow) {
        Assertions.assertThat(explodedResultSet.getResults())
                  .containsExactly(expectedRow);
        return this;
    }

    public ValidatableResponse containsRowsInAnyOrder(final Map<String, Object>... expectedRows) {
        Assertions.assertThat(explodedResultSet.getResults())
                  .containsExactlyInAnyOrder(expectedRows);
        return this;
    }

    private ExplodedResultSet explodeResultSet(final ResultSet resultSet) {
        try {
            final String[] columnNames = getColumnNames(resultSet);
            int size = 0;
            final List<Map<String, Object>> rowsAndColumns = new ArrayList<>();
            while (resultSet.next()) {
                final Map<String, Object> row = new HashMap<>();
                size++;
                for (final String column : columnNames) {
                    final Object object = resultSet.getObject(column);
                    row.put(column, object);
                }
                rowsAndColumns.add(row);
            }
            return new ExplodedResultSet(size, rowsAndColumns);
        } catch (final Exception ex) {
            throw new NoResultsFoundException("No results found in result set", ex);
        }
    }

    private String[] getColumnNames(final ResultSet resultSet) throws SQLException {
        final int columnCount = resultSet.getMetaData()
                                         .getColumnCount();
        final String[] columns = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columns[i] = resultSet.getMetaData()
                                  .getColumnName(i);
        }
        return columns;
    }
}
