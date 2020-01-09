package com.okta.springbootvue.repository.oil;

import com.okta.springbootvue.entity.oil.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public
interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
    @Query( value = "SELECT * FROM Employee e where e.USERNAME = :username and e.PASSWORD = :password",
            nativeQuery = true)
        Employee findPatientByEmployee(@Param("username") String username,
    @Param("password") String password);
}