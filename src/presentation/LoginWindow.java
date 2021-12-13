package presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public LoginWindow(){
        add(panel1);
        setTitle("Login");
        setSize(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public String getUsername(){
        return textField1.getText();
    }
    public String getPassword(){
        return passwordField1.getText();
    }

    public void addLoginActionListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
}
