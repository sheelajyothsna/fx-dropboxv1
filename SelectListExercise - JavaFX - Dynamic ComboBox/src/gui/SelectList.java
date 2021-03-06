package gui;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * <p>
 * Title: MainScreen
 * </p>
 *
 * <p>
 * Description: The main screen for a Select List exercise
 * </p>
 *
 * <p>
 * Copyright: Copyright 2016
 * </p>
 *
 * @author Lynn Robert Carter
 * @ modified by SHEELA JYOTHSNA
 * @version 1.00
 */
@SuppressWarnings("restriction")
public class SelectList extends Application {
	Pane theRoot;

	private final double WINDOW_WIDTH = 610;
	private final double WINDOW_HEIGHT = 500;

	// Array of Countries from the Internet
	ObservableList<String> emptyList = FXCollections.observableArrayList();
	ObservableList<String> arrayCountries = FXCollections.observableArrayList("- no country selected - ",
			"Abkhazia", "Afghanistan", "Albania", "Algeria", "Andorra",
			"Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia",
			"Austria", "Azerbaijan", "Bahamas, The", "Bahrain", "Bangladesh",
			"Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil",
			"Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi",
			"Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Republic",
			"Chad", "Chile", "China", "China (Taiwan), Republic of ", "Colombia",
			"Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Cook Islands", "Costa Rica",
			"Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark",
			"Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
			"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
			"Estonia", "Ethiopia", "Fiji", "France", "Gabon",
			"Gambia, The", "Georgia", "Germany", "Ghana", "Greece",
			"Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
			"Haiti", "Honduras", "Hungary", "Iceland", "India",
			"Indonesia", "Iran", "Iraq", "Ireland", "Israel",
			"Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South",
			"Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
			"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
			"Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi",
			"Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
			"Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova",
			"Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
			"Myanmar (Burma)", "Nagorno-Karabakh ", "Namibia", "Nauru", "Nepal",
			"Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
			"Niue", "Northern Cyprus ", "Norway", "Oman", "Pakistan",
			"Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay",
			"Peru", "Philippines", "Poland", "Portugal", "Qatar",
			"Romania", "Russia", "Rwanda", "Sahrawi Arab Democratic Republic", "Saint Kitts and Nevis",
			"Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "São Tomé and Príncipe",
			"Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
			"Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"Somaliland", "South Ossetia", "South Sudan", "Spain", "Sri Lanka",
			"Sudan", "Sudan, South", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand",
			"Timor-Leste", "Togo", "Tonga", "Transnistria", "Trinidad and Tobago",
			"Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda",
			"Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
			"Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
			"Yemen", "Zambia", "Zimbabwe");

	// The following are the various User Interface (UI) elements on the MainScreen

	// UI Section asking the user to select a country from a JComboBox
	Label labelSelectACountry = new Label("Select a country:");

	ComboBox<String> comboboxSelectCountry;

	// UI Section showing the user the text of the currently selected item
	Label labelSelectedCountry = new Label("Selected country:");
	TextField outputSelectedCountry = new TextField("- no country selected -");

	// UI Section showing the user the index of the currently selected item
	Label labelSelectedCountryIndex = new Label("Selected country list index:");
	TextField outputSelectedCountryIndex = new TextField("0");

	// Flag used to ignore ComboBox events when ComboBox objects are being updated
	boolean boolUpdatingComboBox = true;

	// The quit button that terminates the execution of this application
	Button buttonQuit = new Button();

	// The select list state variable keeps track of how many previously selected
	// items are at the top of the enhanced select list
	int selectListState = 0;

	// This string hold the first item string for doing a reset;
	String stringDefaultItem = "";

	// The following is the most recent selection
	String stringSelectItem1 = "";
	int intFoundIndex1 = 1;
	String stringSelectItem2 ="";
	int intFoundIndex2 =0;
	String stringSelectItem3 ="";
	int intFoundIndex3 =0;
	

