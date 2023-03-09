package ro.ase.acs.sql.main;

import ro.ase.acs.sql.classes.AddData;
import ro.ase.acs.sql.classes.BuildTable;
import ro.ase.acs.sql.classes.DataRead;
import ro.ase.acs.sql.interfaces.DataInserator;
import ro.ase.acs.sql.interfaces.DataReader;
import ro.ase.acs.sql.interfaces.TableCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);

            TableCreator tableCreator = new BuildTable();
            tableCreator.createTable(connection);

            DataInserator dataInserator = new AddData();
            dataInserator.insertData(connection);

            DataReader dataReader = new DataRead();
            dataReader.readData(connection);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}