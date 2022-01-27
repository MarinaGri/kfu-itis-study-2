package ru.itis.stream;

import java.io.IOException;
import java.io.OutputStream;

public class CSVOutputStream extends OutputStream {
    private OutputStream os;

    public CSVOutputStream(OutputStream os){
        if(os == null) throw new IllegalArgumentException();
        this.os = os;
    }

    public void writeCSV(String[] strs) throws IOException {
        for(String str: strs){
            char[] chars = str.toCharArray();
            for(char ch: chars){
                os.write(ch << 8);
                os.write(ch << 0);
            }
            os.write(',' << 8);
            os.write(',' << 0);
        }
    }

    @Override
    public void write(int b) throws IOException {
        os.write(b);
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
}