	/*****
	 * MainScreen constructor - Establish the MainScreen as a window in the middle
	 * of the computer screen, complete with all of the various UI elements, and
	 * make it visible to the user as an interactive application window.
	 */

	private void initializeTheUserInterface() {

		labelSelectACountry.setFont(Font.font("Arial", 18));
		labelSelectACountry.setMinWidth(320);
		labelSelectACountry.setAlignment(Pos.BASELINE_LEFT);
		labelSelectACountry.setLayoutX(90);
		labelSelectACountry.setLayoutY(80);

		comboboxSelectCountry = new ComboBox<String>();
		comboboxSelectCountry.setStyle("-fx-font: 14 \"Arial\";");
		comboboxSelectCountry.setOnAction((event) -> {if (!boolUpdatingComboBox) selectCountryItem(); });
		comboboxSelectCountry.setPrefWidth(320);
		comboboxSelectCountry.setMinHeight(30);
		comboboxSelectCountry.setLayoutX(200);
		comboboxSelectCountry.setLayoutY(100);

		labelSelectedCountry.setFont(Font.font("Arial", 14));
		labelSelectedCountry.setMinWidth(320);
		labelSelectedCountry.setAlignment(Pos.BASELINE_LEFT);
		labelSelectedCountry.setLayoutX(190);
		labelSelectedCountry.setLayoutY(160);

		outputSelectedCountry.setFont(Font.font("Dialog", 14));
		outputSelectedCountry.setMinWidth(320);
		outputSelectedCountry.setAlignment(Pos.BASELINE_LEFT);
		outputSelectedCountry.setLayoutX(200);
		outputSelectedCountry.setLayoutY(180);

		labelSelectedCountryIndex.setFont(Font.font("Dialog", 14));
		labelSelectedCountryIndex.setMinWidth(320);
		labelSelectedCountryIndex.setAlignment(Pos.BASELINE_LEFT);
		labelSelectedCountryIndex.setLayoutX(190);
		labelSelectedCountryIndex.setLayoutY(220);

		outputSelectedCountryIndex.setFont(Font.font("Dialog", 14));
		outputSelectedCountryIndex.setMinWidth(320);
		outputSelectedCountryIndex.setAlignment(Pos.BASELINE_LEFT);
		outputSelectedCountryIndex.setLayoutX(200);
		outputSelectedCountryIndex.setLayoutY(240);

		buttonQuit.setFont(Font.font("Dialog", 14));
		buttonQuit.setMinWidth(70);
		buttonQuit.setAlignment(Pos.BASELINE_LEFT);
		buttonQuit.setLayoutX(500);
		buttonQuit.setLayoutY(430);
		buttonQuit.setText("  Quit  ");
		buttonQuit.setOnAction((event) -> doQuit());


		// Load up the ComboBox will all of the items for the select list
		loadComboBoxData();
		comboboxSelectCountry.getSelectionModel().select(0);
		boolUpdatingComboBox = false;

		// At this point, the program waits for the user to do something and
		// the code reacts to that user action.
	}

	/**
	 * Graphical User Interface (GUI) initialization. This sets the font and
	 * the location for each graphical element in the window, links in the
	 * action listeners, and adds all of them into the JFrame for display.
	 *
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("SHEELA JYOTHSNA");					// Label the stage (a window)

		initializeTheUserInterface();							// Define all the GUI elements

		theRoot = new Pane();									// Create a pane within the window

		theRoot.getChildren().addAll(labelSelectACountry, comboboxSelectCountry,
				labelSelectedCountry, outputSelectedCountry, labelSelectedCountryIndex,
				outputSelectedCountryIndex, buttonQuit);

		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);	// Create the scene with
		// the required width and height

		theStage.setScene(theScene);								// Set the scene on the stage

		theStage.show();											// Show the stage to the user

	}


	/**
	 * Code to handle the "Quit" button.
	 * @param e ActionEvent
	 */
	private void doQuit() {
		System.exit(0);
	}

