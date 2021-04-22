package sample.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.Main;
import sample.models.Cheque;
import sample.models.Dish;
import sample.models.Employee;
import sample.models.Order;
import sample.models.dto.OrderDTO;

//import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestApi {

    private static final String ServerURL = "http://127.0.0.1:8090";

    /**
     * Создание заказа
     * Использует POST
     *
     * @param order
     */
    public void CreateOrder(Order order) {
        HttpClass.PostRequest(ServerURL + "/orders/", order.toJson());
    }

   /* ResponseEntity<Order[]> response = new RestTemplate().getForEntity("http://localhost:8090/employee/",Order[].class);
    Order[] orders = response.getBody();
    List<Order> orderList = List.of(orders);*/

    /**
     * Получение всех персон
     * Использует GET
     *
     * @return
     */
    /*public List<Order> GetOrder() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OrderDTO[]> response
                = restTemplate.getForEntity("http://localhost:8090/orders/",OrderDTO[].class);
        OrderDTO[] orders = response.getBody();
        List<OrderDTO> orderList = List.of(orders);
        return orderList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }*/

    public Order GetOrderById(int id, Main mainApp) {
        String value = HttpClass.GetRequest(ServerURL + "/orders/" + String.valueOf(id));
        JsonArray jsonResult = JsonParser.parseString(value).getAsJsonArray();
        JsonObject currentOrder = jsonResult.get(0).getAsJsonObject();
        String guest_id = currentOrder.get("guest_id").getAsString();
        Integer cashier_id = currentOrder.get("cashier_id").getAsInt();
        Integer summ = currentOrder.get("summ").getAsInt();
        Boolean is_ready = currentOrder.get("is_ready").getAsBoolean();
        Boolean is_active = currentOrder.get("is_active").getAsBoolean();
        Order newOrder = new Order(cashier_id, guest_id, summ, id, is_active, is_ready);
        return newOrder;
    }

    /**
     * Обновление заказа
     * Использует PUT
     *
     * @param order
     */
    public void updateOrder(Order order) {
        Integer id = order.getId();
        String jsonString = order.toJson();
        HttpClass.PutRequest(ServerURL + "/orders/" + id, jsonString);
    }

    /**
     * Удаление персоны.
     * Использует DELETE
     *
     * @param order
     */
    public boolean deleteOrder(Order order) {
        Integer id = order.getId();
        System.out.println(id);
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/orders/" + id);
    }

    /**
     * Создание персоны
     * Использует POST
     *
     * @param employee
     */
    public void CreateEmployee(Employee employee) {
        HttpClass.PostRequest(ServerURL + "/employees", employee.toJson());
    }

    /**
     * Получение всех работников
     * Использует GET
     *
     * @return
     */
    public List<Employee> GetEmployee() {
        List<Employee> result = new ArrayList<>();
        String value = HttpClass.GetRequest(ServerURL + "/employees");

        /*value = value.substring(1, value.length()-1);
        String[] keyValuePairs = value.split(":");              //split the string to creat key-value pairs
        Map<String,String> map = new HashMap<>(); */

        JsonArray jsonResult = JsonParser.parseString(value).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentEmployee = jsonResult.get(i).getAsJsonObject();

            String job_id = currentEmployee.get("job_id").getAsString();
            String FCs = currentEmployee.get("fcs").getAsString();
            String passport = currentEmployee.get("passport").getAsString();
            String INILA = currentEmployee.get("inila").getAsString();
            String military_id = currentEmployee.get("military_id").getAsString();
            String phone = currentEmployee.get("phone").getAsString();
            String password = currentEmployee.get("password").getAsString();
            Integer salary = currentEmployee.get("salary").getAsInt();
            Integer id = currentEmployee.get("id").getAsInt();

            Employee newEmployee = new Employee(job_id, FCs, /*passport, INILA, military_id,*/ phone, password, id/*, salary*/);

            System.out.println("get:");
            System.out.println(newEmployee.toJson());
            System.out.println(id);
            result.add(newEmployee);
        }
        return result;
    }

    public Employee GetEmployeeById(int id) {
        String value = HttpClass.GetRequest(ServerURL + "/employees/" + String.valueOf(id));
        JsonObject currentEmployee = JsonParser.parseString(value).getAsJsonObject();
        String job_id = currentEmployee.get("job_id").getAsString();
        String FCs = currentEmployee.get("fcs").getAsString();
        String passport = currentEmployee.get("passport").getAsString();
        String INILA = currentEmployee.get("inila").getAsString();
        String military_id = currentEmployee.get("military_id").getAsString();
        String phone = currentEmployee.get("phone").getAsString();
        String password = currentEmployee.get("password").getAsString();
        Integer salary = currentEmployee.get("salary").getAsInt();
        Employee newEmployee = new Employee(job_id, FCs, /*passport, INILA, military_id,*/ phone, password, id/*, salary*/);
        return newEmployee;
    }

    /**
     * Обновление персоны
     * Использует PUT
     *
     * @param employee
     */
    public void updateEmployee(Employee employee) {
        Integer id = employee.getId();
        String jsonString = employee.toJson();
        HttpClass.PutRequest(ServerURL + "/employees/" + id, jsonString);
    }

    /**
     * Удаление персоны.
     * Использует DELETE
     *
     * @param employee
     */
    public boolean deleteEmployee(Employee employee) {
        Integer id = employee.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/employees/" + id);
    }

    public void createCheque(Cheque cheque) {
        HttpClass.PostRequest(ServerURL + "/cheques", cheque.toJson());
    }

    public List<Cheque> getCheques() {
        List<Cheque> result = new ArrayList<>();
        String value = HttpClass.GetRequest(ServerURL + "/cheques");
        JsonArray jsonResult = JsonParser.parseString(value).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentDish = jsonResult.get(i).getAsJsonObject();
            Integer order_id =  currentDish.get("order_id").getAsInt();
            Integer dish_id = currentDish.get("dish_id").getAsInt();
            Integer amount = currentDish.get("amount").getAsInt();
            Cheque cheque = new Cheque(order_id, dish_id, amount);
            result.add(cheque);
        }

        return result;
    }

    public void updateCheque(Cheque cheque) {
        Integer id = cheque.getOrder_id();
        String jsonString = cheque.toJson();
        HttpClass.PutRequest(ServerURL + "/dishes/" + id, jsonString);
    }

    public boolean deleteDish(Dish dish) {
        Integer id = dish.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/dishes/" + id);
    }

    public void CreateDish(Dish dish) {
        HttpClass.PostRequest(ServerURL + "/dishes", dish.toJson());
    }

    public List<Dish> getDishes() {
        List<Dish> result = new ArrayList<>();
        String value = HttpClass.GetRequest(ServerURL + "/dishes");
        JsonArray jsonResult = JsonParser.parseString(value).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentDish = jsonResult.get(i).getAsJsonObject();
            String name =  currentDish.get("name").getAsString();
            Integer id = currentDish.get("id").getAsInt();
            Integer price = currentDish.get("price").getAsInt();
            Dish dish = new Dish(id, price, name);
            result.add(dish);
        }

        return result;
    }

    public void updateDish(Dish dish) {
        Integer id = dish.getId();
        String jsonString = dish.toJson();
        HttpClass.PutRequest(ServerURL + "/dishes/" + id, jsonString);
    }

}