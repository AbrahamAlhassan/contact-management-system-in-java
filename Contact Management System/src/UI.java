import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    private JFrame frame;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactList;
    private ContactManager contactManager;

    public UI() {
        contactManager = new ContactManager();
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Contact Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        frame.add(new JScrollPane(contactList), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton addButton = new JButton("Add Contact");
        JButton removeButton = new JButton("Remove Contact");
        panel.add(addButton);
        panel.add(removeButton);
        frame.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter name:");
                String phone = JOptionPane.showInputDialog(frame, "Enter phone:");
                String email = JOptionPane.showInputDialog(frame, "Enter email:");
                if (name != null && phone != null && email != null) {
                    Contact contact = new Contact(name, phone, email);
                    contactManager.addContact(contact);
                    contactListModel.addElement(contact);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contact = contactList.getSelectedValue();
                if (contact != null) {
                    contactManager.removeContact(contact);
                    contactListModel.removeElement(contact);
                }
            }
        });

        loadContactsToList();
        frame.setVisible(true);
    }

    private void loadContactsToList() {
        for (Contact contact : contactManager.getContacts()) {
            contactListModel.addElement(contact);
        }
    }
}
