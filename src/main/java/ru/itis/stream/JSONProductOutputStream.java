package ru.itis.stream;

import ru.itis.hw_12.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

public class JSONProductOutputStream extends OutputStream {
    private OutputStream os;

    public JSONProductOutputStream(OutputStream os){
        if(os == null) throw new IllegalArgumentException("OutputStream should not be null");
        this.os = os;
    }

    public void writeProduct(Product p) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(os, p);
    }

    @Override
    public void write(byte[] b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        os.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        os.flush();
    }

    @Override
    public void close() throws IOException {
        os.close();
    }

    @Override
    public void write(int b) throws IOException {

    }
}
