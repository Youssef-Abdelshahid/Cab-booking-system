
package cabbookingsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



public abstract class User implements Serializable{
    String userName;
    String userEmail;
    String userPass;

    public User() {
    }

    
    public User(String userName, String userEmail, String userPass) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
    public static User login(String enteredEmail, String enteredPassword) {
    try (Scanner scanner = new Scanner(new File("Users.txt"))) {
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            String password = scanner.nextLine().trim();
            String userType = scanner.nextLine().trim();
            scanner.nextLine(); 

            if (email.equals(enteredEmail.trim()) && password.equals(enteredPassword.trim())) {
                return retrieveUserData(email, userType);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println(e);
    }

    return null;
}

private static User retrieveUserData(String userEmail, String userType) {
    switch (userType) {
        case "Driver":
            return retrieveDriverData(userEmail);
        case "Passenger":
            return retrievePassengerData(userEmail);
        case "Admin":
            return retrieveAdminData(userEmail);
        default:
            return null;
    }
}

private static User retrieveDriverData(String userEmail) {
    try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            String name = scanner.nextLine().trim();
            String password = scanner.nextLine().trim();
            int modelYear = Integer.parseInt(scanner.nextLine().trim());
            String carName = scanner.nextLine().trim();
            String licenseNo = scanner.nextLine().trim();
            String carColor = scanner.nextLine().trim();
            int totalTrips = Integer.parseInt(scanner.nextLine().trim());
            double totalRevenue = Double.parseDouble(scanner.nextLine().trim());
            scanner.nextLine(); 

            if (email.equals(userEmail)) {
                return new Driver(name, email, password, modelYear, carName, licenseNo, carColor, totalTrips, totalRevenue);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println(e);
    }

    return null;
}

private static User retrievePassengerData(String userEmail) {
    try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            String name = scanner.nextLine().trim();
            String password = scanner.nextLine().trim();
            int numberOfTrips = Integer.parseInt(scanner.nextLine().trim());
            double totalPayments = Double.parseDouble(scanner.nextLine().trim());
            scanner.nextLine(); 

            if (email.equals(userEmail)) {
                return new Passenger(email, name, password, numberOfTrips, totalPayments);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println(e);
    }

    return null;
}

private static User retrieveAdminData(String userEmail) {
    try (Scanner scanner = new Scanner(new File("Admins.txt"))) {
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            String password = scanner.nextLine().trim();
            String name = scanner.nextLine().trim();
            scanner.nextLine(); 

            if (email.equals(userEmail)) {
                return new Admin(email, password, name);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println(e);
    }

    return null;
}


    

    public void writeToUsersFile(String userType) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("Users.txt", true))) {
            printWriter.println(userEmail);
            printWriter.println(userPass);
            printWriter.println(userType);
            printWriter.println("<>");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void updateDriverTotalRevenue(Driver driver, double fare) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                String name = scanner.nextLine().trim();
                String password = scanner.nextLine().trim();
                int modelYear = Integer.parseInt(scanner.nextLine().trim());
                String carName = scanner.nextLine().trim();
                String licenseNo = scanner.nextLine().trim();
                String carColor = scanner.nextLine().trim();
                int totalTrips = Integer.parseInt(scanner.nextLine().trim());
                double totalRevenue = Double.parseDouble(scanner.nextLine().trim());
                String splitter = scanner.nextLine();

                if (email.equals(driver.getUserEmail())) {
                    totalRevenue += fare;
                }

                lines.add(email);
                lines.add(name);
                lines.add(password);
                lines.add(String.valueOf(modelYear));
                lines.add(carName);
                lines.add(licenseNo);
                lines.add(carColor);
                lines.add(String.valueOf(totalTrips));
                lines.add(String.valueOf(totalRevenue));
                lines.add(splitter);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("Drivers.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Driver total revenue updated successfully.");
    }

    public void updatePassengerTotalPayments(Passenger passenger, double fare) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                String name = scanner.nextLine().trim();
                String password = scanner.nextLine().trim();
                int numberOfTrips = Integer.parseInt(scanner.nextLine().trim());
                double totalPayments = Double.parseDouble(scanner.nextLine().trim());
                String splitter = scanner.nextLine();

                if (email.equals(passenger.getUserEmail())) {
                    totalPayments += fare;
                }

                lines.add(email);
                lines.add(name);
                lines.add(password);
                lines.add(String.valueOf(numberOfTrips));
                lines.add(String.valueOf(totalPayments));
                lines.add(splitter);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("Passengers.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Passenger total payments updated successfully.");
    }


    public void incrementDriverTrips(Driver driver) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Drivers.txt"))) {
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                String name = scanner.nextLine().trim();
                String password = scanner.nextLine().trim();
                int modelYear = Integer.parseInt(scanner.nextLine().trim());
                String carName = scanner.nextLine().trim();
                String licenseNo = scanner.nextLine().trim();
                String carColor = scanner.nextLine().trim();
                int totalTrips = Integer.parseInt(scanner.nextLine().trim());
                double totalRevenue = Double.parseDouble(scanner.nextLine().trim());
                String splitter = scanner.nextLine();

                if (email.equals(driver.getUserEmail())) {
                    totalTrips++;
                }

                lines.add(email);
                lines.add(name);
                lines.add(password);
                lines.add(String.valueOf(modelYear));
                lines.add(carName);
                lines.add(licenseNo);
                lines.add(carColor);
                lines.add(String.valueOf(totalTrips));
                lines.add(String.valueOf(totalRevenue));
                lines.add(splitter);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("Drivers.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Driver trips incremented successfully.");
    }

    public void incrementPassengerTrips(Passenger passenger) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Passengers.txt"))) {
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine().trim();
                String name = scanner.nextLine().trim();
                String password = scanner.nextLine().trim();
                int numberOfTrips = Integer.parseInt(scanner.nextLine().trim());
                double totalPayments = Double.parseDouble(scanner.nextLine().trim());
                String splitter = scanner.nextLine();

                if (email.equals(passenger.getUserEmail())) {
                    numberOfTrips++;
                }

                lines.add(email);
                lines.add(name);
                lines.add(password);
                lines.add(String.valueOf(numberOfTrips));
                lines.add(String.valueOf(totalPayments));
                lines.add(splitter);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("Passengers.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Passenger trips incremented successfully.");
    }
}
   

