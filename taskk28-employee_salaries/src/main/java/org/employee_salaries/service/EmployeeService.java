package org.employee_salaries.service;

import org.employee_salaries.dto.EmployeeStatusDTO;
import org.employee_salaries.repository.EmployeeRepository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(Connection conn) {
        this.employeeRepository = new EmployeeRepository(conn);
    }

    /**
     * Delegates fetching grouped employees by status.
     */
    public Map<String, List<EmployeeStatusDTO>> getGroupedEmployeesByStatus(String statusCode) throws Exception {
        return employeeRepository.getEmployeeStatusesGroupedByDepartment(statusCode);
    }
}
