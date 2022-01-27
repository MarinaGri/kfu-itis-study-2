package ru.itis.regex;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URIParser {
    private String scheme = "([A-Za-z][A-Za-z0-9+-\\.]*)"; //group 1
    private String user = "([^\\/\\?#\\[\\]]*)";           //group 2

    private String IPv6 = "(?:(?:[0-9A-Fa-f]{1,4}:){0,7}(?:[0-9A-Fa-f]{1,4})|" +
            "(?:::(?:[0-9A-Fa-f]{1,4}:){0,5})(?:[0-9A-Fa-f]{1,4})|" +
            "(?:(?:[0-9A-Fa-f]{1,4}:){0,5}(?:[0-9A-Fa-f]{1,4})::)|" +
            "(?:[0-9A-Fa-f]{1,4}:){0,4}(?:[0-9A-Fa-f]{1,4})::(?:[0-9A-Fa-f]{1,4}:){0,4}(?:[0-9A-Fa-f]{1,4}))";

    public String host = "((?:[A-Za-z0-9][A-Za-z0-9-_]*\\.)*[A-Za-z0-9]+|\\[" + IPv6 +"\\])";   //group 3
    private String port = "([0-9]+)";                                                           //group 4
    public String authority = "((?:" + user + "@)?" + host + "(?::" + port + ")?)";             //group 5
    private String path  = "([\\w%/.]*)";                                                   //group 6
    private String query = "((?:[A-Za-z0-9%]*=[A-Za-z0-9%]*)*(?:&(?:[A-Za-z0-9%]*=[A-Za-z0-9%]*))?)"; //group 7
    private String fragment = "(\\S*)";                                                         //group 8
    public String URI = "^"+ scheme +"?(?::\\/\\/" + authority + ")?" + "\\/" + path + "(?:\\?" + query + ")?(?:#" + fragment + ")?$";

    public  String[] parse(String str) throws URISyntaxException {
        Pattern r = Pattern.compile(URI);
        Matcher m = r.matcher(str);
        String[] arr = new String[8];
        if(!m.matches()){
            throw new URISyntaxException(str,"Incorrect string");
        }
        else{
            if(m.find(0)){
                for(int i = 0; i < 8; i++){
                    arr[i] = m.group(i+1);
                }
            }
        }
        return arr;
    }
}
