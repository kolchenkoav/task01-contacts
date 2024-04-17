package org.example.repository;

import org.example.model.Contacts;
import org.junit.jupiter.api.Test;

import static org.example.service.DataInit.listOfContacts;
import static org.junit.jupiter.api.Assertions.*;

class WorkingWithContactsTest {

    @Test
    void parseLineValid() {
        WorkingWithContacts working = new WorkingWithContacts();
        String line = "Иванов Иван Иванович; +7(900)123-45-67; ivanov@mail.ru";
        Contacts contact = working.parseLine(line);
        assertNotNull(contact);
        assertEquals("Иванов Иван Иванович", contact.getFullName());
        assertEquals("+7(900)123-45-67", contact.getPhoneNumber());
        assertEquals("ivanov@mail.ru", contact.getEmail());
    }

    @Test
    void parseLineInvalidFormat() {
        WorkingWithContacts working = new WorkingWithContacts();
        String line = "Иванов Иван";
        Contacts contact = working.parseLine(line);
        assertNull(contact);
    }

    @Test
    void parseLineInvalidEmail() {
        WorkingWithContacts working = new WorkingWithContacts();
        String line = "Иванов Иван Иванович; +7(900)123-45-67; invalid_email";
        Contacts contact = working.parseLine(line);
        assertNull(contact);
    }

    @Test
    void addContactValid() {
        WorkingWithContacts working = new WorkingWithContacts();
        Contacts contact = new Contacts();
        contact.setFullName("Петров Петр Петрович");
        contact.setPhoneNumber("+7(900)123-45-68");
        contact.setEmail("petrov@mail.ru");
        working.AddContact(contact);
        assertTrue(listOfContacts.containsKey(contact.getEmail()));
    }

    @Test
    void addContactDuplicate() {
        WorkingWithContacts working = new WorkingWithContacts();
        Contacts contact1 = new Contacts();
        contact1.setFullName("Сидоров Сидр Сидорович");
        contact1.setPhoneNumber("+7(900)123-45-69");
        contact1.setEmail("sidorov@mail.ru");
        working.AddContact(contact1);

        Contacts contact2 = new Contacts();
        contact2.setFullName("Иванов Иван Иванович");
        contact2.setPhoneNumber("+7(900)123-45-70");
        contact2.setEmail("sidorov@mail.ru");
        working.AddContact(contact2);

        assertEquals(2, listOfContacts.size());
    }

    @Test
    void deleteContactValid() {
        WorkingWithContacts working = new WorkingWithContacts();
        Contacts contact = new Contacts();
        contact.setFullName("Федоров Федор Федорович");
        contact.setPhoneNumber("+7(900)123-45-71");
        contact.setEmail("fedorov@mail.ru");
        listOfContacts.put(contact.getEmail(), contact);

        working.DeleteContact(contact.getEmail());
        assertFalse(listOfContacts.containsKey(contact.getEmail()));
    }

    @Test
    void deleteContactNonExisting() {
        WorkingWithContacts working = new WorkingWithContacts();
        working.DeleteContact("non_existing@mail.ru");
        assertEquals(2, listOfContacts.size());
    }
}