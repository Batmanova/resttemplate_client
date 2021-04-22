package sample.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.models.Employee;

import java.util.Objects;

public class EmployeeDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("fc_s")
    private String fcS;
    @JsonProperty("job_name")
    private String jobName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("password")
    private String password;

    public EmployeeDTO(Integer id, String fcS, String jobName, String phone, String password) {
        this.id = id;
        this.fcS = fcS;
        this.jobName = jobName;
        this.phone = phone;
        this.password = password;
    }

    public EmployeeDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(fcS, that.fcS) && Objects.equals(jobName, that.jobName) && Objects.equals(phone, that.phone) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fcS, jobName, phone, password);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", fcS='" + fcS + '\'' +
                ", jobName='" + jobName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Employee toModel(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.jobName, employeeDTO.fcS, employeeDTO.phone, employeeDTO.password, employeeDTO.id);
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFcS() {
        return fcS;
    }

    public void setFcS(String fcS) {
        this.fcS = fcS;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
