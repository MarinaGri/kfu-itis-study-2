package ru.itis.regex;

import java.util.Objects;

public class LamodaProduct {
    private String name;
    private String price;
    private String ref;

    public LamodaProduct(String name, String price, String ref){
        this.name = name;
        this.price = price;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRef() {
        return ref;
    }

    @Override
    public String toString() {
        return "\n" + name + " ("+ price + " p) \n"  + ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LamodaProduct that = (LamodaProduct) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(ref, that.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, ref);
    }
}
