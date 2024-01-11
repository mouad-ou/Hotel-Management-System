package com.hotel.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteClientController implements Initializable {

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

    @FXML
    public void handleSupAction(javafx.event.ActionEvent actionEvent) {
        // Validate input fields
        if (!validateInput()) {
            return;
        }

        int clientId = Integer.parseInt(number.getText());

        // Assuming your delete query would look like this
        String query = "DELETE FROM clients WHERE ClientID=?";

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, clientId);

            int deletedRows = pst.executeUpdate();

            if (deletedRows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Client Deleted", "Client information successfully deleted.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Delete Error", "Failed to delete client. Please check the client ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "SQL Error", "Error occurred while deleting the client information.");
        }
    }

    private boolean validateInput() {
        String clientIdText = number.getText();

        if (clientIdText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter the Client ID.");
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
