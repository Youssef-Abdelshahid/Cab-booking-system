//GUI
package cabbookingsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.constant.ConstantDescs.NULL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Yousef
 */
public class CabBookingSystem extends Application {

    @SuppressWarnings("unchecked")
    public static ArrayList<Trip> reqTrips = new ArrayList();
    @SuppressWarnings("unchecked")
    public static HashMap<Trip, Boolean> acceptedTrips = new HashMap();
    private String enteredemail = "";
    private List<String> data;
    private ObservableList<String> searchResults;
    private ListView<String> resultsListView;
    private Group root = new Group();

    @Override
    public void start(Stage primaryStage) {
        login(primaryStage);
    }

    public void createAccount(Stage primaryStage) {
        // Create a new Group for the create account scene
        Group createAccountRoot = new Group();

        // Create a grid pane for layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);  // Center alignment
        gridPane.setVgap(21);
        gridPane.setHgap(43);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateX(205);
        gridPane.setTranslateY(gridPane.getTranslateY() - 20);

        // Create toggle buttons
        ToggleGroup toggleGroup = new ToggleGroup();
        ToggleButton driverToggleButton = new ToggleButton("Driver");
        addHoverScaleAnimation(driverToggleButton);
        driverToggleButton.setSelected(true);
        driverToggleButton.setPrefWidth(170);
        driverToggleButton.setPrefHeight(50);
        driverToggleButton.setId("driverToggleButton");
        driverToggleButton.setToggleGroup(toggleGroup);
        FromTopAnimation(driverToggleButton);

        ToggleButton passengerToggleButton = new ToggleButton("Passenger");
        passengerToggleButton.setPrefWidth(170);
        passengerToggleButton.setPrefHeight(50);
        passengerToggleButton.setId("passengerToggleButton");
        addHoverScaleAnimation(passengerToggleButton);
        addHoverScaleAnimation(passengerToggleButton);
        FromTopAnimation(passengerToggleButton);
        passengerToggleButton.setToggleGroup(toggleGroup);

        // Create text fields
        TextField fnameTextField = new TextField();
        FadeAnimation(fnameTextField);
        TextField lnameTextField = new TextField();
        FadeAnimation(lnameTextField);
        TextField emailTextField = new TextField();
        FadeAnimation(emailTextField);
        TextField passwordTextField = new TextField();
        FadeAnimation(passwordTextField);
        TextField carColorTextField = new TextField();
        FadeAnimation(carColorTextField);
        TextField carModelTextField = new TextField();
        FadeAnimation(carModelTextField);
        TextField carPlateTextField = new TextField();
        FadeAnimation(carPlateTextField);
        // Add labels and text fields to the grid pane
        gridPane.add(driverToggleButton, 3, 1);
        gridPane.add(passengerToggleButton, 4, 1);
        gridPane.setPadding(new Insets(20));
// ... (Rest of the code remains the same)
// Code for Passenger
        Label name = new Label("Name:");
        gridPane.add(name, 2, 2);
        FadeAnimation(name);
        fnameTextField.setId("field");
        gridPane.add(fnameTextField, 3, 2);
        fnameTextField.setPromptText("First Name");

        lnameTextField.setId("field");
        fnameTextField.setPrefWidth(100);
        lnameTextField.setPrefWidth(100);
        gridPane.add(lnameTextField, 4, 2);

        fnameTextField.setPrefHeight(50);
        lnameTextField.setPrefHeight(50);
        lnameTextField.setPromptText("Last Name");
        Label email = new Label("Email:");
        FadeAnimation(email);
        gridPane.add(email, 2, 3);
        emailTextField.setId("field");
        gridPane.add(emailTextField, 3, 3, 2, 1); // Span 2 columns
        emailTextField.setPrefHeight(50);
        emailTextField.setPromptText("EX miu@miu.com");

        Label password = new Label("Password:");
        gridPane.add(password, 2, 4);
        FadeAnimation(password);
        passwordTextField.setId("field");
        gridPane.add(passwordTextField, 3, 4, 2, 1); // Span 2 columns
        passwordTextField.setPrefHeight(50);
        passwordTextField.setPromptText("Enter new password");

        Label carColor = new Label("Car Color:");
        FadeAnimation(carColor);
        gridPane.add(carColor, 2, 5);
        carColorTextField.setId("field");
        gridPane.add(carColorTextField, 3, 5, 2, 1); // Span 2 columns
        carColorTextField.setPrefHeight(50);
        carColorTextField.setPromptText("color");

        Label carModel = new Label("Car Model:");
        FadeAnimation(carModel);
        gridPane.add(carModel, 2, 6);
        carModelTextField.setId("field");
        gridPane.add(carModelTextField, 3, 6, 2, 1); // Span 2 columns
        carModelTextField.setPrefHeight(50);
        carModelTextField.setPromptText("model");

        Label modelYear = new Label("Model Year:");
        FadeAnimation(modelYear);
        gridPane.add(modelYear, 2, 7);
        TextField modelYearTextField = new TextField();
        modelYearTextField.setId("modelYearTextField");
        gridPane.add(modelYearTextField, 3, 7, 2, 1); // Span 2 columns
        modelYearTextField.setPrefHeight(50);
        modelYearTextField.setPromptText(" model year");

        Label carPlateNum = new Label("Car Plate Number:");
        FadeAnimation(carPlateNum);
        gridPane.add(carPlateNum, 2, 8);

        carPlateTextField.setId("field");
        gridPane.add(carPlateTextField, 3, 8, 2, 1); // Span 2 columns
        carPlateTextField.setPrefHeight(50);
        carPlateTextField.setPromptText("Plate no.");

        Button saveButton = new Button("Save");
        TransFadeAnimation(saveButton);
        Button clearButton = new Button("Clear");
        TransFadeAnimation(clearButton);
        saveButton.setPrefSize(170, 50);
        clearButton.setPrefSize(170, 50);

        saveButton.setId("saveButton");
        gridPane.add(saveButton, 4, 9);
        gridPane.add(clearButton, 3, 9);

        clearButton.setId("clearButton");

        addHoverScaleAnimation(saveButton);
        addHoverScaleAnimation(clearButton);
        clearButton.setOnAction(e
                -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to clear all fields?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                fnameTextField.clear();
                lnameTextField.clear();
                emailTextField.clear();
                passwordTextField.clear();
                carColorTextField.clear();
                carModelTextField.clear();
                carPlateTextField.clear();
                modelYearTextField.clear();
            }
        });

        saveButton.setOnAction(e
                -> {
            // Check if any required field is empty
            ToggleButton selectedType = (ToggleButton) toggleGroup.getSelectedToggle();
            if (selectedType.getText().equals("Driver")) {
                boolean datacompleted = false;
               
                if (fnameTextField.getText().isEmpty()
                        || lnameTextField.getText().isEmpty()
                        || emailTextField.getText().isEmpty()
                        || passwordTextField.getText().isEmpty()
                        || carColorTextField.getText().isEmpty()
                        || carModelTextField.getText().isEmpty()
                        || carPlateTextField.getText().isEmpty()
                        || modelYearTextField.getText().isEmpty()) {

                    // Display an error alert for empty fields
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Save Error");
                    alert.setHeaderText("Empty Fields");
                    alert.setContentText("Please fill in all required fields.");
                    alert.showAndWait();
                }else{
                    datacompleted = true;
                }
                boolean isNumericYearCreateScreen = false;
                try {
                    int x = Integer.parseInt(modelYearTextField.getText().trim());
                    isNumericYearCreateScreen = true;
                } catch (NumberFormatException ex) {
                    isNumericYearCreateScreen = false;
                }
                if (!isNumericYearCreateScreen) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Save Error");
                    alert.setHeaderText("Invalid Entry");
                    alert.setContentText("Please enter a number as the car model year.");
                    alert.showAndWait();
                    modelYearTextField.clear();
                }
                
                if(isNumericYearCreateScreen && datacompleted){
                                      
                    //user.addUser
                    Driver d = new Driver(fnameTextField.getText().trim() + " " + lnameTextField.getText().trim(), emailTextField.getText().trim(),
                            passwordTextField.getText().trim(), Integer.valueOf(modelYearTextField.getText()), carModelTextField.getText(), carPlateTextField.getText(),
                            carColorTextField.getText(), 0, 0);
                    new Admin().addUser(d);
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Save Successful");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Account information saved successfully!");
                    successAlert.showAndWait();

                    // Clear the fields after successful save
                    fnameTextField.clear();
                    lnameTextField.clear();
                    emailTextField.clear();
                    passwordTextField.clear();
                    carColorTextField.clear();
                    carModelTextField.clear();
                    carPlateTextField.clear();
                    modelYearTextField.clear();
                
                }

            }
            if (selectedType.getText().equals("Passenger")) {
                if (fnameTextField.getText().isEmpty()
                        || lnameTextField.getText().isEmpty()
                        || emailTextField.getText().isEmpty()
                        || passwordTextField.getText().isEmpty()) {

                    // Display an error alert for empty fields
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Save Error");
                    alert.setHeaderText("Empty Fields");
                    alert.setContentText("Please fill in all required fields.");
                    alert.showAndWait();
                } else {
                    //user.addUser
                    Passenger p = new Passenger(emailTextField.getText().trim(), fnameTextField.getText().trim() + " " + lnameTextField.getText().trim(),
                            passwordTextField.getText().trim(), 0, 0);
                    new Admin().addUser(p);
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Save Successful");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Account information saved successfully!");
                    successAlert.showAndWait();

                    // Clear the fields after successful save
                    fnameTextField.clear();
                    lnameTextField.clear();
                    emailTextField.clear();
                    passwordTextField.clear();
                    carColorTextField.clear();
                    carModelTextField.clear();
                    carPlateTextField.clear();
                    modelYearTextField.clear();
                }
            }
        });

