package org.employee_salaries.repository;

import org.employee_salaries.dto.EmployeeStatusDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class EmployeeRepository {

    private final Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Fetches employees with a given status and groups them by department.
     *
     * @param statusCode the employee status (e.g., "new", "leaving" ... )
     * @return Map<DepartmentName, List of Employees>
     */
    public Map<String, List<EmployeeStatusDTO>> getEmployeeStatusesGroupedByDepartment(String statusCode) throws Exception {
        String sql = "SELECT * FROM employee_status_view WHERE status_code = ? ORDER BY department_name, employee_name";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, statusCode);
            ResultSet rs = ps.executeQuery();

            Map<String, List<EmployeeStatusDTO>> groupedByDepartment = new LinkedHashMap<>();

            while (rs.next()) {
                EmployeeStatusDTO dto = new EmployeeStatusDTO();
                dto.setStaffNumber(rs.getString("employee_staff_number"));
                dto.setEmployeeName(rs.getString("employee_name"));
                dto.setEmploymentDate(rs.getDate("employment_date").toLocalDate());

                var term = rs.getDate("termination_date");
                dto.setTerminationDate(term != null ? term.toLocalDate() : null);

                dto.setRoleName(rs.getString("role_name"));
                dto.setDepartmentName(rs.getString("department_name"));
                dto.setStatusCode(rs.getString("status_code"));
                dto.setStatusDescription(rs.getString("status_description"));

                groupedByDepartment
                        .computeIfAbsent(dto.getDepartmentName(), k -> new ArrayList<>())
                        .add(dto);
            }

            return groupedByDepartment;
        }
    }
}
