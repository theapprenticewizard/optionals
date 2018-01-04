import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class OptionalTest {

    @Test
    public void testEmptyOptionalCreation() {
        Optional<String> optional = Optional.empty();
        assertFalse(optional.isPresent());
    }
}
