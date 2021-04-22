package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.Main;
import sample.models.Employee;
import sample.models.Order;
import sample.models.dto.EmployeeDTO;
import sample.models.dto.OrderDTO;
import sample.utils.RestApi;

public class CashierWork {

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> guestColumn;
    @FXML
    private TableColumn<Order, String> cashierColumn;
    @FXML
    private TableColumn<Order, String> summColumn;

    @FXML
    private Label guestLabel;
    @FXML
    private Label cashierLabel;
    @FXML
    private Label summLabel;

    private Main.MainApp mainApp;
    private RestApi myApiSession;

    public CashierWork() { }

    public void initialize(Main.MainApp mainApp) {
        this.mainApp = mainApp;
        guestColumn.setCellValueFactory(cellData -> cellData.getValue().getGuestIdProperty());
        cashierColumn.setCellValueFactory(cellData -> this.GetEmployeeById(cellData.getValue().getCashier_id()).getFCsProperty());
        summColumn.setCellValueFactory(cellData -> cellData.getValue().getIsReady());

        showDetails(null);

        orderTable.setItems(mainApp.getOrderData());

        orderTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showDetails(newValue))
        );
    }

    public Employee GetEmployeeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDTO> response
                = restTemplate.getForEntity("http://localhost:8090/employee/" + Integer.toString(id),EmployeeDTO.class);
        EmployeeDTO employeeDTO = response.getBody();
        return employeeDTO.toModel(employeeDTO);
    }

    @FXML
    private void handleDeleteAction() {
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order currentOrder = orderTable.getItems().get(selectedIndex);
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://127.0.0.1:8090/orders/delete/" + currentOrder.getId();
                restTemplate.delete(url);
                //orderTable.getItems().remove(selectedIndex);
                mainApp.UpdateTable(true);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("ОШИБКА");
                alert.setHeaderText("Заказ не выбран");
                alert.setContentText("Пожалуйста выберите заказ");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Заказ не выбран");
            alert.setContentText("Пожалуйста выберите заказ");

            alert.showAndWait();
        }
    }

    private void showDetails(Order order) {
        if (order != null) {
            guestLabel.setText(order.getGuest_id());
            cashierLabel.setText(Integer.toString(order.getCashier_id()));
            summLabel.setText(Integer.toString(order.getSumm()));
        } else {
            guestLabel.setText("");
            cashierLabel.setText("");
            summLabel.setText("");
        }
    }

    public void setMainApp(Main.MainApp mainApp) {
        this.mainApp = mainApp;
        /*this.myApiSession = mainApp.getApiSession();
        orderTable.setItems(mainApp.getOrderData());*/
    }

    @FXML
    private void handleNewOrder() {
        boolean isOkClicked = mainApp.newOrder();
    }

    @FXML
    private void handleAddDish() {
        if (orderTable.getSelectionModel().getSelectedItem() != null) {
            mainApp.addDishes(orderTable.getSelectionModel().getSelectedItem());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Заказ не выбран");
            alert.setContentText("Пожалуйста выберите заказ");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleSetReady() {
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order currentOrder = orderTable.getItems().get(selectedIndex);
            currentOrder.setIs_ready(true);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<OrderDTO> requestUpdate = new HttpEntity<>(currentOrder.toDto());
            String url = "http://localhost:8090/orders/update" ;//+ Integer.toString(currentOrder.getId());
            restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, OrderDTO.class);
            mainApp.UpdateTable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Заказ не выбран");
            alert.setContentText("Пожалуйста выберите заказ");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleSetInactive() {
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order currentOrder = orderTable.getItems().get(selectedIndex);
            currentOrder.setIs_active(false);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<OrderDTO> requestUpdate = new HttpEntity<>(currentOrder.toDto());
            String url = "http://localhost:8090/orders/update" ;//+ Integer.toString(currentOrder.getId());
            restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, OrderDTO.class);
            mainApp.UpdateTable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Заказ не выбран");
            alert.setContentText("Пожалуйста выберите заказ");

            alert.showAndWait();
        }
    }
}
