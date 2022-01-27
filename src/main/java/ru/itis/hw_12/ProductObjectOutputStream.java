package ru.itis.hw_12;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ProductObjectOutputStream extends OutputStream {
    private OutputStream output;
    private ObjectOutputStream oos;

    public ProductObjectOutputStream(OutputStream output){
        if(output != null){
            this.output = output;
        }
    }

    public void writeProduct(Product product) throws IOException {
        try{
            oos = new ObjectOutputStream(output);
            oos.writeObject(product);
        } catch (IOException e) {
            throw new IOException("Product record failed", e);
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        output.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        output.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        output.flush();
    }

    @Override
    public void close() throws IOException {
        output.close();
    }


    @Override
    public void write(int b) throws IOException {
        output.write(b);
    }
}
