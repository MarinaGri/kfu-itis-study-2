package ru.itis.regex;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyURI {

    private String scheme;
    private String authority;
    private String user;
    private String host;
    private int port = -1;
    private String path;
    private String query;
    private String fragment;


    public MyURI(String str) throws NullPointerException, URISyntaxException {
        if(str == null) throw new NullPointerException();

        MyURIParser u = new MyURIParser();
        String[] attributes = u.parse(str);

        this.scheme = attributes[0];
        this.authority = attributes[1];
        this.user = attributes[2];
        this.host = attributes[3];
        if(attributes[4] != null){
            port = Integer.parseInt(attributes[4]);
        }
        this.path = attributes[5];
        this.query = attributes[6];
        this.fragment = attributes[7];
    }

    public String getScheme() {
        return scheme;
    }

    public String getAuthority() {
        return authority;
    }

    public String getUser() {
        return user;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public String getFragment() {
        return fragment;
    }

    private class MyURIParser {
        private String scheme = "([A-Za-z][A-Za-z0-9+.-]*)";                      //group 1
        private String user = "([\\w\\s-.!~*'():;&=+$,%]*)";                      //group 2
        private String IPv4 = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        private String IPv6 = "(?:(?:[0-9A-Fa-f]{1,4}:){0,7}(?:[0-9A-Fa-f]{1,4})|" +
                "(?:::(?:[0-9A-Fa-f]{1,4}:){0,5})(?:[0-9A-Fa-f]{1,4})|" +
                "(?:(?:[0-9A-Fa-f]{1,4}:){0,5}(?:[0-9A-Fa-f]{1,4})::)|" +
                "(?:(?:[0-9a-fA-f]{1,4}::(?:[0-9a-fA-f]{0,4}:){0,4}[0-9a-fA-f]{1,4})|" +
                "(?:(?:[0-9a-fA-f]{1,4}:){1,2}:(?:[0-9a-fA-f]{1,4}:){0,3}[0-9a-fA-f]{1,4})|" +
                "(?:(?:[0-9a-fA-f]{1,4}:){1,3}:(?:[0-9a-fA-f]{1,4}:){0,2}[0-9a-fA-f]{1,4})|" +
                "(?:(?:[0-9a-fA-f]{1,4}:){1,4}:(?:[0-9a-fA-f]{1,4}:){0,2}[0-9a-fA-f]{1,4})|" +
                "(?:(?:[0-9a-fA-f]{1,4}:){1,4}:(?:[0-9a-fA-f]{1,4}:)?[0-9a-fA-f]{1,4})|" +
                "(?:(?:[0-9a-fA-f]{1,4}:){1,5}:[0-9a-fA-f]{1,4})))";

        private String host = "((?:[A-Za-z0-9][A-Za-z0-9-_]*\\.)*[A-Za-z0-9]+(?:[A-Za-z][A-Za-z0-9]*)" +       //group 3
                "|(?:\\[" + IPv6 +"])|" + IPv4 +")";
        private String port = "([0-9]+)";                                                                      //group 4
        private String authority = "((?:" + user + "@)?" + "(?:" + host + ")?(?::" + port + ")?)";             //group 5
        private String path  = "([\\w%/.:@&=+$,\\\\;-]*)";                                                     //group 6
        private String query = "((?:[\\w.~%!$'()*+;,:-]*=[\\w.~%!$'()*+;,:-]*)*" +                             //group 7
                "(?:&(?:[\\w.~%!$'()*+;,:-]*=[\\w.~%!$'()*+;,:-]*))?)";
        private String fragment = "(.*)";                                                                      //group 8
        private String URI = "^(?:"+ scheme +":)?(?://)?(?:" + authority + ")?" + "(?:/)?" + path +
                "(?:\\?" + query + ")?(?:#" + fragment + ")?$";

        public String[] parse(String str) throws URISyntaxException {
            Pattern r = Pattern.compile(URI);
            Matcher m = r.matcher(str);
            String[] arr = new String[8];
            if(!m.matches()){
                throw new URISyntaxException(str,"Incorrect string");
            }
            else{
                for(int i = 0; i < 8; i++){
                    if(m.find(0)){
                        arr[i] = m.group(i+1);
                    }
                }
            }
            return arr;
        }
    }
}
