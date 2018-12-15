package excercise_1_15_12;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

public class App {

    static final String DB_URL = "jdbc:mysql://localhost/productsbase";
    static final String USER = "userproduct";
    static final String PASS = "qwerty";


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose action: 1 - show products, 2 - delete product, 3 - update product, 4 - show all products, 5 - add product");

        //register
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //connection
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //options
        switch (scanner.nextInt()){
            case 1: {
                try {
                    new Methods().showProduct(connection.createStatement());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                try {
                    new Methods().deleteProduct(connection.prepareStatement("DELETE FROM products WHERE product_id = ?"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 3:{
                try {
                    new Methods().updateProducts(connection.prepareStatement("UPDATE products " + "SET DESCRIPTION = ? WHERE product_id = ?"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4:{
                try {
                    new Methods().showAllProducts(connection.createStatement());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 5:{
                try {
                    new Methods().addProduct(connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?);"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // close
        try {
            assert connection != null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
