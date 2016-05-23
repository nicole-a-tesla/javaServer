package nmccabe;

import java.util.Objects;

public class RangeDecoder {
    private final String rangeString;
    private final String[] rangeArray;
    private final byte[] allBytes;
    private int lowerBound;
    private int upperBound;

    public RangeDecoder(byte[] allBytes, String rangeString) {
        this.rangeString = rangeString;
        this.rangeArray = getRangeAsArray();
        this.allBytes = allBytes;
        setBoundsAccordingToRangeType();
    }

    public int lowerBound() {
        return this.lowerBound;
    }

    public int upperBound() {
        return this.upperBound;
    }

    public int getNormalLowerBound() {
        String lowerBoundString =  rangeArray[0];
        return asInteger(lowerBoundString);
    }

    public int getNormalUpperBound() {
        String upperBoundString = rangeArray[1];
        return Integer.parseInt(upperBoundString) + 1;
    }

    private String[] getRangeAsArray() {
        return rangeString.split("=")[1].split("-");
    }

    private void setBoundsAccordingToRangeType() {
        if (bothUpperAndLowerBoundsSpecified()) {
            this.lowerBound = getNormalLowerBound();
            this.upperBound = getNormalUpperBound();
        }
        else if (lowerBoundMissing()) {
            int requestedBytesCount = asInteger(rangeArray[1]);
            this.lowerBound = getLowerBoundForUnspecifiedLowerBound(requestedBytesCount);
            this.upperBound = getUpperBoundForMissingBoundCondition();
        }
        else if (upperBoundMissing()) {
            this.lowerBound = getNormalLowerBound();
            this.upperBound = getUpperBoundForMissingBoundCondition();
        }
    }

    private int getUpperBoundForMissingBoundCondition() {
        return this.allBytes.length;
    }

    private int getLowerBoundForUnspecifiedLowerBound(int requestedByteCount) {
        return this.allBytes.length - requestedByteCount;
    }

    public boolean lowerBoundMissing() {
        return Objects.equals(rangeArray[0], "");
    }

    public boolean upperBoundMissing() {
        return rangeArray.length == 1;
    }

    public boolean bothUpperAndLowerBoundsSpecified() {
        return rangeArray.length == 2 && isInteger(rangeArray[0]);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private int asInteger(String str) {
        return Integer.parseInt(str);
    }

}
