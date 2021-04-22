package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.Main;
import sample.models.dto.EmployeeDTO;
import sample.models.dto.OrderDTO;
import sample.utils.RestApi;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private final IntegerProperty cashier_id;
    private final StringProperty guest_id;
    private final IntegerProperty summ;
    private final BooleanProperty is_ready;
    private final BooleanProperty is_active;

    private final IntegerProperty id;


    public Order() {
        this(null, null, null, null, null);
    }

    //Перегрузка, чтоб с JSON было удобно извлекать, уже указывается определенный id
    public Order(Integer cashier_id, String guest_id, Integer summ, Boolean is_active, Boolean is_ready) {
        this.cashier_id = new SimpleIntegerProperty(cashier_id);
        this.guest_id = new SimpleStringProperty(guest_id);
        this.summ = new SimpleIntegerProperty(summ);
        this.id = null;
        this.is_active = new SimpleBooleanProperty(is_active);
        this.is_ready = new SimpleBooleanProperty(is_ready);
    }

    public Order(Integer cashier_id, String guest_id, Integer summ, Integer id, Boolean is_active, Boolean is_ready) {
        this.cashier_id = new SimpleIntegerProperty(cashier_id);
        this.guest_id = new SimpleStringProperty(guest_id);
        this.summ = new SimpleIntegerProperty(summ);
        this.id = new SimpleIntegerProperty(id);
        this.is_active = new SimpleBooleanProperty(is_active);
        this.is_ready = new SimpleBooleanProperty(is_ready);
    }

    //@Override
    public String toJson() {

        Map<String, String> map = new HashMap<>();
        map.put("guest_id", guest_id.get());
        map.put("summ", String.valueOf(summ.get()));
        map.put("cashier_id", String.valueOf(cashier_id.get()));
        map.put("is_active", String.valueOf(is_active.get()));
        map.put("is_ready", String.valueOf(is_ready.get()));
        //map.put("id", String.valueOf(id.get()));

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public OrderDTO toDto() {
        try {
            return new OrderDTO(this.getId(), summ.get(), is_ready.get(), is_active.get(), guest_id.get(), cashier_id.get());
        } catch (NullPointerException n) {
            return new OrderDTO(summ.get(), is_ready.get(), is_active.get(), guest_id.get(), cashier_id.get());
        }
    }

    public Integer getCashier_id() {
        return cashier_id.get();
    }

    public void setCashier_id(int cashier_id) { this.cashier_id.set(cashier_id); }

    public IntegerProperty getCashierIdProperty() {
        return cashier_id;
    }

    public String getGuest_id() {
        return guest_id.get();
    }

    public void setGuest_id(String guest_id) { this.guest_id.set(guest_id); }

    public StringProperty getGuestIdProperty() {
        return guest_id;
    }

    public int getSumm() {
        return summ.get();
    }

    public void setSumm(String summ) {
        this.summ.set(Integer.parseInt(summ));
    }

    public IntegerProperty getSummProperty() {
        return summ;
    }

    public int getId() {
        return id.get();
    }

    public BooleanProperty getIs_ready() { return is_ready; }

    public SimpleStringProperty getIsReady() {
        if (is_ready.get()) {
            return new SimpleStringProperty("ready");
        } else {
            return new SimpleStringProperty("not ready");
        }
    }

    public void setIs_ready(boolean is_ready) { this.is_ready.set(is_ready);}

    public BooleanProperty getIs_activeProperty() { return is_active; }

    public boolean getIs_active() { return  is_active.get(); }

    public void setIs_active(boolean is_active) { this.is_active.set(is_active);}
}
