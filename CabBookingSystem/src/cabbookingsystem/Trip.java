
package cabbookingsystem;


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



public class Trip implements Serializable{
    private Passenger passenger;
    private String tripID;
    private Driver driver;
    private String source;
    private String destination;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double fare;
    private Duration tripTimeTaken;
    private double tripDistance;



    public Trip(Passenger passenger, String tripID, Driver driver, String source, String destination, LocalDateTime startTime, LocalDateTime endTime, double fare, Duration tripTimeTaken, double tripDistance) {
        this.passenger = passenger;
        this.tripID = tripID;
        this.driver = driver;
        this.source = source;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fare = fare;
        this.tripTimeTaken = tripTimeTaken;
        this.tripDistance = tripDistance;
    }



    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Duration getTripTimeTaken() {
        return tripTimeTaken;
    }

    public void setTripTimeTaken(Duration tripTimeTaken) {
        this.tripTimeTaken = tripTimeTaken;
    }

    public double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(double tripDistance) {
        this.tripDistance = tripDistance;
    }
    

    
  public static Trip findCompletedTrip(String tripID) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Trips.dat"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Trip> trips = (ArrayList<Trip>) objectInputStream.readObject();
            for (Trip completedTrip : trips) {
                if (completedTrip.getTripID().equals(tripID) && completedTrip.getEndTime() == null) {
                    return completedTrip;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return null;
    }

    public static void replaceCompletedTripInTripsFile(Trip updatedTrip) {
        File file = new File("Trips.dat");
        @SuppressWarnings("unchecked")
        ArrayList<Trip> trips;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            
            trips = (ArrayList<Trip>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            trips = new ArrayList<>();
        }

        for (int i = 0; i < trips.size(); i++) {
            Trip existingTrip = trips.get(i);
            if (existingTrip.getTripID().equals(updatedTrip.getTripID())) {
                trips.set(i, updatedTrip);
                break;
            }
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(trips);
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Trip updated successfully.");
    }

    
   @Override
public String toString() {
    String x = "Trip Report:\n";
        x = x + "passenger: " + passenger.getUserName() + '\n';
        x = x + "TripId: " + tripID + '\n';
    if (driver != null) {
        x = x + "Driver: " + driver.getUserName() + '\n';
    }
        x = x + "Pick up location: " + source + '\n';
        x = x + "Destination: " + destination + '\n';
    if (startTime != null) {
        x = x + "Start Time: " + startTime + '\n';
    }
    if (endTime != null) {
        x = x + "End Time: " + endTime + '\n';
    }
        x = x + "Fare: " + fare + '\n';
        x = x + "Distance: " + tripDistance + " km" + '\n';
    if (tripTimeTaken != null) {
        x = x + "Estimated Time For The Trip: " + tripTimeTaken + '\n';
    }

    return x;
}
}
