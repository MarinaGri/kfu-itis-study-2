package ru.itis.hw_12;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class ProductObjectInputStream extends InputStream {
    private InputStream input;
    private ObjectInputStream ois;

    public ProductObjectInputStream(InputStream input){
        if(input != null){
            this.input = input;
        }
    }

    public Product readProduct() throws IOException {
        try{
            ois = new ObjectInputStream(input);
            return (Product)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Reading product failed", e);
        }
    }

    @Override
    public int read() throws IOException {
        return input.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return input.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return input.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return input.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return input.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        return input.readNBytes(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return input.skip(n);
    }

    @Override
    public int available() throws IOException {
        return input.available();
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    @Override
    public void mark(int readlimit) {
        input.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        input.reset();
    }

    @Override
    public boolean markSupported() {
        return input.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return input.transferTo(out);
    }
}