// Add buttons to the gridPane
        // gridPane.getChildren().addAll(saveButton, clearButton);
        passengerToggleButton.setOnAction(o
                -> {
            FadeAnimation(passengerToggleButton);
            FadeAnimation(driverToggleButton);
            FadeAnimation(name);
            FadeAnimation(fnameTextField);
            FadeAnimation(lnameTextField);
            FadeAnimation(emailTextField);
            FadeAnimation(passwordTextField);
            FadeAnimation(email);
            FadeAnimation(password);
            FadeAnimation(saveButton);
            FadeAnimation(clearButton);
            gridPane.getChildren().removeAll(carModelTextField, carPlateTextField, carModel, carPlateNum, carColorTextField, carColor, modelYear, modelYearTextField);

            gridPane.getChildren().removeAll(saveButton, clearButton);
            gridPane.add(saveButton, 4, 5);
            gridPane.add(clearButton, 3, 5);
            gridPane.setVgap(50);
            gridPane.setHgap(50);

        });
        driverToggleButton.setOnAction(eh
                -> {
            FadeAnimation(passengerToggleButton);
            FadeAnimation(driverToggleButton);
            FadeAnimation(fnameTextField);
            FadeAnimation(lnameTextField);
            FadeAnimation(emailTextField);
            FadeAnimation(passwordTextField);
            FadeAnimation(carColorTextField);
            FadeAnimation(carModelTextField);
            FadeAnimation(carPlateTextField);
            FadeAnimation(name);
            FadeAnimation(email);
            FadeAnimation(password);
            FadeAnimation(carColor);
            FadeAnimation(carModel);
            FadeAnimation(modelYear);
            FadeAnimation(carPlateNum);
            FadeAnimation(carPlateTextField);
            FadeAnimation(saveButton);
            FadeAnimation(clearButton);
            gridPane.getChildren().removeAll(carModelTextField, carPlateTextField, carModel, carPlateNum, carColorTextField, carColor, modelYear, modelYearTextField);
            gridPane.getChildren().addAll(carModelTextField, carPlateTextField, carModel, carPlateNum, carColorTextField, carColor, modelYear, modelYearTextField);

            gridPane.getChildren().removeAll(saveButton, clearButton);
            gridPane.add(saveButton, 4, 9);
            gridPane.add(clearButton, 3, 9);
            gridPane.setVgap(21);
            gridPane.setHgap(43);

        });

        Button backBt = new Button("<-");
        FadeAnimation(backBt);
        backBt.setId("Backbt");
        backBt.setPrefSize(40, 40);
        backBt.setTranslateX(10);
        backBt.setTranslateY(10);
        HBox bth = new HBox(backBt);

        backBt.setOnAction(eh
                -> {

            gridPane.getChildren().clear();
            bth.getChildren().clear();
            primaryStage.setScene(null);
            login(primaryStage);

        });

        // Add the grid pane to the new root
        createAccountRoot.getChildren().addAll(gridPane, backBt);

        // Update the existing stage
        primaryStage.setTitle("Create Account - CAB BOOKING SYSTEM");
        Scene scene = new Scene(createAccountRoot, 1250, 650);
        scene.getStylesheets().add(CabBookingSystem.class.getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    public void passengerInterface(Stage primaryStage, Passenger passenger) {
        Image icon = new Image(getClass().getResourceAsStream("/AppIcon.jpg"));
        primaryStage.getIcons().add(icon);
        VBox vbox = new VBox();

        ToggleGroup menuOfButtons = new ToggleGroup();

        StackPane stack = new StackPane();
        vbox.setSpacing(40);

        Image backgroundImage = new Image("/passback.png");
        // Create a BackgroundImage
        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundImg);

        stack.setBackground(background);

        Label cabBookingSystem = new Label("   Cab Booking \n       System");
        cabBookingSystem.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        cabBookingSystem.setStyle("-fx-text-fill: green ;");

        vbox.getChildren().add(cabBookingSystem);

        //Home Button
        ToggleButton homeButton = new ToggleButton("Home");
        addHoverScaleAnimation(homeButton);
        FadeAnimation(homeButton);
        homeButton.setToggleGroup(menuOfButtons);
        homeButton.setPrefSize(250, 20);

        vbox.getChildren().add(homeButton);
        homeButton.setId("navbar");
        Image h = new Image("/home.png");
        ImageView imageView1 = new ImageView(h);
        stack.getChildren().add(imageView1);
        imageView1.setTranslateX(132);

        Image homegif = new Image("/homeVid.gif");
        ImageView gifhomeView = new ImageView(homegif);
        stack.getChildren().add(gifhomeView);
        gifhomeView.setFitHeight(540);
        gifhomeView.setFitWidth(960);
        gifhomeView.setTranslateX(132);

        homeButton.setOnAction(e3 -> {
            stack.getChildren().clear();
            stack.getChildren().addAll(imageView1, gifhomeView, vbox);
        });

        //view my profile Button
        ToggleButton viewMyProfile = new ToggleButton("View My Profile");
        addHoverScaleAnimation(viewMyProfile);
        FadeAnimation(viewMyProfile);
        viewMyProfile.setToggleGroup(menuOfButtons);
        viewMyProfile.setPrefSize(250, 20);
        viewMyProfile.setFont(new Font(13));
        GridPane requestMenu = new GridPane();
        viewMyProfile.setId("navbar");

        //Handling of view my profile button
        viewMyProfile.setOnAction(e2
                -> {

            stack.getChildren().clear();
            Rectangle rectangle = new Rectangle(1250, 600, Color.BLACK);
            stack.getChildren().add(rectangle);
            stack.getChildren().add(vbox);
            Image mypr = new Image("/myprof.png");
            ImageView imageView = new ImageView(mypr);
            PageFade(imageView);
            imageView.setTranslateX(131);

            stack.getChildren().add(imageView);
            String view = new Admin().viewUserReport(passenger.userEmail);
            Label myProf = new Label(view);
            FadeAnimation(myProf);
            myProf.setFont(new Font(40));
            myProf.setTextFill(Color.WHITE);
            myProf.setAlignment(Pos.CENTER);
            myProf.setTranslateX(100);
            myProf.setTranslateY(90);
            stack.getChildren().add(myProf);

        });
        vbox.getChildren().add(viewMyProfile);

        //View My Trips Button
        ToggleButton myTrips = new ToggleButton("View My Trips");
        addHoverScaleAnimation(myTrips);
        FadeAnimation(myTrips);
        myTrips.setToggleGroup(menuOfButtons);
        myTrips.setPrefSize(250, 20);
        vbox.getChildren().add(myTrips);
        myTrips.setId("navbar");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMaxWidth(380);
        scrollPane.setTranslateX(437);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setId("sid");

        Image data = new Image("/myt.png");
        ImageView imageViewd = new ImageView(data);
        imageViewd.setTranslateX(132);

        VBox optionsContainer = new VBox();
        //Handling of view my trips
        myTrips.setOnAction(e
                -> {
            try {
                stack.getChildren().clear();
                Rectangle rectangle = new Rectangle(1250, 600, Color.BLACK);
                stack.getChildren().add(rectangle);
                stack.getChildren().add(vbox);
                @SuppressWarnings("unchecked")
                ArrayList<Trip> tripsMade = new ArrayList();

                optionsContainer.setSpacing(15);
                tripsMade = passenger.viewMyTrips();
                if (!tripsMade.isEmpty()) {
                    stack.getChildren().add(imageViewd);
                    stack.getChildren().add(scrollPane);
                    VBox textContainer = new VBox();
                    textContainer.setSpacing(15);

                    for (Trip tripOption : tripsMade) {
                        // Create a Text node for each trip and set its content
                        Text tripText = new Text(tripOption.toString());
                        FadeAnimation(tripText);
                        PageFade(imageViewd);
                        tripText.setFill(Color.WHITE); // Set text color to white
                        textContainer.getChildren().add(tripText);
                    }

                    scrollPane.setContent(textContainer);
                } else {

                    Image datnf = new Image("/artboard_1x.png");
                    ImageView imageView = new ImageView(datnf);
                    PageFade(imageView);
                    imageView.setTranslateX(132);
                    stack.getChildren().add(imageView);
                }

            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        });

        //Request Button
        ToggleButton requestButton = new ToggleButton("Request Trip");
        addHoverScaleAnimation(requestButton);
        FadeAnimation(requestButton);
        requestButton.setToggleGroup(menuOfButtons);
        requestButton.setPrefSize(250, 20);
        vbox.getChildren().add(requestButton);
        requestButton.setId("navbar");

        vbox.setSpacing(5);
        Button req = new Button("Find a driver");
        req.setId("reqbutton");
        TextField destinationField = new TextField();
        TextField pickUpField = new TextField();
        Label pickUpLocation = new Label("Pick Up:");
        Label destination = new Label("Destination:");
        Image reqi = new Image("/req.png");
        ImageView imageView = new ImageView(reqi);

        requestButton.setOnAction(e
                -> {
            stack.getChildren().clear();

            stack.getChildren().remove(requestMenu);
            requestMenu.getChildren().clear();
            Rectangle rectangle = new Rectangle(1250, 600, Color.BLACK);
            stack.getChildren().add(rectangle);

            vbox.setMinWidth(180);
            stack.getChildren().add(vbox);

            requestMenu.setMaxWidth(400);

            requestMenu.setVisible(true);

            //aniamtion
            FadeAnimation(req);
            FromRightAnimation(destinationField);
            FromRightAnimation(pickUpField);
            FromRightAnimation(pickUpLocation);
            FromRightAnimation(destination);
            PageFade(imageView);

            requestMenu.add(pickUpLocation, 0, 0);
            pickUpLocation.setFont(new Font(20));
            pickUpLocation.setTextFill(Color.WHITE);

            requestMenu.add(pickUpField, 1, 0);
            pickUpField.setId("PF1");
            pickUpField.setFont(new Font(20));

            requestMenu.add(destination, 0, 1);
            destination.setFont(new Font(20));
            destination.setTextFill(Color.WHITE);

            requestMenu.add(destinationField, 1, 1);
            destinationField.setFont(new Font(20));
            destinationField.setId("PF1");
            GridPane.setColumnSpan(req, 2);
            req.setFont(new Font(30));

            req.setPrefSize(600, 70);
            requestMenu.add(req, 0, 2);

            requestMenu.setTranslateX(78);

            imageView.setTranslateX(131.5);
            stack.getChildren().add(imageView);

            requestMenu.setVgap(10);
            requestMenu.setHgap(7);
            requestMenu.setAlignment(Pos.CENTER);

            stack.getChildren().add(requestMenu);

        });

        req.setOnAction(e1
                -> {
            String pickupLocation = pickUpField.getText().trim();
            String destinationLocation = destinationField.getText().trim();

            if (pickupLocation.isEmpty() || destinationLocation.isEmpty()) {

                Label errorLabel = new Label("Pickup and destination cannot be empty!");
                errorLabel.setTextFill(Color.RED);

                pickUpField.setStyle("-fx-border-color: red;");
                destinationField.setStyle("-fx-border-color: red;");

                requestMenu.add(errorLabel, 0, 3);

                GridPane.setColumnSpan(errorLabel, GridPane.REMAINING);
                errorLabel.setAlignment(Pos.CENTER);

                PauseTransition visiblePause = new PauseTransition(Duration.seconds(4));
                visiblePause.setOnFinished(event -> {
                    errorLabel.setVisible(false);
                    pickUpField.setStyle("");
                    destinationField.setStyle("");
                });
                visiblePause.play();
            } else {
                stack.getChildren().clear();
                stack.getChildren().add(vbox);

                requestMenu.setVisible(false);
                Image gifImage = new Image("/giphy.gif");
                ImageView gifImageView = new ImageView(gifImage);
                PageFade(gifImageView);
                gifImageView.setTranslateX(110);

                stack.getChildren().add(gifImageView);
                Trip requestedTrip = passenger.requestTrip(pickUpField.getText(), destinationField.getText());
                reqTrips.add(requestedTrip);

                Task<Void> waitForAcceptanceTask = new Task<>() {
                    @Override
                    protected Void call() {

                        while (true) {
                            //ArrayList<Trip> acceptedTrips = SharedData.getAcceptedTrips();
                            if (acceptedTrips.containsKey(requestedTrip)) {
                                // The requested trip is accepted
                                updateMessage("Driver accepted the trip: " + requestedTrip);
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                        return null;
                    }
                };
                waitForAcceptanceTask.messageProperty().addListener((observable, oldValue, newValue)
                        -> {
                    System.out.println(newValue);
                    gifImageView.setVisible(false);

                    Trip trip = null;
                    for (Map.Entry<Trip, Boolean> e : acceptedTrips.entrySet()) {
                        if (e.getKey().getPassenger() == passenger) {
                            trip = e.getKey();
                            break;
                        }
                    }
                    String details = trip.getDriver().toString();
                    details += "Pick up location " + trip.getSource() + '\n'
                            + "Destination: " + trip.getDestination() + '\n'
                            + "Fare: " + trip.getFare() + '\n'
                            + "Distance: " + trip.getTripDistance() + '\n'
                            + "The Driver is arriving soon...";

                    Label tripDetails = new Label(details);
                    FadeAnimation(tripDetails);
                    tripDetails.setAlignment(Pos.CENTER);
                    tripDetails.setFont(new Font(24));
                    tripDetails.setTranslateX(-130);
                    tripDetails.setTranslateY(10);
                    tripDetails.setTextFill(Color.WHITE);

                    Image postacc = new Image("/wait.png");
                    ImageView imageViewpostacc = new ImageView(postacc);
                    imageViewpostacc.setTranslateX(131);
                    stack.getChildren().add(imageViewpostacc);

                    stack.getChildren().add(tripDetails);
                    tripDetails.setVisible(true);
                    Task<Void> waitForArrivalTask = new Task<>() {
                        @Override
                        protected Void call() {

                            while (true) {
                                if (acceptedTrips.get(requestedTrip)) {
                                    // The requested trip is accepted
                                    updateMessage("Driver accepted the trip: " + requestedTrip);
                                    break;
                                }

                                try {

                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    break;
                                }
                            }
                            return null;
                        }
                    };

                    waitForArrivalTask.messageProperty().addListener((observable1, oldValue1, newValue1)
                            -> {

                        tripDetails.setVisible(false);

                        Label arrival = new Label("The driver has arrived\n Enjoy your ride!");
                        FadeAnimation(arrival);
                        arrival.setAlignment(Pos.CENTER);
                        arrival.setFont(new Font(30));
                        arrival.setTranslateX(-130);
                        arrival.setTextFill(Color.WHITE);
                        stack.getChildren().add(arrival);

                    });

                    new Thread(waitForArrivalTask).start();

                    Task<Void> waitForPaymentTask = new Task<>() {
                        @Override
                        protected Void call() {

                            while (true) {

                                if (!acceptedTrips.containsKey(requestedTrip)) {
                                    // The requested trip is accepted
                                    updateMessage("Driver accepted the trip: " + requestedTrip);

                                    break;

                                }

                                try {

                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    break;
                                }
                            }
                            return null;
                        }
                    };

                    waitForPaymentTask.messageProperty().addListener((observable1, oldValue1, newValue1)
                            -> {

                        stack.getChildren().clear();
                        Rectangle rectangle = new Rectangle(1250, 600, Color.BLACK);
                        stack.getChildren().add(rectangle);
                        stack.getChildren().add(vbox);
                        Image ty = new Image("/ty.png");
                        ImageView ty1 = new ImageView(ty);
                        PageFade(ty1);
                        ty1.setTranslateX(131);
                        stack.getChildren().add(ty1);
                    });

                    new Thread(waitForPaymentTask).start();
                });
                new Thread(waitForAcceptanceTask).start();
            }
        });

//        //Settings Button
        vbox.setPadding(new Insets(10));
        stack.getChildren().add(vbox);

        vbox.setSpacing(40);
        Scene passengerScene = new Scene(stack, 1250, 600);
        primaryStage.setScene(passengerScene);

        //Set the title of the page
        primaryStage.setTitle("Passenger");
        passengerScene.getStylesheets().add(CabBookingSystem.class.getResource("Style.css").toExternalForm());
        // Show the stage.
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void driverInterface(Stage primaryStage, Driver driver) {
        Image icon = new Image(getClass().getResourceAsStream("/AppIcon.jpg"));
        primaryStage.getIcons().add(icon);
        VBox vbox = new VBox();
        StackPane stack = new StackPane();

        ToggleGroup navmenu = new ToggleGroup();
        vbox.setSpacing(40);

        Image backgroundImage = new Image("/passback.png");

        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundImg);

        stack.setBackground(background);

        Label cabBookingSystem = new Label("   Cab Booking \n       System");
        cabBookingSystem.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        cabBookingSystem.setStyle("-fx-text-fill: green ;");
        cabBookingSystem.setTranslateX(5);
        cabBookingSystem.setTranslateY(4);

        vbox.getChildren().add(cabBookingSystem);

        //Home
        ToggleButton homeButton2 = new ToggleButton("Home");
        addHoverScaleAnimation(homeButton2);
        FadeAnimation(homeButton2);
        homeButton2.setPrefSize(250, 20);
        vbox.getChildren().add(homeButton2);
        homeButton2.setTranslateX(4);
        homeButton2.setToggleGroup(navmenu);
        homeButton2.setId("navbar");

        Image homed = new Image("/home.png");
        ImageView imageViewhome = new ImageView(homed);
        imageViewhome.setTranslateX(131);
        stack.getChildren().add(imageViewhome);

        Image homegif = new Image("/driverVid.gif");
        ImageView gifhomeView = new ImageView(homegif);
        PageFade(gifhomeView);
        stack.getChildren().add(gifhomeView);
        gifhomeView.setFitHeight(540);
        gifhomeView.setFitWidth(960);
        gifhomeView.setTranslateX(132);
        homeButton2.setOnAction(e3 -> {
            stack.getChildren().clear();
            stack.getChildren().addAll(imageViewhome, gifhomeView, vbox);
        });

        //view my profile Button
        ToggleButton viewMyProfile2 = new ToggleButton("View my profile");
        addHoverScaleAnimation(viewMyProfile2);
        FadeAnimation(viewMyProfile2);
        viewMyProfile2.setPrefSize(250, 20);
        viewMyProfile2.setTranslateX(4);
        viewMyProfile2.setFont(new Font(13));
        viewMyProfile2.setId("navbar");
        viewMyProfile2.setToggleGroup(navmenu);

        viewMyProfile2.setOnAction(e
                -> {

            stack.getChildren().clear();
            Rectangle rectangle = new Rectangle(1250, 600, Color.BLACK);
            stack.getChildren().add(rectangle);
            stack.getChildren().add(vbox);

            Image myprof = new Image("/myprof.png");
            ImageView imageViewhome2 = new ImageView(myprof);
            PageFade(imageViewhome2);
            imageViewhome2.setTranslateX(131);
            stack.getChildren().add(imageViewhome2);
            String view = new Admin().viewUserReport(driver.userEmail);
            Label myProf = new Label(view);
            FadeAnimation(myProf);
            myProf.setFont(new Font(27));
            myProf.setTextFill(Color.WHITE);
            myProf.setAlignment(Pos.CENTER);
            myProf.setTranslateX(125);
            myProf.setTranslateY(90);
            stack.getChildren().add(myProf);

        });

        vbox.getChildren().add(viewMyProfile2);

        //View My Trips Button
        ToggleButton myTrips2 = new ToggleButton("View my trips");
        addHoverScaleAnimation(myTrips2);
        FadeAnimation(myTrips2);
        myTrips2.setPrefSize(250, 20);
        vbox.getChildren().add(myTrips2);
        myTrips2.setTranslateX(4);
        myTrips2.setId("navbar");
        myTrips2.setToggleGroup(navmenu);

        myTrips2.setOnAction(e
                -> {

            Image vt = new Image("/hisd.png");
            ImageView imageViewhome4 = new ImageView(vt);
            PageFade(imageViewhome4);
            imageViewhome4.setTranslateX(131);
            imageViewhome4.setTranslateY(0);
            stack.getChildren().add(imageViewhome4);
            @SuppressWarnings("unchecked")
            ArrayList<Trip> tripsMade = new ArrayList();
            VBox optionsContainer = new VBox();
            optionsContainer.setSpacing(15);
            tripsMade = driver.viewMyTrips();

            if (!tripsMade.isEmpty()) {
                ScrollPane scroll = new ScrollPane();
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                scroll.setPrefSize(10, 10);

                scroll.setMaxWidth(380);
                scroll.setTranslateX(437);
                scroll.setStyle("-fx-background-color: transparent;");
                scroll.setId("sid2");

                for (Trip tripOption : tripsMade) {
                    Text manyT = new Text(tripOption.toString());
                    FadeAnimation(manyT);

                    manyT.setFill(Color.WHITE);
                    optionsContainer.getChildren().add(manyT);
                }

                scroll.setContent(optionsContainer);

                stack.getChildren().add(scroll);
            } else {
                Image datnf2 = new Image("/artboard_1x.png");
                ImageView imageView2 = new ImageView(datnf2);
                PageFade(imageView2);
                imageView2.setTranslateX(130);

                stack.getChildren().add(imageView2);
            }
        });

        // View Available trips
        ToggleButton availableTrips = new ToggleButton("Available trips");
        addHoverScaleAnimation(availableTrips);
        FadeAnimation(availableTrips);
        availableTrips.setPrefSize(250, 20);
        availableTrips.setTranslateX(4);
        vbox.getChildren().add(availableTrips);

        availableTrips.setId("navbar");
        availableTrips.setToggleGroup(navmenu);
        availableTrips.setOnAction(e
                -> {

            stack.getChildren().clear();
            Rectangle rectangle = new Rectangle(1250, 600, Color.BLACK);
            stack.getChildren().add(rectangle);
            stack.getChildren().add(vbox);
            VBox optionsContainer = new VBox();
            optionsContainer.setSpacing(15);
            if (!reqTrips.isEmpty()) {

                ScrollPane scrollPane = new ScrollPane();

                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

                Image datnf3 = new Image("/brav.png");
                ImageView imageView3 = new ImageView(datnf3);
                PageFade(imageView3);
                imageView3.setTranslateX(130);

                stack.getChildren().add(imageView3);
                scrollPane.setMaxWidth(550);
                scrollPane.setTranslateX(-88);
                scrollPane.setStyle("-fx-background-color: transparent;");
                scrollPane.setId("sid");

                for (Trip tripOption : reqTrips) {
                    HBox optionBox = createOptionBox(tripOption.toString(), driver, optionsContainer, stack, scrollPane);
                    optionsContainer.getChildren().add(optionBox);

                    optionBox.getChildren().forEach(innerNode -> {
                        if (innerNode instanceof Label) {
                            ((Label) innerNode).setTextFill(Color.WHITE);
                        }
                    });
                }

                scrollPane.setContent(optionsContainer);

                stack.getChildren().add(scrollPane);

            } else {

                Image avtr = new Image("/noavt.png");
                ImageView imageViewhome3 = new ImageView(avtr);
                PageFade(imageViewhome3);
                stack.getChildren().add(imageViewhome3);
                imageViewhome3.setTranslateX(132);
                imageViewhome3.setTranslateY(0);

            }
        });

        //Settings Button
        stack.getChildren().add(vbox);
        Scene driverScene = new Scene(stack, 1250, 600);

        primaryStage.setScene(driverScene);

        //Set the title of the page
        primaryStage.setTitle("Driver");

        // Show the stage.
        primaryStage.setResizable(false);
        primaryStage.show();
        driverScene.getStylesheets().add(CabBookingSystem.class.getResource("Style.css").toExternalForm());
    }

    private HBox createOptionBox(String option, Driver driver, VBox vbox, StackPane stack2, ScrollPane scroll) {
        HBox optionHBox = new HBox();
        optionHBox.setSpacing(10);

        Label optionLabel = new Label(option);
        FadeAnimation(optionLabel);
        optionLabel.setFont(new Font(18));

        Button acceptButton = new Button("Accept");
        FadeAnimation(acceptButton);
        acceptButton.setTranslateX(-15);
        acceptButton.setId("BTA");

        acceptButton.setOnAction(event
                -> {

            String acceptedTripId = extractTripId(option);
            System.out.println(acceptedTripId);
            Trip acceptedTrip = null;
            for (Trip t : reqTrips) {
                if (t.getTripID().equals(acceptedTripId)) {
                    acceptedTrip = t;
                    break;
                }
            }
            driver.acceptTrip(acceptedTripId, reqTrips);
            acceptedTrips.put(acceptedTrip, false);
            vbox.setVisible(false);
            stack2.getChildren().remove(scroll);
            Label onTheWay = new Label("On the way to the Passenger!");
            FadeAnimation(onTheWay);
            onTheWay.setTranslateY(-55);
            onTheWay.setTranslateX(-80);
            onTheWay.setFont(new Font(30));
            onTheWay.setId("otw");
            onTheWay.setTextFill(Color.WHITE);
            Button driverArrived = new Button("Arrived");
            driverArrived.setId("BTA");
            driverArrived.setTranslateX(-80);
            driverArrived.setAlignment(Pos.BOTTOM_CENTER);
            driverArrived.setPrefSize(200, 40);
            stack2.getChildren().addAll(onTheWay, driverArrived);
            String ID = acceptedTrip.getTripID();
            driverArrived.setOnAction(eh
                    -> {
                onTheWay.setVisible(false);
                driverArrived.setVisible(false);
                VBox way = new VBox();
                way.setMaxWidth(600);
                Trip tp = null;
                for (Map.Entry<Trip, Boolean> m : acceptedTrips.entrySet()) {

                    if (m.getKey().getTripID().equals(ID)) {
                        acceptedTrips.remove(m.getKey());
                        tp = m.getKey();
                        break;
                    }
                }
                acceptedTrips.put(tp, true);
                Label arrivedToThePassenger = new Label("You have arrived to the passenger successfully \n Going to the destiation...");
                FadeAnimation(arrivedToThePassenger);
                arrivedToThePassenger.setTextFill(Color.WHITE);
                arrivedToThePassenger.setFont(new Font(24));
                arrivedToThePassenger.setTranslateX(-80);
                Button acceptPayment = new Button("Accept payment");
                FadeAnimation(acceptPayment);
                acceptPayment.setId("BTA");
                acceptPayment.setTranslateX(-80);
                acceptPayment.setTranslateY(25);
                way.setAlignment(Pos.CENTER);
                way.getChildren().addAll(arrivedToThePassenger, acceptPayment);
                stack2.getChildren().add(way);

                acceptPayment.setOnAction(a
                        -> {
                    Trip tripaya = null;
                    for (Map.Entry<Trip, Boolean> m : acceptedTrips.entrySet()) {

                        if (m.getKey().getTripID().equals(ID)) {
                            acceptedTrips.remove(m.getKey());
                            tripaya = m.getKey();
                            break;
                        }
                    }
                    driver.acceptPayment(ID);

                    stack2.getChildren().remove(way);
                    Label ended = new Label("The trip has ended successfully\nThe fare is: " + tripaya.getFare());
                    FadeAnimation(ended);
                    ended.setTextFill(Color.WHITE);
                    ended.setAlignment(Pos.CENTER);
                    ended.setFont(new Font(20));
                    ended.setTranslateX(-80);
                    stack2.getChildren().add(ended);

                });
            });
        });

        optionHBox.getChildren().addAll(optionLabel, acceptButton);
        optionHBox.setAlignment(Pos.CENTER_RIGHT);

        return optionHBox;
    }

    private HBox createTripsLinesBox(String option) {
        HBox optionHBox = new HBox();
        optionHBox.setSpacing(10);

        Label optionLabel = new Label(option);
        optionLabel.setFont(new Font(18));
        optionLabel.setTextFill(Color.BLACK);
        optionHBox.setAlignment(Pos.CENTER_RIGHT);
        optionHBox.getChildren().addAll(optionLabel);

        return optionHBox;
    }

    private String extractTripId(String tripReportText) {

        String[] lines = tripReportText.split("\n");
        if (lines.length >= 2) {
            String tripIdLine = lines[2];
            String[] parts = tripIdLine.split(":");
            if (parts.length == 2) {
                return parts[1].trim();
            }
        }
        return null;
    }

    void login(Stage primaryStage) {
        Image icon = new Image(getClass().getResourceAsStream("/AppIcon.jpg"));
        primaryStage.getIcons().add(icon);
        Group root = new Group();
        primaryStage.setTitle("CAB BOOKING SYSTEM");
        primaryStage.setResizable(false);

        // BACKGROUND
        Image back = new Image("/log1.png");
        ImageView mv = new ImageView(back);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), mv);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), mv);
        translateTransition.setFromX(50); // Starting Y position
        translateTransition.setToX(0); // Ending Y position 
        translateTransition.setAutoReverse(false);
        translateTransition.setCycleCount(1);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.play();

        // GRID AND ALIGNMENT
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // EMAIL LABEL AND FIELD
        Label emailLabel = new Label("  Email: ");
        TextField emailField = new TextField();
        emailField.setPrefWidth(270);
        emailField.setPrefHeight(50);
        emailField.setId("email");
        emailField.setPromptText("EMAIL");
        gridPane.add(emailLabel, 12, 45);
        gridPane.add(emailField, 12, 46);

        // PASSWORD LABEL AND FIELD
        Label passwordLabel = new Label("  Password: ");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(270);
        passwordField.setPrefHeight(50);
        passwordField.setId("pass5");
        passwordField.setPromptText("PASSWORD");
        gridPane.add(passwordLabel, 12, 50);
        gridPane.add(passwordField, 12, 51);

        // LOGIN AND CREATE ACCOUNT BUTTON
        Button submitButton = new Button("LOG IN");
        addHoverScaleAnimation(submitButton);
        TransFadeAnimation(submitButton);
        submitButton.setId("submitButton");
        submitButton.setPrefWidth(270);
        submitButton.setPrefHeight(50);

        Button createAccButton = new Button("CREATE ACCOUNT?");
        addHoverScaleAnimation(createAccButton);
        TransFadeAnimation(createAccButton);
        createAccButton.setId("creatacc");
        createAccButton.setPrefWidth(270);
        createAccButton.setPrefHeight(50);

        gridPane.add(submitButton, 12, 59);
        gridPane.add(createAccButton, 12, 62);

        // EVENT HANDLER (LOGIN AND CREATE ACCOUNT)
        submitButton.setOnAction(e
                -> {

            String email = emailField.getText();
            String password = passwordField.getText();
            User user = User.login(email, password);
            if (user == null) {
                Label errorLabel = new Label("Incorrect email or password. Please try again.");
                errorLabel.setTextFill(Color.RED);

                emailField.setStyle("-fx-border-color: red;");
                passwordField.setStyle("-fx-border-color: red;");

                emailField.clear();
                passwordField.clear();

                gridPane.add(errorLabel, 12, 63);

                PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
                visiblePause.setOnFinished(event
                        -> {
                    gridPane.getChildren().remove(errorLabel);
                    emailField.setStyle("");
                    passwordField.setStyle("");
                });
                visiblePause.play();
            } else {
                Thread loginThread = new Thread(()
                        -> {
                    Platform.runLater(()
                            -> {
                        if (user instanceof Passenger) {
                            Passenger p = (Passenger) user;
                            passengerInterface(new Stage(), p);
                        } else if (user instanceof Driver) {
                            Driver d = (Driver) user;
                            driverInterface(new Stage(), d);
                        } else {
                            Admin a = (Admin) user;
                            adminInterface(new Stage(), a);
                        }
                    });
                });
                loginThread.start();
            }
        });

        createAccButton.setOnAction(e
                -> {
            createAccount(primaryStage);
        });

        // SHOW SETTING
        root.getChildren().addAll(mv, gridPane);
        Scene scene = new Scene(root, 1250, 600);
        scene.getStylesheets().add(CabBookingSystem.class.getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void adminInterface(Stage AdminScreen, Admin temp3) {
        Image icon = new Image(getClass().getResourceAsStream("/AppIcon.jpg"));
        AdminScreen.getIcons().add(icon);
        AdminScreen.setMinWidth(1400);
        AdminScreen.setMinHeight(700);
        AdminScreen.setResizable(false);

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1200, 600);
        String css = this.getClass().getResource("/AdminCSS.css").toExternalForm();
        scene.getStylesheets().add(css);

        //drop shadow effect cretiopn to be applied on nodes in the project//
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        dropShadow.setRadius(5);
        dropShadow.setColor(javafx.scene.paint.Color.rgb(0, 0, 0, 0.3)); //Set shadow color and opacity//

        //Side menu bar/
        VBox leftBox = new VBox(10);
        leftBox.setMinWidth(180);
        leftBox.setId("leftBox");
        leftBox.setPadding(new Insets(10));
        Button Home = new Button("Home");
        Button Add = new Button("Add User");
        Button Edit = new Button("Edit User");
        Button Delete = new Button("Delete User");
        Button MoreData = new Button("More Data");
        Button Search = new Button("Search");
        addHoverScaleAnimation(Home);
        addHoverScaleAnimation(Add);
        addHoverScaleAnimation(Edit);
        addHoverScaleAnimation(Delete);
        addHoverScaleAnimation(MoreData);
        addHoverScaleAnimation(Search);
        Home.setMinSize(150, 30);
        Add.setMinSize(150, 30);
        Edit.setMinSize(150, 30);
        Delete.setMinSize(150, 30);
        MoreData.setMinSize(150, 30);
        Search.setMinSize(150, 30);
        leftBox.setSpacing(25);
        Home.setId("Home");
        Add.setId("Add");
        Edit.setId("Edit");
        Delete.setId("Delete");
        MoreData.setId("MoreData");
        Search.setId("Search");
        root.setLeft(leftBox);
        leftBox.setEffect(dropShadow);
        leftBox.getChildren().addAll(Home, Add, Edit, Delete, Search, MoreData);

        // Top bar with user label and logout
        HBox topBar = new HBox();
        topBar.setMinHeight(60);
        topBar.setEffect(dropShadow);
        topBar.setId("topBar");
        Region space = new Region(); // for layout//
        topBar.prefWidthProperty().bind(AdminScreen.widthProperty());

        Label userLabel = new Label("Welcome " + temp3.userName); // Replace with actual user name
        userLabel.setId("userLabel");
        userLabel.setFont(new Font(24));
        userLabel.setPadding(new Insets(10));

        Button logOut = new Button("Log out");
        logOut.setId("logOut");
        logOut.setAlignment(Pos.TOP_RIGHT);
        logOut.setPrefSize(160, 58);

        HBox.setHgrow(userLabel, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(logOut, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(space, javafx.scene.layout.Priority.ALWAYS);
        topBar.getChildren().addAll(userLabel, space, logOut);
        root.setTop(topBar);

        //Home Screen//
        VBox MiddleScreen = new VBox(5);
        MiddleScreen.setId("MiddleScreen");
        MiddleScreen.setMinSize(1020, 540);
        MiddleScreen.setPadding(new Insets(10));

        //Trip Data//
        Label totalRevenueTripsLabel = new Label();
        double totalfare = Math.round(temp3.getTotalFares() * 100.0) / 100.0;
        double averagefare = Math.round(temp3.getAverageFare() * 100.0) / 100.0;

        String totalfareAsString = Double.toString(totalfare);
        String averagefareAsString = Double.toString(averagefare);
        totalRevenueTripsLabel.setText("Sum of Fares from all trips: " + totalfareAsString + " LE" + "\n" + "Average of all fares: " + averagefareAsString + " LE");
        totalRevenueTripsLabel.setId("totalRevenueTripsLabel");

        //User Table view//
        @SuppressWarnings("unchecked")
        TableView<String[]> UsersTable = new TableView<>();
        UsersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        UsersTable.setEffect(dropShadow);
        UsersTable.setMaxHeight(500);

        TableColumn<String[], String> nameCol = new TableColumn<>("Name");

        nameCol.setMinWidth(245);
        nameCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[0]);
        });

        TableColumn<String[], String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(280);
        emailCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[1]);
        });

        TableColumn<String[], String> passwordCol = new TableColumn<>("Password");
        passwordCol.setMinWidth(235);
        passwordCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[2]);
        });

        TableColumn<String[], String> userTypeCol = new TableColumn<>("UserType");
        userTypeCol.setMinWidth(240);

        userTypeCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[3]);
        });

        //Setting data into table using observale list of string array//
        ObservableList<String[]> UserData = FXCollections.observableArrayList();
        Admin temp = new Admin();
        String[] UsersArr = temp.getUsersByLine().split("\n");
        for (String user : UsersArr) {
            String[] x = user.split(",");
            UserData.addAll(x);
        }

        //Adding table elements to table//
        UsersTable.getColumns().addAll(nameCol, emailCol, passwordCol, userTypeCol);
        UsersTable.getItems().addAll(UserData);

        // Trips Table View//
        TableView<String[]> TripsTable = new TableView<>();
        TripsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TripsTable.setEffect(dropShadow);
        TripsTable.setMaxHeight(500);
        TableColumn<String[], String> tripidCol = new TableColumn<>("Trip ID");
        TableColumn<String[], String> passengerCol = new TableColumn<>("Passenger");
        TableColumn<String[], String> driverCol = new TableColumn<>("Driver");
        TableColumn<String[], String> tripfareCol = new TableColumn<>("Trip Fare");
        TableColumn<String[], String> sourceCol = new TableColumn<>("Source");
        TableColumn<String[], String> destinationCol = new TableColumn<>("Destination");
        TableColumn<String[], String> tripdistanceCol = new TableColumn<>("Trip Distance");
        TableColumn<String[], String> starttimeCol = new TableColumn<>("Start Time");
        TableColumn<String[], String> endtimeCol = new TableColumn<>("End Time");
        TableColumn<String[], String> timetakenCol = new TableColumn<>("Time Taken");
        tripidCol.setMinWidth(132.5);
        passengerCol.setMinWidth(132.5);
        driverCol.setMinWidth(132.5);
        tripfareCol.setMinWidth(80);
        sourceCol.setMinWidth(110);
        destinationCol.setMinWidth(110);
        tripdistanceCol.setMinWidth(110);
        starttimeCol.setMinWidth(110);
        endtimeCol.setMinWidth(110);
        timetakenCol.setMinWidth(110);

        tripidCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[0]);
        });
        passengerCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[1]);
        });

        driverCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[2]);
        });

        tripfareCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[3]);
        });
        sourceCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[4]);
        });

        destinationCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[5]);
        });

        tripdistanceCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[6]);
        });

        starttimeCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[7]);
        });

        endtimeCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[8]);
        });

        timetakenCol.setCellValueFactory(data -> {
            String[] row = data.getValue();
            return new SimpleStringProperty(row[9]);
        });

        ObservableList<String[]> Tripdata = FXCollections.observableArrayList();
        String[] TripsArr;
        try {
            if (temp3.getTripsByLine() == null) {
                TripsArr = new String[0];
            } else {
                TripsArr = temp3.getTripsByLine().split("\n");
            }
            for (String trip : TripsArr) {
                String[] x = trip.split(",");
                Tripdata.addAll(x);
            }
        } catch (IOException ex) {
            Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        TripsTable.getColumns().addAll(tripidCol, passengerCol, driverCol, tripfareCol, sourceCol, destinationCol, tripdistanceCol,
                starttimeCol, endtimeCol, timetakenCol);
        TripsTable.getItems().addAll(Tripdata);

        //Home Screen Buttons//
        Button viewuserstable = new Button("Users");
        addHoverScaleAnimation(viewuserstable);
        viewuserstable.setId("viewuserstablebtn");
        Button viewtripstable = new Button("Trips ");
        addHoverScaleAnimation(viewuserstable);
        viewtripstable.setId("viewtripstablebtn");

        //Add user//
        Label fullNameLabel = new Label(" Full Name:");
        TextField FullNameField = new TextField();

        Label email = new Label(" Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label(" Password:");
        PasswordField passwordField = new PasswordField();

        Label CarName = new Label(" Car Name:");
        TextField CarNameField = new TextField();

        Label CarModelYear = new Label(" Car Model Year:");
        TextField CarModelYearField = new TextField();

        Label CarLicenseNumber = new Label(" Car License Plate: ");
        TextField CarLicenseField = new TextField();

        Label CarColor = new Label(" Car Color: ");
        TextField CarColorField = new TextField();

        Label levelLabel = new Label("Type:");
        ComboBox<String> levelComboBox = new ComboBox<>();
        levelComboBox.getItems().addAll("Admin", "Driver", "Passenger");

        Button addUserButton = new Button("Add User");
        addUserButton.setId("addUserButton");
        //Edit user//
        Label emailEdit = new Label("Please Enter The Email Of The User To Edit: ");
        emailEdit.setId("emailEdit");
        Button editUser = new Button("Edit User");
        editUser.setId("editStudentButton");

        Button confirmEdit = new Button("Confirm");
        confirmEdit.setId("editStudentButton2");

        Label newname = new Label("New Name: ");

        TextField newnameField = new TextField();

        Label newemail = new Label("New Email: ");

        TextField newemailField = new TextField();

        Label newpassword = new Label("New Password: ");

        TextField newpassField = new TextField();

        //delete user//
        Label emailDelete = new Label("Please Enter The Email Of The User You Want To Delete: ");
        Button deleteuser = new Button("Delete User");
        deleteuser.setId("deleteuser");

        //moredata//
        VBox PWMTbox = new VBox(); //Box to contain passenger with max trips
        PWMTbox.setAlignment(Pos.CENTER);
        PWMTbox.setId("PWMTbox");
        PWMTbox.setMinSize(227, 390);
        PWMTbox.setMaxSize(240, 390);
        PWMTbox.setEffect(dropShadow);
        PWMTbox.setSpacing(60);
        Label PWMTLabel = new Label("Passenger With Maximum Trips");
        PWMTLabel.setId("MoredataLabels");
        PWMTLabel.setAlignment(Pos.TOP_CENTER);
        Image profilelogo = new Image("ProfileLogo.png");
        ImageView ProfileLogo = new ImageView(profilelogo);
        ProfileLogo.setFitHeight(150);
        ProfileLogo.setFitWidth(145);
        Label PWMTResult = new Label(temp3.viewPassengerWithMaxTrips());
        PWMTResult.setTextAlignment(TextAlignment.CENTER);
        PWMTResult.setId("MoredataLabels");
        PWMTResult.setAlignment(Pos.BOTTOM_CENTER);

        VBox PWMSbox = new VBox(); //Box to contain Passenger with most spending
        PWMSbox.setId("PWMSbox");
        PWMSbox.setMinSize(227, 390);
        PWMSbox.setMaxSize(225, 390);
        PWMSbox.setEffect(dropShadow);
        PWMSbox.setSpacing(60);
        PWMSbox.setAlignment(Pos.CENTER);
        Label PWMSLabel = new Label("Passenger With The Most Spending");
        PWMSLabel.setId("MoredataLabels");
        PWMSLabel.setAlignment(Pos.TOP_CENTER);
        ImageView ProfileLogo2 = new ImageView(profilelogo);
        ProfileLogo2.setFitHeight(150);
        ProfileLogo2.setFitWidth(145);
        Label PWMSResult = new Label(temp3.viewPassengerWithMaxPayments());
        PWMSResult.setTextAlignment(TextAlignment.CENTER);
        PWMSResult.setId("MoredataLabels");
        PWMSResult.setAlignment(Pos.BOTTOM_CENTER);

        VBox DWMRbox = new VBox(); //Box to contain driver with max revenue
        DWMRbox.setId("DWMRbox");
        DWMRbox.setMinSize(227, 390);
        DWMRbox.setMaxSize(225, 390);
        DWMRbox.setEffect(dropShadow);
        DWMRbox.setSpacing(60);
        DWMRbox.setAlignment(Pos.CENTER);
        Label DWMRLabel = new Label("Driver With Maximum Revenue");
        DWMRLabel.setId("MoredataLabels");
        DWMRLabel.setAlignment(Pos.TOP_CENTER);
        ImageView ProfileLogo3 = new ImageView(profilelogo);
        ProfileLogo3.setFitHeight(150);
        ProfileLogo3.setFitWidth(145);
        Label DWMRResult = new Label(temp3.viewDriverWithMaxRevenue());
        DWMRResult.setTextAlignment(TextAlignment.CENTER);
        DWMRResult.setId("MoredataLabels");
        DWMRResult.setAlignment(Pos.BOTTOM_CENTER);

        VBox DWMTbox = new VBox(); //Box to contain Driver with most trips
        DWMTbox.setId("PWMSbox");
        DWMTbox.setMinSize(227, 390);
        DWMTbox.setMaxSize(225, 390);
        DWMTbox.setEffect(dropShadow);
        DWMTbox.setSpacing(60);
        DWMTbox.setAlignment(Pos.CENTER);
        Label DWMTLabel = new Label("Driver With Most Trips");
        DWMTLabel.setId("MoredataLabels");
        DWMTLabel.setAlignment(Pos.TOP_CENTER);
        ImageView ProfileLogo4 = new ImageView(profilelogo);
        ProfileLogo4.setFitHeight(150);
        ProfileLogo4.setFitWidth(145);
        Label DWMTResult = new Label(temp3.viewDriverWithMaxTrips());
        DWMTResult.setTextAlignment(TextAlignment.CENTER);
        DWMTResult.setId("MoredataLabels");
        DWMTResult.setAlignment(Pos.BOTTOM_CENTER);

        HBox WhiteDatabox = new HBox(); //White box to contain all 4 Vbox's containing the data
        WhiteDatabox.setId("WhiteDatabox");
        WhiteDatabox.setAlignment(Pos.CENTER);
        WhiteDatabox.setSpacing(20);
        WhiteDatabox.setMinSize(1000, 460);
        WhiteDatabox.setMaxSize(1000, 460);
        WhiteDatabox.setEffect(dropShadow);

        //View user details box in more data menu//
        VBox userdata = new VBox();
        userdata.setAlignment(Pos.CENTER);
        userdata.setMinHeight(300);
        userdata.setEffect(dropShadow);
        userdata.setSpacing(20);
        userdata.setId("userdatabox");
        ImageView ProfileLogo5 = new ImageView("ProfileLogoWhite.png");
        ProfileLogo5.setFitHeight(300);
        ProfileLogo5.setFitWidth(290);
        ImageView closelogo = new ImageView("closelogo.png");
        closelogo.setFitHeight(50);
        closelogo.setFitWidth(50);
        //objects to get the data of the "More data" stats//
        Driver DWMRobject = new Driver(temp3.getDriverByName(removeSecondLineFromString(temp3.viewDriverWithMaxRevenue().trim()))); //DWMR
        Driver DWMTobject = new Driver(temp3.getDriverByName(removeSecondLineFromString(temp3.viewDriverWithMaxTrips().trim()))); //DWMT
        Passenger PWMTobject = new Passenger(temp3.getPassengerByName(removeSecondLineFromString(temp3.viewPassengerWithMaxTrips().trim()))); //PWMT
        Passenger PWMSobject = new Passenger(temp3.getPassengerByName(removeSecondLineFromString(temp3.viewPassengerWithMaxPayments().trim()))); //PWMS//

        Label dataofuser = new Label();
        dataofuser.setId("dataofuser");
        Button closebox = new Button();
        closebox.setGraphic(closelogo);
        closebox.setId("closebox");
        BorderPane datapane = new BorderPane();
        datapane.setLeft(ProfileLogo5);
        datapane.setCenter(dataofuser);
        datapane.setRight(closebox);

        //View user details box in search menu//
        VBox userdataSearch = new VBox();
        userdataSearch.setAlignment(Pos.CENTER);
        userdataSearch.setMinHeight(300);
        userdataSearch.setEffect(dropShadow);
        userdataSearch.setSpacing(20);
        userdataSearch.setId("userdatabox");
        ImageView ProfileLogo6 = new ImageView("ProfileLogoWhite.png");
        ProfileLogo6.setFitHeight(300);
        ProfileLogo6.setFitWidth(290);
        ImageView closelogo2 = new ImageView("closelogo.png");
        closelogo2.setFitHeight(50);
        closelogo2.setFitWidth(50);

        Label dataofuserSearch = new Label();
        dataofuserSearch.setId("dataofuser");
        Button closeboxSearch = new Button();
        closeboxSearch.setGraphic(closelogo2);
        closeboxSearch.setId("closebox");
        BorderPane datapaneSearch = new BorderPane();
        datapaneSearch.setLeft(ProfileLogo6);
        datapaneSearch.setCenter(dataofuserSearch);
        datapaneSearch.setRight(closeboxSearch);

        //View user details box in home/table menu//
        VBox userdataBoxTable = new VBox();
        userdataBoxTable.setAlignment(Pos.CENTER);
        userdataBoxTable.setMinHeight(300);
        userdataBoxTable.setEffect(dropShadow);
        userdataBoxTable.setSpacing(20);
        userdataBoxTable.setId("userdatabox");
        ImageView ProfileLogoForHome = new ImageView("ProfileLogoWhite.png");
        ProfileLogoForHome.setFitHeight(300);
        ProfileLogoForHome.setFitWidth(290);
        ImageView closelogoForHome = new ImageView("closelogo.png");
        closelogoForHome.setFitHeight(50);
        closelogoForHome.setFitWidth(50);

        Label dataofuserHome = new Label();
        dataofuserHome.setId("dataofuser");
        Button closeboxHome = new Button();
        closeboxHome.setGraphic(closelogoForHome);
        closeboxHome.setId("closebox");
        BorderPane datapaneHome = new BorderPane();
        datapaneHome.setLeft(ProfileLogoForHome);
        datapaneHome.setCenter(dataofuserHome);
        datapaneHome.setRight(closeboxHome);

        //View User Trips Box//
        VBox TripdataBox = new VBox();
        TripdataBox.setAlignment(Pos.CENTER);
        TripdataBox.setMinHeight(250);
        TripdataBox.setMinWidth(290);
        TripdataBox.setEffect(dropShadow);
        TripdataBox.setSpacing(20);
        TripdataBox.setId("TripdataBox");

        Text textContent = new Text();
        textContent.setId("textContent");
        ScrollPane scrollPaneTRIPS = new ScrollPane();
        scrollPaneTRIPS.setId("scrollpaneTRIPS");
        scrollPaneTRIPS.setContent(textContent);

        //Seach Menu//
        VBox searchMenuBox = new VBox(10);
        searchMenuBox.setId("searchMenuBox");
        searchMenuBox.setAlignment(Pos.CENTER);
        searchMenuBox.setMinSize(900, 500);
        searchMenuBox.setMaxSize(900, 500);

        data = readDataFromFile("Users.txt");
        resultsListView = new ListView<>();
        resultsListView.setMaxSize(800, 400);
        searchResults = FXCollections.observableArrayList();
        resultsListView.setItems(searchResults);
        TextField searchField = new TextField();
        searchField.setPromptText("Search for any user by email");

        //Start Menu//
        MiddleScreen.getChildren().addAll(viewuserstable, viewtripstable, UsersTable);
        root.setCenter(MiddleScreen);

        //Event Handlers//  
        logOut.setOnAction(e -> {

            AdminScreen.close();

        });
        EventHandler<javafx.scene.input.MouseEvent> labelClickHandlerDWMT = event -> {

            root.getChildren().remove(WhiteDatabox);
            MiddleScreen.getChildren().clear();
            dataofuser.setText(temp3.viewDriverReport(DWMTobject.getUserName()));
            userdata.getChildren().removeAll(datapane);
            userdata.getChildren().addAll(datapane);
            TripdataBox.getChildren().clear();
            TripdataBox.getChildren().addAll(scrollPaneTRIPS);
            textContent.setText("Trips are viewed in home tab or search menu." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
            MiddleScreen.getChildren().addAll(userdata, TripdataBox);
            root.setCenter(MiddleScreen);
        };
        DWMTLabel.setOnMouseClicked(labelClickHandlerDWMT);
        DWMTResult.setOnMouseClicked(labelClickHandlerDWMT);

        EventHandler<javafx.scene.input.MouseEvent> labelClickHandlerDWMR = event -> {
            root.getChildren().remove(WhiteDatabox);
            MiddleScreen.getChildren().clear();
            userdata.getChildren().removeAll(datapane);
            dataofuser.setText(temp3.viewDriverReport(DWMRobject.getUserName()));
            userdata.getChildren().addAll(datapane);
            TripdataBox.getChildren().clear();
            TripdataBox.getChildren().addAll(scrollPaneTRIPS);
            textContent.setText("Trips are viewed in home tab or search menu." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
            MiddleScreen.getChildren().addAll(userdata, TripdataBox);
            root.setCenter(MiddleScreen);

        };
        DWMRLabel.setOnMouseClicked(labelClickHandlerDWMR);
        DWMRResult.setOnMouseClicked(labelClickHandlerDWMR);

        EventHandler<javafx.scene.input.MouseEvent> labelClickHandlerPWMT = event -> {
            root.getChildren().remove(WhiteDatabox);
            MiddleScreen.getChildren().clear();
            userdata.getChildren().removeAll(datapane);
            dataofuser.setText(temp3.viewPassengerReport(PWMTobject.getUserName()));
            userdata.getChildren().addAll(datapane);
            TripdataBox.getChildren().clear();
            TripdataBox.getChildren().addAll(scrollPaneTRIPS);
            textContent.setText("Trips are viewed in home tab or search menu." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
            MiddleScreen.getChildren().addAll(userdata, TripdataBox);
            root.setCenter(MiddleScreen);
        };
        PWMTLabel.setOnMouseClicked(labelClickHandlerPWMT);
        PWMTResult.setOnMouseClicked(labelClickHandlerPWMT);

        EventHandler<javafx.scene.input.MouseEvent> labelClickHandlerPWMS = event -> {
            root.getChildren().remove(WhiteDatabox);
            MiddleScreen.getChildren().clear();
            userdata.getChildren().removeAll(datapane);
            dataofuser.setText(temp3.viewPassengerReport(PWMSobject.getUserName()));
            userdata.getChildren().addAll(datapane);
            TripdataBox.getChildren().clear();
            TripdataBox.getChildren().addAll(scrollPaneTRIPS);
            textContent.setText("Trips are viewed in home tab or search menu." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
            MiddleScreen.getChildren().addAll(userdata, TripdataBox);
            root.setCenter(MiddleScreen);

        };
        PWMSLabel.setOnMouseClicked(labelClickHandlerPWMS);
        PWMSResult.setOnMouseClicked(labelClickHandlerPWMS);

        closebox.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            TripdataBox.getChildren().clear();
            MiddleScreen.getChildren().clear();

            if (!PWMTbox.getChildren().contains(PWMTLabel)) {
                PWMTbox.getChildren().add(PWMTLabel);
            }

            if (!PWMTbox.getChildren().contains(ProfileLogo)) {
                PWMTbox.getChildren().add(ProfileLogo);
            }

            if (!PWMTbox.getChildren().contains(PWMTResult)) {
                PWMTbox.getChildren().add(PWMTResult);
            }

            if (!PWMSbox.getChildren().contains(PWMSLabel)) {
                PWMSbox.getChildren().add(PWMSLabel);
            }

            if (!PWMSbox.getChildren().contains(ProfileLogo2)) {
                PWMSbox.getChildren().add(ProfileLogo2);
            }

            if (!PWMSbox.getChildren().contains(PWMSResult)) {
                PWMSbox.getChildren().add(PWMSResult);
            }

            if (!DWMRbox.getChildren().contains(DWMRLabel)) {
                DWMRbox.getChildren().add(DWMRLabel);
            }

            if (!DWMRbox.getChildren().contains(ProfileLogo3)) {
                DWMRbox.getChildren().add(ProfileLogo3);
            }

            if (!DWMRbox.getChildren().contains(DWMRResult)) {
                DWMRbox.getChildren().add(DWMRResult);
            }

            if (!WhiteDatabox.getChildren().contains(PWMTbox)) {
                WhiteDatabox.getChildren().add(PWMTbox);
            }

            if (!WhiteDatabox.getChildren().contains(PWMSbox)) {
                WhiteDatabox.getChildren().add(PWMSbox);
            }

            if (!WhiteDatabox.getChildren().contains(DWMRbox)) {
                WhiteDatabox.getChildren().add(DWMRbox);
            }
            if (!WhiteDatabox.getChildren().contains(DWMTbox)) {
                DWMTbox.getChildren().addAll(DWMTLabel, ProfileLogo4, DWMTResult);
                WhiteDatabox.getChildren().add(DWMTbox);
            }

            MiddleScreen.getChildren().add(WhiteDatabox);
            root.setCenter(WhiteDatabox);

        });

        viewuserstable.setOnAction(e -> {

            MiddleScreen.getChildren().clear();
            MiddleScreen.getChildren().addAll(viewuserstable, viewtripstable, UsersTable);

        });

        UsersTable.setOnMouseClicked(event -> {

            if (event.getClickCount() == 1) { // Check for single-click event
                if (!UsersTable.getSelectionModel().isEmpty()) {
                    TablePosition<?, ?> pos = UsersTable.getSelectionModel().getSelectedCells().get(0);
                    int row = pos.getRow();
                    int col = pos.getColumn();

                    // Access data from the selected cell
                    Object selectedData = UsersTable.getColumns().get(col).getCellData(row);

                    // Perform actions with the selected cell data
                    if (col == 1) { //selected cell is in email column

                        textContent.setText(null);
                        MiddleScreen.getChildren().clear();
                        userdataBoxTable.getChildren().removeAll(datapaneHome);
                        userdataBoxTable.getChildren().addAll(datapaneHome);
                        TripdataBox.getChildren().clear();
                        TripdataBox.getChildren().addAll(scrollPaneTRIPS);

                        MiddleScreen.getChildren().addAll(userdataBoxTable, TripdataBox);

                        String userType = temp3.userTypeSelectedByEmail((String) selectedData);
                        if (null != userType) {
                            switch (userType) {
                                case "Driver":
                                    dataofuserHome.setText(temp3.viewDriverReportByEmail(((String) selectedData).trim()));
                                    Driver x = new Driver();
                                    x.setUserEmail((String) selectedData);
                                    try {
                                        textContent.setText(temp3.getDriverTripsByList(x.viewMyTrips(), x.getUserEmail()));
                                    } catch (IOException ex) {
                                        Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (!textContent.getText().contains("TRIP")) {

                                        textContent.setText("No Trips" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                                    }
                                    break;
                                case "Passenger":
                                    dataofuserHome.setText(temp3.viewPassengerReportByEmail(((String) selectedData).trim()));
                                    Passenger x2 = new Passenger();
                                    x2.setUserEmail((String) selectedData);
                                    try {
                                        textContent.setText(temp3.getPassengerTripsByList(x2.viewMyTrips(), x2.getUserEmail()));
                                    } catch (IOException ex) {
                                        Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (!textContent.getText().contains("TRIP")) {

                                        textContent.setText("No Trips" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                                    }
                                    break;
                                case "Admin":
                                    dataofuserHome.setText("This user is an admin." + "\n" + "There are no reports for admins." + "\n" + "Thank you for your understanding.");
                                    textContent.setText("Admins have no trips." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                                    break;
                                default:
                                    break;
                            }
                        }

                    }
                    if (col == 0) { //selected cell is in name column
                        MiddleScreen.getChildren().clear();
                        textContent.setText(null);
                        userdataBoxTable.getChildren().clear();
                        userdataBoxTable.getChildren().addAll(datapaneHome);
                        TripdataBox.getChildren().clear();
                        TripdataBox.getChildren().addAll(scrollPaneTRIPS);
                        MiddleScreen.getChildren().addAll(userdataBoxTable, TripdataBox);
                        String userType = temp3.userTypeSelectedByName((String) selectedData);
                        switch (userType) {
                            case "Driver":
                                dataofuserHome.setText(temp3.viewDriverReport((String) selectedData));
                                Driver x = new Driver();
                                x.setUserName((String) selectedData);
                                try {
                                    textContent.setText(temp3.getDriverTripsByListByName(x.viewMyTripsByName(), x.getUserName()));
                                } catch (IOException ex) {
                                    Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (!textContent.getText().contains("TRIP")) {

                                    textContent.setText("No Trips" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                                }
                                break;
                            case "Passenger":
                                dataofuserHome.setText(temp3.viewPassengerReport((String) selectedData));
                                Passenger x2 = new Passenger();
                                x2.setUserName((String) selectedData);
                                try {
                                    textContent.setText(temp3.getPassengerTripsByListByName(x2.viewMyTripsByName(), x2.getUserName()));
                                } catch (IOException ex) {
                                    Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (!textContent.getText().contains("TRIP")) {

                                    textContent.setText("No Trips" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                                }
                                break;
                            case "Admin":
                                dataofuserHome.setText("This user is an admin." + "\n" + "There are no reports for admins." + "\n" + "Thank you for your understanding.");
                                textContent.setText("Admins have no trips." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                                break;
                            default:
                                dataofuserHome.setText("User not found");
                                break;
                        }
                    }
                }
            }
        });

        closeboxHome.setOnAction((ActionEvent e) -> {
            userdataBoxTable.getChildren().removeAll(datapaneHome);
            TripdataBox.getChildren().clear();
            MiddleScreen.getChildren().clear();
            MiddleScreen.getChildren().addAll(viewuserstable, viewtripstable, UsersTable);
            root.setCenter(MiddleScreen);
        });

        viewtripstable.setOnAction(e -> {

            userdataBoxTable.getChildren().removeAll(datapaneHome);
            MiddleScreen.getChildren().clear();
            MiddleScreen.getChildren().addAll(viewuserstable, viewtripstable, TripsTable, totalRevenueTripsLabel);

        });

        TripsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check for single-click event
                TablePosition<?, ?> pos = TripsTable.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                int col = pos.getColumn();

                // Access data from the selected cell
                Object selectedData = TripsTable.getColumns().get(col).getCellData(row);

                // Perform actions with the selected cell data
                System.out.println("Selected Data: " + selectedData);
            }
        });
        deleteuser.setOnAction(e -> {
            String deletestatus = temp3.deleteUser(emailField.getText().trim());
            if ((emailField.getText().trim().isEmpty()) || !(emailField.getText().trim().contains("@"))) { //rest of condition//

                Alert fail = new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Failure");
                fail.setContentText("Please Enter A Valid Email.");
                fail.showAndWait();
                emailField.clear();

            } else if (deletestatus == "User Not found") {
                Alert fail = new Alert(AlertType.ERROR);
                fail.setHeaderText("Failure");
                fail.setContentText("User not found.");
                fail.showAndWait();
                emailField.clear();

            } else if (deletestatus == "deleted") {
                Alert Success = new Alert(AlertType.INFORMATION);
                Success.setHeaderText("Success");
                Success.setContentText("User Deleted Successfully.");
                Success.showAndWait();
                emailField.clear();

            }

        });

        MoreData.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            TripdataBox.getChildren().clear();
            MiddleScreen.getChildren().clear();

            if (!PWMTbox.getChildren().contains(PWMTLabel)) {
                PWMTbox.getChildren().add(PWMTLabel);
            }

            if (!PWMTbox.getChildren().contains(ProfileLogo)) {
                PWMTbox.getChildren().add(ProfileLogo);
            }

            if (!PWMTbox.getChildren().contains(PWMTResult)) {
                PWMTbox.getChildren().add(PWMTResult);
            }

            if (!PWMSbox.getChildren().contains(PWMSLabel)) {
                PWMSbox.getChildren().add(PWMSLabel);
            }

            if (!PWMSbox.getChildren().contains(ProfileLogo2)) {
                PWMSbox.getChildren().add(ProfileLogo2);
            }

            if (!PWMSbox.getChildren().contains(PWMSResult)) {
                PWMSbox.getChildren().add(PWMSResult);
            }

            if (!DWMRbox.getChildren().contains(DWMRLabel)) {
                DWMRbox.getChildren().add(DWMRLabel);
            }

            if (!DWMRbox.getChildren().contains(ProfileLogo3)) {
                DWMRbox.getChildren().add(ProfileLogo3);
            }

            if (!DWMRbox.getChildren().contains(DWMRResult)) {
                DWMRbox.getChildren().add(DWMRResult);
            }

            if (!WhiteDatabox.getChildren().contains(PWMTbox)) {
                WhiteDatabox.getChildren().add(PWMTbox);
            }

            if (!WhiteDatabox.getChildren().contains(PWMSbox)) {
                WhiteDatabox.getChildren().add(PWMSbox);
            }

            if (!WhiteDatabox.getChildren().contains(DWMRbox)) {
                WhiteDatabox.getChildren().add(DWMRbox);
            }
            if (!WhiteDatabox.getChildren().contains(DWMTbox)) {
                DWMTbox.getChildren().addAll(DWMTLabel, ProfileLogo4, DWMTResult);
                WhiteDatabox.getChildren().add(DWMTbox);
            }
            FromRightAnimation(DWMTbox);
            FromRightAnimation(DWMRbox);
            FromRightAnimation(PWMSbox);
            FromRightAnimation(PWMTbox);
            MiddleScreen.getChildren().add(WhiteDatabox);
            root.setCenter(WhiteDatabox);

        });

        editUser.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            if ((emailField.getText().trim().isEmpty()) || !(emailField.getText().trim().contains("@"))) { //Add rest of validation//

                Alert fail = new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Failure");
                fail.setContentText("Please Enter A Valid Email.");
                fail.showAndWait();
                emailField.clear();

            } else if (!temp3.isUser(emailField.getText().trim())) {

                Alert fail = new Alert(AlertType.ERROR);
                fail.setHeaderText("Failure");
                fail.setContentText("User not found.");
                fail.showAndWait();
                emailField.clear();

            } else {
                enteredemail = emailField.getText().trim();
                emailField.clear();
                MiddleScreen.getChildren().removeAll(emailEdit, newname, newnameField, newemail, newemailField, newpassword, newpassField, emailDelete, emailField, editUser);
                MiddleScreen.getChildren().addAll(newname, newnameField, newemail, newemailField, newpassword, newpassField, confirmEdit);
            }

        });

        confirmEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean nameedited = false;
                boolean emailedited = false;
                boolean passedited = false;

                if (temp3.isDriver(enteredemail)) {
                    nameedited = false;
                    emailedited = false;
                    passedited = false;

                    if (!(newnameField.getText().trim().isEmpty()) && (newnameField.getText().trim().contains(" "))) {

                        temp3.editFile("Drivers.txt", enteredemail, "name", newnameField.getText().trim());
                        nameedited = true;
                        newnameField.clear();

                    }
                    if (!(newpassField.getText().trim().isEmpty())) {

                        temp3.editFile("Drivers.txt", enteredemail, "password", newpassField.getText().trim());
                        passedited = true;
                        newpassField.clear();

                    }
                    if (!(newemailField.getText().trim().isEmpty()) && (newemailField.getText().trim().contains("@"))) {

                        temp3.editFile("Drivers.txt", enteredemail, "email", newemailField.getText().trim());
                        emailedited = true;
                        newemailField.clear();

                    }

                    if (passedited == true && nameedited == true && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Name, Email And Password Have Been Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == true && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Name And Email Have Been Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == true && nameedited == false && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Email And Password Have Been Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == false && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Email Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == true && emailedited == false) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Name Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == true && nameedited == false && emailedited == false) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Password Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == false && emailedited == false) {

                        Alert Success = new Alert(AlertType.ERROR);
                        Success.setHeaderText("Failure");
                        Success.setContentText("Enter Valid Credentaisl");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }

                } else if (temp3.isPassenger(enteredemail)) {
                    nameedited = false;
                    emailedited = false;
                    passedited = false;

                    if (!(newnameField.getText().trim().isEmpty()) && (newnameField.getText().trim().contains(" "))) {

                        temp3.editFile("Passengers.txt", enteredemail, "name", newnameField.getText().trim());
                        nameedited = true;

                    }
                    if (!(newpassField.getText().trim().isEmpty())) {

                        temp3.editFile("Passengers.txt", enteredemail, "password", newpassField.getText().trim());
                        passedited = true;

                    }
                    if (!(newemailField.getText().trim().isEmpty()) && (newemailField.getText().trim().contains("@"))) {

                        temp3.editFile("Passengers.txt", enteredemail, "email", newemailField.getText().trim());
                        emailedited = true;

                    }

                    newemailField.clear();
                    newpassField.clear();
                    newnameField.clear();
                    if (passedited == true && nameedited == true && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Name, Email And Password Have Been Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == true && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Name And Email Have Been Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == true && nameedited == false && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Email And Password Have Been Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == false && emailedited == true) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Email Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == false && nameedited == true && emailedited == false) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Name Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }
                    if (passedited == true && nameedited == false && emailedited == false) {

                        Alert Success = new Alert(AlertType.INFORMATION);
                        Success.setHeaderText("Success");
                        Success.setContentText("Passenger Password Edited Successfully.");
                        Success.showAndWait();
                        newnameField.clear();
                        newemailField.clear();
                        newpassField.clear();
                        MiddleScreen.getChildren().clear();
                        MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                    }

                } else {

                    Alert Success = new Alert(AlertType.ERROR);
                    Success.setHeaderText("Failure");
                    Success.setContentText("Not Edited.");
                    Success.showAndWait();
                    newnameField.clear();
                    newemailField.clear();
                    newpassField.clear();
                    MiddleScreen.getChildren().clear();
                    MiddleScreen.getChildren().addAll(emailDelete, emailField, editUser);

                }
            }
        });
        Home.setOnAction(e -> {
            root.getChildren().remove(WhiteDatabox);
            MiddleScreen.setAlignment(null);
            MiddleScreen.getChildren().clear();
            FullNameField.clear();
            emailField.clear();
            passwordField.clear();
            CarNameField.clear();
            CarModelYearField.clear();
            CarLicenseField.clear();
            CarColorField.clear();
            levelComboBox.getSelectionModel().clearSelection();
            MiddleScreen.getChildren().addAll(viewuserstable, viewtripstable, UsersTable);
            root.setCenter(MiddleScreen);
        }
        );

        Search.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            MiddleScreen.getChildren().clear();
            FullNameField.clear();
            emailField.clear();
            passwordField.clear();
            CarNameField.clear();
            CarModelYearField.clear();
            CarLicenseField.clear();
            CarColorField.clear();
            levelComboBox.getSelectionModel().clearSelection();
            searchMenuBox.getChildren().removeAll(searchField, resultsListView);
            searchMenuBox.getChildren().addAll(searchField, resultsListView);
            MiddleScreen.getChildren().addAll(searchMenuBox);
            MiddleScreen.setAlignment(Pos.CENTER);
            root.setCenter(MiddleScreen);
        }
        );

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.toLowerCase();
            search(query);
        });

        resultsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = resultsListView.getSelectionModel().getSelectedItem();
                if (!(selectedItem.trim() == "No results found.")) {
                    if (selectedItem != null) {
                        MiddleScreen.setAlignment(null);
                        MiddleScreen.getChildren().clear();
                        userdataSearch.getChildren().removeAll(datapane, datapaneSearch);

                        if (temp3.isDriver(selectedItem)) {
                            dataofuserSearch.setText(temp3.viewDriverReportByEmail(selectedItem.trim()));
                            Driver x = new Driver();
                            x.setUserEmail((String) selectedItem);
                            try {
                                textContent.setText(temp3.getDriverTripsByList(x.viewMyTrips(), x.getUserEmail()));
                            } catch (IOException ex) {
                                Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (!textContent.getText().contains("TRIP")) {

                                textContent.setText("No Trips" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                            }
                        } else if (temp3.isPassenger(selectedItem)) {
                            dataofuserSearch.setText(temp3.viewPassengerReportByEmail(selectedItem.trim()));
                            Passenger x2 = new Passenger();
                            x2.setUserEmail((String) selectedItem);
                            try {
                                textContent.setText(temp3.getPassengerTripsByList(x2.viewMyTrips(), x2.getUserEmail()));
                            } catch (IOException ex) {
                                Logger.getLogger(CabBookingSystem.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (!textContent.getText().contains("TRIP")) {

                                textContent.setText("No Trips" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                            }
                        } else if (temp3.isAdmin(selectedItem.trim())) {
                            dataofuserSearch.setText("This user is an admin." + "\n" + "There are no reports for admins." + "\n" + "Thank you for your understanding.");
                            textContent.setText("Admins have no trips." + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                        }
                        TripdataBox.getChildren().clear();
                        TripdataBox.getChildren().addAll(scrollPaneTRIPS);
                        userdataSearch.getChildren().addAll(datapaneSearch);
                        MiddleScreen.getChildren().addAll(userdataSearch, TripdataBox);
                        root.setCenter(MiddleScreen);
                    }
                } else {
                    // this is to end the handler when "No results found." is selected or basically make it do nothing.
                }
            }
        });

        closeboxSearch.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            searchField.clear();
            TripdataBox.getChildren().clear();
            MiddleScreen.getChildren().clear();
            MiddleScreen.getChildren().addAll(searchMenuBox);
            MiddleScreen.setAlignment(Pos.CENTER);
            root.setCenter(MiddleScreen);

        });

        Add.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            MiddleScreen.getChildren().clear();
            FullNameField.clear();
            emailField.clear();
            passwordField.clear();
            CarNameField.clear();
            CarModelYearField.clear();
            CarLicenseField.clear();
            CarColorField.clear();
            levelComboBox.getSelectionModel().clearSelection();
            MiddleScreen.getChildren().addAll(fullNameLabel, FullNameField, email, emailField, passwordLabel, passwordField, levelLabel, levelComboBox, addUserButton);
            root.setCenter(MiddleScreen);

            levelComboBox.setOnAction(event -> {
                String selectedItem = levelComboBox.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    // Handle null selection
                    MiddleScreen.getChildren().removeAll(emailDelete, emailEdit, CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField
                    );
                    return;
                }

                if ("Driver".equals(selectedItem)) {
                    MiddleScreen.getChildren().removeAll(emailDelete, emailEdit, CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField
                    );
                    MiddleScreen.getChildren().remove(addUserButton);
                    MiddleScreen.getChildren().addAll(
                            CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField, addUserButton
                    );
                } else if ("Admin".equals(selectedItem) || "Passenger".equals(selectedItem)) {
                    MiddleScreen.getChildren().removeAll(emailDelete, emailEdit, CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField
                    );
                }
            });

        });

        addUserButton.setOnAction(e -> {

            if (levelComboBox.getSelectionModel().getSelectedItem() == "Driver") {

                String carName = CarNameField.getText().trim();
                String carModelYear = CarModelYearField.getText().trim();
                String carLicense = CarLicenseField.getText().trim();
                String carColor = CarColorField.getText().trim();
                String fullName = FullNameField.getText().trim();
                String emailEntered = emailField.getText().trim();
                String password = passwordField.getText().trim();
                String selectedUserType = levelComboBox.getSelectionModel().getSelectedItem();

                if (carName.isEmpty() && carModelYear.isEmpty() && carLicense.isEmpty() && carColor.isEmpty() && fullName.isEmpty() && emailEntered.isEmpty() && password.isEmpty() && selectedUserType == null) {

                    handleFailure("Please fill in all the required information.");

                } else if ((fullName.isEmpty() || !fullName.contains(" ")) && emailEntered.isEmpty() && password.isEmpty()) {

                    handleFailure("Please enter a Full Name, a Valid Email, and a Valid Password.");

                } else {

                    if (carName.isEmpty() || carModelYear.isEmpty() || carLicense.isEmpty() || carColor.isEmpty()) { //if car information is empty//
                        handleFailure("Please fill in all the car information.");
                        CarNameField.clear();
                        CarModelYearField.clear();
                        CarLicenseField.clear();
                        CarColorField.clear();
                    }

                    //validate car model year as an integer//
                    boolean isNumericYear = false;
                    try {
                        int year = Integer.parseInt(carModelYear);
                        isNumericYear = true;
                    } catch (NumberFormatException ex) {
                        isNumericYear = false;
                    }
                    if (!isNumericYear) {
                        handleFailure("Please Enter a Number as a Car Model Year.");
                        CarModelYearField.clear();
                    }

                    // Validate driver information
                    if (fullName.isEmpty() || emailEntered.isEmpty() || password.isEmpty() || selectedUserType == null) {
                        if (fullName.isEmpty() || !fullName.contains(" ") && emailEntered.isEmpty() || !emailEntered.contains("@")) {
                            handleFailure("Please Enter a Driver's Full Name and a Valid Email.");
                            FullNameField.clear();
                            emailField.clear();
                        } else if (fullName.isEmpty() || !fullName.contains(" ")) {
                            handleFailure("Please Enter a Driver's Full Name.");
                            FullNameField.clear();
                        } else if (emailEntered.isEmpty() || !emailEntered.contains("@")) {
                            handleFailure("Please Enter a Driver's Valid Email.");
                            emailField.clear();
                        } else if (password.isEmpty()) {
                            handleFailure("Please Enter a Driver's Valid Password.");
                            passwordField.clear();
                        } else if (emailEntered.isEmpty() || !emailEntered.contains("@") && password.isEmpty()) {
                            handleFailure("Please Enter a Driver's Valid Email and Password.");
                            emailField.clear();
                            passwordField.clear();
                        } else if (selectedUserType == null) {
                            handleFailure("Please Select a Driver's User Type.");
                        }
                    }
                }

                //Validating User is non-existent//
                if (temp3.isUser(emailField.getText().trim())) {
                    handleFailure("A driver with this email already exists.");
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                }

                //Adding user//
                if (levelComboBox.getSelectionModel().getSelectedItem() == "Driver" && !(passwordField.getText().trim().isEmpty()) && !(FullNameField.getText().trim().isEmpty()) && (FullNameField.getText().trim().contains(" ")) && !(emailField.getText().trim().isEmpty()) && (emailField.getText().trim().contains("@")) && !(CarNameField.getText().trim().isEmpty()) && !(CarModelYearField.getText().trim().isEmpty()) && !(CarLicenseField.getText().trim().isEmpty()) && !(CarColorField.getText().trim().isEmpty())) {
                    String CarYear = CarModelYearField.getText().trim();
                    int caryear = Integer.parseInt(CarYear);
                    Driver newDriver = new Driver(FullNameField.getText().trim(), emailField.getText().trim(), passwordField.getText().trim(), caryear, CarNameField.getText().trim(), CarLicenseField.getText().trim(), CarColorField.getText().trim(), 0, 0);
                    temp3.addUser(newDriver);
                    Alert Success = new Alert(AlertType.INFORMATION);
                    Success.setHeaderText("Success");
                    Success.setContentText("New Driver Added Successfully.");
                    Success.showAndWait();
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                    CarNameField.clear();
                    CarModelYearField.clear();
                    CarLicenseField.clear();
                    CarColorField.clear();
                    levelComboBox.getSelectionModel().clearSelection();
                    MiddleScreen.getChildren().removeAll(
                            CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField
                    );

                }
            }

            if (levelComboBox.getSelectionModel().getSelectedItem() == "Passenger") {

                String fullNameEntered = FullNameField.getText().trim();
                String emailEntered = emailField.getText().trim();
                String passwordEntered = passwordField.getText().trim();

                if (fullNameEntered.isEmpty() && emailEntered.isEmpty() && passwordEntered.isEmpty()) {
                    handleFailure("Please Enter Passenger Full Name, Email, and Password.");
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                } else if (fullNameEntered.isEmpty() || !fullNameEntered.contains(" ")) {
                    handleFailure("Please Enter a Passenger Full Name.");
                    FullNameField.clear();
                } else if (emailEntered.isEmpty() || !emailEntered.contains("@")) {
                    handleFailure("Please Enter a Valid Email.");
                    emailField.clear();
                } else if (passwordEntered.isEmpty()) {
                    handleFailure("Please Enter a Valid Password.");
                    passwordField.clear();
                }

                //Validating User is non-existent//
                if (temp3.isUser(emailEntered)) {
                    handleFailure("A passenger with this email already exists.");
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                }

                //Adding user//
                if (levelComboBox.getSelectionModel().getSelectedItem() == "Passenger" && !(passwordField.getText().trim().isEmpty()) && !(FullNameField.getText().trim().isEmpty()) && (FullNameField.getText().trim().contains(" ")) && !(emailField.getText().trim().isEmpty()) && (emailField.getText().trim().contains("@"))) {
                    Passenger newpassenger = new Passenger(emailField.getText().trim(), FullNameField.getText().trim(), passwordField.getText().trim(), 0, 0);
                    temp3.addUser(newpassenger);
                    Alert Success = new Alert(AlertType.INFORMATION);
                    Success.setHeaderText("Success");
                    Success.setContentText("New Passenger Added Successfully.");
                    Success.showAndWait();
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                    CarNameField.clear();
                    CarModelYearField.clear();
                    CarLicenseField.clear();
                    CarColorField.clear();
                    levelComboBox.getSelectionModel().clearSelection();
                    MiddleScreen.getChildren().removeAll(
                            CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField
                    );

                }
            }
            if (levelComboBox.getSelectionModel().getSelectedItem() == "Admin") {
                String fullNameEntered = FullNameField.getText().trim();
                String emailEntered = emailField.getText().trim();
                String passwordEntered = passwordField.getText().trim();

                if (fullNameEntered.isEmpty() && emailEntered.isEmpty() && passwordEntered.isEmpty()) {
                    handleFailure("Please Enter Admin Full Name, Email, and Password.");
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                } else if (fullNameEntered.isEmpty() || !fullNameEntered.contains(" ")) {
                    handleFailure("Please Enter a Admin Full Name.");
                    FullNameField.clear();
                } else if (emailEntered.isEmpty() || !emailEntered.contains("@")) {
                    handleFailure("Please Enter a Valid Email.");
                    emailField.clear();
                } else if (passwordEntered.isEmpty()) {
                    handleFailure("Please Enter a Valid Password.");
                    passwordField.clear();
                }

                //Validating User is non-existent//
                if (temp3.isUser(emailEntered)) {
                    handleFailure("An admin with this email already exists.");
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                }
                //Adding user//
                if (levelComboBox.getSelectionModel().getSelectedItem() == "Admin" && !(passwordField.getText().trim().isEmpty()) && !(FullNameField.getText().trim().isEmpty()) && (FullNameField.getText().trim().contains(" ")) && !(emailField.getText().trim().isEmpty()) && (emailField.getText().trim().contains("@"))) {
                    Admin newadmin = new Admin(emailField.getText().trim(), passwordField.getText().trim(), FullNameField.getText().trim());
                    //Use the current admin logged in object to add the user//
                    temp3.addUser(newadmin);
                    Alert Success = new Alert(AlertType.INFORMATION);
                    Success.setHeaderText("Success");
                    Success.setContentText("New Admin Added Successfully.");
                    Success.showAndWait();
                    FullNameField.clear();
                    emailField.clear();
                    passwordField.clear();
                    CarNameField.clear();
                    CarModelYearField.clear();
                    CarLicenseField.clear();
                    CarColorField.clear();
                    levelComboBox.getSelectionModel().clearSelection();
                    MiddleScreen.getChildren().removeAll(
                            CarName, CarNameField, CarModelYear, CarModelYearField,
                            CarLicenseNumber, CarLicenseField, CarColor, CarColorField
                    );
                }
            }

        });

        Edit.setOnAction(e -> {
            MiddleScreen.setAlignment(null);
            MiddleScreen.getChildren().clear();
            FullNameField.clear();
            emailField.clear();
            passwordField.clear();
            CarNameField.clear();
            CarModelYearField.clear();
            CarLicenseField.clear();
            CarColorField.clear();
            levelComboBox.getSelectionModel().clearSelection();
            MiddleScreen.getChildren().addAll(emailEdit, emailField, editUser);
            root.setCenter(MiddleScreen);
        }
        );

        Delete.setOnAction(e
                -> {
            MiddleScreen.setAlignment(null);
            MiddleScreen.getChildren().clear();
            FullNameField.clear();
            emailField.clear();
            passwordField.clear();
            CarNameField.clear();
            CarModelYearField.clear();
            CarLicenseField.clear();
            CarColorField.clear();
            levelComboBox.getSelectionModel().clearSelection();
            MiddleScreen.getChildren().addAll(emailDelete, emailField, deleteuser);
            root.setCenter(MiddleScreen);
        }
        );

        //stage//
        AdminScreen.setTitle("Admin");
        AdminScreen.setScene(scene);
        AdminScreen.show();
    }

    public static String removeSecondLineFromString(String originalString) {
        int indexOfNewline = originalString.indexOf('\n'); // Find the index of the first newline character

        if (indexOfNewline != -1) { // Check if there is a newline character
            return originalString.substring(0, indexOfNewline); // Return the substring from start till the first newline character
        } else {
            return originalString; // Return the original string if there's no newline character
        }
    }

    private List<String> readDataFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void search(String query) {
        searchResults.clear();
        boolean found = false;
        if (!query.isEmpty()) {
            for (String item : data) {
                if (item.toLowerCase().contains(query) && item.contains("@") && item.contains(".com")) {
                    searchResults.add(item);
                    found = true;
                }
            }
            if (!found) {
                searchResults.add("No results found.");
            }
        }
    }

    private void handleFailure(String message) {
        Alert fail = new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Failure");
        fail.setContentText(message);
        fail.showAndWait();
    }

    private static void AdminFade(Node obj) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), obj);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), obj);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public static void PageFade(Node obj) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), obj);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public static void FromRightAnimation(Node obj) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), obj);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), obj);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        translateTransition.setFromX(-30); // Starting Y position
        translateTransition.setToX(0); // Ending Y position 
        translateTransition.setAutoReverse(false);
        translateTransition.setCycleCount(1);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.play();

    }

    public static void TransFadeAnimation(Node obj) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), obj);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), obj);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        translateTransition.setFromY(30); // Starting Y position
        translateTransition.setToY(0); // Ending Y position 
        translateTransition.setAutoReverse(false);
        translateTransition.setCycleCount(1);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.play();
    }

    public static void FromTopAnimation(Node obj) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), obj);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), obj);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        translateTransition.setFromY(-30); // Starting Y position
        translateTransition.setToY(0); // Ending Y position 
        translateTransition.setAutoReverse(false);
        translateTransition.setCycleCount(1);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.play();
    }

    public void FadeAnimation(Node obj) {

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700), obj);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private void addHoverScaleAnimation(Node obj) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), obj);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);

        obj.setOnMouseEntered(e -> {
            scaleTransition.playFromStart();
        });

        obj.setOnMouseExited(e -> {
            scaleTransition.setRate(-1);
            scaleTransition.play();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
