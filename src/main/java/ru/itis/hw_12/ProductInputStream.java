package ru.itis.hw_12;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ProductInputStream extends InputStream {

    private  InputStream in;

    public ProductInputStream(InputStream in) {
        if(in != null) {
            this.in = in;
        }
    }

    public Product readProduct() throws IOException {
        char[] ch = new char[5];
        for(int i = 0; i < 5; i++){
            ch[i] = (char)((in.read()<<8) | in.read()<<0);
        }

        ByteBuffer buffer = ByteBuffer.allocate(8);
        for(int i = 0; i < 8; i++){
            buffer.put((byte)in.read());
        }
        buffer.rewind();
        double price = buffer.getDouble();

        int c1 = in.read();
        int c2 = in.read();
        int c3 = in.read();
        int c4 = in.read();

        int num = (c1<<24 | c2<<16 | c3<<8 | c4<<0);
        return new Product(String.valueOf(ch), price, num);
    }

    @Override
    public int read() throws IOException {
        return in.read();
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
    public void skipNBytes(long n) throws IOException {
        in.skipNBytes(n);
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
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
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
}
