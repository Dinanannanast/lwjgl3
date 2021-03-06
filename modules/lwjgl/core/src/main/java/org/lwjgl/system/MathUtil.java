/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.system;

/**
 * Math utility class.
 *
 * <p>Method names in this class are prefixed with {@code math} to avoid ambiguities when used with static imports.</p>
 */
public final class MathUtil {

    private MathUtil() {
    }

    /**
     * Returns true if the specified integer {@code value} is a power-of-two
     * number.
     *
     * @param value the value to test
     *
     * @return true if the value if a power-of-two number.
     */
    public static boolean mathIsPoT(int value) {
        return Integer.bitCount(value) == 1;
    }

    /**
     * Rounds the specified integer {@code value} up to the next power-of-two
     * number. The returned value will be equal to {@code value} if it already
     * is a power-of-two number.
     *
     * @param value the value to round-up. Must be a number between {@code 1} and
     *              <code>1 &lt;&lt; 30</code>.
     *
     * @return the power-of-two rounded value
     */
    public static int mathRoundPoT(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    public static boolean mathHasZeroByte(int value) {
        return ((value - 0x01010101) & ~value & 0x80808080) != 0;
    }

    public static boolean mathHasZeroByte(long value) {
        return ((value - 0x0101010101010101L) & ~value & 0x8080808080808080L) != 0L;
    }

    public static boolean mathHasZeroShort(int value) {
        return (value & 0xFFFF) == 0 || (value >>> 16) == 0;
    }

    public static boolean mathHasZeroShort(long value) {
        return ((value - 0x0001000100010001L) & ~value & 0x8000800080008000L) != 0L;
    }

    /**
     * Returns as the most significant 64 bits of the 128-bit product of two {@code uint64_t} factors.
     *
     * @param x the first value
     * @param y the second value
     *
     * @return the result
     */
    public static long mathMultiplyHighU64(long x, long y) {
        long x0 = x & 0xFFFF_FFFFL;
        long x1 = x >>> 32;
        long y0 = y & 0xFFFF_FFFFL;
        long y1 = y >>> 32;

        long t = x1 * y0 + ((x0 * y0) >>> 32);

        return x1 * y1 + (t >>> 32) + (((t & 0xFFFF_FFFFL) + x0 * y1) >>> 32);
    }

    /**
     * Returns as the most significant 64 bits of the 128-bit product of two {@code int64_t} factors.
     *
     * @param x the first value
     * @param y the second value
     *
     * @return the result
     */
    public static long mathMultiplyHighS64(long x, long y) {
        long x0 = x & 0xFFFF_FFFFL;
        long x1 = x >> 32;
        long y0 = y & 0xFFFF_FFFFL;
        long y1 = y >> 32;

        long t = x1 * y0 + ((x0 * y0) >>> 32);

        return x1 * y1 + (t >> 32) + (((t & 0xFFFF_FFFFL) + x0 * y1) >> 32);
    }

}