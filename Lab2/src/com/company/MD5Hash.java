package com.company;

public class MD5Hash {
    private final String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    private final long[] result = {0x67452301L, 0xefcdab89L, 0x98badcfeL, 0x10325476L};
    private final int[][] S = {{7, 12, 17, 22},
            {5, 9, 14, 20},
            {4, 11, 16, 23},
            {6, 10, 15, 21}};

    public static long b2iu(byte b) {
        return b < 0 ? b & 0x7F + 128 : b;
    }

    private static long F(long x, long y, long z) {
        return (x & y) | ((~x) & z);
    }

    private static long G(long x, long y, long z) {
        return (x & z) | (y & (~z));
    }

    private static long H(long x, long y, long z) {
        return x ^ y ^ z;
    }

    private static long I(long x, long y, long z) {
        return y ^ (x | (~z));
    }

    private static long FF(long a, long b, long c, long d, long x, long s, long ac) {
        a += (F(b, c, d) & 0xFFFFFFFFL) + x + ac;
        a = ((a & 0xFFFFFFFFL) << s) | ((a & 0xFFFFFFFFL) >>> (32 - s));
        a += b;
        return (a & 0xFFFFFFFFL);
    }

    private static long GG(long a, long b, long c, long d, long x, long s, long ac) {
        a += (G(b, c, d) & 0xFFFFFFFFL) + x + ac;
        a = ((a & 0xFFFFFFFFL) << s) | ((a & 0xFFFFFFFFL) >>> (32 - s));
        a += b;
        return (a & 0xFFFFFFFFL);
    }

    private static long HH(long a, long b, long c, long d, long x, long s, long ac) {
        a += (H(b, c, d) & 0xFFFFFFFFL) + x + ac;
        a = ((a & 0xFFFFFFFFL) << s) | ((a&0xFFFFFFFFL) >>> (32 - s));
        a += b;
        return (a & 0xFFFFFFFFL);
    }

    private static long II(long a, long b, long c, long d, long x, long s, long ac) {
        a += (I(b, c, d) & 0xFFFFFFFFL) + x + ac;
        a = ((a & 0xFFFFFFFFL) << s) | ((a&0xFFFFFFFFL) >>> (32 - s));
        a += b;
        return (a & 0xFFFFFFFFL);
    }

    private long[] divGroup (byte[] inputBytes, int index ) {
        long [] temp = new long[16];
        for(int i = 0; i < 16; i++) {
            temp[i] = b2iu(inputBytes[4 * i + index])|
                    (b2iu(inputBytes[4 * i + 1 + index])) << 8|
                    (b2iu(inputBytes[4 * i + 2 + index])) << 16|
                    (b2iu(inputBytes[4 * i + 3 + index])) << 24;
        }
        return temp;
    }

