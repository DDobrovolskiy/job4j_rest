package ru.job4j.auth.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return StreamSupport.stream(
                this.employeeRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    public Optional<Employee> findById(long id) {
        return this.employeeRepository.findById(id);
    }

    public Employee create(@NonNull Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee update(@NonNull Employee employee) {
        return create(employee);
    }

    public void delete(@PathVariable long id) {
        this.employeeRepository.deleteById(id);
    }
}
