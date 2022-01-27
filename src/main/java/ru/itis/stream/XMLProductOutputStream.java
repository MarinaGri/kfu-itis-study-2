package ru.itis.stream;

import ru.itis.hw_12.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;

public class XMLProductOutputStream extends OutputStream{
    private OutputStream os;

    public XMLProductOutputStream(OutputStream os){
        if(os == null) throw new IllegalArgumentException("OutputStream should not be null");
        this.os = os;
    }

    public void writeProduct(Product p) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Product.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(p, os);
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