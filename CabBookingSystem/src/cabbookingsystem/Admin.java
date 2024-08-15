/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cabbookingsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Admin extends User {

    public Admin(String userEmail, String userPass, String userName) {
        super(userName, userEmail, userPass);
    }

    Admin() {
    }

    public void addUser(User user) {

        if (user instanceof Driver) {
            user.writeToUsersFile("Driver");
            Driver driver = (Driver) user;
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("Drivers.txt", true))) {
                printWriter.println(driver.userEmail);
                printWriter.println(driver.userName);
                printWriter.println(driver.userPass);
                printWriter.println(driver.getModelYear());
                printWriter.println(driver.getCarName());
                printWriter.println(driver.getLicenseNo());
                printWriter.println(driver.getCarColor());
                printWriter.println(driver.getTotalTrips());
                printWriter.println(driver.getTotalRevenue());
                printWriter.println("<>");
            } catch (IOException e) {
                System.out.println(e);
            }
        } else if (user instanceof Passenger) {
            user.writeToUsersFile("Passenger");
            Passenger passenger = (Passenger) user;
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("Passengers.txt", true))) {
                printWriter.println(passenger.userEmail);
                printWriter.println(passenger.userName);
                printWriter.println(passenger.userPass);
                printWriter.println(passenger.getNumberOfTrips());
                printWriter.println(passenger.getTotalPayements());
                printWriter.println("<>");
            } catch (IOException e) {
                System.out.println(e);
            }
        } else if (user instanceof Admin) {
            user.writeToUsersFile("Admin");
            Admin admin = (Admin) user;
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("Admins.txt", true))) {
                printWriter.println(admin.userEmail);
                printWriter.println(admin.userPass);
                printWriter.println(admin.userName);
                printWriter.println("<>");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public String deleteUser(String userEmail) {
        boolean found = false;
        String deletestatus = "no action";
        try {
            File usersFile = new File("Users.txt");

            Scanner scanner = new Scanner(usersFile);
            ArrayList<String> linesToKeep = new ArrayList<>();

            String lineToRemove = userEmail;

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (currentLine.equals(lineToRemove)) {
                    found = true;
                    for (int i = 0; i < 3; i++) {
                        if (scanner.hasNextLine()) {
                            scanner.nextLine();
                        }
                    }
                } else {
                    linesToKeep.add(currentLine);
                }
            }

            scanner.close();

            try (PrintWriter writer = new PrintWriter(usersFile)) {
                for (String line : linesToKeep) {
                    writer.println(line);
                }
            }
            if (!found) {
                return deletestatus = "User Not found";
            }
            if (isDriver(userEmail)) {
                deleteUserFromFiles("Drivers.txt", 9, userEmail);
            } else if (isPassenger(userEmail)) {
                deleteUserFromFiles("Passengers.txt", 5, userEmail);
            } else {
                deleteUserFromFiles("Admins.txt", 3, userEmail);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return deletestatus = "deleted";
    }

    public String userTypeSelectedByEmail(String userEmail) {
        String usertype;

        if (isDriver(userEmail)) {
            usertype = "Driver";
        } else if (isPassenger(userEmail)) {
            usertype = "Passenger";
        } else {
            usertype = "Admin";
        }
        return usertype;
    }

    public String userTypeSelectedByName(String userName) {
        String usertype = "not found";
        Driver xD = getDriverByName(userName.trim());
        Passenger xP = getPassengerByName(userName.trim());
        if (xD == null && xP == null) {
            usertype = "Admin";
        } else {
            if (xD != null) {
                if (isDriver(xD.userEmail)) {
                    usertype = "Driver";
                }
            } else {
                if (isPassenger(xP.userEmail)) {
                    usertype = "Passenger";
                }
            }

        }

        return usertype;
    }

    public boolean isDriver(String userEmail) {
        try {
            Scanner scanner = new Scanner(new File("Drivers.txt"));

            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                if (email.equals(userEmail)) {
                    return true;
                }

                for (int i = 0; i < 9; i++) {
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isUser(String userEmail) {
        try {
            Scanner scanner = new Scanner(new File("Users.txt"));

            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                if (email.equals(userEmail)) {
                    return true;
                }

                for (int i = 0; i < 3; i++) {
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isAdmin(String userEmail) {

        if (!isPassenger(userEmail) && !isDriver(userEmail)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isPassenger(String userEmail) {
        try {
            Scanner scanner = new Scanner(new File("Passengers.txt"));

            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                if (email.equals(userEmail)) {
                    return true;
                }

                for (int i = 0; i < 5; i++) {
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public void deleteUserFromFiles(String typeFileName, int numberOfLines, String userEmail) {
        try {
            File typeFile = new File(typeFileName);

            Scanner scanner = new Scanner(typeFile);
            ArrayList<String> linesToKeep = new ArrayList<>();

            String lineToRemove = userEmail;

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (currentLine.equals(lineToRemove)) {
                    for (int i = 0; i < numberOfLines; i++) {
                        if (scanner.hasNextLine()) {
                            scanner.nextLine();
                        }
                    }
                } else {
                    linesToKeep.add(currentLine);
                }
            }

            scanner.close();

            try (PrintWriter writer = new PrintWriter(typeFile)) {
                for (String line : linesToKeep) {
                    writer.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    

    private Admin getAdminByEmail(String userEmail) {
        try (Scanner scanner = new Scanner(new File("Admins.txt"))) {

            while (scanner.hasNext()) {
                String email = scanner.nextLine();
                String pass = scanner.nextLine();
                String Username = scanner.nextLine();
                scanner.nextLine();

                if (email.equals(userEmail)) {
                    return new Admin(email, pass, Username);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return null;

    }

    private User getUserByEmail(String userEmail) {
        try (Scanner scanner = new Scanner(new File("Users.txt"))) {

            while (scanner.hasNext()) {
                String email = scanner.nextLine();
                String pass = scanner.nextLine();
                String userType = scanner.nextLine();
                scanner.nextLine();

                if (email.equals(userEmail)) {
                    if (userType.equals("Driver")) {
                        return getDriverByEmail(userEmail);
                    } else if (userType.equals("Passenger")) {
                        return getPassengerByEmail(userEmail);
                    } else {
                        return getAdminByEmail(userEmail);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return null;
    }

    public Driver getDriverByName(String userName) {
        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (driverName.equals(userName)) {
                    return new Driver(driverName, driverEmail, driverPass, modelYear, carName, licenseNo, carColor, totalTrips, totalRevenue);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    private Driver getDriverByEmail(String userEmail) {
        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (driverEmail.equals(userEmail)) {
                    return new Driver(driverName, driverEmail, driverPass, modelYear, carName, licenseNo, carColor, totalTrips, totalRevenue);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public Passenger getPassengerByName(String userName) {
        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (passengerName.equals(userName)) {
                    return new Passenger(passengerEmail, passengerName, passengerPass, numberOfTrips, totalPayments);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    private Passenger getPassengerByEmail(String userEmail) {
        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (passengerEmail.equals(userEmail)) {
                    return new Passenger(passengerEmail, passengerName, passengerPass, numberOfTrips, totalPayments);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }


    public String viewDriverReportByEmail(String driveremail) {
        String driverdata = "not found";
        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (driverEmail.equals(driveremail)) {
                    driverdata = "Report for: " + driverEmail + "\n" + "\n" + "Type: Driver" + "\n" + "Name: " + driverName + "\n" + "Model Year: " + modelYear + "\n" + "Car Name: "
                            + carName + "\n" + "License Number: " + licenseNo + "\n" + "Car Color: " + carColor + "\n" + "Total Trips: " + totalTrips + "\n" + "Total Revenue: " + (Math.round(totalRevenue * 100.0) / 100.0) + "LE";
                    break;
                } else {
                    driverdata = "data not found";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return driverdata;

    }

    
    public String viewPassengerReportByEmail(String passengerE) {
        String passengerdata = null;
        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (passengerEmail.equals(passengerE)) {
                    passengerdata = "Report for: " + passengerEmail + "\n" + "\n" + "Type: Passenger" + "\n" + "Name: "
                            + passengerName + "\n" + "Number of Trips: " + numberOfTrips + "\n" + "Total payments: " + (Math.round(totalPayments * 100.0) / 100.0) + "LE";
                    break;

                } else {
                    passengerdata = "data not found";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return passengerdata;
    }

    public String viewDriverWithMaxTrips() {
        Driver driverWithMaxTrips = findDriverWithMaxTrips();
        return driverWithMaxTrips.userName + "\n" + driverWithMaxTrips.getTotalTrips() + " Trips";
    }

    private Driver findDriverWithMaxTrips() {
        Driver driverWithMaxTrips = null;
        int maxTrips = -1;

        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (totalTrips > maxTrips) {
                    maxTrips = totalTrips;
                    driverWithMaxTrips = new Driver(driverName, driverEmail, driverPass, modelYear, carName, licenseNo, carColor, totalTrips, totalRevenue);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return driverWithMaxTrips;
    }

    public String viewDriverWithMaxRevenue() {
        Driver driverWithMaxRevenue = findDriverWithMaxRevenue();
        return driverWithMaxRevenue.userName + "\n" + (Math.round(driverWithMaxRevenue.getTotalRevenue() * 100.0) / 100.0) + "LE";
    }

    private Driver findDriverWithMaxRevenue() {
        Driver driverWithMaxRevenue = null;
        double maxRevenue = -1;

        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (totalRevenue > maxRevenue) {
                    maxRevenue = totalRevenue;
                    driverWithMaxRevenue = new Driver(driverName, driverEmail, driverPass, modelYear, carName, licenseNo, carColor, totalTrips, totalRevenue);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return driverWithMaxRevenue;
    }

    public String viewPassengerWithMaxTrips() {
        Passenger passengerWithMaxTrips = findPassengerWithMaxTrips();
        return passengerWithMaxTrips.userName + "\n" + passengerWithMaxTrips.getNumberOfTrips() + " Trips";
    }

    private Passenger findPassengerWithMaxTrips() {
        Passenger passengerWithMaxTrips = null;
        int maxTrips = -1;

        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (numberOfTrips > maxTrips) {
                    maxTrips = numberOfTrips;
                    passengerWithMaxTrips = new Passenger(passengerEmail, passengerName, passengerPass, numberOfTrips, totalPayments);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return passengerWithMaxTrips;
    }

    public String viewPassengerWithMaxPayments() {
        Passenger passengerWithMaxPayments = findPassengerWithMaxPayments();
        return passengerWithMaxPayments.userName + "\n" + (Math.round(passengerWithMaxPayments.getTotalPayements() * 100.0) / 100.0) + "LE";
    }

    private Passenger findPassengerWithMaxPayments() {
        Passenger passengerWithMaxPayments = null;
        double maxPayments = -1;

        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (totalPayments > maxPayments) {
                    maxPayments = totalPayments;
                    passengerWithMaxPayments = new Passenger(passengerEmail, passengerName, passengerPass, numberOfTrips, totalPayments);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return passengerWithMaxPayments;
    }




    public static void editFile(String fileName, String userEmail, String chosenEdit, String newValue) {
        try {

            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(userEmail)) {

                    if ("name".equals(chosenEdit)) {
                        writer.write(line + "\n");
                        writer.write(newValue + "\n");
                        reader.readLine();

                    } else if ("email".equals(chosenEdit)) {

                        writer.write(newValue + "\n");
                        writer.write(reader.readLine() + "\n");
                        writer.write(reader.readLine() + "\n");

                    } else if ("password".equals(chosenEdit)) {

                        writer.write(line + "\n");//write existing email
                        writer.write(reader.readLine() + "\n");
                        writer.write(newValue + "\n");//write new password
                        reader.readLine();

                    }
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            reader.close();

            //rewrites the edited data in the original file
            try {
                File inputFile2 = new File(fileName);
                File tempFile2 = new File("temp.txt");

                BufferedReader reader2 = new BufferedReader(new FileReader(tempFile2));
                BufferedWriter writer2 = new BufferedWriter(new FileWriter(inputFile2));

                String line2;
                while ((line2 = reader2.readLine()) != null) {
                    writer2.write(line2 + "\n");
                }

                writer2.close();
                reader2.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            synchronizeWithUsersFile(userEmail, chosenEdit, newValue);//apply the same edits to users file
            tempFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void editUsersFile(String fileName, String userEmail, String chosenEdit, String newValue) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(userEmail)) {

                    if ("email".equals(chosenEdit)) {

                        writer.write(newValue + "\n");
                        writer.write(reader.readLine() + "\n");
                        writer.write(reader.readLine() + "\n");

                    } else if ("password".equals(chosenEdit)) {

                        writer.write(line + "\n");//write existing email
                        writer.write(newValue + "\n");//write new password
                        reader.readLine();

                    }
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            reader.close();
            reader = new BufferedReader(new FileReader(inputFile));

            try {
                File inputFile2 = new File(fileName);
                File tempFile2 = new File("temp.txt");

                BufferedReader reader2 = new BufferedReader(new FileReader(tempFile2));
                BufferedWriter writer2 = new BufferedWriter(new FileWriter(inputFile2));

                String line2;
                while ((line2 = reader2.readLine()) != null) {
                    writer2.write(line2 + "\n");
                }

                writer2.close();
                reader2.close();
                reader2 = new BufferedReader(new FileReader(inputFile));

            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void synchronizeWithUsersFile(String userEmail, String chosenEdit, String newValue) {
        if (!chosenEdit.equals("name")) {
            editUsersFile("Users.txt", userEmail, chosenEdit, newValue);
        }

    }

    public String getUsersByLine() {
        String users = "";
        try (Scanner scanner = new Scanner(new File("Users.txt"))) {
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                String password = scanner.nextLine().trim();
                String userType = scanner.nextLine().trim();
                scanner.nextLine();
                User user = getUserByEmail(email);
                users += user.userName + "," + user.userEmail + "," + user.userPass + "," + userType + "\n";

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return users;
    }

    public String getTripsByLine() throws IOException {
        File file = new File("Trips.dat");
        String trips = "";

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) objectInputStream.readObject();

            for (Trip trip : allTrips) {
                Double tripFare = trip.getFare();
                tripFare = Math.round(tripFare * 100.0) / 100.0;
                double tripDistance = trip.getTripDistance();
                tripDistance = Math.round(tripDistance * 100.0) / 100.0;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Customize the pattern as needed
                String formattedStartTime = trip.getStartTime().toLocalTime().format(formatter);
                String formattedEndTime = trip.getEndTime().format(formatter);

                trips += trip.getTripID() + "," + trip.getPassenger().getUserName() + "," + trip.getDriver().getUserName() + ","
                        + String.valueOf(tripFare + " LE") + "," + trip.getSource() + "," + trip.getDestination() + ","
                        + String.valueOf(tripDistance + " KM") + "," + String.valueOf(formattedStartTime) + ","
                        + String.valueOf(formattedEndTime) + "," + String.valueOf(trip.getTripTimeTaken().toMinutes()) + " Minutes" + "\n";
            }
            return trips;
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e);
        }

        return null;
    }

    public String getDriverTripsByList(ArrayList<Trip> AllTrips, String email) throws IOException {
        String trips = "";

        ArrayList<Trip> allTrips = new ArrayList<>();
        Driver temp = new Driver();
        temp.setUserEmail(email);
        allTrips = temp.viewMyTrips();
        for (Trip trip : allTrips) {
            Double tripFare = trip.getFare();
            tripFare = Math.round(tripFare * 100.0) / 100.0;
            double tripDistance = trip.getTripDistance();
            tripDistance = Math.round(tripDistance * 100.0) / 100.0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Customize the pattern as needed
            String formattedStartTime = trip.getStartTime().toLocalTime().format(formatter);
            String formattedEndTime = trip.getEndTime().format(formatter);

            trips += "Trip ID: " + trip.getTripID() + "\n" + "Passenger: " + trip.getPassenger().getUserName() + "\n" + "Driver: " + trip.getDriver().getUserName() + "\n" + "Trip Fare: "
                    + String.valueOf(tripFare + " LE") + "\n" + "Source: " + trip.getSource() + "\n" + "Destination: " + trip.getDestination() + "\n"
                    + "Trip Distance: " + String.valueOf(tripDistance + " KM") + "\n" + "Start Time: " + String.valueOf(formattedStartTime) + "\n"
                    + "End Time: " + String.valueOf(formattedEndTime) + "\n" + "Time Taken: " + String.valueOf(trip.getTripTimeTaken().toMinutes()) + " Minutes" + "\n" + "-----------------------------" + "\n";
        }
        return trips;

    }

    public String getPassengerTripsByList(ArrayList<Trip> AllTrips, String email) throws IOException {
        String trips = "";

        ArrayList<Trip> allTrips = new ArrayList<>();
        Passenger temp = new Passenger();
        temp.setUserEmail(email);
        allTrips = temp.viewMyTrips();
        for (Trip trip : allTrips) {
            Double tripFare = trip.getFare();
            tripFare = Math.round(tripFare * 100.0) / 100.0;
            double tripDistance = trip.getTripDistance();
            tripDistance = Math.round(tripDistance * 100.0) / 100.0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Customize the pattern as needed
            String formattedStartTime = trip.getStartTime().toLocalTime().format(formatter);
            String formattedEndTime = trip.getEndTime().format(formatter);

            trips += "Trip ID: " + trip.getTripID() + "\n" + "Passenger: " + trip.getPassenger().getUserName() + "\n" + "Driver: " + trip.getDriver().getUserName() + "\n" + "Trip Fare: "
                    + String.valueOf(tripFare + " LE") + "\n" + "Source: " + trip.getSource() + "\n" + "Destination: " + trip.getDestination() + "\n"
                    + "Trip Distance: " + String.valueOf(tripDistance + " KM") + "\n" + "Start Time: " + String.valueOf(formattedStartTime) + "\n"
                    + "End Time: " + String.valueOf(formattedEndTime) + "\n" + "Time Taken: " + String.valueOf(trip.getTripTimeTaken().toMinutes()) + " Minutes" + "\n" + "-----------------------------" + "\n";
        }
        return trips;

    }

    public String getPassengerTripsByListByName(ArrayList<Trip> AllTrips, String name) throws IOException {
        String trips = "";

        ArrayList<Trip> allTrips = new ArrayList<>();
        Passenger temp = new Passenger();
        temp.setUserName(name);
        allTrips = temp.viewMyTripsByName();
        for (Trip trip : allTrips) {
            Double tripFare = trip.getFare();
            tripFare = Math.round(tripFare * 100.0) / 100.0;
            double tripDistance = trip.getTripDistance();
            tripDistance = Math.round(tripDistance * 100.0) / 100.0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Customize the pattern as needed
            String formattedStartTime = trip.getStartTime().toLocalTime().format(formatter);
            String formattedEndTime = trip.getEndTime().format(formatter);

            trips += "Trip ID: " + trip.getTripID() + "\n" + "Passenger: " + trip.getPassenger().getUserName() + "\n" + "Driver: " + trip.getDriver().getUserName() + "\n" + "Trip Fare: "
                    + String.valueOf(tripFare + " LE") + "\n" + "Source: " + trip.getSource() + "\n" + "Destination: " + trip.getDestination() + "\n"
                    + "Trip Distance: " + String.valueOf(tripDistance + " KM") + "\n" + "Start Time: " + String.valueOf(formattedStartTime) + "\n"
                    + "End Time: " + String.valueOf(formattedEndTime) + "\n" + "Time Taken: " + String.valueOf(trip.getTripTimeTaken().toMinutes()) + " Minutes" + "\n" + "-----------------------------" + "\n";
        }
        return trips;

    }

    public String getDriverTripsByListByName(ArrayList<Trip> AllTrips, String name) throws IOException {
        String trips = "";

        ArrayList<Trip> allTrips = new ArrayList<>();
        Driver temp = new Driver();
        temp.setUserName(name);
        allTrips = temp.viewMyTripsByName();
        for (Trip trip : allTrips) {
            Double tripFare = trip.getFare();
            tripFare = Math.round(tripFare * 100.0) / 100.0;
            double tripDistance = trip.getTripDistance();
            tripDistance = Math.round(tripDistance * 100.0) / 100.0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Customize the pattern as needed
            String formattedStartTime = trip.getStartTime().toLocalTime().format(formatter);
            String formattedEndTime = trip.getEndTime().format(formatter);

            trips += "Trip ID: " + trip.getTripID() + "\n" + "Passenger: " + trip.getPassenger().getUserName() + "\n" + "Driver: " + trip.getDriver().getUserName() + "\n" + "Trip Fare: "
                    + String.valueOf(tripFare + " LE") + "\n" + "Source: " + trip.getSource() + "\n" + "Destination: " + trip.getDestination() + "\n"
                    + "Trip Distance: " + String.valueOf(tripDistance + " KM") + "\n" + "Start Time: " + String.valueOf(formattedStartTime) + "\n"
                    + "End Time: " + String.valueOf(formattedEndTime) + "\n" + "Time Taken: " + String.valueOf(trip.getTripTimeTaken().toMinutes()) + " Minutes" + "\n" + "-----------------------------" + "\n";
        }
        return trips;

    }

    public double getTotalFares() {
        double totalFares = 0.0;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Trips.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) ois.readObject();

            for (Trip trip : allTrips) {
                totalFares += trip.getFare();
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalFares;
    }
    
    public String viewDriverReport(String drivername) {
        String driverdata = "not found";
        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (driverName.equals(drivername)) {
                    driverdata = "Report for: " + driverEmail + "\n" + "\n" + "Type: Driver" + "\n" + "Name: " + driverName + "\n" + "Model Year: " + modelYear + "\n" + "Car Name: "
                            + carName + "\n" + "License Number: " + licenseNo + "\n" + "Car Color: " + carColor + "\n" + "Total Trips: " + totalTrips + "\n" + "Total Revenue: " + (Math.round(totalRevenue * 100.0) / 100.0) + "LE";
                    break;
                } else {
                    driverdata = "data not found";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return driverdata;

    }
    
    public String viewPassengerReport(String passengerN) {
        String passengerdata = null;
        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (passengerName.equals(passengerN)) {
                    passengerdata = "Report for: " + passengerEmail + "\n" + "\n" + "Type: Passenger" + "\n" + "Name: "
                            + passengerName + "\n" + "Number of Trips: " + numberOfTrips + "\n" + "Total payments: " + (Math.round(totalPayments * 100.0) / 100.0) + "LE";
                    break;

                } else {
                    passengerdata = "data not found";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return passengerdata;
    }


    public double getAverageFare() {
        double totalFares = 0.0;
        int tripCount = 0;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Trips.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) ois.readObject();

            for (Trip trip : allTrips) {
                totalFares += trip.getFare();
                tripCount++;
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double averageFare = (tripCount > 0) ? (totalFares / tripCount) : 0.0;
        return averageFare;
    }

    public String viewUserReport(String userEmail) {
        User user = getUserByEmail(userEmail);

        if (user != null) {
            if (user instanceof Driver) {

                return viewDriver((Driver) user);

            } else if (user instanceof Passenger) {
                return viewPassenger((Passenger) user);
            } else {
                System.out.println("Invalid user type for report.");
                return null;
            }
        } else {
            System.out.println("User not found for: " + userEmail);
            return null;
        }
    }

    private String viewDriver(Driver driver) {
        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {

            while (scanner.hasNext()) {
                String driverEmail = scanner.nextLine();
                String driverName = scanner.nextLine();
                String driverPass = scanner.nextLine();
                int modelYear = Integer.parseInt(scanner.nextLine());
                String carName = scanner.nextLine();
                String licenseNo = scanner.nextLine();
                String carColor = scanner.nextLine();
                int totalTrips = Integer.parseInt(scanner.nextLine());
                double totalRevenue = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (driverEmail.equals(driver.userEmail)) {
                    String z;
                    z = "Email: " + driver.userEmail + "\n";
                    z += "Name: " + driverName + "\n";
                    z += "Model Year: " + modelYear + "\n";
                    z += "Car Name: " + carName + "\n";
                    z += "License Number: " + licenseNo + "\n";
                    z += "Car Color: " + carColor + "\n";
                    z += "Total Trips: " + totalTrips + "\n";
                    z += "Total Revenue: " + totalRevenue + "\n";
                    return z;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    private String viewPassenger(Passenger passenger) {
        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {

            while (scanner.hasNext()) {
                String passengerEmail = scanner.nextLine();
                String passengerName = scanner.nextLine();
                String passengerPass = scanner.nextLine();
                int numberOfTrips = Integer.parseInt(scanner.nextLine());
                double totalPayments = Double.parseDouble(scanner.nextLine());
                scanner.nextLine();

                if (passengerEmail.equals(passenger.userEmail)) {
                    String z;

                    z = "Name: " + passengerName + "\n";
                    z += "Email: " + passenger.userEmail + "\n";
                    z += "Number of Trips: " + numberOfTrips + "\n";
                    z += "Total payments: " + totalPayments + "\n";
                    return z;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

}
