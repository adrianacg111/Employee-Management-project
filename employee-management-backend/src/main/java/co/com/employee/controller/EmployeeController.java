package co.com.employee.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.employee.entity.Employee;
import co.com.employee.service.AddressValidationService;
import co.com.employee.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private final AddressValidationService addressValidationService;
    public EmployeeController(EmployeeService employeeService, AddressValidationService addressValidationService) {
        this.employeeService = employeeService;
        this.addressValidationService = addressValidationService;
    }
    
    @GetMapping
    public List<Employee> listEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
    	 if (!addressValidationService.isValidAddress(employee.getAddress())) {
             return ResponseEntity.badRequest().body("La dirección proporcionada no es válida.");
         }
    	 if (result.hasErrors()) {
             // Puedes concatenar todos los errores en un solo mensaje o devolverlos como una lista.
             String errorMessages = result.getAllErrors().stream()
                 .map(error -> error.getDefaultMessage())
                 .collect(Collectors.joining(", "));
             return ResponseEntity.badRequest().body("Errores de validación: " + errorMessages);
         }
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editEmployee(@Valid @PathVariable Long id, @RequestBody Employee employee) {
    	 if (!addressValidationService.isValidAddress(employee.getAddress())) {
             return ResponseEntity.badRequest().body("La dirección proporcionada no es válida.");
         }
        return ResponseEntity.of(employeeService.updateEmployee(id, employee));
    }
}
