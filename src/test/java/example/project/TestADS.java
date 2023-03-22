package example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import example.project.domain.Scenario;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class TestADS {

    @Test
    public void testPrintPath() {
        // delete the above line and implement this properly to test if ads.printPath() prints the ADSPath.
        // hint: see testHelloWorld() in our previous lab session.
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ADS ads = new ADS("some path");
        ads.printPath();
        assertEquals("some path", outContent.toString().trim());
    }

    @Test
    public void testPredict() {
        ADS ads = mock();
        Scenario scenario = new Scenario("special scenario leading to the prediction of [0, 0]");
        when(ads.predict(scenario)).thenReturn(Arrays.asList(0,0));

        List<Object> prediction = ads.predict(scenario); // the prediction will be null since the ADS is dummy.
        assertEquals(Arrays.asList(0, 0), prediction);
        verify(ads, times(1)).predict(scenario);
        verify(ads, times(0)).printPath();
    }

}