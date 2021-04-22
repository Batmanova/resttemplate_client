package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.Main;
import sample.models.Cheque;
import sample.models.Dish;
import sample.models.Order;
import sample.models.dto.ChequeDTO;
import sample.models.dto.DishDTO;
import sample.models.dto.EmployeeDTO;
import sample.utils.RestApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddController {
    @FXML
    private TableView<TmpModel> chequeTable;
    @FXML
    private TableColumn<TmpModel, String> dishColumn;
    @FXML
    private TableColumn<TmpModel, TextField> amountColumn;

    private Stage dialogStage;
    boolean okClicked = false;
    private Main.MainApp mainApp;
    ObservableList<TmpModel> orderData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage, Main.MainApp mainApp, Order order) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChequeDTO[]> response = restTemplate.getForEntity("http://localhost:8090/cheque/" + Integer.toString(order.getId()), ChequeDTO[].class);
        ChequeDTO[] chequeDTO = response.getBody();
        List<ChequeDTO> orderList = List.of(chequeDTO);
        List<Cheque> cheques = orderList.stream().map(ChequeDTO::toModel).collect(Collectors.toList());

        ResponseEntity<DishDTO[]> dishesArray = restTemplate.getForEntity("http://localhost:8090/dishes", DishDTO[].class);
        DishDTO[] dishes = dishesArray.getBody();
        List<DishDTO> chequeList = List.of(dishes);
        List<Dish> dishList = chequeList.stream().map(DishDTO::toModel).collect(Collectors.toList());

        List<TmpModel> tmp = new ArrayList<>();
        for (int i = 0; i < dishList.size(); i++) {

            tmp.add(new TmpModel(dishList.get(i).getName(), getAmount(cheques, dishList.get(i), order)));
        }

        orderData.addAll(tmp);
        chequeTable.setItems(orderData);
    }

    public int getAmount(List<Cheque> cheques, Dish dish, Order order) {
        for (int i = 0; i < cheques.size(); i++) {
            if (cheques.get(i).getDish_id() == dish.getId()) {
                if (cheques.get(i).getOrder_id() == order.getId()) {
                   return cheques.get(i).getAmount();
                }
            }
        }
        return 0;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleOk() {

    }
}
