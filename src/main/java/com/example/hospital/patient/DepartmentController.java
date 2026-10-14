
package com.example.hospital.patient;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:5174")
public class DepartmentController {

    private final DepartmentRepository repository;

    public DepartmentController(
            DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(
            @PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Department createDepartment(
            @RequestBody Department department) {
        return repository.save(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department updatedDepartment) {

        return repository.findById(id)
                .map(department -> {
                    department.setName(
                            updatedDepartment.getName());
                    department.setDescription(
                            updatedDepartment.getDescription());
                    department.setLocation(
                            updatedDepartment.getLocation());
                    department.setHeadOfDepartment(
                            updatedDepartment.getHeadOfDepartment());
                    department.setPhone(
                            updatedDepartment.getPhone());

                    return repository.save(department);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
