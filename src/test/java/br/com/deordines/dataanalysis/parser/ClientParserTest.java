package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Client;
import br.com.deordines.dataanalysis.stubs.ClientStub;
import br.com.deordines.dataanalysis.stubs.LineStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;

@RunWith(MockitoJUnitRunner.class)
public class ClientParserTest {

    @Before
    public void setup() {
        ReflectionTestUtils.setField(AParser.class, "DEFAULT_CHARACTER", "ç");
    }

    @Test
    public void should_returnClient_when_givenValidLine() {
        String line = LineStub.client1();
        Client tested = ClientParser.parse(line);
        Client expectateValue = ClientStub.client1();
        Assert.assertEquals(expectateValue.toString(), tested.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throwNoSuchElementException_when_givenInvalidLine() {
        String line = "any_data";
        ClientParser.parse(line);
    }
}