    String hashing(String inputStr) {
        StringBuilder resStr = new StringBuilder();
        byte[] inputBytes = inputStr.getBytes();
        byte[] tempBytes = new byte[64];
        long[] groups;
        long temp;
        int byteLength = inputBytes.length;
        int rest  = byteLength % 64;
        int groupCount = byteLength / 64;

        for (int i = 0; i < groupCount; i++) {
            groups = divGroup(inputBytes, i * 64);
            transformation(groups);
        }

        if (rest <= 56) {
            tempBytes[rest] = (byte) (1<<7);
            long len = ((long) byteLength << 3);
            System.arraycopy(inputBytes, byteLength - rest, tempBytes, 0, rest);

            for (int i = 1; i < 56 - rest; i++) {
                tempBytes[rest + i] = 0;
            }

            for (int i = 0; i < 8; i++) {
                tempBytes[56 + i] = (byte)(len & 0xFFL);
                len = len >> 8;
            }
        } else {
            tempBytes[rest] = (byte) (1<<7);
            long length = ((long) byteLength << 3);
            tempBytes[rest] = (byte) (1<<7);

            System.arraycopy(inputBytes, byteLength - rest, tempBytes, 0, rest);

            for (int i = rest+1; i < 64; i++)
                tempBytes[i] = 0;

            groups = divGroup(tempBytes,0);
            transformation (groups);

            for (int i = 0; i < 56; i++) {
                tempBytes[i] = 0;
            }

            for (int i = 0; i < 8; i++){
                tempBytes[56 + i] = (byte)(length & 0xFFL);
                length = length >> 8;
            }
        }

        groups = divGroup(tempBytes,0);
        transformation (groups);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                temp = result[i] & 0x0FL;
                String a = hex[(int)(temp)];
                result[i] = result[i] >> 4;
                temp = result[i] & 0x0FL;
                resStr.append(hex[(int) (temp)]).append(a);
                result[i] = result[i] >> 4;
            }
        }

        return resStr.toString();
    }

    private void transformation(long[] groups) {
        long a = result[0];
        long b = result[1];
        long c = result[2];
        long d = result[3];

        a = FF(a, b, c, d, groups[0], S[0][0], 0xd76aa478L); /* 1 */
        d = FF(d, a, b, c, groups[1], S[0][1], 0xe8c7b756L); /* 2 */
        c = FF(c, d, a, b, groups[2], S[0][2], 0x242070dbL); /* 3 */
        b = FF(b, c, d, a, groups[3], S[0][3], 0xc1bdceeeL); /* 4 */
        a = FF(a, b, c, d, groups[4], S[0][0], 0xf57c0fafL); /* 5 */
        d = FF(d, a, b, c, groups[5], S[0][1], 0x4787c62aL); /* 6 */
        c = FF(c, d, a, b, groups[6], S[0][2], 0xa8304613L); /* 7 */
        b = FF(b, c, d, a, groups[7], S[0][3], 0xfd469501L); /* 8 */
        a = FF(a, b, c, d, groups[8], S[0][0], 0x698098d8L); /* 9 */
        d = FF(d, a, b, c, groups[9], S[0][1], 0x8b44f7afL); /* 10 */
        c = FF(c, d, a, b, groups[10], S[0][2], 0xffff5bb1L); /* 11 */
        b = FF(b, c, d, a, groups[11], S[0][3], 0x895cd7beL); /* 12 */
        a = FF(a, b, c, d, groups[12], S[0][0], 0x6b901122L); /* 13 */
        d = FF(d, a, b, c, groups[13], S[0][1], 0xfd987193L); /* 14 */
        c = FF(c, d, a, b, groups[14], S[0][2], 0xa679438eL); /* 15 */
        b = FF(b, c, d, a, groups[15], S[0][3], 0x49b40821L); /* 16 */

        a = GG(a, b, c, d, groups[1], S[1][0], 0xf61e2562L); /* 17 */
        d = GG(d, a, b, c, groups[6], S[1][1], 0xc040b340L); /* 18 */
        c = GG(c, d, a, b, groups[11], S[1][2], 0x265e5a51L); /* 19 */
        b = GG(b, c, d, a, groups[0], S[1][3], 0xe9b6c7aaL); /* 20 */
        a = GG(a, b, c, d, groups[5], S[1][0], 0xd62f105dL); /* 21 */
        d = GG(d, a, b, c, groups[10], S[1][1], 0x2441453L); /* 22 */
        c = GG(c, d, a, b, groups[15], S[1][2], 0xd8a1e681L); /* 23 */
        b = GG(b, c, d, a, groups[4], S[1][3], 0xe7d3fbc8L); /* 24 */
        a = GG(a, b, c, d, groups[9], S[1][0], 0x21e1cde6L); /* 25 */
        d = GG(d, a, b, c, groups[14], S[1][1], 0xc33707d6L); /* 26 */
        c = GG(c, d, a, b, groups[3], S[1][2], 0xf4d50d87L); /* 27 */
        b = GG(b, c, d, a, groups[8], S[1][3], 0x455a14edL); /* 28 */
        a = GG(a, b, c, d, groups[13], S[1][0], 0xa9e3e905L); /* 29 */
        d = GG(d, a, b, c, groups[2], S[1][1], 0xfcefa3f8L); /* 30 */
        c = GG(c, d, a, b, groups[7], S[1][2], 0x676f02d9L); /* 31 */
        b = GG(b, c, d, a, groups[12], S[1][3], 0x8d2a4c8aL); /* 32 */

        a = HH(a, b, c, d, groups[5], S[2][0], 0xfffa3942L); /* 33 */
        d = HH(d, a, b, c, groups[8], S[2][1], 0x8771f681L); /* 34 */
        c = HH(c, d, a, b, groups[11], S[2][2], 0x6d9d6122L); /* 35 */
        b = HH(b, c, d, a, groups[14], S[2][3], 0xfde5380cL); /* 36 */
        a = HH(a, b, c, d, groups[1], S[2][0], 0xa4beea44L); /* 37 */
        d = HH(d, a, b, c, groups[4], S[2][1], 0x4bdecfa9L); /* 38 */
        c = HH(c, d, a, b, groups[7], S[2][2], 0xf6bb4b60L); /* 39 */
        b = HH(b, c, d, a, groups[10], S[2][3], 0xbebfbc70L); /* 40 */
        a = HH(a, b, c, d, groups[13], S[2][0], 0x289b7ec6L); /* 41 */
        d = HH(d, a, b, c, groups[0], S[2][1], 0xeaa127faL); /* 42 */
        c = HH(c, d, a, b, groups[3], S[2][2], 0xd4ef3085L); /* 43 */
        b = HH(b, c, d, a, groups[6], S[2][3], 0x4881d05L); /* 44 */
        a = HH(a, b, c, d, groups[9], S[2][0], 0xd9d4d039L); /* 45 */
        d = HH(d, a, b, c, groups[12], S[2][1], 0xe6db99e5L); /* 46 */
        c = HH(c, d, a, b, groups[15], S[2][2], 0x1fa27cf8L); /* 47 */
        b = HH(b, c, d, a, groups[2], S[2][3], 0xc4ac5665L); /* 48 */

        a = II(a, b, c, d, groups[0], S[3][0], 0xf4292244L); /* 49 */
        d = II(d, a, b, c, groups[7], S[3][1], 0x432aff97L); /* 50 */
        c = II(c, d, a, b, groups[14], S[3][2], 0xab9423a7L); /* 51 */
        b = II(b, c, d, a, groups[5], S[3][3], 0xfc93a039L); /* 52 */
        a = II(a, b, c, d, groups[12], S[3][0], 0x655b59c3L); /* 53 */
        d = II(d, a, b, c, groups[3], S[3][1], 0x8f0ccc92L); /* 54 */
        c = II(c, d, a, b, groups[10], S[3][2], 0xffeff47dL); /* 55 */
        b = II(b, c, d, a, groups[1], S[3][3], 0x85845dd1L); /* 56 */
        a = II(a, b, c, d, groups[8], S[3][0], 0x6fa87e4fL); /* 57 */
        d = II(d, a, b, c, groups[15], S[3][1], 0xfe2ce6e0L); /* 58 */
        c = II(c, d, a, b, groups[6], S[3][2], 0xa3014314L); /* 59 */
        b = II(b, c, d, a, groups[13], S[3][3], 0x4e0811a1L); /* 60 */
        a = II(a, b, c, d, groups[4], S[3][0], 0xf7537e82L); /* 61 */
        d = II(d, a, b, c, groups[11], S[3][1], 0xbd3af235L); /* 62 */
        c = II(c, d, a, b, groups[2], S[3][2], 0x2ad7d2bbL); /* 63 */
        b = II(b, c, d, a, groups[9], S[3][3], 0xeb86d391L); /* 64 */

        result[0] += a;
        result[1] += b;
        result[2] += c;
        result[3] += d;

        result[0] = result[0] & 0xFFFFFFFFL;
        result[1] = result[1] & 0xFFFFFFFFL;
        result[2] = result[2] & 0xFFFFFFFFL;
        result[3] = result[3] & 0xFFFFFFFFL;
    }
}
