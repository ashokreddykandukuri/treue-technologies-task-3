import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Appointment {
    private LocalDate date;
    private LocalTime time;
    private String serviceProvider;
    private String user;

    public Appointment(LocalDate date, LocalTime time, String serviceProvider, String user) {
        this.date = date;
        this.time = time;
        this.serviceProvider = serviceProvider;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public String getUser() {
        return user;
    }
}

public class systemappointment {
    private List<User> users;
    private List<Appointment> appointments;
    private User loggedInUser;
    private Scanner scanner;

    public systemappointment() {
        users = new ArrayList<>();
        appointments = new ArrayList<>();
        loggedInUser = null;
        scanner = new Scanner(System.in);
    }

    public void registerUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public void loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");
                return;
            }
        }
        System.out.println("Login failed. Please check your credentials.");
    }

    public void scheduleAppointment(LocalDate date, LocalTime time, String serviceProvider) {
        if (loggedInUser != null) {
            Appointment appointment = new Appointment(date, time, serviceProvider, loggedInUser.getUsername());
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("You need to log in to schedule an appointment.");
        }
    }

    public void viewAppointments() {
        if (loggedInUser != null) {
            System.out.println("Your Appointments:");
            for (Appointment appointment : appointments) {
                if (appointment.getUser().equals(loggedInUser.getUsername())) {
                    System.out.println("Date: " + appointment.getDate() +
                            " Time: " + appointment.getTime() +
                            " Service Provider: " + appointment.getServiceProvider());
                }
            }
        } else {
            System.out.println("You need to log in to view appointments.");
        }
    }
    public static void main(String[] args) {
        systemappointment appointmentSystem = new systemappointment();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Appointment Scheduling System");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    appointmentSystem.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    appointmentSystem.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    if (appointmentSystem.loggedInUser != null) {
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        String dateString = scanner.nextLine();
                        LocalDate date = LocalDate.parse(dateString);
                        System.out.print("Enter time (hh:mm): ");
                        String timeString = scanner.nextLine();
                        LocalTime time = LocalTime.parse(timeString);
                        System.out.print("Enter service provider: ");
                        String serviceProvider = scanner.nextLine();
                        appointmentSystem.scheduleAppointment(date, time, serviceProvider);
                    } else {
                        System.out.println("You need to log in to schedule an appointment.");
                    }
                    break;
                case 4:
                    appointmentSystem.viewAppointments();
                    break;
                case 5:
                    System.out.println("Exiting the Appointment Scheduling System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}