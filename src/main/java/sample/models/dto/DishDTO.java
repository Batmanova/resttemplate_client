package sample.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.models.Dish;

import java.util.Objects;

public class DishDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("price")
    private int price;
    @JsonProperty("name")
    private String name;

    public DishDTO() {
    }

    public DishDTO(int id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDTO dishDTO = (DishDTO) o;
        return id == dishDTO.id && price == dishDTO.price && Objects.equals(name, dishDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name);
    }

    @Override
    public String toString() {
        return "DishDTO{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dish toModel() {
        return new Dish(id, price, name);
    }
}
