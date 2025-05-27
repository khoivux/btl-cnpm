package view.user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import dao.UserDAO;
import model.User;

public class LoginFrm extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrm() {
        super("Login");

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setPreferredSize(new Dimension(100, 30));

        JLabel lblTitle = new JLabel("User Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel pnUsername = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnUsername.add(new JLabel("Username:"));
        pnUsername.add(txtUsername);

        JPanel pnPassword = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnPassword.add(new JLabel("Password:"));
        pnPassword.add(txtPassword);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnButton.add(btnLogin);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        pnMain.add(lblTitle);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
        pnMain.add(pnUsername);
        pnMain.add(pnPassword);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        pnMain.add(pnButton);

        btnLogin.addActionListener(this);

        this.setSize(500, 350);
        this.setLocationRelativeTo(null); 
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (((JButton) e.getSource()).equals(btnLogin))) {
            User user = new User();
            user.setUsername(txtUsername.getText());
            user.setPassword(new String(txtPassword.getPassword()));

            UserDAO userDAO = new UserDAO();
            if (userDAO.checkLogin(user)) {
                if (user.getRole().equalsIgnoreCase("data_entry")) {
                    new DataEntryHomeFrm(user).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Chức năng của vị trí " + user.getRole() + " đang được xây dựng!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!");
            }
        }
    }
    
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrm frame = new LoginFrm();
            frame.setVisible(true);
        });
    }
}
