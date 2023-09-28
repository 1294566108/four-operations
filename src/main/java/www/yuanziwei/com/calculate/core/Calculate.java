package www.yuanziwei.com.calculate.core;

public class Calculate {

    // 比较大小
    public static boolean isGreater(String numA, String numB) {
        int[] num;
        num = comFraction(splFraction(numA), splFraction(numB));
        return num[0] > num[1];
    }

    // 求公因数
    public static int getFactor(int numerator, int denominator) {
        if (numerator < denominator) {
            int temp = numerator;
            numerator = denominator;
            denominator = temp;
        }
        if (numerator % denominator == 0) {
            return denominator;
        } else {
            return getFactor(denominator, numerator % denominator);
        }

    }

    public static String simplify(int numerator, int denominator) {
        int overnum = 0;
        int factor = getFactor(numerator, denominator);
        numerator /= factor;
        denominator /= factor;
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 1) {
            return String.valueOf(numerator);
        } else if (numerator > denominator) {
            overnum = numerator / denominator;
            numerator -= overnum * denominator;
            return overnum + "'" + numerator + "/" + denominator;
        } else {
            return numerator + "/" + denominator;
        }
    }

    public static boolean isFraction(String num) {
        return num.contains("/");
    }

    //分割字符串分数
    public static int[] splFraction(String num) {
        String[] str = num.split("'|/");
        int[] splFra = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            splFra[i] = Integer.parseInt(str[i]);
        }
        return splFra;
    }

    //通分
    public static int[] comFraction(int[] numA, int[] numB) {

        //存储通分后的A分子、B分子、AB共同分母
        int[] num = new int[3];

        if (numA.length == 3 && numB.length == 3) {
            //两者皆为带分数
            num[0] = (numA[0] * numA[2] + numA[1]) * numB[2];
            num[1] = (numB[0] * numB[2] + numB[1]) * numA[2];
            num[2] = numA[2] * numB[2];
        } else if (numA.length == 3 && numB.length == 2) {
            //A为带分数，B为真分数
            num[0] = (numA[0] * numA[2] + numA[1]) * numB[1];
            num[1] = numB[0] * numA[2];
            num[2] = numA[2] * numB[1];
        } else if (numA.length == 2 && numB.length == 3) {
            //A为真分数，B为带真分数
            num[0] = numA[0] * numB[2];
            num[1] = (numB[0] * numB[2] + numB[1]) * numA[1];
            num[2] = numA[1] * numB[2];
        } else if (numA.length == 2 && numB.length == 2) {
            //两者皆为真分数
            num[0] = numA[0] * numB[1];
            num[1] = numB[0] * numA[1];
            num[2] = numA[1] * numB[1];
        } else if (numA.length == 1 && numB.length == 3) {
            //A为整数，B为带分数
            num[0] = numA[0] * numB[2];
            num[1] = numB[0] * numB[2] + numB[1];
            num[2] = numB[2];
        } else if (numA.length == 3 && numB.length == 1) {
            //A为带分数，B为整数
            num[0] = numA[0] * numA[2] + numA[1];
            num[1] = numB[0] * numA[2];
            num[2] = numA[2];
        } else if (numA.length == 1 && numB.length == 2) {
            //A为整数，B为真分数
            num[0] = numA[0] * numB[1];
            num[1] = numB[0];
            num[2] = numB[1];
        } else if (numA.length == 2 && numB.length == 1) {
            //A为真分数，B为整数
            num[0] = numA[0];
            num[1] = numB[0] * numA[1];
            num[2] = numA[1];
        } else {
            //A、B皆为整数
            num[0] = numA[0];
            num[1] = numB[0];
            num[2] = 1;
        }

        return num;
    }

    public static String add(String numA, String numB) {

        int[] num = comFraction(splFraction(numA), splFraction(numB));
        return simplify(num[0] + num[1], num[2]);
    }

    public static String sub(String numA, String numB) {
        int[] num = comFraction(splFraction(numA), splFraction(numB));
        return simplify(num[0] - num[1], num[2]);
    }

    public static String mul(String numA, String numB) {
        int[] num = comFraction(splFraction(numA), splFraction(numB));
        return simplify(num[0] * num[1], num[2] * num[2]);
    }

    public static String div(String numA, String numB) {
        int[] num = comFraction(splFraction(numA), splFraction(numB));
        if (num[0] == 0) {
            return "0";
        } else {
            return simplify(num[0], num[1]);
        }
    }


}
