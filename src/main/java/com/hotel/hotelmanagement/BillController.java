package com.hotel.hotelmanagement;

import javafx.collections.FXCollections;
import com.hotel.hotelmanagement.BillInfoController;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BillController implements Initializable {

    @FXML
    private TableColumn<Bill, Double> Amount;
    @FXML
    private TableColumn<Bill, String> Date;
    @FXML
    private TableColumn<Bill, String> billID;
    @FXML
    private TableColumn<Bill, String> cusIDNumber;
    @FXML
    private TableColumn<Bill, String> customerName;
    @FXML
    private TableColumn<Bill, String> roomNumber;
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TextField search;

    private Connection connection;
    private DBConnection dbConnection;
    private PreparedStatement pst;

    public static final ObservableList<Bill> bills = FXCollections.observableArrayList();
    public static final List<Bill> billList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        cusIDNumber.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        billID.setCellValueFactory(new PropertyValueFactory<>("billID"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        try {
            initBillList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        billTable.setItems(bills);
    }

    public void initBillList() throws IOException {
        billList.clear();
        bills.clear();
        String query = "SELECT * FROM bill";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int room_number = Integer.parseInt(rs.getString("roomNumber"));
                int cus_number = Integer.parseInt(rs.getString("customerIDNumber"));
                int bill_id = Integer.parseInt(rs.getString("billID"));
                String date = rs.getString("billDate");
                String cus_name = rs.getString("customerName");
                double bill_amount = Double.parseDouble(rs.getString("billAmount"));
                billList.add(new Bill(bill_id, cus_name, cus_number, date, bill_amount, room_number));
                bills.add(new Bill(bill_id, cus_name, cus_number, date, bill_amount, room_number));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchBills(String searchText) {
        bills.clear();
        for (Bill bill : billList) {
            if (bill.getDate().startsWith(searchText)) {
                bills.add(bill);
            }
        }
    }

    @FXML
    public void handleSearchKey(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            String searchText = search.getText();
            searchBills(searchText);
        }
    }

    @FXML
    public void clickBill(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Bill selectedBill = billTable.getSelectionModel().getSelectedItem();
            if (selectedBill != null) {
                openBillInfo(selectedBill);
            }
        }
    }

    private void openBillInfo(Bill selectedBill) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("billinfo.fxml"));
        Parent root = loader.load();
        Bill Bill = loader.getController();

        // Populate the fields in BillInfoController with data from the selected bill
        Bill.setRoomNumber(selectedBill.getRoomNumber());
        Bill.setCustomerName(selectedBill.getCustomerName());
        Bill.setAmount(selectedBill.getAmount());
        Bill.setCustomerID(selectedBill.getCustomerID());

        // Create a new Stage
        Stage stage = new Stage();
        stage.setTitle("Bill Information");
        stage.setScene(new Scene(root));

        // Show the Stage
        stage.show();
    }
}
