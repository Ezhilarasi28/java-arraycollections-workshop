package se.lexicon;

import java.util.HashMap;
import java.util.Scanner;

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

            IO.print("Enter first name: ");
            String firstName = userInput.nextLine();

            IO.print("Enter last name: ");
            String lastName = userInput.nextLine();

            IO.print("Enter email address: ");
            String email = userInput.nextLine();

            IO.print("Enter phone number: ");
            String phone = userInput.nextLine();

            // Use full name as the hashmap key
            String fullName = firstName + " " + lastName;

            // Store contact details
            contacts.put(fullName, new String[]{
                    firstName, lastName, email, phone
            });
            IO.println("\n--- Added Contact ---");
            IO.println("First Name : " + firstName);
            IO.println("Last Name  : " + lastName);
            IO.println("Email      : " + email);
            IO.println("Phone      : " + phone);
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
                IO.println("Contact updated successfully!");

            } else {
                IO.println("Contact not found.");
            }
        }

       //methos5: search contact
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

        for (String[] contact : contacts.values()) {
            IO.println("--------------------------");
            IO.println("First Name : " + contact[0]);
            IO.println("Last Name  : " + contact[1]);
            IO.println("Email      : " + contact[2]);
            IO.println("Phone      : " + contact[3]);
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
                contacts.remove(fullName);

                String[] deletedContact = contacts.remove(fullName);

                IO.println("\n--- Deleted Contact ---");
                IO.println("First Name : " + deletedContact[0]);
                IO.println("Last Name  : " + deletedContact[1]);
                IO.println("Email      : " + deletedContact[2]);
                IO.println("Phone      : " + deletedContact[3]);
                IO.println("Contact deleted successfully!");

            } else {
                IO.println("Contact not found.");
            }
        }
}

