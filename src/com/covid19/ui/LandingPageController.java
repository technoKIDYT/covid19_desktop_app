package com.covid19.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.utitlities.Utilities;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LandingPageController implements Initializable {

	@FXML
	Button btnGetStarted;

	private Stage dashboardStage;
	private double xOffset, yOffset;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void handleGetStarted(ActionEvent actionEvent) {
		if (Utilities.isConnectedToInternet()) {
			Service<Void> service = new Service<Void>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									Stage thisStage = (Stage) btnGetStarted.getScene().getWindow();
									thisStage.close();
									Parent root = null;
									try {
										dashboardStage = new Stage();
										root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
									} catch (IOException e) {
										e.printStackTrace();
									}
									dashboardStage.initStyle(StageStyle.UNDECORATED);
									root.autosize();
									Scene scene = new Scene(root);
									scene.getStylesheets()
											.add(getClass().getResource("application.css").toExternalForm());
									dashboardStage.setTitle("Covid_19 Tracker Desktop App");
									scene.setFill(Color.TRANSPARENT);
									dashboardStage.setScene(scene);
									dashboardStage.show();
									root.setOnMousePressed(new EventHandler<MouseEvent>() {
										@Override
										public void handle(MouseEvent event) {
											xOffset = event.getSceneX();
											yOffset = event.getSceneY();
										}
									});
									root.setOnMouseDragged(new EventHandler<MouseEvent>() {
										@Override
										public void handle(MouseEvent event) {
											dashboardStage.setX(event.getScreenX() - xOffset);
											dashboardStage.setY(event.getScreenY() - yOffset);
										}
									});
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

}
