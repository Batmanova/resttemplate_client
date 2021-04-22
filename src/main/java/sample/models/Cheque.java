package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Cheque {

    private final IntegerProperty order_id;
    private final IntegerProperty dish_id;
    private final TextField amountField;
    private Integer amount;

    public Cheque() {
        this(null, null, null);
    }

    public Cheque(Integer order_id, Integer dish_id, Integer amount) {
        this.order_id = new SimpleIntegerProperty(order_id);
        this.dish_id = new SimpleIntegerProperty(dish_id);
        this.amount = amount;
        this.amountField = new TextField(String.valueOf(amount));
    }

    public String toJson() {
        Map<String, String> map = new HashMap<>();

        map.put("order_id", String.valueOf(order_id.get()));
        map.put("dish_id", String.valueOf(dish_id.get()));
        map.put("amount", String.valueOf(amount));

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public int getOrder_id() {
        return order_id.get();
    }

    public IntegerProperty order_idProperty() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id.set(order_id);
    }

    public int getDish_id() {
        return dish_id.get();
    }

    public IntegerProperty dish_idProperty() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id.set(dish_id);
    }

    public TextField getAmountField() {
        return amountField;
    }

    public int getAmount() {
        return amount;
    }

    public Integer amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
