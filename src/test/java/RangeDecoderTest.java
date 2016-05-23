import nmccabe.RangeDecoder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RangeDecoderTest {
    byte[] allBytes;

    @Before
    public void setUp() {
        allBytes = "This is a file that contains text to read part of in order to fulfill a 206.\n".getBytes();
    }

    @Test
    public void decodesSingleDigitRanges() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=0-4");
        assertEquals(0, decoder.lowerBound());
        assertEquals(5, decoder.upperBound());
    }

    @Test
    public void decodesDoubleDigitRanges() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=10-44");
        assertEquals(10, decoder.lowerBound());
        assertEquals(45, decoder.upperBound());
    }

    @Test
    public void determinesIfBothRangeBoundsGiven() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=0-4");
        assertTrue(decoder.bothUpperAndLowerBoundsSpecified());
    }

    @Test
    public void determinesIfLowerBoundMissing() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=-6");
        assertFalse(decoder.bothUpperAndLowerBoundsSpecified());
    }

    @Test
    public void determinesIfUpperBoundMissing() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=4-");
        assertFalse(decoder.bothUpperAndLowerBoundsSpecified());
    }

    @Test
    public void determinesMissingLowerBound() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=-6");
        assertTrue(decoder.lowerBoundMissing());
    }

    @Test
    public void missingLowerBoundDoesntThrowFalsePositives() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=4-");
        assertFalse(decoder.lowerBoundMissing());
        RangeDecoder otherDecoder = new RangeDecoder(allBytes, "bytes=0-4");
        assertFalse(otherDecoder.lowerBoundMissing());
    }

    @Test
    public void determinesMissingUpperBound() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=4-");
        assertTrue(decoder.upperBoundMissing());
    }

    @Test
    public void missingUpperBoundDoesntThrowFalsePositives() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=-6");
        assertFalse(decoder.upperBoundMissing());
        RangeDecoder otherDecoder = new RangeDecoder(allBytes, "bytes=0-4");
        assertFalse(otherDecoder.upperBoundMissing());
    }
    @Test
    public void decodesUnspecifiedLowerBound() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=-6");
        int lower = decoder.lowerBound();
        int upper = decoder.upperBound();
        assertEquals(71, lower);
        assertEquals(77, upper);
    }

    @Test
    public void decodesUnspecifiedUpperBound() throws Exception {
        RangeDecoder decoder = new RangeDecoder(allBytes, "bytes=4-");
        assertEquals(4, decoder.lowerBound());
        assertEquals(77, decoder.upperBound());
    }
}
