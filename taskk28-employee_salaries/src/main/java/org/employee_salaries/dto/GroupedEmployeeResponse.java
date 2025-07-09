package org.employee_salaries.dto;

import java.util.List;
import java.util.Map;

public class GroupedEmployeeResponse {
    private Map<String, List<EmployeeStatusDTO>> groupedEmployees;

    public GroupedEmployeeResponse(Map<String, List<EmployeeStatusDTO>> groupedEmployees) {
        this.groupedEmployees = groupedEmployees;
    }

    public Map<String, List<EmployeeStatusDTO>> getGroupedEmployees() {
        return groupedEmployees;
    }

    public void setGroupedEmployees(Map<String, List<EmployeeStatusDTO>> groupedEmployees) {
        this.groupedEmployees = groupedEmployees;
    }
}
