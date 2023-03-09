package ro.ase.acs.sql.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataInserator {
    public void insertData(Connection connection) throws SQLException;
}
