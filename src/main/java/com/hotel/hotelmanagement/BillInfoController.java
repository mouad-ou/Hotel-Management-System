package com.hotel.hotelmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BillInfoController {

    @FXML
    private TextField roomNumber;

    @FXML
    private TextField customerName;

    @FXML
    private TextField Amount;

    @FXML
    private TextField customerIDNumber;

    @FXML
    private Button print;

    @FXML
    public void handlePrintAction() {
        // Handle the print action here
        System.out.println("Print button clicked");

        // Create a FileChooser for choosing the directory
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose PDF Directory");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the dialog
        File selectedDirectory = fileChooser.showSaveDialog(print.getScene().getWindow());

        // Check if the user selected a directory
        if (selectedDirectory != null) {
            // Create a PDF file
            File pdfFile = new File(selectedDirectory.getAbsolutePath() + File.separator + "bill.pdf");

            // Customize the logic to generate the PDF content
            String pdfContent = generatePdfContent();

            // Write the content to the PDF file
            try {
                Files.write(pdfFile.toPath(), pdfContent.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("PDF saved to: " + pdfFile.getAbsolutePath());
        }
    }

    private String generatePdfContent() {
        // Customize this method to generate the PDF content
        // For simplicity, we'll return a basic example
        return String.format("PDF Content:\nRoom Number: %s\nCustomer Name: %s\nAmount: %s\nCustomer ID: %s",
                             roomNumber.getText(), customerName.getText(), Amount.getText(), customerIDNumber.getText());
    }
}
