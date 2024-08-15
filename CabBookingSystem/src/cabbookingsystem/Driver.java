/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cabbookingsystem;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Yousef
 */
public class Driver extends User implements Serializable {

    private int modelYear;
    private String carName;
    private String licenseNo;
    private String carColor;
    private int totalTrips;
    private double totalRevenue;

    public Driver(String userName, String userEmail, String userPass, int modelYear, String carName, String licenseNo, String carColor, int totalTrips, double totalRevenue) {
        super(userName, userEmail, userPass);
        this.modelYear = modelYear;
        this.carName = carName;
        this.licenseNo = licenseNo;
        this.carColor = carColor;
        this.totalTrips = totalTrips;
        this.totalRevenue = totalRevenue;
    }

    public Driver(Driver driver) {

        this.userName = driver.userName;
        this.userEmail = driver.userEmail;
        this.userPass = driver.userPass;
        this.carColor = driver.carColor;
        this.carName = driver.carName;
        this.licenseNo = driver.licenseNo;
        this.modelYear = driver.modelYear;
        this.totalRevenue = driver.totalRevenue;
        this.totalTrips = driver.totalTrips;

    }

    Driver() {

    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(int totalTrips) {
        this.totalTrips = totalTrips;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }


    public void acceptTrip(String tripID, ArrayList<Trip> reqTrips) {

        Trip acceptedTrip = null;
        for (Trip t : reqTrips) {
            if (t.getTripID().equals(tripID)) {
                acceptedTrip = t;
                break;
            }
        }
        if (acceptedTrip != null) {
            acceptedTrip.setDriver(this);
            acceptedTrip.setStartTime(LocalDateTime.now());

            writeTripToTripsFile(acceptedTrip);
            reqTrips.remove(acceptedTrip);

            System.out.println("Trip with ID " + tripID + " accepted successfully.");
        } else {
            System.out.println("Trip with ID " + tripID + " not found or already accepted.");
        }
    }

    

    private void writeTripToTripsFile(Trip acceptedTrip) {
        File file = new File("Trips.dat");
        @SuppressWarnings("unchecked")
        ArrayList<Trip> trips;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            
            trips = (ArrayList<Trip>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            trips = new ArrayList<>();
        }

        trips.add(acceptedTrip);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(trips);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    

    public void acceptPayment(String tripID) {
        Trip completedTrip = Trip.findCompletedTrip(tripID);

        if (completedTrip != null) {
            completedTrip.setEndTime(LocalDateTime.now());
            Duration duration = Duration.between(completedTrip.getStartTime(), completedTrip.getEndTime());
            completedTrip.setTripTimeTaken(duration);

            updateDriverTotalRevenue(completedTrip.getDriver(), completedTrip.getFare());

            updatePassengerTotalPayments(completedTrip.getPassenger(), completedTrip.getFare());

            incrementDriverTrips(completedTrip.getDriver());
            incrementPassengerTrips(completedTrip.getPassenger());

            Trip.replaceCompletedTripInTripsFile(completedTrip);

            System.out.println("Payment accepted for trip with ID " + tripID);
        } else {
            System.out.println("Trip with ID " + tripID + " not found or already completed.");
        }
    }


    public ArrayList<Trip> viewMyTrips() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Trips.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) objectInputStream.readObject();

            if (allTrips.isEmpty()) {
                System.out.println("No trips found.");
                return new ArrayList<>();
            } else {
                ArrayList<Trip> driverTrips = new ArrayList<>();
                System.out.println("Trips for the current passenger:");

                for (Trip trip : allTrips) {
                    Driver d = trip.getDriver();
                    if (d.getUserEmail().equals(this.getUserEmail())) {
                        driverTrips.add(trip);
                    }
                }

                return driverTrips;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    public ArrayList<Trip> viewMyTripsByName() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Trips.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) objectInputStream.readObject();

            if (allTrips.isEmpty()) {
                System.out.println("No trips found.");
                return new ArrayList<>();
            } else {
                ArrayList<Trip> driverTrips = new ArrayList<>();
                System.out.println("Trips for the current passenger:");

                for (Trip trip : allTrips) {
                    Driver d = trip.getDriver();
                    if (d.getUserName().equals(this.getUserName())) {
                        driverTrips.add(trip);
                    }
                }

                return driverTrips;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    @Override
    public String toString() {
        String x = "Driver name: " + userName + '\n';
        x += "Car name: " + carName + '\n';
        x += "Model: " + licenseNo + '\n';
        x += "Car color: " + carColor + '\n';
        x += "License Number: " + licenseNo + '\n';
        x += "Total Trips: " + totalTrips + '\n';
        return x;
    }

}
