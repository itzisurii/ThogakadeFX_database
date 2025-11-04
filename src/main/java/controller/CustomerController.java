package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<CustomerDTO> tblCustomerManagement;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCusID;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtTitle;

    @FXML
    void btnAddActionOn(ActionEvent event) {

    }

    @FXML
    void btnBackToDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearActionOn(ActionEvent event) {
        txtCusID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDOB.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }

    @FXML
    void btnDeleteActionOn(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadCustomerDetails();
    }

    private void loadCustomerDetails(){
        customerDTOS.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakademanagement", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                CustomerDTO customerDTO = new CustomerDTO(resultSet.getString("CustomerID"), resultSet.getString("Title"), resultSet.getString("Name"), resultSet.getString("DateOfBirth"),resultSet.getDouble("Salary"), resultSet.getString("Address"), resultSet.getString("City"), resultSet.getString("Province"), resultSet.getString("PostalCode"));
                System.out.println(customerDTO);
                customerDTOS.add(customerDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblCustomerManagement.setItems(customerDTOS);

    }
}
