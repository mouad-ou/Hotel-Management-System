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

public class AddClientController implements Initializable {

    @FXML
    private TextField clientIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private Button addButton;

    private Connection connection;
    private DBConnection dbConnection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    @FXML
    public void handleAddAction(javafx.event.ActionEvent actionEvent) {
        // Validate input fields
        if (!validateInput()) {
            return;
        }
        String id = clientIdField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        // Assuming your insert query would look like this
        String query = "INSERT INTO clients (ClientID,FullName, Email, PhoneNumber) VALUES (?,?, ?, ?)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, phone);

            int insertedRows = pst.executeUpdate();

            if (insertedRows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Client Added", "Client information successfully added.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Add Error", "Failed to add client. Please check the client information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "SQL Error", "Error occurred while adding the client information.");
        }
    }

    private boolean validateInput() {
    	String id = clientIdField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
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
