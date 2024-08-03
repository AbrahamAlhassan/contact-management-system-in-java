import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;
    private static final String FILE_NAME = "C:\\Users\\user pc\\IdeaProjects\\Contact Management System\\src\\text";

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        saveContacts();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (List<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no contacts to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
