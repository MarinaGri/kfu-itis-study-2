package ru.itis.regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LamodaParser {

    private final String name = "\"html-attribute-value\">((?:[А-Я][а-я]*)(?:\\s+[A-Z][a-z]*)?)|alt=\"((?:[А-Я][а-я]*)(?:\\s+[A-Z][a-z]*)?)";
    private final String price = "data-price<\\/span>=\"<span class=\"html-attribute-value\">(\\d+)|price&amp;quot;:&amp;quot;(\\d+\\s*\\d*)";
    private final String discount = "\"price\":\"(\\d+\\s*\\d*)\"|discount<\\/span>\"&gt;<\\/span>(\\d+\\s*\\d*)";
    private final String blockOfPrInfo ="((?:[A-Za-z][A-Za-z0-9+-\\.]*)(?::\\/\\/)?(?:(?:[A-Za-z0-9][A-Za-z0-9-_]*\\.)*[A-Za-z0-9]+)?(?::[0-9]+)?\\/p(?:\\/[A-Za-z0-9-]+)+\\/).*?external-link\" target=\"_blank\"";
    private final String reference = "([A-Za-z][A-Za-z0-9+-\\.]*)(?::\\/\\/)?((?:[A-Za-z0-9][A-Za-z0-9-_]*\\.)*[A-Za-z0-9]+)?(:([0-9]+))?\\/p(?:\\/[A-Za-z0-9-]+)+\\/";
    private final String scheme = "https";
    private final String host = "www.lamoda.ru";
    private final String port = "443";

    public void findProductInfo(String text, Collection<LamodaProduct> col){
        String ref, prName, prPrice;
        ref = prName = prPrice = null;

        Pattern r = Pattern.compile(blockOfPrInfo);
        Matcher m = r.matcher(text);

        while (m.find()){
            ref = createFullRef(m.group(1));
            Pattern rN = Pattern.compile(name);
            Matcher mN = rN.matcher(m.group());
            if(mN.find()) {
                if (mN.group(1) == null) {
                    prName = mN.group(2);
                }
                else {
                    prName = mN.group(1);
                }
            }

            Pattern pP = Pattern.compile(price);
            Matcher mP = pP.matcher(m.group());

            Pattern rD = Pattern.compile(discount);
            Matcher mD = rD.matcher(m.group());

            //if the product is discounted
            if(mD.find()) {
                if(mD.group(1) == null) {
                    prPrice = mD.group(2);
                }
                else {
                    prPrice = mD.group(1);
                }
            }
            else if (mP.find()){
                if(mP.group(1) == null){
                    prPrice = mP.group(2);
                }
                else {
                    prPrice = mP.group(1);
                }
            }

            LamodaProduct l = new LamodaProduct(prName, prPrice, ref);
            col.add(l);
        }
    }

    private String createFullRef(String str) {
        Pattern r = Pattern.compile(reference);
        Matcher m = r.matcher(str);
        if(m.find()){
            if(m.group(3) == null){
                int ind =  m.end(2);
                str = str.substring(0, ind) + ":" + port + str.substring(ind);
            }
            if(m.group(2) == null){
                int ind =  m.end(1);
                str = str.substring(0, ind) + host + str.substring(ind);
            }
            if(m.group(1) == null){
                str = scheme + "://" + str;
            }
        }
       return str;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter file path");
        //C:/Users/Marina/Desktop/kkk.html
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        List<String> lines = Files.readAllLines(Paths.get(path), UTF_8);
        StringBuilder builder = new StringBuilder();
        for (String s : lines) {
            builder.append(s);
        }
        String text = builder.toString();
        LamodaParser parser = new LamodaParser();
        HashSet<LamodaProduct> products = new HashSet<>();
        parser.findProductInfo(text, products);
        System.out.println(products.toString());
    }
}
