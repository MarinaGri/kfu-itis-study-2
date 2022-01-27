package ru.itis.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class CSVInputStream extends InputStream {
    private InputStream is;

    public CSVInputStream(InputStream is){
        if(is == null) throw new IllegalArgumentException();
        this.is = is;
    }

    public String[] readCSV() throws IOException {
        int first;
        StringBuilder sb = new StringBuilder();
        while((first = is.read()) != -1) {
            ByteBuffer b = ByteBuffer.allocate(2);
            b.put((byte)first);
            b.put((byte)is.read());
            b.rewind();
            sb.append(b.getChar());
//            char ch = (char)(first << 8 | is.read() << 0);
//            sb.append(ch);
        }
        String str = sb.toString();
        return str.split(",");
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return is.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return is.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return is.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return is.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        return is.readNBytes(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return is.skip(n);
    }

    @Override
    public int available() throws IOException {
        return is.available();
    }

    @Override
    public void close() throws IOException {
        is.close();
    }

    @Override
    public void mark(int readlimit) {
        is.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        is.reset();
    }

    @Override
    public boolean markSupported() {
        return is.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return is.transferTo(out);
    }
}
