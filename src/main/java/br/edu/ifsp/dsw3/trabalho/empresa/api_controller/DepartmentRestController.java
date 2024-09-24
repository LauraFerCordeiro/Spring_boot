package br.edu.ifsp.dsw3.trabalho.empresa.api_controller;

import br.edu.ifsp.dsw3.trabalho.empresa.model.dao.DepartmentDAO;
import br.edu.ifsp.dsw3.trabalho.empresa.model.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    @Autowired
    private DepartmentDAO departmentDAO;

    // Buscar todos os departamentos
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentDAO.findAll();
        return ResponseEntity.ok(departments);
    }

    // Buscar departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentDAO.findById(id);
        return department.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Criar um novo departamento
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentDAO.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    // Atualizar um departamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        Optional<Department> optionalDepartment = departmentDAO.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            // Atualizar os campos necessários
            department.setName(updatedDepartment.getName());
            department.setDescription(updatedDepartment.getDescription());
            department.setCategory(updatedDepartment.getCategory());
            department.setTelephone(updatedDepartment.getTelephone());
            department.setAddress(updatedDepartment.getAddress());
            department.setWorkers(updatedDepartment.getWorkers());

            Department savedDepartment = departmentDAO.save(department);
            return ResponseEntity.ok(savedDepartment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar um departamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        Optional<Department> department = departmentDAO.findById(id);
        if (department.isPresent()) {
            departmentDAO.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
