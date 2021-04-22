package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Dish {

    private final IntegerProperty id;
    private final IntegerProperty price;
    private final StringProperty name;

    public Dish() { this(null, null, null); }

    public Dish(Integer id, Integer price, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.price = new SimpleIntegerProperty(price);
        this.name = new SimpleStringProperty(name);
    }

    public Dish(Integer price, String name) {
        this.id = null;
        this.price = new SimpleIntegerProperty(price);
        this.name = new SimpleStringProperty(name);
    }

    public String toJson() {
        Map<String, String> map = new HashMap<>();

        map.put("price", String.valueOf(price.get()));
        map.put("name", name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
