package presentation;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AdminPresentation extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JButton seeAllUsersButton;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton button2;
    private JLabel label5;
    private JTextField textField5;
    private JButton searchButton;
    private JTable table2;
    private JScrollPane usersTable;
    private JPasswordField passwordField1;

    public AdminPresentation(){
        add(mainPanel);
        setTitle("Admin interface");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String comboboxValue(){
        return comboBox1.getSelectedItem().toString();
    }

    public String getUserIdValue(){
        return textField1.getText();
    }

    public String getUsernameValue(){
        return textField2.getText();
    }

    public String getPasswordValue(){
        return passwordField1.getText();
    }
    public String getAdminValue(){
        return textField4.getText();
    }

    public Long getIdSearchValue(){
        return Long.parseLong(textField5.getText());
    }

    public void refreshUserTable(Object[][] data, String[] columnNames){
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void refreshFoundUsersTable(Object[][] data, String[] columnNames){
        DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void addSeeAllUsersActionListener(ActionListener listener){
        seeAllUsersButton.addActionListener(listener);
    }

    public void addCreateUpdateDeleteActionListener(ActionListener listener){
        button2.addActionListener(listener);
    }
    public void addSearchButtonListener(ActionListener listener){
        searchButton.addActionListener(listener);
    }


}
