package com.ruoyi.common.utils;

/**
 *@ClassName: FloatCmpUtils
 *@Description: 浮点数比较
 *@author: Ricardo
 *@Date: 2019/4/18 10:48
 **/
/**
 * Created by Frank
 * 比较浮点数
 */
public class FloatCmpUtils{
    // 公差
    private final static Double EPSILON = 0.0000001;

    public static void main(String[] args) {
        double da = 3 * .3333333333;
        double db = 0.99999992857;

        // 基本类型可以优先使用==进行比较
        if (da == db) {
            System.out.println("Java considers " + da + "==" + db);
        }
        // 使用重载的equals方法
        else if (equals(da, db, 0.0000001)) {
            System.out.println("Equal within epsilon " + EPSILON);
        } else {
            System.out.println(da + "!=" + db);
        }

        //equalsOrGreater()
        double da1 = 140.32;
        int db1 = 140;
        if(equalsOrGreater(da1,db1,0.001)){
            System.out.println("equalsOrGreater!");
        }
        if(da1>=db1){
            System.out.println("equalsOrGreater!");
        }
        da1=140.00;
        if(equalsOrGreater(da1,db1,0.001)){
            System.out.println("equalsOrGreater!");
        }
        if(da1>=db1){
            System.out.println("equalsOrGreater!");
        }
        if(da1==db1){
            System.out.println("equals!");
        }
    }

    /**
     * 在给定精度范围内比较2个double类型值
     *
     * @param a   参数a
     * @param b   参数b
     * @param eps 给定的公差值
     * @return 比较结果
     */
    public static boolean equals(double a, double b, double eps) {
        return a == b || Math.abs(a - b) < eps;
    }
    /**
     * 在给定精度范围内比较2个double类型值
     *
     * @param a   参数a
     * @param b   参数b
     * @param eps 给定的公差值
     * @return 比较结果
     */
    public static boolean equalsOrGreater(double a, double b, double eps) {
        return a == b || a - b > 0;
    }
    /**
     * 在缺省的误差范围内比较2个double类型值
     *
     * @param a 参数a
     * @param b 参数b
     * @return 比较结果
     */
    public static boolean equals(double a, double b) {
        return equals(a, b, EPSILON);
    }
}