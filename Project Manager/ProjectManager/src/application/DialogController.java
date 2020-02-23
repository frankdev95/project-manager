package application;

import java.time.format.DateTimeFormatter;

import application.datamodel.ProjectDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DialogController {
	@FXML
	private TextField projectNumber, projectPaid, contractorAdrHouse, contractorAdrStreet, contractorAdrCity, contractorAdrPostcode, projectFinalised;
	@FXML
	private DatePicker projectDeadline;
	
	ProjectDataSource projectDatasource;
	
	public void initialize() {
		projectDatasource = new ProjectDataSource();
	}
	
	public void processResults() {
		String getDate = projectDeadline.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));		
		int number = Integer.parseInt(projectNumber.getText());
		int paid = Integer.parseInt(projectPaid.getText());
		
		projectDatasource.updateContractorDetails(number, contractorAdrHouse.getText(), contractorAdrStreet.getText(), contractorAdrCity.getText(), contractorAdrPostcode.getText());
		projectDatasource.updateInvoiceDetails(number, getDate, paid);
		projectDatasource.finaliseProject(number, projectFinalised.getText());
	}
}
