package ru.itis.weather_hw_14;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {
    private ActionListener listener;
    private ActionEvent actionEvent;

    public MyKeyListener(ActionListener listener, ActionEvent actionEvent){
        this.listener = listener;
        this.actionEvent = actionEvent;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER ) {
            listener.actionPerformed(actionEvent);
        }
    }
}
