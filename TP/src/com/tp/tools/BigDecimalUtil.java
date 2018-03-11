package com.tp.tools;

import java.math.BigDecimal;

/**
 * ����Java�ļ����Ͳ��ܹ���ȷ�ĶԸ�����������㣬����������ṩ�� ȷ�ĸ��������㣬�����Ӽ��˳���������롣
 */
public class BigDecimalUtil {
	private static final int DEF_DIV_SCALE = 2; // ����಻��ʵ��
	private static BigDecimal b1, b2;

	public BigDecimalUtil() {

	}

	/**
	 * �ṩ��ȷ�ļӷ����㡣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ��������ĺ�
	 */
	public static double add(double v1, double v2) {
		b1 = new BigDecimal(Double.toString(v1));
		b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * �ṩ��ȷ�ļ������㡣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ��������Ĳ�
	 */
	public static double sub(double v1, double v2) {
		b1 = new BigDecimal(Double.toString(v1));
		b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * �ṩ��ȷ�ĳ˷����㡣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ��������Ļ�
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * �ṩ����ԣ���ȷ�ĳ����㣬�����������ʱ����ȷ�� С����Ժ�10λ���Ժ�������������롣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return �����������
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * �ṩ����ԣ���ȷ�ĳ����㡣�����������ʱ����scale����ָ �����ȣ��Ժ�������������롣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @param scale
	 *            ��ʾ��ʾ��Ҫ��ȷ��С����Ժ�λ��
	 * @return �����������
	 */
	public static int div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/**
	 * �ṩ��ȷ��С��λ�������봦�?
	 * 
	 * @param v
	 *            ��Ҫ�������������
	 * @param scale
	 *            С��������λ
	 * @return ���������Ľ��
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static int compare(double val1, double val2) {  
		BigDecimal data1 = new BigDecimal(val1);  
	    BigDecimal data2 = new BigDecimal(val2);  
	    return data1.compareTo(data2);  
	}  
}
