package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    private IntegerProperty id;
    private StringProperty job_id;
    private StringProperty FCs;
   /* private StringProperty passport;
    private StringProperty INILA; //снилс
    private StringProperty military_id; //военный билет*/
    private StringProperty phone;
    private StringProperty password;
   // private IntegerProperty salary;


    public Employee() {
        this(null, null, null, null, null);
    }

    //Перегрузка, чтоб с JSON было удобно извлекать, уже указывается определенный id
    public Employee(String job_id, String FCs, /*String passport, String INILA, String military_id,*/ String phone, String password, Integer id /*Integer salary*/) {
        this.job_id = new SimpleStringProperty(job_id);
        this.FCs = new SimpleStringProperty(FCs);
        /*this.passport = new SimpleStringProperty(passport);
        this.INILA = new SimpleStringProperty(INILA);
        this.military_id = new SimpleStringProperty(military_id);*/
        this.phone = new SimpleStringProperty(phone);
        this.password = new SimpleStringProperty(password);
        this.id = new SimpleIntegerProperty(id);
        //this.salary = new SimpleIntegerProperty(salary);
    }

    public Employee(String job_id, String FCs, String passport, String INILA, String military_id, String phone, String password /*, Integer salary*/) {
        this.job_id = new SimpleStringProperty(job_id);
        this.FCs = new SimpleStringProperty(FCs);
        /*this.passport = new SimpleStringProperty(passport);
        this.INILA = new SimpleStringProperty(INILA);
        this.military_id = new SimpleStringProperty(military_id);*/
        this.phone = new SimpleStringProperty(phone);
        this.password = new SimpleStringProperty(password);
        this.id = null;
        //this.salary = new SimpleIntegerProperty(salary);
    }

    //@Override
    public String toJson() {

        Map<String, String> map = new HashMap<>();
        map.put("job_id", String.valueOf(job_id.get()));
        map.put("fcs", FCs.get());
        /*map.put("passport", passport.get());
        map.put("inila", INILA.get());
        map.put("military_id", military_id.get());*/
        map.put("phone", phone.get());
        map.put("password", password.get());
        //map.put("id", String.valueOf(id.get()));
        //map.put("salary", String.valueOf(salary.get()));

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public String getJob_id() {
        return job_id.get();
    }

    public void setJob_id(String job_id) { this.job_id.set(job_id); }

    public StringProperty getJobIdProperty() {
        return job_id;
    }

    public String getFCs() {
        return FCs.get();
    }

    public void setFCs(String FCs) {
        this.FCs.set(FCs);
    }

    public StringProperty getFCsProperty() {
        return FCs;
    }

    /*public String getPassport() {
        return passport.get();
    }

    public void setPassport(String passport) {
        this.passport.set(passport);
    }

    public StringProperty getPassportProperty() {
        return passport;
    }

    public String getINILA() {
        return INILA.get();
    }

    public void setINILA(String INILA) {
        this.INILA.set(INILA);
    }

    public StringProperty getINILAProperty() {
        return INILA;
    }

    public String getMilitary_id() {
        return military_id.get();
    }

    public void setMilitary_id(String military_id) {
        this.military_id.set(military_id);
    }

    public StringProperty getMilitary_idProperty() {
        return military_id;
    }*/

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

   /* public IntegerProperty getSalaryProperty() { return  salary; }

    public int getSalary() { return salary.get(); }

    public void setSalary(Integer salary) { this.salary.set(salary); }*/

    public int getId() {
        return id.get();
    }
}
