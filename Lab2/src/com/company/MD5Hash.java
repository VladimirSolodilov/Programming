package com.company;

public class MD5Hash {
    private final String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"}; //16-ричная ССЧ

    //Стандартные параметры
    private final long[] result = {0x67452301L, 0xefcdab89L, 0x98badcfeL, 0x10325476L};

    //Числа, используещиеся при трансформации в раундах
    private final int[][] S = {{7, 12, 17, 22},
            {5, 9, 14, 20},
            {4, 11, 16, 23},
            {6, 10, 15, 21}};

    //Метод для хранения знакового бита информации
    public static long b2iu(byte b) {
        return b < 0 ? b & 0x7F + 128 : b;
    }

    //Логические операции
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

    //Операции преобразования исходных значений
    //0xFFFFFFFFL - 32 бита со знаком
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
        a = ((a & 0xFFFFFFFFL) << s) | ((a & 0xFFFFFFFFL) >>> (32 - s));
        a += b;

        return (a & 0xFFFFFFFFL);
    }

    private static long II(long a, long b, long c, long d, long x, long s, long ac) {
        a += (I(b, c, d) & 0xFFFFFFFFL) + x + ac;
        a = ((a & 0xFFFFFFFFL) << s) | ((a&0xFFFFFFFFL) >>> (32 - s));
        a += b;

        return (a & 0xFFFFFFFFL);
    }

    private long[] divGroups (byte[] inputBytes, int index) {
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

        //Обработка группы
        for (int i = 0; i < groupCount; i++) {
            groups = divGroups(inputBytes, i * 64);
            transformation(groups);
        }

        //Обработка хвоста после группировки
        if (rest <= 56) {
            tempBytes[rest] = (byte) (1 << 7);
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
            tempBytes[rest] = (byte) (1 << 7);
            long length = ((long) byteLength << 3);
            tempBytes[rest] = (byte) (1 << 7);

            System.arraycopy(inputBytes, byteLength - rest, tempBytes, 0, rest);

            for (int i = rest + 1; i < 64; i++)
                tempBytes[i] = 0;

            groups = divGroups(tempBytes,0);
            transformation (groups);

            for (int i = 0; i < 56; i++) {
                tempBytes[i] = 0;
            }

            for (int i = 0; i < 8; i++) {
                tempBytes[56 + i] = (byte)(length & 0xFFL);
                length = length >> 8;
            }
        }

        groups = divGroups(tempBytes,0);
        transformation(groups);

        //Преобразование полученного хэша в шестнадцаричный вид
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

    //Каждая группа состоит из 512 бит информации
    private void transformation(long[] groups) {
        long a = result[0];
        long b = result[1];
        long c = result[2];
        long d = result[3];

        a = FF(a, b, c, d, groups[0], S[0][0], 0xd76aa478L);
        d = FF(d, a, b, c, groups[1], S[0][1], 0xe8c7b756L);
        c = FF(c, d, a, b, groups[2], S[0][2], 0x242070dbL);
        b = FF(b, c, d, a, groups[3], S[0][3], 0xc1bdceeeL);
        a = FF(a, b, c, d, groups[4], S[0][0], 0xf57c0fafL);
        d = FF(d, a, b, c, groups[5], S[0][1], 0x4787c62aL);
        c = FF(c, d, a, b, groups[6], S[0][2], 0xa8304613L);
        b = FF(b, c, d, a, groups[7], S[0][3], 0xfd469501L);
        a = FF(a, b, c, d, groups[8], S[0][0], 0x698098d8L);
        d = FF(d, a, b, c, groups[9], S[0][1], 0x8b44f7afL);
        c = FF(c, d, a, b, groups[10], S[0][2], 0xffff5bb1L);
        b = FF(b, c, d, a, groups[11], S[0][3], 0x895cd7beL);
        a = FF(a, b, c, d, groups[12], S[0][0], 0x6b901122L);
        d = FF(d, a, b, c, groups[13], S[0][1], 0xfd987193L);
        c = FF(c, d, a, b, groups[14], S[0][2], 0xa679438eL);
        b = FF(b, c, d, a, groups[15], S[0][3], 0x49b40821L);

        a = GG(a, b, c, d, groups[1], S[1][0], 0xf61e2562L);
        d = GG(d, a, b, c, groups[6], S[1][1], 0xc040b340L);
        c = GG(c, d, a, b, groups[11], S[1][2], 0x265e5a51L);
        b = GG(b, c, d, a, groups[0], S[1][3], 0xe9b6c7aaL);
        a = GG(a, b, c, d, groups[5], S[1][0], 0xd62f105dL);
        d = GG(d, a, b, c, groups[10], S[1][1], 0x2441453L);
        c = GG(c, d, a, b, groups[15], S[1][2], 0xd8a1e681L);
        b = GG(b, c, d, a, groups[4], S[1][3], 0xe7d3fbc8L);
        a = GG(a, b, c, d, groups[9], S[1][0], 0x21e1cde6L);
        d = GG(d, a, b, c, groups[14], S[1][1], 0xc33707d6L);
        c = GG(c, d, a, b, groups[3], S[1][2], 0xf4d50d87L);
        b = GG(b, c, d, a, groups[8], S[1][3], 0x455a14edL);
        a = GG(a, b, c, d, groups[13], S[1][0], 0xa9e3e905L);
        d = GG(d, a, b, c, groups[2], S[1][1], 0xfcefa3f8L);
        c = GG(c, d, a, b, groups[7], S[1][2], 0x676f02d9L);
        b = GG(b, c, d, a, groups[12], S[1][3], 0x8d2a4c8aL);

        a = HH(a, b, c, d, groups[5], S[2][0], 0xfffa3942L);
        d = HH(d, a, b, c, groups[8], S[2][1], 0x8771f681L);
        c = HH(c, d, a, b, groups[11], S[2][2], 0x6d9d6122L);
        b = HH(b, c, d, a, groups[14], S[2][3], 0xfde5380cL);
        a = HH(a, b, c, d, groups[1], S[2][0], 0xa4beea44L);
        d = HH(d, a, b, c, groups[4], S[2][1], 0x4bdecfa9L);
        c = HH(c, d, a, b, groups[7], S[2][2], 0xf6bb4b60L);
        b = HH(b, c, d, a, groups[10], S[2][3], 0xbebfbc70L);
        a = HH(a, b, c, d, groups[13], S[2][0], 0x289b7ec6L);
        d = HH(d, a, b, c, groups[0], S[2][1], 0xeaa127faL);
        c = HH(c, d, a, b, groups[3], S[2][2], 0xd4ef3085L);
        b = HH(b, c, d, a, groups[6], S[2][3], 0x4881d05L);
        a = HH(a, b, c, d, groups[9], S[2][0], 0xd9d4d039L);
        d = HH(d, a, b, c, groups[12], S[2][1], 0xe6db99e5L);
        c = HH(c, d, a, b, groups[15], S[2][2], 0x1fa27cf8L);
        b = HH(b, c, d, a, groups[2], S[2][3], 0xc4ac5665L);

        a = II(a, b, c, d, groups[0], S[3][0], 0xf4292244L);
        d = II(d, a, b, c, groups[7], S[3][1], 0x432aff97L);
        c = II(c, d, a, b, groups[14], S[3][2], 0xab9423a7L);
        b = II(b, c, d, a, groups[5], S[3][3], 0xfc93a039L);
        a = II(a, b, c, d, groups[12], S[3][0], 0x655b59c3L);
        d = II(d, a, b, c, groups[3], S[3][1], 0x8f0ccc92L);
        c = II(c, d, a, b, groups[10], S[3][2], 0xffeff47dL);
        b = II(b, c, d, a, groups[1], S[3][3], 0x85845dd1L);
        a = II(a, b, c, d, groups[8], S[3][0], 0x6fa87e4fL);
        d = II(d, a, b, c, groups[15], S[3][1], 0xfe2ce6e0L);
        c = II(c, d, a, b, groups[6], S[3][2], 0xa3014314L);
        b = II(b, c, d, a, groups[13], S[3][3], 0x4e0811a1L);
        a = II(a, b, c, d, groups[4], S[3][0], 0xf7537e82L);
        d = II(d, a, b, c, groups[11], S[3][1], 0xbd3af235L);
        c = II(c, d, a, b, groups[2], S[3][2], 0x2ad7d2bbL);
        b = II(b, c, d, a, groups[9], S[3][3], 0xeb86d391L);

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
