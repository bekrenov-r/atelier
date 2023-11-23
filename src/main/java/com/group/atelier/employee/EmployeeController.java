package com.group.atelier.employee;

import com.group.atelier.model.dto.request.EmployeeRegistrationRequest;
import com.group.atelier.model.dto.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Secured("ADMIN")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponse> registerEmployee(@RequestBody EmployeeRegistrationRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.registerEmployee(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dismissEmployee(@PathVariable Long id){
        employeeService.dismissEmployee(id);
        return ResponseEntity.ok().build();
    }
}
