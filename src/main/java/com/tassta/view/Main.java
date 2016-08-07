package com.tassta.view;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	  private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/login.fxml"));
			this.primaryStage=primaryStage;
			Scene scene=new Scene(root,300,200);
			scene.getStylesheets().clear();
			scene.getStylesheets().add("/style.css");

			this.primaryStage.setScene(scene);

			this.primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
