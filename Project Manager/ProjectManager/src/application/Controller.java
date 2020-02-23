package application;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import application.datamodel.ProjectDataSource;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/* Create a controller class which is linked to the corresponding fxml file. */
public class Controller {
	
	/* Declare a new projectDataSource object which manages communication to the database. */
	ProjectDataSource projectDataSource;
	
	/* Retrieve the fxml variables linked to the controller class. */
	@FXML
	private TextField projectNumber, projectName, buildingType, erfNumber, projectFee, projectPaid, projectAdrHouse,
			projectAdrStreet, projectAdrCity, projectAdrPostCode;
	@FXML
	private DatePicker projectDeadline;
	@FXML
	private TextField architectFirstName, architectLastName, architectPhone, architectEmail, architectAdrHouse,
			architectAdrStreet, architectAdrCity, architectAdrPostcode;
	@FXML
	private TextField contractorFirstName, contractorLastName, contractorPhone, contractorEmail, contractorAdrHouse,
			contractorAdrStreet, contractorAdrCity, contractorAdrPostcode;
	@FXML
	public TextField customerFirstName, customerLastName, customerPhone, customerEmail, customerAdrHouse,
			customerAdrStreet, customerAdrCity, customerAdrPostcode;
	
	@FXML
	private BorderPane mainBorderPane;
	
	/* Instantiate a new projectDataSource object */ 
	public void initialize() {
		projectDataSource = new ProjectDataSource();

		if (!projectDataSource.open()) {
			System.out.println("Connection failed");
			return;
		}
	}
	
	/* Show the edit project dialog pane when the user clicks on the associated menu item */
	@FXML
	public void showEditProjectDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("projectEditDialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = dialog.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = fxmlLoader.getController();
			controller.processResults();
		}
	}
	
	
	/* Call the correct functions to store the information the user enters into the database after they click the submit button.*/
	@FXML
	public void submitButtonClicked() throws SQLException {
		
		int customerAddressId = projectDataSource.insertAddress(customerAdrHouse.getText(), customerAdrStreet.getText(), customerAdrCity.getText(), customerAdrPostcode.getText());
		int architectAddressId = projectDataSource.insertAddress(architectAdrHouse.getText(), architectAdrStreet.getText(), architectAdrCity.getText(), architectAdrPostcode.getText());
		int contractorAddressId = projectDataSource.insertAddress(contractorAdrHouse.getText(), contractorAdrStreet.getText(), contractorAdrCity.getText(), contractorAdrPostcode.getText());
		int projectAddressId = projectDataSource.insertAddress(projectAdrHouse.getText(), projectAdrStreet.getText(), projectAdrCity.getText(), projectAdrPostCode.getText());
		
		int customerId = projectDataSource.insertCustomer(customerFirstName.getText(), customerLastName.getText(), customerPhone.getText(), customerEmail.getText(), customerAddressId);
		int contractorId = projectDataSource.insertContractor(contractorFirstName.getText(), contractorLastName.getText(), contractorPhone.getText(), contractorEmail.getText(), contractorAddressId);
		int architectId = projectDataSource.insertArchitect(architectFirstName.getText(), architectLastName.getText(), architectPhone.getText(), architectEmail.getText(), architectAddressId);
		
		String getDate = projectDeadline.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		int erf = Integer.parseInt(erfNumber.getText()), fee = Integer.parseInt(projectFee.getText()), paid = Integer.parseInt(projectPaid.getText());
		int invoiceId = projectDataSource.insertInvoice(customerId, erf, fee, paid, getDate);
		
		int number = Integer.parseInt(projectNumber.getText()); 
		projectDataSource.insertProject(number, projectName.getText(), buildingType.getText(), projectAddressId, invoiceId, customerId, contractorId, architectId);
		
	}
}
