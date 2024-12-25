import javax.swing.*;
import java.awt.*;

public class AddUserFrame extends JFrame {
    private JTextField txtName, txtEmail;

    public AddUserFrame() {
        setTitle("Add New User");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblName = new JLabel("Name:");
        txtName = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JButton btnAddUser = new JButton("Add User");
        btnAddUser.addActionListener(e -> addUser());

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnAddUser);

        add(panel);
    }

    private void addUser() {
        String name = txtName.getText();
        String email = txtEmail.getText();

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);

        JOptionPane.showMessageDialog(this, "User added successfully!");
        dispose();
    }
}
