package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.Main;
import sample.models.Order;
import sample.models.dto.EmployeeDTO;
import sample.models.dto.OrderDTO;
import sample.utils.RestApi;

public class NewOrder {
    @FXML
    private TextField nameField;

    private Stage dialogStage;
    private Order order;
    boolean okClicked = false;
    private Main.MainApp mainApp;
    //private RestApi myApiSession;

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage, Main.MainApp mainApp) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;
        //this.myApiSession = mainApp.getApiSession();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleOK() {
        Order order = new Order(mainApp.getEmployee_id(), nameField.getText(), 0, true, false);
        RestTemplate restTemplate = new RestTemplate();
        OrderDTO orderDTO = order.toDto();
        HttpEntity<OrderDTO> request = new HttpEntity<>(orderDTO);
        ResponseEntity<OrderDTO> response = restTemplate.exchange("http://localhost:8090/orders/add", HttpMethod.POST, request, OrderDTO.class);
        mainApp.UpdateTable(true);
        dialogStage.close();
    }
}
