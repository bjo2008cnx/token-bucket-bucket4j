package io.github.bucket4j;

import java.io.Serializable;

/**
 * Describes both result of consumption and tokens remaining in the bucket after consumption.
 *
 * @see Bucket#tryConsumeAndReturnRemaining(long)
 */
public class ConsumptionProbe implements Serializable {

    private final boolean consumed;
    private final long remainingTokens;
    private final long nanosToWaitForRefill;

    public static ConsumptionProbe consumed(long remainingTokens) {
        return new ConsumptionProbe(true, remainingTokens, 0);
    }

    public static ConsumptionProbe rejected(long remainingTokens, long nanosToWaitForRefill) {
        return new ConsumptionProbe(false, remainingTokens, nanosToWaitForRefill);
    }

    private ConsumptionProbe(boolean consumed, long remainingTokens, long nanosToWaitForRefill) {
        this.consumed = consumed;
        this.remainingTokens = Math.max(0L, remainingTokens);
        this.nanosToWaitForRefill = nanosToWaitForRefill;
    }

    /**
     * Flag describes result of consumption operation.
     *
     * @return true if tokens was consumed
     */
    public boolean isConsumed() {
        return consumed;
    }

    /**
     * Return the tokens remaining in the bucket
     *
     * @return the tokens remaining in the bucket
     */
    public long getRemainingTokens() {
        return remainingTokens;
    }

    /**
     * Returns zero if {@link #isConsumed()} returns true, else time in nanos which need to wait until requested amount of tokens will be refilled
     *
     * @return Zero if {@link #isConsumed()} returns true, else time in nanos which need to wait until requested amount of tokens will be refilled
     */
    public long getNanosToWaitForRefill() {
        return nanosToWaitForRefill;
    }

    @Override
    public String toString() {
        return "ConsumptionResult{" +
                "consumed=" + consumed +
                ", remainingTokens=" + remainingTokens +
                ", nanosToWaitForRefill=" + nanosToWaitForRefill +
                '}';
    }

}
