import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {

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
        NoSuchElementException noSuchElementException = assertThrows(
                NoSuchElementException.class, integer::get
        );
    }

}
