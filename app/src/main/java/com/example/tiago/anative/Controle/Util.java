package com.example.tiago.anative.Controle;

/**
 * Created by Tiago on 29/05/2016.
 */
public class Util {

    public static String encodeString(String palavra) {
        char one;
        StringBuffer n = new StringBuffer(palavra.length());
        for (int i = 0; i < palavra.length(); i++) {
            one = palavra.charAt(i);
            switch (one) {
                case ' ':
                    n.append('%');
                    n.append('2');
                    n.append('0');
                    break;
                default:
                    n.append(one);
            }
        }
        return n.toString();
    }
}
