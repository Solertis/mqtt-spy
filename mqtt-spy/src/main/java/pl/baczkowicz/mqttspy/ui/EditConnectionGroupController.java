/***********************************************************************************
 * 
 * Copyright (c) 2015 Kamil Baczkowicz
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *    
 * The Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * 
 *    Kamil Baczkowicz - initial API and implementation and/or initial documentation
 *    
 */
package pl.baczkowicz.mqttspy.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.baczkowicz.mqttspy.common.generated.ProtocolEnum;
import pl.baczkowicz.mqttspy.configuration.ConfigurationManager;
import pl.baczkowicz.mqttspy.configuration.ConfiguredConnectionDetails;
import pl.baczkowicz.mqttspy.configuration.ConfiguredConnectionGroupDetails;
import pl.baczkowicz.mqttspy.exceptions.ConfigurationException;
import pl.baczkowicz.mqttspy.ui.properties.ConnectionListItemProperties;
import pl.baczkowicz.mqttspy.ui.properties.ConnectionTreeItemProperties;
import pl.baczkowicz.mqttspy.utils.ConnectionUtils;

/**
 * Controller for editing a single connection.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EditConnectionGroupController extends AnchorPane implements Initializable
{
	final static Logger logger = LoggerFactory.getLogger(EditConnectionGroupController.class);
	
	@FXML
	private TextField connectionGroupNameText;
	
	// Action buttons
	
	@FXML
	private Button connectButton;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button undoButton;
	
	@FXML 
	private TableView<ConnectionListItemProperties> connectionList;
	
	@FXML 
	private TableColumn<ConnectionListItemProperties, String> nameColumn;
       
	@FXML 
	private TableColumn<ConnectionListItemProperties, String> protocolColumn;
	
	@FXML 
	private TableColumn<ConnectionListItemProperties, String> detailsColumn;
	
	// Other fields

	private MainController mainController;

	private ConfiguredConnectionGroupDetails editedConnectionGroupDetails;

	private boolean openNewMode;

	private Object existingConnection;

	private ArrayList<ConfiguredConnectionDetails> connections;

	private boolean recordModifications;

	private int noModificationsLock;

	private EditConnectionsController editConnectionsController;
	
	// ===============================
	// === Initialisation ============
	// ===============================

	public void initialize(URL location, ResourceBundle resources)
	{
		nameColumn.setCellValueFactory(new PropertyValueFactory<ConnectionListItemProperties, String>("name"));
		protocolColumn.setCellValueFactory(new PropertyValueFactory<ConnectionListItemProperties, String>("protocol"));
		detailsColumn.setCellValueFactory(new PropertyValueFactory<ConnectionListItemProperties, String>("details"));
		detailsColumn.setCellFactory(new Callback<TableColumn<ConnectionListItemProperties,String>, TableCell<ConnectionListItemProperties,String>>()
		{			
			@Override
			public TableCell<ConnectionListItemProperties, String> call(
					TableColumn<ConnectionListItemProperties, String> param)
			{
				final TableCell<ConnectionListItemProperties, String> cell = new TableCell<ConnectionListItemProperties, String>()
				{
					@Override
					public void updateItem(final String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (getTableRow().getItem() != null)
						{
							final ConnectionListItemProperties row = (ConnectionListItemProperties) getTableRow().getItem();
							
							setGraphic(ConnectionController.createSecurityIcons(
									row.isTlsEnabled(), 
									row.isUserAuthenticationEnabled(),
									true));
							setText(item);
						}
						else
						{
							setGraphic(null);
							setText(null);
						}
					}
				};
				return cell;
			}
		});
		//securityColumn.setCellValueFactory(new PropertyValueFactory<ConnectionListItemProperties, String>("security"));
		
		connectionGroupNameText.textProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue)
			{
				onChange();
			}		
		});		
	}

	public void init()
	{
	}

	// ===============================
	// === FXML ======================
	// ===============================
	
	@FXML
	private void undo()
	{
		editedConnectionGroupDetails.undo();
		editConnectionsController.listConnections();
		
		// Note: listing connections should display the existing one
		
		updateButtons();
	}
	
	
	@FXML
	private void save()
	{
		editedConnectionGroupDetails.apply();
		editConnectionsController.listConnections();
				
		updateButtons();
		
		// TODO
//		logger.debug("Saving connection " + getConnectionName().getText());
//		if (configurationManager.saveConfiguration())
//		{
//			DialogUtils.showTooltip(saveButton, "Changes for connection " + editedConnectionDetails.getName() + " have been saved.");
//		}
	}	
	
	@FXML
	public void createConnection() throws ConfigurationException
	{
//		readAndDetectChanges();
//		final String validationResult = ConnectivityUtils.validateConnectionDetails(editedConnectionDetails, false);
//		
//		if (validationResult != null)
//		{
//			DialogUtils.showValidationWarning(validationResult);
//		}
//		else
//		{					
//			if (editedConnectionDetails.isModified())
//			{	
//				Action response = DialogUtils.showApplyChangesQuestion("connection " + editedConnectionDetails.getName()); 
//				if (response == Dialog.ACTION_YES)
//				{
//					save();
//				}
//				else if (response == Dialog.ACTION_NO)
//				{
//					// Do nothing
//				}
//				else
//				{
//					return;
//				}
//			}
//			
//			if (!openNewMode)
//			{
//				connectionManager.disconnectAndCloseTab(existingConnection);
//			}
//			
//			logger.info("Opening connection " + getConnectionName().getText());
//	
//			// Get a handle to the stage
//			Stage stage = (Stage) connectButton.getScene().getWindow();
//	
//			// Close the window
//			stage.close();
//	 
//			Platform.runLater(new Runnable()
//			{
//				@Override
//				public void run()
//				{
//					try
//					{						
//						connectionManager.openConnection(editedConnectionDetails, getMainController());
//					}
//					catch (ConfigurationException e)
//					{
//						// TODO: show warning dialog for invalid
//						logger.error("Cannot open conection {}", editedConnectionDetails.getName(), e);
//					}					
//				}				
//			});
//			
//		}
	}

	// ===============================
	// === Logic =====================
	// ===============================
	
	public void onChange()
	{
		if (recordModifications/* && !emptyConnectionList*/)
		{					
			if (readAndDetectChanges())
			{
				updateButtons();			
				editConnectionsController.listConnections();
			}
		}				
	}
	
	private boolean readAndDetectChanges()
	{
		editedConnectionGroupDetails.getGroup().setName(connectionGroupNameText.getText());
		
		boolean changed = !editedConnectionGroupDetails.getGroup().getName().
				equals(editedConnectionGroupDetails.getLastSavedValues().getName());
			
		logger.debug("Values read. Changed = " + changed);
		editedConnectionGroupDetails.setModified(changed);
		
		return changed;
	}

	public void editConnectionGroup(final ConfiguredConnectionGroupDetails connectionGroup, final List<ConnectionTreeItemProperties> list)
	{	
		synchronized (this)
		{
			this.editedConnectionGroupDetails = connectionGroup;
			this.connections = new ArrayList<>();
			for (final ConnectionTreeItemProperties item : list)
			{
				if (!item.isGroup() && item.getConnection() != null)
				{
					connections.add(item.getConnection());
				}
			}			
			
			// Set 'open connection' button mode
			openNewMode = true;
			existingConnection = null;
			//connectButton.setText("Open connection");
			
//			logger.debug("Editing connection id={} name={}", editedConnectionGroupDetails.getId(),
//					editedConnectionGroupDetails.getName());
//			for (final MqttAsyncConnection mqttConnection : connectionManager.getGroups())
//			{
//				if (connectionDetails.getId() == mqttConnection.getProperties().getConfiguredProperties().getId() && mqttConnection.isOpened())
//				{
//					openNewMode = false;
//					existingConnection = mqttConnection;
//					connectButton.setText("Close and re-open existing connection");
//					break;
//				}				
//			}
			
			displayConnectionDetails(editedConnectionGroupDetails);		
						
			updateButtons();
		}
	}
	
	private void updateButtons()
	{
		if (editedConnectionGroupDetails != null && editedConnectionGroupDetails.isModified())
		{
			saveButton.setDisable(false);
			undoButton.setDisable(false);
		}
		else
		{
			saveButton.setDisable(true);
			undoButton.setDisable(true);
		}
	}
	
	private void displayConnectionDetails(final ConfiguredConnectionGroupDetails group)
	{	
		connectionGroupNameText.setText(group.getGroup().getName());
		connectionGroupNameText.setDisable(group.getGroup().getID().equals(ConfigurationManager.DEFAULT_GROUP));
		connectionList.getItems().clear();
		
		for (final ConfiguredConnectionDetails connection : connections)
		{
			final ProtocolEnum protocol = connection.getProtocol();
			
			final ConnectionListItemProperties properties = new ConnectionListItemProperties(
					connection.getName(), 
					(protocol == null ? ProtocolEnum.MQTT_DEFAULT.value() : protocol.value()), 
					connection.getClientID() + "@" + ConnectionUtils.serverURIsToString(connection.getServerURI()), 
					connection.getSSL() != null, 
					connection.getUserAuthentication() != null);
			
			connectionList.getItems().add(properties);
		}

		
		// editedConnectionGroupDetails.setBeingCreated(false);
	}		

	// ===============================
	// === Setters and getters =======
	// ===============================
	
	public void setEditConnectionsController(EditConnectionsController editConnectionsController)
	{
		this.editConnectionsController = editConnectionsController;		
	}
	
	public void setMainController(MainController mainController)
	{
		this.mainController = mainController;
	}
	
	public void setRecordModifications(boolean recordModifications)
	{
		if (!recordModifications)
		{
			logger.trace("Modifications suspended...");
			noModificationsLock++;
			this.recordModifications = recordModifications;
		}
		else
		{ 
			noModificationsLock--;
			// Only allow modifications once the parent caller removes the lock
			if (noModificationsLock == 0)
			{
				logger.trace("Modifications restored...");
				this.recordModifications = recordModifications;
			}
		}
	}

	/**
	 * @return the mainController
	 */
	public MainController getMainController()
	{
		return mainController;
	}
}
