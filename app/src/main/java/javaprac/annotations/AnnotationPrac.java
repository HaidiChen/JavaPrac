package javaprac.annotations;

import java.awt.*;
import java.awt.event.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import javax.swing.*;

import javaprac.Prac;


public class AnnotationPrac implements Prac {

    @Override
    public void runPrac() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ButtonFrame();
            frame.setTitle("Annotation Practice");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    @Override
    public String getDescription() {
        return "Practice annotations - create an annotation for addActionListener method.";
    }
}

class ActionListenerInstaller {

    public static void processAnnotations(Object obj) {
        try {
            Class<?> clazz = obj.getClass();

            for (Method m : clazz.getDeclaredMethods()) {
                ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
                if (a != null) {
                    Field f = clazz.getDeclaredField(a.source());
                    f.setAccessible(true);
                    addListener(f.get(obj), obj, m);
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private static void addListener(Object source, final Object param, final Method m)
            throws ReflectiveOperationException {
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method mm, Object[] args) throws Throwable {
                System.out.println("You are calling the proxy instance.");
                return m.invoke(param);
            }
        };

        Object listener = Proxy.newProxyInstance(null, new Class[]{ActionListener.class}, handler);
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source, listener);
    }
}

class ButtonFrame extends JFrame {

    private static final int DEFAUL_WIDTH = 300;
    private static final int DEFAUL_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    public ButtonFrame() {
        setSize(DEFAUL_WIDTH, DEFAUL_HEIGHT);

        panel = new JPanel();
        add(panel);

        yellowButton = new JButton("Yellow");
        blueButton = new JButton("Blue");
        redButton = new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        ActionListenerInstaller.processAnnotations(this);
    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground() {
        panel.setBackground(Color.YELLOW);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueBackground() {
        panel.setBackground(Color.BLUE);
    }

    @ActionListenerFor(source = "redButton")
    public void redBackground() {
        panel.setBackground(Color.RED);
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ActionListenerFor {
    String source();
}
