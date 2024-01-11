package com.hotel.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.hotel.hotelmanagement.EmployeeController.employeeList;
import static com.hotel.hotelmanagement.EmployeeController.employees;

public class DeleteEmployeeController implements Initializable {

    @FXML
    private Button delete;

    @FXML
    private TextField number;

    private Connection connection;
    private DBConnection dbConnection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public void handleSupAction(javafx.event.ActionEvent actionEvent) {
        String EmployeeID = number.getText();
        String query = "DELETE FROM employee WHERE EmployeeID = ?";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, EmployeeID);

            int deletedRows = pst.executeUpdate();

            if (deletedRows > 0) {
                // Remove the EmployeeID from the lists if it was successfully deleted from the database
            	employeeList.removeIf(Employee -> Employee.getEmployeeID() == Integer.parseInt(EmployeeID));
            	employees.removeIf(Employee -> Employee.getEmployeeID() == Integer.parseInt(EmployeeID));

                showAlert(Alert.AlertType.INFORMATION, "Employee Deleted", "Employee successfully deleted.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Delete Error", "Failed to delete EmployeeID. Please check the Employee number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "SQL Error", "Error occurred while deleting the Employee.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
