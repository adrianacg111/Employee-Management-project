package co.com.employee.service;

import co.com.employee.entity.Employee;
import co.com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


	// Employee repository injection
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CityValidationService cityValidationService;

 // Method to get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

 // Method to get an employee by their id
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

 // Method to save a new employee
    public Employee saveEmployee(Employee employee) {
    	  if (employee.getDateOfBirth() != null && employee.getHireDate() != null) {
              if (employee.getHireDate().isBefore(employee.getDateOfBirth())) {
                  throw new IllegalArgumentException("The hiring date cannot be before the birthdate.");
              }
          }
   	   if (employee.getDateArrival() != null && employee.getDateOfBirth() != null) {
              if (employee.getHireDate().isBefore(employee.getDateOfBirth())) {
                  throw new IllegalArgumentException("The arrival date cannot be before the birthdate.");
              }
   	   }
    	 if (!cityValidationService.isValidCity(employee.getLocationCity())) {
             throw new IllegalArgumentException("La ciudad proporcionada no es válida");
         }
    	  validarTelefono(employee.getTelephone()); 
  	    validarSalario(employee.getSalary());
        return employeeRepository.save(employee);
    }

 // Method to update an existing employee
    public Optional<Employee> updateEmployee(Long id, Employee employee) {
    	 if (!cityValidationService.isValidCity(employee.getLocationCity())) {
             throw new IllegalArgumentException("La ciudad proporcionada no es válida");
         }
    	   if (employee.getDateOfBirth() != null && employee.getHireDate() != null) {
               if (employee.getHireDate().isBefore(employee.getDateOfBirth())) {
                   throw new IllegalArgumentException("The hiring date cannot be before the birthdate.");
               }
           }
    	   if (employee.getDateArrival() != null && employee.getDateOfBirth() != null) {
               if (employee.getHireDate().isBefore(employee.getDateOfBirth())) {
                   throw new IllegalArgumentException("The arrival date cannot be before the birthdate.");
               }
    	   }
    	   validarTelefono(employee.getTelephone()); 
    	    validarSalario(employee.getSalary());
        if (employeeRepository.existsById(id)) {
            employee.setId(id); // Asegura que el empleado tiene el ID correcto
            return Optional.of(employeeRepository.save(employee));
        }
        return Optional.empty(); // Retorna vacío si no se encuentra el empleado
    }

 // Method to delete an employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false; 
    }
    private static final String PHONE_PATTERN = "^\\d{10}$"; 
    public void validarTelefono(String telefono) {
        if (!telefono.matches(PHONE_PATTERN)) {
            throw new IllegalArgumentException("The phone must be in a valid format (10 digits).");
        }
        System.out.println("Teléfono válido: " + telefono);
    }

    public void validarSalario(double salario) {
        if (salario <= 0) {
            throw new IllegalArgumentException("The salary must be greater than 0.");
        }
        System.out.println("Salario válido: " + salario);
    }
}
