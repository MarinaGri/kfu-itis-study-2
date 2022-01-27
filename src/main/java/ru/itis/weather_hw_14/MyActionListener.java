package ru.itis.weather_hw_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class MyActionListener implements ActionListener {
    private JFrame frame;
    private JTextField input;
    private JPanel mainPanel;

    public MyActionListener(JFrame frame, JTextField input, JPanel mainPanel){
        this.frame = frame;
        this.input = input;
        this.mainPanel = mainPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.remove(mainPanel);
        JPanel panel = null;
        try {
            panel = new JPanelWithBackground("src/main/java/ru/itis/weather_hw_14/sky.jpg");
        } catch (IOException ioException) {
            JOptionPane.showInternalMessageDialog(null, "Window background not found");
        }
        frame.add(panel);
        WeatherForecastGetter w = new WeatherForecastGetter();
        try {
            JTextArea textArea = new JTextArea(w.getWeather(input.getText()), 20, 20);
            textArea.setFont(new Font("font", Font.BOLD, 17));
            JScrollPane scroll = new JScrollPane(textArea);
            panel.add(scroll);
        } catch (UnknownHostException UHEx) {
            JOptionPane.showInternalMessageDialog(null, "Connection lost");
        } catch (IllegalArgumentException | IOException Ex){
            JOptionPane.showInternalMessageDialog(null, "City not found");
        }
        JButton backButton = new JButton("Back");
        panel.add(backButton);
        JPanel finalPanel = panel;
        SwingUtilities.updateComponentTreeUI(frame);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(finalPanel);
                frame.add(mainPanel);
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
    }
}
