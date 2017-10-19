package jdotest.model.database;

import java.time.Instant;
import static org.junit.Assert.assertTrue;

public class TestUtils {
    public static void assertInRange(Instant before, Instant between, Instant after) {
        before = before.minusSeconds(1);
        after = after.plusSeconds(1);
        assertTrue(before+" > "+between, between.isAfter(before) || between.equals(before));
        assertTrue(after+" < "+between, between.isBefore(after) || between.equals(after));
    }
}
