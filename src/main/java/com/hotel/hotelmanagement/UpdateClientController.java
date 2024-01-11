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

public class UpdateClientController implements Initializable {

    @FXML
    private TextField clientId;

    @FXML
    private TextField fullName;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button updateButton;

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

        int id = Integer.parseInt(clientId.getText());
        String name = fullName.getText();
        String mail = email.getText();
        String phone = phoneNumber.getText();

        // Assuming your update query would look like this
        String query = "UPDATE clients SET FullName=?, Email=?, PhoneNumber=? WHERE ClientID=?";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, mail);
            pst.setString(3, phone);
            pst.setInt(4, id);

            int updatedRows = pst.executeUpdate();

            if (updatedRows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Client Updated", "Client information successfully updated.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Error", "Failed to update client. Please check the client information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "SQL Error", "Error occurred while updating the client information.");
        }
    }

    private boolean validateInput() {
        String clientIdText = clientId.getText();
        String name = fullName.getText();
        String mail = email.getText();
        String phone = phoneNumber.getText();

        if (clientIdText.isEmpty() || name.isEmpty() || mail.isEmpty() || phone.isEmpty()) {
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
