package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by hugui on 14-2-14.
 */
public class NumberUtils {
    /*该类并非是线程全的，便是format方法是安全的。。。*/
    public static final NumberFormat fmt = new DecimalFormat("#.##");

    /**
     * 将传入的参数保留两位小数
     */
    public static String formatPercentage(Number number) {
        return fmt.format(number);
    }

    public static Integer sumInt(Integer one, Integer... other) {
        if (one == null) {
            one = 0;
        }
        if (other == null) {
            return one;
        }
        Integer result = one;
        for (Integer integer : other) {
            result = sumInt(result, integer);
        }
        return result;
    }

    /**
     * number/dividend，保留2位小数点
     *
     * @param number   被除数
     * @param dividend 除数
     * @return double
     */
    public static double divide(Number number, Integer dividend) {
        try {

            if (number == null || dividend == null || dividend == 0 || number.equals(Double.NaN)) {
                return 0.0;
            }
            BigDecimal b1 = new BigDecimal(number.toString());
            BigDecimal b2 = BigDecimal.valueOf(dividend);
            return b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static double divide(Number number, Long dividend) {
        try {

            if (number == null || dividend == null || dividend == 0 || number.equals(Double.NaN)) {
                return 0.0;
            }
            BigDecimal b1 = new BigDecimal(number.toString());
            BigDecimal b2 = BigDecimal.valueOf(dividend);
            return b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * 方法描述：解析为int ，如果传入的参数o 为空则返回默认值dft
     */
    public static Integer parseInt(Object object, int defaultValue) {
        if (object == null || object.equals("")) {
            return defaultValue;
        } else {
            return Integer.parseInt(object.toString());
        }
    }

    /**
     * 方法描述：解析为int ，如果传入的参数o 为空则返回默认值dft
     */
    public static Integer parseInt(Object object) {
        return parseInt(object, 0);
    }

    /**
     * 方法描述：Integer进行求和
     *
     * @author: guanfu.xie
     */
    public static int sumInt(Integer o, Integer n) {
        if (null == o) {
            o = 0;
        }
        if (null == n) {
            n = 0;
        }
        return o + n;
    }

    public static long sumLong(Long o, Long n) {
        if (null == o) {
            o = 0L;
        }
        if (null == n) {
            n = 0L;
        }
        return o + n;
    }

    /**
     * 格式化数字
     *
     * @param number 需要格式化的数字
     * @return 格式化后的数字 Double
     * @throws java.text.ParseException 格式化异常
     */
    public static Double parseDouble(Number number) throws ParseException {
        if (number == null || number.equals(Double.NaN))
            return 0.0;
        else
            return Double.valueOf(numberFormat(number));
    }


    public static String numberFormat(Number number) throws ParseException {
        if (number == null || number.equals(Double.POSITIVE_INFINITY) || number.equals(Double.NEGATIVE_INFINITY) || number.equals(Double.NaN))
            return "0";
        else
            return new DecimalFormat("##.##").format(number);
    }

    /**
     * 方法描述：解析为double 如果传入的参数o 为空或非数字则返回默认值dft
     */
    public static Double parseDouble(Object o, double dft) {
        if (o == null) {
            return dft;
        } else {
            return Double.parseDouble(o.toString());
        }
    }

    /**
     * 方法描述：Double数据相加
     */
    public static double sumDouble(Double d1, Double d2) {
        if (null == d1) {
            d1 = 0d;
        }
        if (null == d2) {
            d2 = 0d;
        }
        double dd = new BigDecimal(d1).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        double dd2 = new BigDecimal(d2).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        return new BigDecimal(dd + dd2).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    public static Integer getDefaultIfNull(Integer i, Integer defaultValue) {
        if (i == null) {
            return defaultValue;
        }
        return i;
    }

    public static Integer subInt(Integer i, Integer i2) {
        if (i == null) {
            i = 0;
        }
        if (i2 == null) {
            i2 = 0;
        }
        return i - i2;
    }

    public static Long sub(Long i, Long i2) {
        if (i == null) {
            i = 0L;
        }
        if (i2 == null) {
            i2 = 0L;
        }
        return i - i2;
    }

    public static Double subDouble(Double d1, Double d2) {
        if (d1 == null) {
            d1 = 0d;
        }
        if (d2 == null) {
            d2 = 0d;
        }
        BigDecimal decimal1 = new BigDecimal(d1.toString()).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal decimal2 = new BigDecimal(d2.toString()).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal result = decimal1.subtract(decimal2);
        result = result.setScale(4, BigDecimal.ROUND_HALF_UP);
        return result.doubleValue();
    }

    public static Double subDoubleAbs(Double d1, Double d2) {
        return Math.abs(subDouble(d1, d2));
    }

    public static boolean isNotNullZero(Integer i) {
        return i != null && i != 0;
    }


    public static Integer sumInt(Object num1, Object num2) {
        if (num1 == null) {
            num1 = 0;
        }
        if (num2 == null) {
            num2 = 0;
        }
        num1 = Integer.valueOf(num1.toString());
        num2 = Integer.valueOf(num2.toString());
        return sumInt((Integer) num1, (Integer) num2);
    }

    public static Double sumDouble(Object num1, Object num2) {
        if (num1 == null) {
            num1 = 0;
        }
        if (num2 == null) {
            num2 = 0;
        }
        num1 = Double.valueOf(num1.toString());
        num2 = Double.valueOf(num2.toString());
        return sumDouble((Double) num1, (Double) num2);
    }


    /**
     * 求两个数的最大公约数
     *
     * @param m
     * @param n
     * @return
     */
    public static int divisor(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }

        if (m % n == 0) {
            return n;
        } else {
            return divisor(n, m % n);
        }
    }

    public static boolean equals(Number a, Number b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null) {
            return b.equals(a);
        }
        return a.equals(b);
    }
}
