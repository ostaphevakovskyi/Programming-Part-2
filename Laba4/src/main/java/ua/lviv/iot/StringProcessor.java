package ua.lviv.iot;

import java.io.*;
import java.util.regex.*;

public class StringProcessor {
    private final String ttt = "https://github.com/lavanych22/JavaLabs/tree/master/Lab4,12 https://github.com/admin/JavaLabs/tree/maste";

    public String readInputText() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter String: ");
        return br.readLine();
    }

    public String processText(String inputText) {
        String regexp = "github.com/\\w+";
        StringBuilder finalString = new StringBuilder();

        Matcher m = Pattern.compile(regexp).matcher(inputText);

        while (m.find()) {
            String s = inputText.substring(m.start(), m.end());
            String final_string = s.replaceAll("github.com/", "");

            finalString.append(final_string);
            finalString.append(" ");
        }


        return finalString.toString();
    }

    public void showResult(String resultText) {
        System.out.println("\nYour string:\n" + resultText);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output"))) {
            writer.write(resultText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
