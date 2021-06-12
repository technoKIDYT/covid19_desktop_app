package com.covid19.ui;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.covid19.api.Covid;
import com.covid19.model.Country;
import com.covid19.model.World;
import com.utitlities.Utilities;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

	@FXML
	ComboBox<String> drpCountry, drpCountryCode;

	@FXML
	DatePicker inpDate;

	@FXML
	Label lblData, lblConfirmed, lblRecovered, lblCritical, lblDeath;

	@FXML
	VBox vboxOpt;

	@FXML
	Button btnSubmit;

	String[] countries = new String[] { "Afghanistan", "Albania", "Algeria", "AmericanSamoa", "Andorra", "Angola",
			"Anguilla", "Antarctica", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
			"Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan",
			"Bolivia", "Bosnia", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
			"Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
			"Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
			"Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
			"Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
			"Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
			"Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France",
			"France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon",
			"Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe",
			"Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands",
			"Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
			"Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait",
			"Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
			"Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
			"Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
			"Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
			"Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco",
			"Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
			"New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
			"Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay",
			"Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania",
			"Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
			"Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone",
			"Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
			"South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena",
			"St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden",
			"Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan",
			"Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia",
			"Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
			"United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
			"Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
			"Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine" };

	String[] alpha2Codes = new String[] { "AFG", "AX", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW",
			"AU", "AT", "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BA", "BW", "BV", "BR",
			"VG", "IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL", "CN", "HK", "MO", "CX",
			"CC", "CO", "KM", "CG", "CD", "CK", "CR", "CI", "HR", "CU", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC", "EG",
			"SV", "GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA", "GM", "GE", "DE", "GH",
			"GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HU", "IS", "IN",
			"ID", "IR", "IQ", "IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KP", "KR", "KW", "KG",
			"LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MK", "MG", "MW", "MY", "MV", "ML", "MT", "MH", "MQ",
			"MR", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM", "NA", "NR", "NP", "NL", "AN",
			"NC", "NZ", "NI", "NE", "NG", "NU", "NF", "MP", "NO", "OM", "PK", "PW", "PS", "PA", "PG", "PY", "PE", "PH",
			"PN", "PL", "PT", "PR", "QA", "RE", "RO", "RU", "RW", "BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM",
			"ST", "SA", "SN", "RS", "SC", "SL", "SG", "SK", "SI", "SB", "SO", "ZA", "GS", "SS", "ES", "LK", "SD", "SR",
			"SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO", "TT", "TN", "TR", "TM", "TC",
			"TV", "UG", "UA", "AE", "GB", "US", "UM", "UY", "UZ", "VU", "VE", "VN", "VI", "WF", "EH", "YE", "ZM",
			"ZW" };

	private Covid covid;
	private Country country;
	private World world;
	private boolean isCountry, isCode;
	private String query, date;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		drpCountry.getItems().addAll(countries);
		drpCountryCode.getItems().addAll(alpha2Codes);
		lblConfirmed.setText("");
		lblCritical.setText("");
		lblData.setText("");
		lblDeath.setText("");
		lblRecovered.setText("");
		inpDate.setVisible(false);
		vboxOpt.setDisable(false);
		covid = new Covid();
	}

	public void loadApi(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(drpCountry)) {
			query = drpCountry.getSelectionModel().getSelectedItem();
			isCountry = true;
			isCode = false;
		} else if (actionEvent.getSource().equals(drpCountryCode)) {
			query = drpCountryCode.getSelectionModel().getSelectedItem();
			isCountry = false;
			isCode = true;
		}

		/*
		 * else if (!inpDate.getEditor().getText().isEmpty()) { date =
		 * inpDate.getValue().toString(); System.out.println(date); }
		 */
		if (Utilities.isConnectedToInternet()) {
			vboxOpt.setDisable(true);
			Service<Void> service = new Service<Void>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							// Api here
							if (isCountry) {
								country = covid.getLatestCountryDataByName(query);
								drpCountryCode.getSelectionModel().clearSelection();
							} else if (isCode) {
								country = covid.getLatestCountryDataByCode(query);
								drpCountry.getSelectionModel().clearSelection();
							} else {
								drpCountryCode.getSelectionModel().clearSelection();
								drpCountry.getSelectionModel().clearSelection();
								world = covid.getLatestTotals();
							}

							/*
							 * else { drpCountry.getSelectionModel().clearSelection();
							 * drpCountryCode.getSelectionModel().clearSelection(); world =
							 * covid.getDailyReportTotals(date, ""); }
							 */
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									if (country != null) {
										lblData.setText(country.getName());
										lblConfirmed.setText(country.getCase1().getConfirmed() + "");
										lblCritical.setText(country.getCase1().getCritical() + "");
										lblDeath.setText(country.getCase1().getDeaths() + "");
										lblRecovered.setText(country.getCase1().getRecovered() + "");
									} else if (world != null) {
										lblData.setText("World Wide");
										lblConfirmed.setText(world.getConfirmed() + "");
										lblCritical.setText(world.getCritical() + "");
										lblDeath.setText(world.getDeaths() + "");
										lblRecovered.setText(world.getRecovered() + "");
									}
									drpCountry.getSelectionModel().clearSelection();
									drpCountryCode.getSelectionModel().clearSelection();
									vboxOpt.setDisable(false);
									//
								}
							});
							return null;
						}
					};
				}
			};
			service.start();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Internet Connection");
			alert.setContentText("Please check your internet connection and then try again..!!!");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get() == ButtonType.OK || option.get() == ButtonType.CANCEL) {
				alert.close();
			}
		}
	}

	public void clearData() {
		lblConfirmed.setText("");
		lblCritical.setText("");
		lblDeath.setText("");
		lblRecovered.setText("");
	}

}
