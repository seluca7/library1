package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class UserPresentation extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JTable table1;
    private JButton seeAllBooksButton;
    private JLabel label1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboBox1;
    private JButton button2;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label6;
    private JTextField textField6;
    private JLabel label7;
    private JTextField textField7;
    private JButton searchButton;
    private JTable table2;
    private JTextField textField9;
    private JLabel label9;
    private JLabel label10;
    private JTextField textField10;
    private JLabel label11;
    private JSpinner spinner1;
    private JButton addToBasketButton;
    private JTextField priceField;
    private JTextField textField8;
    private JButton refreshButton;
    private JTable table3;
    private JTextField textField12;
    private JTextField textField13;
    private JButton okButton;
    private JComboBox comboBox3;
    private JLabel priceLabel;
    private JButton sellButton;
    private JComboBox formatComboBox;

    public UserPresentation(){
        add(mainPanel);
        setTitle("User interface");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public String getTitle(){
        return textField1.getText();
    }
    public String getAuthor(){
        return textField2.getText();
    }
    public String getGenre(){
        return textField3.getText();
    }
    public Long getId(){
        return Long.parseLong(textField4.getText());
    }

    public String getIdString(){
        return textField4.getText();
    }

    public String getSearchTitle(){
        return textField5.getText();
    }

    public String getSearchAuthor(){
        return textField6.getText();
    }

    public String getSearchGenre(){
        return textField7.getText();
    }

    public String comboboxValue(){
        return comboBox1.getSelectedItem().toString();
    }

    public Double getPrice(){
        return Double.parseDouble(priceField.getText());
    }

    public String getPriceString(){
        return priceField.getText();
    }

    public String getAddToBasketBookId(){
        return textField9.getText();
    }

    public String getAddToBasketBasketId(){
        return textField10.getText();
    }

    public String getAddToBasketQuantity(){
        return spinner1.getValue().toString();
    }

    public String getCRUDBasketId(){
        return textField12.getText();
    }

    public String getCRUDUserId(){
        return textField13.getText();
    }

    public String getCRUDBasketComboBox(){
        return comboBox3.getSelectedItem().toString();
    }

    public String getCurrentBasketId(){
        return textField8.getText();
    }

    public String getFormat(){
        return formatComboBox.getSelectedItem().toString();
    }

    public void setPrice(Double price){
        priceLabel.setText(price.toString());
    }

    public void refreshBooksTable(Object[][] data, String[] columnNames){
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }


    public void refreshFoundBooksTable(Object[][] data, String[] columnNames){
        DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void refreshShoppingBasketTable(Object[][] data, String[] columnNames){
        DefaultTableModel tableModel = (DefaultTableModel) table3.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void addSeeShoppingBasketActionListener(ActionListener listener){
        refreshButton.addActionListener(listener);
    }

    public void addSellActionListener(ActionListener listener){
        sellButton.addActionListener(listener);
    }

    public void addAddToBaskettActionListener(ActionListener listener){
        addToBasketButton.addActionListener(listener);
    }

    public void addCreateUpdateDeleteBasketActionListener(ActionListener listener){
        okButton.addActionListener(listener);
    }


    public void addSeeAllBooksActionListener(ActionListener listener){
        seeAllBooksButton.addActionListener(listener);
    }

    public void addCreateUpdateDeleteActionListener(ActionListener listener){
        button2.addActionListener(listener);
    }
    public void addSearchButtonListener(ActionListener listener){
        searchButton.addActionListener(listener);
    }

}
