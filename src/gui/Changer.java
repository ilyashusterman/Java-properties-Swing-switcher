package gui;

import properties.system.PropertiesChange;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Changer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonExit;
    private JTextField acceptField;
    private JTextField developmentField;
    private JLabel acceptanceLabel;
    private JLabel developmentLabel;
    private PropertiesChange propertiesChange;
    private String value;
    public Changer(String value) {
        this.value=value;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        propertiesChange=new PropertiesChange("./config/application.properties");
        try {
            acceptField.setText(propertiesChange.getValue(value));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Eggs are not supposed to be black!. invalid initialize : "+e.getMessage());
        }

    }

    private void onOK() {
        String replacement=null;
        try{
        replacement = developmentField.getText();
    }catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "Eggs are not supposed to be red. invalid initialize : "+e.getMessage());
        }
        if(replacement!=null) {
            try {
                propertiesChange.changeValue(this.value, replacement);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Eggs are not supposed to be black!. invalid initialize with value : "+e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Properties changed Successfully!");
        }
        else JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green. invalid text : "+replacement);

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        Changer dialog = new Changer("contry");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    @SuppressWarnings("Since15")
    public boolean validIP (String ip) {
        try {
            if ((ip == null) || ip.isEmpty()) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
