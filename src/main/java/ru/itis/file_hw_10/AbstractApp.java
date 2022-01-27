package ru.itis.file_hw_10;
public abstract class AbstractApp {
    public AbstractApp(){
        init();
        start();
    }
    protected abstract void init();
    protected abstract void start();
}