	/**
	 * Code to handle the "SelectCountry" ComboBox selection event.  When
	 * the user selects an item, this code is performed.  (This method is
	 * not called when the boolBuildingComboBox variable is true.)
	 *
	 * @param e		The actual action event object - it is ignored in this method
	 */
	private void selectCountryItem() {
		// There are two cases depending on where a previously selected list items have been selected
		String theSelectedItem = (String) comboboxSelectCountry.getSelectionModel().getSelectedItem();
		int theSelectedIndex = comboboxSelectCountry.getSelectionModel().getSelectedIndex();

		// This switch uses the display state to determine how to do the work
		switch (selectListState) {

		// This display state say that there was a previously selected item
		case 1:

			// The following code determines which part of the select list has been selected
			switch (theSelectedIndex) {

			// The previously used element was selected
			case 0:
				outputSelectedCountry.setText(stringSelectItem1);
				outputSelectedCountryIndex.setText(intFoundIndex1 + "");
				boolUpdatingComboBox = true;
				comboboxSelectCountry.getSelectionModel().select(0);
				boolUpdatingComboBox = false;
				break;

				// The dashed line or the - no country selected - item was selected
			case 1:
			case 2:
				outputSelectedCountry.setText("- no country selected - ");
				outputSelectedCountryIndex.setText("0");
				selectListState = 0;
				stringSelectItem1 = "";
				intFoundIndex1 = 0;
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;

				// The items below the - no country selected - item was selected
			default:
				outputSelectedCountry.setText(theSelectedItem);
    			outputSelectedCountryIndex.setText((theSelectedIndex - 2) + "");
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 2;
    			selectListState = 2;
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
			}
			break;
		case 2:
			switch(theSelectedIndex) {
			
			case 0:
				outputSelectedCountry.setText(stringSelectItem1);
				outputSelectedCountryIndex.setText(intFoundIndex1 + "");
				boolUpdatingComboBox = true;
				comboboxSelectCountry.getSelectionModel().select(0);
				boolUpdatingComboBox = false;
				break;
			case 1:
				outputSelectedCountry.setText(stringSelectItem2);
    			outputSelectedCountryIndex.setText(intFoundIndex2 + "");
				String stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			int intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
    			break;
			case 2:
			case 3:
				outputSelectedCountry.setText("- no country selected - ");
				outputSelectedCountryIndex.setText("0");
				selectListState = 0;
				stringSelectItem1 = "";
				intFoundIndex1 = 0;
				stringSelectItem2 = "";
				intFoundIndex2 = 0;
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
			default:
				outputSelectedCountry.setText(theSelectedItem);
				outputSelectedCountryIndex.setText((theSelectedIndex - 3) + "");
				stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 3;
    			selectListState = 3;
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
			}
			break;
				
		case 3:
			switch (theSelectedIndex) {
    		case 0:
    			outputSelectedCountry.setText(stringSelectItem1);
    			outputSelectedCountryIndex.setText(intFoundIndex1 + "");
    			boolUpdatingComboBox = true;
				comboboxSelectCountry.getSelectionModel().select(0);
				boolUpdatingComboBox = false;
    			break;
    		case 1:
    			outputSelectedCountry.setText(stringSelectItem2);
    			outputSelectedCountryIndex.setText(intFoundIndex2 + "");
    			String stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			int intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
    			break;
    		case 2:
    			outputSelectedCountry.setText(stringSelectItem3);
    			outputSelectedCountryIndex.setText(intFoundIndex3 + "");
    			stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem3;
    			stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex3;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
    			break;
    		case 3:
    		case 4:
    			outputSelectedCountry.setText("- no country selected - ");
				outputSelectedCountryIndex.setText("0");
				selectListState = 0;
				stringSelectItem1 = "";
				intFoundIndex1 = 0;
				stringSelectItem2 = "";
				intFoundIndex2 = 0;
				stringSelectItem3 = "";
				intFoundIndex3 = 0;
				
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
    		default:
    			outputSelectedCountry.setText(theSelectedItem);
    			outputSelectedCountryIndex.setText((theSelectedIndex - 4) + "");
    			stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 4;
    			selectListState = 3;
    			Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
    			break;
    		}
    		break;
			// This display state say that there was no previously selected item
		default:
			if( theSelectedIndex == 0) {
				// This is the code for the - no country selected - items was selected
				outputSelectedCountry.setText(theSelectedItem);
				comboboxSelectCountry.getSelectionModel().select(0);
			} else {
				// This is the code for an item below the - no country selected - items was selected
				outputSelectedCountry.setText(theSelectedItem);
				outputSelectedCountryIndex.setText(theSelectedIndex + "");
				stringSelectItem1 = theSelectedItem;
				intFoundIndex1 = theSelectedIndex;
				selectListState = 1;
				// to update UI elements from non-main thread
				Platform.runLater(new Runnable() {
					@Override public void run() {
						loadComboBoxData();
					}});
				break;
			}
		}
	}

