package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }
    
    /// Challenge Task 1
    @Override
    public ReportingStructure employeeReports(String id) {
        LOG.debug("ReportStructure for employee with id [{}]", id);

        Employee employee = read(id);
        return new ReportingStructure(employee,countReports(employee));
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    /// Support Challenge Task 1
    private Integer countReports(Employee employee) {
    	
    	Integer counter = 0;
    	
    	try {
	    	for(Employee report : employee.getDirectReports()) {
	    		// load report from repository
	            report = read(report.getEmployeeId());
	            // count employee
	    		counter++;
	    		// also count employee's reports
	    		counter += countReports(report);
	    	}
    	} catch(NullPointerException e) {
    		// no direct reports - null list
    		return 0;
    	}
    	
    	return counter;
    }
}
