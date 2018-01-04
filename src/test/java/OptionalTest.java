import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Based on tutorial found at -> <a href="http://www.baeldung.com/java-optional">baeldung.com/java-optional</a>
 * */
public class OptionalTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Test
    public void testEmptyOptionalCreation() {
        Optional<String> optional = Optional.empty();
        assertFalse(optional.isPresent());
        assertThrows(NoSuchElementException.class, optional::get);
    }

    @Test
    public void testCreateOptionalWithOf() {
        Optional<String> optional = Optional.of("foo");
        assertEquals("foo", optional.get());
    }

    @Test
    public void testCreateOptionalWithOfThrowsExceptionWhenNull() {
        try {
            Optional<String> optional = null;
            System.out.println(optional.get());
            fail("test failed");
        } catch (NullPointerException ex) {}
    }

    @Test
    public void testCreateOptionalWithNullable() {
        Optional<Integer> integer = Optional.ofNullable(null);
        assertThrows(NoSuchElementException.class, integer::get);
    }

    @Test
    public void testPrintPresent() throws UnsupportedEncodingException {
        System.setOut(new PrintStream(outContent));
        Optional<String> optional = Optional.of("bazinga!");
        optional.ifPresent(optionOfS -> System.out.print(optionOfS.toUpperCase()));
        assertThat("BAZINGA!", is(outContent.toString("utf-8")));
        System.setOut(System.out);
    }

    @Test
    public void testOrElse() {
        Integer num = (Integer) Optional.ofNullable(null).orElse(3);
        assertThat(num, is(3));
    }

    @Test
    public void testNotNeedingToCastAString() {
        String nullValue = null;
        String value = Optional.ofNullable(nullValue).orElse("worked!");
        assertThat(value, is("worked!"));
    }

    @Test
    public void testOrElseGet() {
        System.setOut(new PrintStream(outContent));
        String value = Optional.ofNullable("text").orElseGet(this::getValue);
        assertThat(outContent.toString(), is(""));
        assertThat(value, is("text"));
        System.setOut(System.out);
    }

    @Test
    public void testOrElseWithSideEffect() {
        System.setOut(new PrintStream(outContent));
        String value = Optional.ofNullable("text").orElse(getValue());
        assertThat(outContent.toString(), is("printed"));
        assertThat(value, is("text"));
        System.setOut(System.out);
    }

    @Test
    public void testOrElseGetWithMethodInvocation() {
        String nullValue = null;
        String value = Optional.ofNullable(nullValue).orElseGet(this::getValue);
        assertThat(value, is("worked!"));
    }

    @Test
    public void testOrElseThrowWillThrowException() {
        String nullValue = null;
        try {
            String value = Optional.ofNullable(nullValue).orElseThrow(RuntimeException::new);
            fail("test did not throw exception");
        } catch (RuntimeException e) { }
    }

    @Test // NOTE: this is the preferred way to access an optional
    public void testReturnWithFilter() {
        Optional<Thing> thingOptional = Optional.of(new Thing(2));
        boolean present = thingOptional.filter( thing -> thing.amount > 2).isPresent();
        assertFalse(present);

        boolean presentOtherCondition = thingOptional.filter( thing -> thing.amount < 10).isPresent();
        assertTrue(presentOtherCondition);
    }

    private String getValue() {
        System.out.print("printed");
        return "worked!";
    }

    public class Thing {
        private int amount;

        public Thing() { }

        public Thing(int amount) {
            this.amount = amount;
        }

        public int getAmmount() {
            return amount;
        }

        public void setAmmount(int ammount) {
            this.amount = ammount;
        }
    }
}
