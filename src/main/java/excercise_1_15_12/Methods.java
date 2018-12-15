package excercise_1_15_12;

import java.sql.*;
import java.util.Scanner;

class Methods {

    private Scanner scanner = new Scanner(System.in);

    void showProduct(Statement statement) {
        ResultSet resultSet = null;

        System.out.println("Give product id to show: ");

        try {
            resultSet = statement.executeQuery("SELECT * FROM products WHERE product_id = " + scanner.nextInt());

            while (resultSet.next()) {

                int product_id = resultSet.getInt("product_id");
                String catalog_number = resultSet.getString("catalog_number");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                System.out.println("Product with id: " + product_id + " catalog number: " + catalog_number + " name: " + name +
                        " description: " + description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void updateProducts(PreparedStatement preparedStatement) {
        System.out.println("Give index and value");
        try {
            System.out.println("Index: ");
            preparedStatement.setInt(2, scanner.nextInt());
            System.out.println("Description: ");
            preparedStatement.setString(1, scanner.next());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteProduct(PreparedStatement preparedStatement) {
        System.out.println("Give product index to delete");
        try {
            preparedStatement.setInt(1, scanner.nextInt());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void showAllProducts(Statement statement) {
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery("SELECT product_id, name FROM products");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int product_id = resultSet.getInt("product_id");

                System.out.println("Product with id: " + product_id + " and name: " + name + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void addProduct(PreparedStatement preparedStatement) {
        try {
            System.out.println("Index: ");
            preparedStatement.setInt(1, scanner.nextInt());
            scanner.nextLine();
            System.out.println("Catalog number: ");
            preparedStatement.setString(2, scanner.nextLine());
            System.out.println("Product name: ");
            preparedStatement.setString(3, scanner.nextLine());
            System.out.println("Description");
            preparedStatement.setString(4, scanner.nextLine());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
