package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class TmpModel {

    private final StringProperty name;
    private final TextField amount;

    public TmpModel() { this(null, null); };

    public TmpModel(String name, Integer amount) {
        this.name = new SimpleStringProperty(name);
        this.amount = new TextField(Integer.toString(amount));
    }
}
