
package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class contactAppTest {

    @BeforeEach
    void setUp() {
        contactApp.contacts.clear();
    }


    // 1. ADD CONTACT - Positive
    @Test
    @DisplayName("Verify that a new contact is added")
    void testAddContactSuccess()
    {
        contactApp.userInput = new Scanner("Anu\nKumar\nanu@gmail.com\n123456\nSweden\n");
        contactApp.addContact();
        assertTrue(contactApp.contacts.containsKey("Anu Kumar"));
        assertEquals(1, contactApp.contacts.size());
    }


    // 2. ADD CONTACT - Negative
    @Test
    @DisplayName("Verify that duplicate email is rejected")
    void testAddDuplicateEmail()
    {
        contactApp.contacts.put("Anu Kumar", new String[]{"Anu", "Kumar", "anu@gmail.com", "123456", "Sweden"});
        contactApp.userInput = new Scanner("Priya\nRaj\nanu@gmail.com\n");
        contactApp.addContact();
        assertEquals(1, contactApp.contacts.size());
        assertFalse(contactApp.contacts.containsKey("Priya Raj"));
    }


    // 3. SEARCH CONTACT - Positive
    @Test
    @DisplayName("Verify that an existing contact is found")
    void testSearchContactFound()
    {
        contactApp.contacts.put("Anu Kumar", new String[]{"Anu", "Kumar", "anu@gmail.com", "123456", "Sweden"});
        contactApp.userInput = new Scanner("Anu Kumar\n");
        contactApp.searchContact();
        assertTrue(contactApp.contacts.containsKey("Anu Kumar"));
    }


    // 4. SEARCH CONTACT - Negative
    @Test
    @DisplayName("Verify that an unknown contact is not found")
    void testSearchContactNotFound()
    {
        contactApp.userInput = new Scanner("Unknown\n");
        contactApp.searchContact();
        assertFalse(contactApp.contacts.containsKey("Unknown"));
    }


    // 5. UPDATE CONTACT - Positive
    @Test
    @DisplayName("Verify that contact details are updated")
    void testUpdateContactSuccess()
    {
        contactApp.contacts.put("Anu Kumar", new String[]{"Anu", "Kumar", "anu@gmail.com", "123456", "Sweden"});
        contactApp.userInput = new Scanner("Anu Kumar\nnew@gmail.com\n987654\n");
        contactApp.updateContact();
        assertEquals("new@gmail.com", contactApp.contacts.get("Anu Kumar")[2]);
        assertEquals("987654", contactApp.contacts.get("Anu Kumar")[3]);
    }


    // 6. UPDATE CONTACT - Negative
    @Test
    @DisplayName("Verify that an unknown contact cannot be updated")
    void testUpdateContactNotFound()
    {
        contactApp.userInput = new Scanner("Unknown\n");
        contactApp.updateContact();
        assertTrue(contactApp.contacts.isEmpty());
    }



    // 7. DELETE CONTACT - Positive
    @Test
    @DisplayName("Verify that an existing contact is deleted")
    void testDeleteContactSuccess()
    {
        contactApp.contacts.put("Anu Kumar", new String[]{"Anu", "Kumar", "anu@gmail.com", "123456", "Sweden"});
        contactApp.userInput = new Scanner("Anu Kumar\n");
        contactApp.deleteContact();
        assertFalse(contactApp.contacts.containsKey("Anu Kumar"));
        assertTrue(contactApp.contacts.isEmpty());
    }


    // 8. DELETE CONTACT - Negative
    @Test
    @DisplayName("Verify that an unknown contact cannot be deleted")
    void testDeleteContactNotFound()
    {
        contactApp.contacts.put("Anu Kumar", new String[]{"Anu", "Kumar", "anu@gmail.com", "123456", "Sweden"});
        contactApp.userInput = new Scanner("Unknown\n");
        contactApp.deleteContact();
        assertEquals(1, contactApp.contacts.size());
        assertTrue(contactApp.contacts.containsKey("Anu Kumar"));
    }
}
