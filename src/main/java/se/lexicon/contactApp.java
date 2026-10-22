/*# Workshop – Contact Management App

## The Scenario

You are building a console-based contact management application.

Think about what a contact manager should do and how a user would interact with it — then build it.

**Create `ContactApp.java`** inside `src/main/java/se/lexicon/`.
Commit regularly as you make progress.

---

## Requirements

Before writing any code, think through the following:

- **Functionality** — what operations should a contact manager support? What would a user expect to be able to do?
- **Data** — what information does a contact hold? How should that be represented given your chosen data structure?
- **Data structure** — which structure fits the way contacts will be stored and accessed? Consider the trade-offs.
- **Constraints** — what rules should the system enforce? Think about what invalid states should never be allowed.
- **Code organisation** — how will you separate concerns? Each operation should have a clear home in your code.*/


package se.lexicon;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class contactApp {

    //this line(hashmap) used for store contact information like name,phone,email
    static HashMap<String,String[]> contacts = new HashMap<>();

    static Scanner userInput = new Scanner(System.in);
    void main(){
        while(true){
            displayMenu();
            IO.print("Enter your option:");
            String option = userInput.nextLine().trim();

            switch(option) {
                case "1" -> addContact();
                case "2" -> updateContact();
                case "3" -> searchContact();
                case "4" -> deleteContact();
                case "5" -> viewAllContacts();
                case "6" -> {
                    IO.print("Goodbye!");
                    return;
                }
                default -> IO.print("\nWrong option!");
            }
            }
        }//main method end
        //Method 1:Display Menu
        static void  displayMenu(){
            IO.println("---------------------------------");
            IO.println("       CONTACT MANAGEMENT APP");
            IO.println("---------------------------------");
            IO.println("1. Add Contact");
            IO.println("2. Update Contact");
            IO.println("3. Search Contact");
            IO.println("4. Delete Contact");
            IO.println("5. View All Contacts");
            IO.println("6. Exit");
            IO.println("----------------------------------");

        }
         //methosd2 add contact
        static void addContact() {
            IO.println("\n--- Add Contact ---");
            String firstName = readRequired("Enter first name: ");
            String lastName = readRequired("Enter last name: ");

            String email=readEmail();// call my email validation mehtod

            // Check  the email already exists
            for (String[] contact : contacts.values()) {
                if (contact[2].equalsIgnoreCase(email)) {
                    IO.println("Error: Email already exists!");
                    return;
                }
            }
            String phone = readRequired("Enter phone number: ");
            String address = readRequired("Enter address: ");

            // Use full name as the hashmap key
            String fullName = firstName + " " + lastName;

            // Store contact details
            contacts.put(fullName, new String[]{
                    firstName, lastName, email, phone,address
            });
            IO.println("\n--- Added Contact ---");
            IO.println("First Name : " + firstName);
            IO.println("Last Name  : " + lastName);
            IO.println("Email      : " + email);
            IO.println("Phone      : " + phone);
            IO.println("Address    : " + address);
            IO.println("Contact added successfully!");
        }
       //method:3 update contact
        static void updateContact() {

            IO.println("---- Update Contact ----");

            // Ask for the contact's full name
            IO.print("Enter full name to update: ");
            String fullName = userInput.nextLine();

            // Check if contact exists
            if (contacts.containsKey(fullName)) {

                // Get existing contact details
                String[] contact = contacts.get(fullName);

                // Ask for new details
                IO.print("Enter new email address: ");
                String email = userInput.nextLine();

                IO.print("Enter new phone number: ");
                String phone = userInput.nextLine();

                // Update the array
                contact[2] = email;
                contact[3] = phone;

                IO.println("\n--- Updated Contact ---");
                IO.println("First Name : " + contact[0]);
                IO.println("Last Name  : " + contact[1]);
                IO.println("Email      : " + contact[2]);
                IO.println("Phone      : " + contact[3]);
                IO.println("Address    : " + contact[4]);
                IO.println("Contact updated successfully!");

            } else {
                IO.println("Contact not found.");
            }
        }

       //methods5: search contact
        static void searchContact() {

            IO.println("\n--- Search Contact ---");

            // Ask for full name
            IO.print("Enter full name to search: ");
            String fullName = userInput.nextLine();

            // Check if contact exists
            if (contacts.containsKey(fullName)) {

                // Get contact details from HashMap
                String[] contact = contacts.get(fullName);

                // Display contact details
                IO.println("Contact Found!");
                IO.println("First Name : " + contact[0]);
                IO.println("Last Name  : " + contact[1]);
                IO.println("Email      : " + contact[2]);
                IO.println("Phone      : " + contact[3]);
                IO.println("Address    : " + contact[4]);

            } else {
                IO.println("Contact not found.");
            }
        }
        //method5: view all the contacts
    static void viewAllContacts() {

        IO.println("\n--- All Saved Contacts ---");

        if (contacts.isEmpty()) {
            IO.println("No contacts available.");
            return;
        }

        // Get all contact names
        ArrayList<String> names = new ArrayList<>(contacts.keySet());

// Sort names alphabetically (A-Z)
        names.sort(String.CASE_INSENSITIVE_ORDER);

// Display contacts in sorted order
        for (String name : names) {
            String[] contact = contacts.get(name);

            IO.println("-------------------------");
            IO.println("First Name : " + contact[0]);
            IO.println("Last Name  : " + contact[1]);
            IO.println("Email      : " + contact[2]);
            IO.println("Phone      : " + contact[3]);
            IO.println("Address    : " + contact[4]);
        }
    }

        //method:6 delete contact
        static void deleteContact() {

            IO.println("--- Delete Contact ---");

            // Ask for full name
            IO.print("Enter full name to delete: ");
            String fullName = userInput.nextLine();

            // Check if contact exists
            if (contacts.containsKey(fullName)) {

                // Remove contact from HashMap

                String[] deletedContact = contacts.remove(fullName);

                IO.println("\n--- Deleted Contact ---");
                IO.println("First Name : " + deletedContact[0]);
                IO.println("Last Name  : " + deletedContact[1]);
                IO.println("Email      : " + deletedContact[2]);
                IO.println("Phone      : " + deletedContact[3]);
                IO.println("Address    : " + deletedContact[4]);
                IO.println("Contact deleted successfully!");

            } else {
                IO.println("Contact not found.");
            }
        }

            static String readRequired(String message) {
                while (true) {
                    IO.print(message);
                    String value = userInput.nextLine().trim();

                    if (!value.isEmpty()) {
                        return value;
                    }

                    IO.println("This field cannot be empty.");
                }
            }

    // add readEmail method
    static String readEmail() {
        while (true) {
            String email = readRequired("Enter email address: ");

            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            IO.println("Invalid email format. Try again.");
        }
    }

} // class end




