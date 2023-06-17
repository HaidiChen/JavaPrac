package javaprac.security;

import java.awt.*;
import javax.swing.*;

import javaprac.Prac;


public class PermissionPrac implements Prac {

    @Override
    public void runPrac() {
        System.setProperty("java.security.policy", "src/main/resources/PermissionTest.policy");
        /*
         * setSecurityManager() and getSecurityManager() will be deprecated though.
         */
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(() -> {
            JFrame frame = new PermissionTestFrame();
            frame.setTitle("Permission Prac");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    @Override
    public String getDescription() {
        return "Learn security - permission usage.";
    }
}

class PermissionTestFrame extends JFrame {

    private static final int TEXT_ROWS = 20;
    private static final int TEXT_COLUMNS = 60;

    private JTextField textField;
    private WordCheckTextArea textArea;

    public PermissionTestFrame() {
        textField = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(textField);
        JButton openButton = new JButton("insert");
        panel.add(openButton);
        openButton.addActionListener(event -> insertWords(textField.getText()));

        add(panel, BorderLayout.NORTH);

        textArea = new WordCheckTextArea();
        textArea.setRows(TEXT_ROWS);
        textArea.setColumns(TEXT_COLUMNS);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        pack();
    }

    private void insertWords(String words) {
        try {
            textArea.append(words + "\n");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(this, "I'm sorry but I can't do that.");
            e.printStackTrace();
        }
    }
}

class WordCheckTextArea extends JTextArea {

    @Override
    public void append(String text) {
        WordCheckPermission p = new WordCheckPermission(text, "insert");
        SecurityManager manager = System.getSecurityManager();
        if (manager != null) {
            manager.checkPermission(p);
        }
        super.append(text);
    }
}
