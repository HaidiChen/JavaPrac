package javaprac.security;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import javax.swing.*;

import javaprac.Prac;


public class ClassLoaderPrac implements Prac {

    @Override
    public void runPrac() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ClassLoaderFrame();
            frame.setTitle("Class Loader Prac");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    @Override
    public String getDescription() {
        return "Practice security - custom class loader usage.";
    }
}

class ClassLoaderFrame extends JFrame {

    private static final int DEFAUL_WIDTH = 300;
    private static final int DEFAUL_HEIGHT = 200;

    private JTextField keyField = new JTextField("3", 4);
    /*
     * Encrypted classes end with '-caesar', e.g. 'javaprac.streams.StreamCreationPrac-caesar'.
     * Normal classes don't need that suffix, e.g. 'javaprac.streams.StreamCreationPrac'.
     */
    private JTextField classNameField = new JTextField("Calculator", 30);

    public ClassLoaderFrame() {
        setSize(DEFAUL_WIDTH, DEFAUL_HEIGHT);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Class"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(classNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Key"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 100;
        gbc.anchor = GridBagConstraints.WEST;
        add(keyField, gbc);

        JButton loadButton = new JButton("Load");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(loadButton, gbc);
        loadButton.addActionListener(event -> {
            runClass(classNameField.getText(), keyField.getText());
        });
        pack();
    }

    private void runClass(String name, String key) {
        try {
            ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("run");
            m.invoke(c.newInstance());
        } catch (Throwable e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
}

class CryptoClassLoader extends ClassLoader {

    private int key;

    public CryptoClassLoader(int key) {
        this.key = key;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            if (name.endsWith("-caesar")) {
                byte[] classBytes = loadClassBytes(name);
                Class<?> clazz = defineClass(name.substring(0, name.lastIndexOf("-")),
                                             classBytes,
                                             0,
                                             classBytes.length);
                if (clazz == null) {
                    throw new ClassNotFoundException(name);
                }

                return clazz;
            }

            return super.loadClass(name);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadClassBytes(String name) throws IOException {
        int lastDotIdx = name.lastIndexOf(".");
        String cname = "build/classes/java/main/" + name.replace(".", "/").replace("-", ".");
        byte[] bytes = Files.readAllBytes(Paths.get(cname));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - key);
        }

        return bytes;
    }
}
