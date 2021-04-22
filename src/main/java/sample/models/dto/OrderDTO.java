package sample.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sample.models.Order;

import java.util.Objects;

/*@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor //automatic constructor generators*/
//@Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("summ")
    private int summ;
    @JsonProperty("isReady")
    private boolean isReady;
    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("name")
    private String name;
    @JsonProperty("employeeId")
    private int employeeId;
    public OrderDTO(Integer id, Integer summ, Boolean isReady, Boolean isActive, String name, Integer employeeId) {
        this.id = id;
        this.summ = summ;
        this.isReady = isReady;
        this.isActive = isActive;
        this.name = name;
        this.employeeId = employeeId;
    }

    public OrderDTO( Integer summ, Boolean isReady, Boolean isActive, String name, Integer employeeId) {
        this.id = -3;
        this.summ = summ;
        this.isReady = isReady;
        this.isActive = isActive;
        this.name = name;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public OrderDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(summ, orderDTO.summ) && Objects.equals(isReady, orderDTO.isReady) && Objects.equals(isActive, orderDTO.isActive) && Objects.equals(name, orderDTO.name) && Objects.equals(employeeId, orderDTO.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summ, isReady, isActive, name, employeeId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", summ=" + summ +
                ", isReady=" + isReady +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }

    public static Order toModel(OrderDTO orderDTO) {
        return new Order(orderDTO.getEmployeeId(), orderDTO.getName(), orderDTO.getSumm(), orderDTO.getId(), orderDTO.isActive(), orderDTO.isReady());
    }
}
