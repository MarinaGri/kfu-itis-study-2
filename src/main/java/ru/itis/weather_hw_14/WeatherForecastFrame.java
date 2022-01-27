package ru.itis.weather_hw_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class WeatherForecastFrame extends JFrame {
    private Font font;
    private Font titleFont;
    private JButton button;
    private JTextField input;
    private JLabel forecast;
    private JLabel label;
    private JPanel panel;

    public WeatherForecastFrame(){
        super("Weather");
        font = new Font("font", Font.BOLD,20);
        File font_file = new File("src/main/java/ru/itis/weather_hw_14/TestFont.ttf");
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException | IOException e) {
            System.out.println("Failed to load font");
        }
        Font sizedFont = titleFont.deriveFont(50f);
        button = new JButton("Search");
        input = new JTextField("", 5);
        forecast = new JLabel("Weather forecast");
        label = new JLabel("Enter the name of city: ");
        try {
            panel = new JPanelWithBackground("src/main/java/ru/itis/weather_hw_14/sky.jpg");
        } catch (IOException e) {
            System.out.println("Failed to load background");
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setResizable(false);
        this.setBounds(dimension.width/2 - 500, dimension.height/2 - 300, 1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        panel.setLayout(new MyLayout());
        forecast.setFont(sizedFont);
        label.setFont(font);
        panel.add(forecast);
        panel.add(label);
        panel.add(input);
        panel.add(button);
        ActionListener listener = new MyActionListener(this, input, panel);
        KeyListener keyListener = new MyKeyListener(listener, (ActionEvent) button.getAction());
        input.addKeyListener(keyListener);
        button.addActionListener(listener);
    }
}
