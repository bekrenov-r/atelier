package com.group.atelier.employee;

import com.group.atelier.model.dto.request.EmployeeRegistrationRequest;
import com.group.atelier.model.dto.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: for role ADMIN
@RestController
@RequestMapping("/employees")
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
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
