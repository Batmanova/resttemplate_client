package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
import sample.utils.DateUtil;
import sample.utils.RestApi;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Authorization {
    @FXML
    private TextField phoneField;
    @FXML
    private TextField passwordField;

    private Stage dialogStage;
    private Employee employee;
    private Main.MainApp mainApp;
    //private RestApi myApiSession;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage, Main.MainApp mainApp) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;
        //this.myApiSession = mainApp.getApiSession();
    }
    /*
    /**
     * Выставление данных для персоны
     *
     * @param order
     */
     /* public void setPerson(Order order) {
        this.order = order;

        firstNameField.setText(order.getFirstName());
        lastNameField.setText(order.getLastName());
        streetField.setText(order.getStreet());
        cityField.setText(order.getCity());
        postalCodeField.setText(Integer.toString(order.getPostalCode()));
        birthdayField.setText(DateUtil.format(order.getBirthday()));
        birthdayField.setPromptText("yyyy-MM-dd");

    } */

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //System.out.println(employee.toJson());
            //Обновляем модель
            //myApiSession.updatePerson(order);
           // mainApp.UpdateTable();
            okClicked = true;
            mainApp.showCashierPlace();
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        /*List<Employee> checkList = myApiSession.GetEmployee();
        for (int i = 0; i < checkList.size(); i++) {
            if (phoneField.getText().equals(checkList.get(i).getPhone())) {
                if (passwordField.getText().equals(checkList.get(i).getPassword())) {
                    mainApp.setEmployee_id(checkList.get(i).getId());
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(dialogStage);
                    alert.setTitle("ОШИБКА");
                    alert.setHeaderText("Некорректные данные");
                    alert.setContentText("Неверный пароль");

                    alert.showAndWait();
                    return false;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("ОШИБКА");
                alert.setHeaderText("Некорректные данные");
                alert.setContentText("Номер телефона не найден");

                alert.showAndWait();
                return false;
            }
        }
        return false;*/
        RestTemplate restTemplate = new RestTemplate();
        EmployeeDTO employee = new EmployeeDTO();
        employee.setPassword(passwordField.getText());
        employee.setPhone(phoneField.getText());
        HttpEntity<EmployeeDTO> request = new HttpEntity<>(employee);
        try {
            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8090/employee/security", HttpMethod.POST, request, String.class);
            String name = response.getBody();
            return true;
        } catch (Exception ite){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Некорректные данные");
            alert.setContentText("Неверный пароль или логин");
            alert.showAndWait();
            return false;
        }
    }
}
