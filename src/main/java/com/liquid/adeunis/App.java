package com.liquid.adeunis;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        String message = args[0];
        AdeunisFlow vs = new AdeunisFlow(message);
        vs.toString();

    }
}
