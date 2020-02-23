package application.datamodel;

import java.time.LocalDate;

public class Project {
	private int number, ERF, feeAmountCharged, feeAmountPaid;
	private String name, buildingType, address; 
	private LocalDate deadline;
	private Person architect, contractor, customer;
	public Project(int number, String name, String buildingType, int ERF, int feeAmountCharged, int feeAmountPaid,
			String address, LocalDate deadline, Person architect, Person contractor, Person customer) {
		super();
		this.number = number;
		this.ERF = ERF;
		this.feeAmountCharged = feeAmountCharged;
		this.feeAmountPaid = feeAmountPaid;
		this.name = name;
		this.buildingType = buildingType;
		this.address = address;
		this.deadline = deadline;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getERF() {
		return ERF;
	}
	public void setERF(int eRF) {
		ERF = eRF;
	}
	public int getFeeAmountCharged() {
		return feeAmountCharged;
	}
	public void setFeeAmountCharged(int feeAmountCharged) {
		this.feeAmountCharged = feeAmountCharged;
	}
	public int getFeeAmountPaid() {
		return feeAmountPaid;
	}
	public void setFeeAmountPaid(int feeAmountPaid) {
		this.feeAmountPaid = feeAmountPaid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public Person getArchitect() {
		return architect;
	}
	public void setArchitect(Person architect) {
		this.architect = architect;
	}
	public Person getContractor() {
		return contractor;
	}
	public void setContractor(Person contractor) {
		this.contractor = contractor;
	}
	public Person getCustomer() {
		return customer;
	}
	public void setCustomer(Person customer) {
		this.customer = customer;
	}
}
