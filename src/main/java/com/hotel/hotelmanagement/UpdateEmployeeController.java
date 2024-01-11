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

public class UpdateEmployeeController implements Initializable {

    @FXML
    private TextField employeeId;

    @FXML
    private TextField name;

    @FXML
    private TextField position;

    @FXML
    private TextField department;

    @FXML
    private TextField salary;

    @FXML
    private Button update;

    private Connection connection;
    private DBConnection dbConnection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    @FXML
    public void handleUpdateAction(javafx.event.ActionEvent actionEvent) {
        // Validate input fields
        if (!validateInput()) {
            return;
        }

        String employeeIdValue = employeeId.getText();
        String nameValue = name.getText();
        String positionValue = position.getText();
        String departmentValue = department.getText();
        String salaryValue = salary.getText();

        // Assuming your update query would look like this
        String query = "UPDATE employee SET FullName=?, Department=?, Position=?, Salary=? WHERE EmployeeID=?";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, nameValue);
            pst.setString(2, departmentValue);
            pst.setString(3, positionValue);
            pst.setString(4, salaryValue);
            pst.setString(5, employeeIdValue);

            int updatedRows = pst.executeUpdate();

            if (updatedRows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Employee Updated", "Employee information successfully updated.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Error", "Failed to update employee. Please check the employee information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "SQL Error", "Error occurred while updating the employee information.");
        }
    }

    private boolean validateInput() {
        String employeeIdValue = employeeId.getText();
        String nameValue = name.getText();
        String positionValue = position.getText();
        String departmentValue = department.getText();
        String salaryValue = salary.getText();

        if (employeeIdValue.isEmpty() || nameValue.isEmpty() || positionValue.isEmpty() || departmentValue.isEmpty() || salaryValue.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in all the fields.");
            return false;
        }

        // Additional validation if needed

        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
