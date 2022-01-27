package ru.itis.hw_12;

import java.io.IOException;
import java.io.Writer;

public class ProductWriter extends Writer {
    private Writer writer;

    public ProductWriter(Writer writer){
        if(writer != null){
            this.writer = writer;
        }
    }


    public void writeProduct(Product product) throws IOException {
        writer.write("Product {\n");
        writer.write("    name = " + product.getName() + "\n");
        writer.write("    price = " + product.getPrice() + "\n");
        writer.write("    quantity = " + product.getQuantity() + "\n}");
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
    @Override
    public void write(int c) throws IOException {
        writer.write(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        writer.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        writer.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        writer.write(str, off, len);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return writer.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return writer.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return writer.append(c);
    }


}
