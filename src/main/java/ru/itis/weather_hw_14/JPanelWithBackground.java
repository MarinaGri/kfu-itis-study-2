package ru.itis.weather_hw_14;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;

    public JPanelWithBackground(String fileName) throws IOException{
        super();
        Path p = Path.of(fileName).normalize().toAbsolutePath();
        backgroundImage = ImageIO.read(new File(p.toString()));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
