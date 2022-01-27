package ru.itis.hw_12;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductReader extends Reader {
    private Reader reader;
    private String block = ".*Product\\s*\\{.*";
    private String name = ".*name\\s*=.*";
    private String price = ".*price\\s*=.*";
    private String quantity = ".*quantity\\s*=.*";

    public ProductReader(Reader reader) {
        if(reader != null){
            this.reader = reader;
        }
    }

    public Product readProduct() throws IOException {
        Product p = new Product();
        StringBuffer sb = new StringBuffer();
        char c;
        int n;
        boolean flag = false;
        while ((n = reader.read())!= -1) {
            sb.append((char) n);
            while (((n = reader.read())!= -1 & (c = (char)n )!= '\n') ){
                sb.append(c);
            }
            Pattern r = Pattern.compile(block);
            Matcher m = r.matcher(sb.toString());
            if (m.matches()) {
                sb.delete(0, sb.length());
                flag = true;
            }
            if(flag){
                String line = sb.toString();
                Pattern r1 = Pattern.compile(name);
                Matcher m1 = r1.matcher(line);

                Pattern r2 = Pattern.compile(price);
                Matcher m2 = r2.matcher(line);

                Pattern r3 = Pattern.compile(quantity);
                Matcher m3 = r3.matcher(line);
                if (m1.matches() || m2.matches() || m3.matches()) {

                    int k = line.indexOf('=');
                    String name = line.substring(k + 1);
                    String[] strs = name.split(" ");

                    if (strs[0].equals("")) k = 1;
                    else k = 0;

                    if (strs[k] != null) {
                        if (m1.find(0))
                            p.setName(strs[k]);
                        else if (m2.find(0))
                            p.setPrice(Double.parseDouble(strs[k]));
                        else if (m3.find(0))
                            p.setQuantity(Integer.parseInt(strs[k]));
                        sb.delete(0, sb.length());
                    }
                    if (p.getQuantity() != -1 && p.getName() != null && Math.abs(p.getPrice()+1) > 1e-8) {
                        return p;
                    }
                }
            }
        }
        throw new IOException("Reading product failed");
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
    @Override
    public int read(CharBuffer target) throws IOException {
        return reader.read(target);
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return reader.read(cbuf);
    }

    @Override
    public long skip(long n) throws IOException {
        return reader.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public boolean markSupported() {
        return reader.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        reader.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        reader.reset();
    }

    @Override
    public long transferTo(Writer out) throws IOException {
        return reader.transferTo(out);
    }
}
