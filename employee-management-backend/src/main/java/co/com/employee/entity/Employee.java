package co.com.employee.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "There can be no empty fields")
    private String firstName;
    
    @NotEmpty(message = "There can be no empty fields")
    private String middleName;
    
    @NotEmpty(message = "There can be no empty fields")
    private String lastName;
    
    @NotEmpty(message = "There can be no empty fields")
    private String locationCity;
    
    @NotEmpty(message = "There can be no empty fields")
    private String address;
    
    @NotNull(message = "There can be no empty fields")
    @PastOrPresent(message = "The birthdate cannot be in the future.")
    private LocalDate dateOfBirth;
    
    @NotEmpty(message = "There can be no empty fields")
    private String telephone;
    
    @NotEmpty(message = "There can be no empty fields")
    private String positionTitle;
    
    @NotNull(message = "There can be no empty fields")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateArrival;
    
    @NotNull(message = "There can be no empty fields")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    
    @NotEmpty(message = "There can be no empty fields")
    private String email;
    
    @NotNull(message = "There can be no empty fields")
    private Double salary;
    
    @NotNull(message = "There can be no empty fields")
    private int timeInPosition;
    
    @NotEmpty(message = "There can be no empty fields")
    private String status;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLocationCity() {
		return locationCity;
	}
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPositionTitle() {
		return positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public int getTimeInPosition() {
		return timeInPosition;
	}
	public void setTimeInPosition(int timeInPosition) {
		this.timeInPosition = timeInPosition;
	}
	public LocalDate getDateArrival() {
		return dateArrival;
	}
	public void setDateArrival(LocalDate dateArrival) {
		this.dateArrival = dateArrival;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
