package www.yuanziwei.com.calculate;

import www.yuanziwei.com.calculate.core.Calculate;
import www.yuanziwei.com.calculate.operation.FractionOpe;
import www.yuanziwei.com.calculate.operation.IntegerOpe;

import java.util.ArrayList;
import java.util.Random;

public class CreateSubject {

	private static final String[] OPERATORS = { "+", "-", "*", "÷" };
	ArrayList<String> expression = new ArrayList<>();// 算式字符串存储
	ArrayList<String> numbs = new ArrayList<>();
	ArrayList<String> opers = new ArrayList<>();
	ArrayList<String> answer = new ArrayList<>();

	Random rd = new Random();

	public CreateSubject(int max) {

		String numA = randNum(max);
		String numB;
		int nums = rd.nextInt(3) + 2;// 几个数字的运算
		String flag;
		expression.add(numA);
		numbs.add(numA);

		for (int i = 0; i < nums - 1; i++) {
			numB = randNum(max);
			flag = OPERATORS[rd.nextInt(4)];

			switch (flag) {
			case "+":
				numA = Calculate.add(numA, numB);
				break;

			case "-":
				if (!Calculate.isGreater(numA, numB)) {
					flag = OPERATORS[0];
					numA = Calculate.add(numA, numB);
				} else {
					numA = Calculate.sub(numA, numB);
				}
				break;

			case "*":
				numA = Calculate.mul(numA, numB);
				break;

			case "÷":
				numA = Calculate.div(numA, numB);
				break;
			}

			expression.add(flag);
			expression.add(numB);
			numbs.add(numB);
			opers.add(flag);
		}
		addBrackets(expression);
		expression.add("=");
		answer.add(numA);
	}

	// 随机生成分数或整数
	public String randNum(int max) {
		String num;
		int flag = rd.nextInt(10) + 1;
		if (flag % 3 == 0) {
			num = FractionOpe.gen(max);
		} else {
			num = IntegerOpe.gen(max);
		}
		return num;
	}

	// 生成必要的括号
	public ArrayList<String> addBrackets(ArrayList<String> expression) {
		String lowlv = "+-";
		String highlv = "*÷";
		if (expression.size() == 5) {
			if (lowlv.contains(expression.get(1)) && highlv.contains(expression.get(3))) {
				expression.add(0, "(");
				expression.add(4, ")");
			}
		}
		if (expression.size() == 7) {
			if (lowlv.contains(expression.get(1)) && highlv.contains(expression.get(3))) {
				expression.add(0, "(");
				expression.add(4, ")");
			}
			if (lowlv.contains(expression.get(3)) && highlv.contains(expression.get(5))) {
				expression.add(0, "(");
				expression.add(6, ")");
			}
		}

		return expression;
	}
}
