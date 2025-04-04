package org.corejava.lambda;

import static java.lang.System.out;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[] {"Mercury", "Venus", "Earth",
                                "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        out.println(Arrays.toString(planets));
        out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        out.println(Arrays.toString(planets));
        out.println("Sorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        out.println(Arrays.toString(planets));

        ActionListener listener = event -> out.println("The time is " + new Date());

        Timer timer = new Timer(1000, listener);
        // Timer timer = new Timer(1000, event -> out.println("The time is " + new Date()));
        timer.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
