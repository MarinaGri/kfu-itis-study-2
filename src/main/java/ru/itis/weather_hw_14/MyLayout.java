package ru.itis.weather_hw_14;

import java.awt.*;

public class MyLayout implements LayoutManager {

    @Override
public void layoutContainer(Container parent) {
    int i = 0;
    parent.getComponent(i++).setBounds(300,50,500,60);
    parent.getComponent(i++).setBounds(200,150,300,30);
    parent.getComponent(i++).setBounds(450, 150, 300,30);
    parent.getComponent(i++).setBounds(650,200,100, 30);
}
    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }


}
