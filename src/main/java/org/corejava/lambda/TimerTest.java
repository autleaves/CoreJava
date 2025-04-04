package org.corejava.lambda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest {

    public static void main(String[] args) {
        TimePrinter listener = new TimePrinter();
        Timer timer = new Timer(1000, listener);
        timer.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("At the tone, the time is "
            + Instant.ofEpochMilli(event.getWhen()));
    }

    
}