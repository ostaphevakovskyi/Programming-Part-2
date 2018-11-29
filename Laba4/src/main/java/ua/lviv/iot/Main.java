package ua.lviv.iot;

import java.io.IOException;

public class Main {
    private static final String STR = "https://github.com/lavanych22/JavaLabs/tree/master/Lab4,12 https://github.com/admin/JavaLabs/tree/maste";

    public static void main(String[] args) {
        StringProcessor stringProcessor = new StringProcessor();
        String finalStr = "";

        try {
            finalStr = stringProcessor.readInputText();
        } catch(IOException e) {
            e.printStackTrace();
        }

        finalStr = stringProcessor.processText(finalStr);

        stringProcessor.showResult(finalStr);
    }
}
