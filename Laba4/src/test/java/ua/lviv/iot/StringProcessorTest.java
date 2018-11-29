package ua.lviv.iot;

import static org.junit.Assert.*;

public class StringProcessorTest {

    @org.junit.Test
    public void processText() {
        StringProcessor processor = new StringProcessor();

        // first check
        assertEquals(processor.processText("https://github.com/lavanych22/JavaLabs/tree/master/Lab4,12 https://github.com/admin/JavaLabs/tree/maste"), "lavanych22 admin ");

        // second check
        assertEquals(processor.processText("bal/JavaLabs/tree/master/Lab4,12 https://github.com//JavaLabs/tree/maste"), "");

        // first check
        assertEquals(processor.processText("github.com/thirdUser/JavaLabs/tree/master/Lab4"), "thirdUser ");


    }
}