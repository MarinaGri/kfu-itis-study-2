package ru.itis.hw_12;

import java.io.IOException;
import java.io.OutputStream;

public class ProductOutputStream extends OutputStream {
    private OutputStream out;

    public ProductOutputStream(OutputStream out) {
        if(out != null){
            this.out = out;
        }
    }

    public void writeProduct(Product p) throws IOException {
        for(int i = 0; i < p.getName().length(); i++) {
            out.write(p.getName().charAt(i) >> 8);
            out.write(p.getName().charAt(i));
        }
        long l = Double.doubleToLongBits(p.getPrice());
        for(int i = 0; i < 8; i++){
            out.write((int) (l >>((7-i) * 8)));
        }
        for(int i = 0; i < 4; i++){
            out.write(p.getQuantity()>>((3-i) * 8));
        }
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
