package sample.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.models.Cheque;

import java.util.Objects;

public class ChequeDTO {
    @JsonProperty("order_id")
    private int orderId;
    @JsonProperty("dish_id")
    private int dishId;
    @JsonProperty("amount")
    private int amount;

    public ChequeDTO(int orderId, int dishId, int amount) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.amount = amount;
    }

    public ChequeDTO() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChequeDTO chequeDTO = (ChequeDTO) o;
        return orderId == chequeDTO.orderId && dishId == chequeDTO.dishId && amount == chequeDTO.amount;
    }

    @Override
    public String toString() {
        return "ChequeDTO{" +
                "orderId=" + orderId +
                ", dishId=" + dishId +
                ", amount=" + amount +
                '}';
    }

    public Cheque toModel() {
        return new Cheque(orderId, dishId, amount);
    }
}
