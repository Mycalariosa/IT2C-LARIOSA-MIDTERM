package lariosa_event;

import java.util.Scanner;

public class Lariosa_event {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ch;

        do {
            System.out.println("\n|------------------------------------|");
            System.out.println("|    WELCOME TO EVENT PLANNING      |");
            System.out.println("|------------------------------------|");
            System.out.println("| 1. ADD                             |");
            System.out.println("| 2. VIEW                            |");
            System.out.println("| 3. UPDATE                          |");
            System.out.println("| 4. DELETE                          |");
            System.out.println("| 5. EXIT                            |");
            System.out.println("|------------------------------------|");

            System.out.print("Choose from 1-5: ");
            int action = sc.nextInt();

            while (action < 1 || action > 5) {
                System.out.print("\tInvalid action. Please enter a number between 1 and 5: ");
                action = sc.nextInt();
            }

            Lariosa_event evnt = new Lariosa_event();

            switch (action) {
                case 1:
                    evnt.addEvent();
                    break;
                case 2:
                    evnt.viewEvents();
                    break;
                case 3:
                    evnt.updateEvent();
                    break;
                case 4:
                    evnt.deleteEvent();
                    break;
                case 5:
                    break;
            }

            System.out.print("\nDo you want to use another system? (Y/N): ");
            ch = sc.next();
        } while (ch.equalsIgnoreCase("Y"));

        System.out.println("\nThank you for using this application");
    }

    public void addEvent() {
        Scanner sc = new Scanner(System.in);
        CONFIG conf = new CONFIG();

        System.out.print("------------------------------------\n");
        System.out.print("Event name: ");
        String ename = sc.nextLine();

        System.out.print("Event date: ");
        String edate = sc.nextLine();

        System.out.print("Event location: ");
        String eloc = sc.nextLine();

        System.out.print("Event description: ");
        String edis = sc.nextLine();
        
        System.out.print("Event organizer: ");
        String eorg = sc.nextLine();

        String sql = "INSERT INTO Events (Event_name, Event_date, Event_location, Event_description, Event_organizer) VALUES (?,?, ?, ?, ?)";
        conf.addRecord(sql, ename, edate, eloc, edis, eorg);
    }

    private void viewEvents() {
        CONFIG con = new CONFIG();
        String lariosamidQuery = "SELECT * FROM Events";
        String[] lariosamidHeaders = {"ID", "Name", "Date", "Location", "Description", "Organizer"};
        String[] lariosamidColumns = {"Event_id", "Event_name", "Event_date", "Event_location", "Event_description", "Event_organizer"};

        con.viewRecords(lariosamidQuery, lariosamidHeaders, lariosamidColumns);
    }

    private void updateEvent() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Event ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume the newline

        System.out.print("Event name: ");
        String ename = sc.nextLine();

        System.out.print("Event date: ");
        String edate = sc.nextLine();

        System.out.print("Event location: ");
        String eloc = sc.nextLine();

        System.out.print("Event description: ");
        String edis = sc.nextLine();
        
        System.out.print("Event organizer: ");
        String eorg = sc.nextLine();

        String qry = "UPDATE Events SET Event_name=?, Event_date=?, Event_location=?, Event_description=?, Event+organizer=? WHERE Event_id = ?";
        CONFIG con = new CONFIG();
        con.updateRecord(qry, ename, edate, eloc, edis,eorg, id);
    }

    private void deleteEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Event ID: ");
        int id = sc.nextInt();

        String sqlDelete = "DELETE FROM Events WHERE Event_id = ?";
        CONFIG con = new CONFIG();
        con.deleteRecord(sqlDelete, id);
    }
}


