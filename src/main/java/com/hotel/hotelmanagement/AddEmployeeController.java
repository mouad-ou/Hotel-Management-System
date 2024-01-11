package com.hotel.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.hotel.hotelmanagement.EmployeeController.employeeList;
import static com.hotel.hotelmanagement.EmployeeController.employees;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    private Button add;

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

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    @FXML
    public void handleAddEmployeeAction(javafx.event.ActionEvent actionEvent) {
        String query = "INSERT INTO employee (EmployeeID, FullName, Department, Position, Salary) VALUES (?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, employeeId.getText());
            pst.setString(2, name.getText());
            pst.setString(3, department.getText());
            pst.setString(4, position.getText());
            pst.setString(5, salary.getText());

            // Add your logic to handle the new Employee data (e.g., save to database)
            // ...
            employeeList.add(new Employee(Integer.parseInt(employeeId.getText()), name.getText(), position.getText(), department.getText(), Double.parseDouble(salary.getText())));
            employees.add(new Employee(Integer.parseInt(employeeId.getText()), name.getText(), position.getText(), department.getText(), Double.parseDouble(salary.getText())));

            pst.executeUpdate();
            // Optionally, you can close the window or perform other actions after adding the employee
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
