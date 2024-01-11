package com.hotel.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExportEmployeeController implements Initializable {

    @FXML
    private TextField path;

    @FXML
    private Button export;

    @FXML
    private Button pathButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize your controller
    }

    @FXML
    public void handleExportAction(javafx.event.ActionEvent actionEvent) {
        String filePath = path.getText();

        if (filePath.isEmpty()) {
            // Handle case where the file path is empty
            return;
        }

        // Assuming you have a method to get employee data as a CSV formatted string
        String employeeData = getEmployeeData();

        // Write the CSV data to the specified file
        writeToFile(filePath, employeeData);

        // Show a success message
        showAlert("Export Successful", "Employee data exported successfully!");

        // Close the window
        closeWindow();
    }

    @FXML
    public void handlePathButtonAction(javafx.event.ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        // Set extension filter if needed
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    private String getEmployeeData() {
        // Replace this method with your logic to retrieve employee data as a CSV formatted string
        // For example, you can iterate through your employee list and format the data
        // ...

        return "EmployeeID,FullName,Department,Position,Salary\n"
             + "1,John Doe,IT,Developer,50000.0\n"
             + "2,Jane Smith,HR,Manager,60000.0\n"
             + "3,Bob Johnson,Finance,Accountant,55000.0";
    }

    private void writeToFile(String filePath, String data) {
        // Write data to the specified file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an alert
        }
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        // Access the stage (window) and close it
        javafx.stage.Stage stage = (javafx.stage.Stage) export.getScene().getWindow();
        stage.close();
    }
}
