/**
 * 
 */
package com.mindex.challenge.data;

/**
 * DTO 
 */
public class ReportingStructure {


	private Employee employee;
	private Integer  numberOfReports;

	public ReportingStructure(Employee employee2, Integer countReports) {
		this.employee = employee2;
		this.numberOfReports = countReports;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Integer getNumberOfReports() {
		return numberOfReports;
	}
	public void setNumberOfReports(Integer numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
}
