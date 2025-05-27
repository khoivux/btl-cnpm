package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.User;

public class DataEntryHomeFrm extends JFrame implements ActionListener {
    private JButton btnUpdateResult, btnCancel;
    private JLabel lblUsername;
    private User user;

    public DataEntryHomeFrm(User user) {
        this.user = user;

        // Thiết lập cửa sổ
        setTitle("Data Entry Client Home");
        setSize(500, 350); // Kích thước giống LoginFrm
        setLocationRelativeTo(null); // Căn giữa màn hình
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel chính với BoxLayout giống LoginFrm
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Padding giống LoginFrm

        // Tiêu đề
        JLabel lblTitle = new JLabel("Data Entry Client Home");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26)); // Phông chữ giống LoginFrm
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);

        // Khoảng cách
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Giống LoginFrm

        // Tên người dùng (căn lề phải, chữ to hơn)
        JPanel pnUsername = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        lblUsername = new JLabel("Welcome, " + user.getUsername() + "!");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18)); // Chữ to hơn (cỡ 18)
        pnUsername.add(lblUsername);
        panel.add(pnUsername);

        // Khoảng cách lớn hơn trước các nút
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Panel cho nút Update Result
        JPanel pnUpdateResult = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnUpdateResult = new JButton("Update Result");
        btnUpdateResult.setPreferredSize(new Dimension(200, 30)); // Nút rộng như yêu cầu trước
        btnUpdateResult.addActionListener(this);
        pnUpdateResult.add(btnUpdateResult);
        panel.add(pnUpdateResult);

        // Khoảng cách giữa các nút
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Panel cho nút Cancel
        JPanel pnCancel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(200, 30)); // Nút rộng như yêu cầu trước
        btnCancel.addActionListener(this);
        pnCancel.add(btnCancel);
        panel.add(pnCancel);

        // Thêm panel vào frame
        setContentPane(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUpdateResult) {
        	new UpdateResultFrm(user).setVisible(true);
            this.dispose();
        } else if (e.getSource() == btnCancel) {
            dispose(); // Đóng form
        }
    }
}