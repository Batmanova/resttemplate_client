package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.controller.*;
import sample.models.Order;
import sample.models.dto.OrderDTO;
import sample.utils.RestApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }

    public static class MainApp extends Application {

        private RestApi myApiSession;
        private Stage primaryStage;
        private BorderPane rootLayout;
        private ObservableList<Order> orderData = FXCollections.observableArrayList();
        private int employee_id;

    public MainApp() {
        myApiSession = new RestApi();
        /*Order tmpOrder1 = new Order(2,"John",2, true, false);
        myApiSession.CreateOrder(tmpOrder1);
        System.out.println("add:");
        System.out.println(tmpOrder1.toJson());
        Employee tmpEmployee = new Employee("cashier", "A.A.A.", "0000 000000", "00", "0", "12345", "12345", 30000);
        myApiSession.CreateEmployee(tmpEmployee);
        System.out.println("add:");
        System.out.println(tmpEmployee.toJson());*/
        this.UpdateTable(false);
    }

        /**
         * Точка входа в прогу
         *
         * @param args
         */
    /*public static void main(String[] args) {
        launch(args);
    }*/

        /**
         * Обновление таблицы с актуальными данными с бека
         */
        public void UpdateTable(boolean authorized) {
            orderData.clear();
            //Читаем коллекцию персон с бека и обновляем ее
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<OrderDTO[]> response
                    = restTemplate.getForEntity("http://localhost:8090/orders/",OrderDTO[].class);
            OrderDTO[] orders = response.getBody();
            List<OrderDTO> orderList = List.of(orders);
            List<Order> allOrders = orderList.stream().map(OrderDTO::toModel).collect(Collectors.toList());
            List<Order> activeOrders = new ArrayList<>();
            if (authorized) {
                for (int i = 0; i < allOrders.size(); i++) {
                    if (allOrders.get(i).getIs_active() & allOrders.get(i).getCashier_id().equals(this.employee_id)) {
                        activeOrders.add(allOrders.get(i));
                    }
                }
                orderData.addAll(activeOrders);
            } else {
                for (int i = 0; i < allOrders.size(); i++) {
                    if (allOrders.get(i).getIs_active()) {
                        activeOrders.add(allOrders.get(i));
                    }
                }
                orderData.addAll(activeOrders);
            }
        }


    /**
     * Инициализация данных
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // /**
    //  * Показывает окно с изменениями данных пользователей
    // *
    // * @param order
    //   * @return
    // */
    public boolean showAuthorizeDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/authorization.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Authorize");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            Authorization controller = loader.getController();
            controller.setDialogStage(dialogStage, this);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showCashierPlace() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cashier.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            rootLayout.setCenter(page);

            CashierWork controller = loader.getController();
            controller.initialize(this);
            //controller.setMainApp(this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean newOrder() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/newOrder.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Order");
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewOrder controller = loader.getController();
            controller.setDialogStage(dialogStage, this);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addDishes(Order order) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/addDishes.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add dishes");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddController controller = loader.getController();
            controller.setDialogStage(dialogStage, this, order);
            dialogStage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Показывает персон
     */
    public void showOrders() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main.fxml"));
            AnchorPane orders = (AnchorPane) loader.load();

            rootLayout.setCenter(orders);

            OrderController controller = loader.getController();
            controller.initialize(this);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Orders");
        initRootLayout();
        showOrders();
    }

    public void setEmployee_id(int id) {
        this.employee_id = employee_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public ObservableList<Order> getOrderData() {
        return orderData;
    }

    public RestApi getApiSession() {
        return myApiSession;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }
}
}
