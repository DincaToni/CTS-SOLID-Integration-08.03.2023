package ro.ase.acs.sql.main;

import ro.ase.acs.sql.classes.AddData;
import ro.ase.acs.sql.classes.BuildTable;
import ro.ase.acs.sql.interfaces.DataInserator;
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
            readData(connection);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void readData(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM employees";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlSelect);
        while(rs.next()) {
            int id = rs.getInt("id");
            System.out.println("id: " + id);
            String name = rs.getString(2);
            System.out.println("name: " + name);
            String address = rs.getString("address");
            System.out.println("address: " + address);
            double salary = rs.getDouble("salary");
            System.out.println("salary: " + salary);
        }
        rs.close();
        statement.close();
    }
}