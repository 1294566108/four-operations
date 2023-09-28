package www.yuanziwei.com.calculate.operation;

import www.yuanziwei.com.calculate.core.Calculate;

import java.util.Random;

public class FractionOpe {

	public static String gen(int max) {
		String fraction;
		Random rd = new Random();
		int numerator = rd.nextInt(max) + 1;
		int denominator = rd.nextInt(max) + 1;

		// 检验是否为整数
		if (numerator == denominator || numerator % denominator == 0) {
			fraction = numerator / denominator + "";
			return fraction;
		}

		// 该数不能超过max
		while (numerator / denominator >= max) {
			numerator = rd.nextInt(max) + 1;
			denominator = rd.nextInt(max) + 1;
		}

		fraction = Calculate.simplify(numerator, denominator);
		return fraction;
	}
}
