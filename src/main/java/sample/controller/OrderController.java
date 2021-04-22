package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.Main;
import sample.models.Employee;
import sample.models.Order;
import sample.models.dto.EmployeeDTO;
import sample.models.dto.OrderDTO;
import sample.utils.RestApi;

import java.util.List;
import java.util.stream.Collectors;

public class OrderController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> guestColumn;
    @FXML
    private TableColumn<Order, String> cashierColumn;
    @FXML
    private TableColumn<Order, String> summColumn;

    private Main.MainApp mainApp;
    //private RestApi myApiSession;

    public OrderController() {
    }

    public void initialize(Main.MainApp mainApp) {
        this.mainApp = mainApp;
        guestColumn.setCellValueFactory(cellData -> cellData.getValue().getGuestIdProperty());
        cashierColumn.setCellValueFactory(cellData -> this.GetEmployeeById(cellData.getValue().getCashier_id()).getFCsProperty());
        summColumn.setCellValueFactory(cellData -> cellData.getValue().getIsReady());

        //showPersonDetails(null);

        //personTable.getSelectionModel().selectedItemProperty().addListener(
          //      ((observableValue, oldValue, newValue) -> showPersonDetails(newValue))
        //);
    }

    public Employee GetEmployeeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDTO> response
                = restTemplate.getForEntity("http://localhost:8090/employee/" + Integer.toString(id),EmployeeDTO.class);
        EmployeeDTO employeeDTO = response.getBody();
        return employeeDTO.toModel(employeeDTO);
    }

    public void setMainApp(Main.MainApp mainApp) {
        this.mainApp = mainApp;
        //this.myApiSession = mainApp.getApiSession();
        orderTable.setItems(mainApp.getOrderData());
    }

    @FXML
    private void authorize() {

        mainApp.showAuthorizeDialog();
    }
}