	/**
	 * The action item selection lists in the ComboBoxes are dynamic. They can
	 * be changed by the program.
	 *
	 * This method throws thread Exception when we are running both background and
	 * UI threads at the same time. So, use runLater thread method (to make thread safe).
	 * Platform.runLater(new Runnable() {
	 *			    @Override public void run() {
	 *			    loadComboBoxData();
	 *			}});
	 *
	 * The buildingComboBox flag is used to signal to the rest of this screen that
	 * a ComboBox is in the process of being updated. Changes to the ComboBox
	 * whether brought about by the user or by code, results in change events.
	 * We do not want change events that come from defining any ComboBox select
	 * list via code to generate update events so we can treat all of those as
	 * coming from the user.
	 *
	 * This routine assumes that the order of the action items in the array is
	 * precisely the correct order for display in the ComboBox. Sorting must be
	 * done elsewhere.
	 * @return
	 *
	 */
	private void loadComboBoxData() {
		// We are going to change the ComboBox, so we need to turn off
		// event processing
		boolUpdatingComboBox = true;


		// Define the ComboBox select list

		// We start by clearing the old list so it is empty
		comboboxSelectCountry.getItems().clear();


		// Based on the state of the select list, display the right number
		// of previously selected items on the top of the whole list
		switch (selectListState) {
		// This is the case where a previous item was selected
		case 1:
			comboboxSelectCountry.getItems().addAll(stringSelectItem1,"----------");
			stringDefaultItem = stringSelectItem1;
			comboboxSelectCountry.getItems().addAll(arrayCountries);
			break;
		case 2:
			comboboxSelectCountry.getItems().addAll(stringSelectItem1,stringSelectItem2,"----------");
			stringDefaultItem = stringSelectItem1;
			comboboxSelectCountry.getItems().addAll(arrayCountries);
			break;
		case 3:
			comboboxSelectCountry.getItems().addAll(stringSelectItem1,stringSelectItem2,stringSelectItem3,"----------");
			stringDefaultItem = stringSelectItem1;
			comboboxSelectCountry.getItems().addAll(arrayCountries);
			break;

			// This is the case where no previous item was selected
		default:
			stringDefaultItem = "";
			comboboxSelectCountry.getItems().addAll(arrayCountries);
			break;
		}

		comboboxSelectCountry.getSelectionModel().select(0);
		Platform.runLater(new Runnable() {
			@Override public void run() {
				ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) comboboxSelectCountry.getSkin();
				((ListView<?>) skin.getPopupContent()).scrollTo(0);
			}});

		// Done updating the ComboBox, so we can now process events normally
		boolUpdatingComboBox=false;
	}

	/**********
	 * This mainline launches the JavaFX application.
	 * 
	 * @param args	The args are ignored
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
