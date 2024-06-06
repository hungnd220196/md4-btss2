package com.ra.bt_ss2.repository;

import com.ra.bt_ss2.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaRepository<Employee, Long> {
    @Query("select em from Employee  em where em.name like concat('%',:name,'%')")
    List<Employee> findEmployeeByName(String name);

    @Query("select em from Employee em where em.name like concat('%',:name,'%') ")
    List<Employee> findEmployeeByNameAndSorting(String name, Pageable pageable);

    @Query("select em from Employee em where em.salary between :minSalary and :maxSalary")
    List<Employee> findEmployeeBySalaryBetweenMinSalaryAndMaxSalary(Double minSalary, Double maxSalary);
    @Query("select em from Employee em order by em.salary desc limit 10")
    List<Employee> find10EmployeeGreatestSalary();

    @Query("select em from Employee em where em.name like concat('%',:name,'%')")
    Page<Employee> findEmployeeByNamePaging(String name, Pageable pageable);


}
