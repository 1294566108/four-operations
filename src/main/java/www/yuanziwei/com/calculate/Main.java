package www.yuanziwei.com.calculate;

import www.yuanziwei.com.calculate.core.JudgeAnswer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("输入 “-n [数值]” 可设置生成的题目个数 ");
		System.out.println("输入 “-r [数值]” 可设置生成的题目中，所用数值和真分数分母的最大数字(不包含该数)");
		System.out.println("输入 “-e Exercises.txt.txt -a Answers.txt.txt” 可校对答案，并将其校对结果输出到Grade.txt文件中");
		System.out.println("输入 “-exit ” 可退出程序");
		System.out.println("PS: -r 指令需要在输入 -n 指令后才能实现，不过可由两者同时输入，例如“-n 10 -r 10”");
		int max;
		int nums;
		while (true) {
			max = 0;
			nums = 0;
			System.out.println("请输入相应指令:");
			Scanner sc = new Scanner(System.in);
			String commands[] = sc.nextLine().toString().trim().replaceAll(" +", " ").split(" ");
			if (commands[0].equals("-exit")) {
				break;
			}

			switch (commands[0]) {
			case "-n":
				if (commands.length == 2) {
					nums = Integer.valueOf(commands[1]).intValue();
					System.out.println("\n请输入 “-r [数值]”指令：");
					sc = new Scanner(System.in);
					String[] cm = sc.nextLine().toString().trim().replaceAll(" +", " ").split(" ");
					if (cm.length == 2 && cm[0].equals("-r")) {
						max = Integer.valueOf(cm[1]).intValue();
					} else {
						System.out.println("\n输入指令错误，请重来！");
						break;
					}
					build(nums, max);
					System.out.println("\n生成题目成功！");
				} else if (commands.length == 4) {
					nums = Integer.valueOf(commands[1]).intValue();
					max = Integer.valueOf(commands[3]).intValue();
					build(nums, max);
					System.out.println("\n生成题目成功！");
				} else {
					System.out.println("\n输入指令错误，请重来！");
				}
				break;
			case "-e":
				if (commands.length == 4 && commands[2].equals("-a")) {
					File testAns = new File(commands[1]);
					File trueAns = new File(commands[3]);
					if (testAns.exists() && trueAns.exists()) {
						new JudgeAnswer(testAns, trueAns);
						System.out.println("\n校对成功，成绩已存入Grade.txt！");
					} else {
						System.out.println("\n输入文件路径错误，请重来！");
					}
				} else {
					System.out.println("\n输入指令错误，请重来！");
				}
				break;
			default :
				System.out.println("\n输入指令错误，请重来！");
				break;
			}
			
		}
		
	}

	public static void build(int numbs, int max) throws IOException {
		CreateSubject cs;
		File fileExe = FileOutput.createFile("Exercises.txt");
		File fileAns = FileOutput.createFile("Answers.txt");
		for (int num = 1; num <= numbs; num++) {
			cs = new CreateSubject(max);
			while (num > 2 && FileOutput.isRepeat(cs, fileAns, fileExe)) {
				cs = new CreateSubject(max);
			}
			FileOutput.outExeAndAns(num, cs, fileAns, fileExe);
		}
	}
}
