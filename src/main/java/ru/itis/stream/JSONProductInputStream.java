package ru.itis.stream;

import ru.itis.hw_12.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JSONProductInputStream extends InputStream {
    private InputStream in;

    public JSONProductInputStream(InputStream in){
        if(in == null) throw new IllegalArgumentException("InputStream should not be null");
        this.in = in;
    }

    public Product readProduct() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product p = mapper.readValue(in, Product.class);
        if(p == null) {
            throw new IOException("Can't read Product");
        }
        else return p;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return in.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return in.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return in.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        return in.readNBytes(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }


    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        in.reset();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return in.transferTo(out);
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
