
package cabbookingsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Passenger extends User implements Serializable {

    private int numberOfTrips;
    private double totalPayments;

    public Passenger(String userEmail, String userName, String userPass, int numberOfTrips, double totalPayments) {
        super(userName, userEmail, userPass);
        this.numberOfTrips = numberOfTrips;
        this.totalPayments = totalPayments;
    }

    public Passenger(Passenger passenger) {

        this.userName = passenger.userName;
        this.userEmail = passenger.userEmail;
        this.userPass = passenger.userPass;
        this.numberOfTrips = passenger.numberOfTrips;
        this.totalPayments = passenger.totalPayments;

    }

    Passenger() {

    }

    public double getTotalPayements() {
        return totalPayments;
    }

    public void setTotalPayements(double totalPayments) {
        this.totalPayments = totalPayments;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }

    public ArrayList<Trip> viewMyTrips() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Trips.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) objectInputStream.readObject();

            if (allTrips.isEmpty()) {
                System.out.println("No trips found.");
                return new ArrayList<>();
            } else {
                ArrayList<Trip> passengerTrips = new ArrayList<>();
                System.out.println("Trips for the current passenger:");

                for (Trip trip : allTrips) {
                    Passenger passenger = trip.getPassenger();
                    if (passenger.getUserEmail().equals(this.getUserEmail())) {
                        passengerTrips.add(trip);
                    }
                }

                return passengerTrips;
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
                ArrayList<Trip> passengerTrips = new ArrayList<>();
                System.out.println("Trips for the current passenger:");

                for (Trip trip : allTrips) {
                    Passenger passenger = trip.getPassenger();
                    if (passenger.getUserName().equals(this.getUserName())) {
                        passengerTrips.add(trip);
                    }
                }

                return passengerTrips;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    private double generateRandomDistance() {
        return Math.random() * 50;
    }

    private double calculateFare(double distance) {
        return distance * 5;
    }

    private String generateTripID() {
        return "TRIP" + System.currentTimeMillis();
    }

    public Trip requestTrip(String source, String destination) {
        double distance = generateRandomDistance();
        double fare = calculateFare(distance);
        Trip requestedTrip = new Trip(this, generateTripID(), null, source, destination, null, null, fare, null, distance);
        return requestedTrip;

    }

    public Trip findRequestedTrip() {
        File file = new File("Trips.dat");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> allTrips = (ArrayList<Trip>) objectInputStream.readObject();

            for (Trip requestedTrip : allTrips) {
                if (requestedTrip.getPassenger() != null && requestedTrip.getPassenger().equals(this)
                        && requestedTrip.getStartTime() == null) {
                    return requestedTrip;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return null;
    }

}
