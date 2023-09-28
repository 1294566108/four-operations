package www.yuanziwei.com.calculate;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileOutput {

	// 将题目和答案写入相应文件中
	public static void outExeAndAns(int num, CreateSubject cs, File fileAns, File fileExe) throws IOException {
		cs.expression.add(0, num + ".\t");
		cs.answer.add(0, num + ".\t");
		outputData(cs.expression, fileExe);
		outputData(cs.answer, fileAns);
	}

	// 检验题目是否重复
	public static boolean isRepeat(CreateSubject cs, File fileAns, File fileExe) throws IOException {
		boolean isRepeat = false;
		String strAns;
		String strExe;
		String[] opers;
		String[] numbs;

		int sameNum;
		int sameOpe;
		int allNums;
		int allOpes;

		BufferedReader brAns = new BufferedReader(new InputStreamReader(Files.newInputStream(fileAns.toPath())));
		BufferedReader brExe = new BufferedReader(new InputStreamReader(Files.newInputStream(fileExe.toPath())));

		while ((strAns = brAns.readLine()) != null) {
			strAns = strAns.replaceAll("\\s", "").substring(strAns.indexOf(".") + 1);
			if (cs.answer.get(0).equals(strAns)) {
				while ((strExe = brExe.readLine()) != null) {
					strExe = strExe.substring(strExe.indexOf(".") + 1, strExe.indexOf("="));
					strExe = strExe.replaceAll("\\s", "");
					numbs = strExe.split("[\\+\\-\\*\\÷]");
					opers = strExe.split("[^\\+\\-\\*\\÷]");
					sameNum = 0;
					sameOpe = 0;
					allOpes = 0;
					allNums = numbs.length;
                    for (String oper : opers) {
                        if (!oper.isEmpty()) {
                            allOpes++;
                        }
                    }
					for (String s : opers) {
						if (!s.isEmpty()) {
							if (cs.opers.contains(s)) {
								sameOpe++;
							}
						}
					}
					for (String s : numbs) {
						if (cs.numbs.contains(s)) {
							sameNum++;
						}
					}
					if (sameOpe == allOpes && sameNum == allNums) {
						isRepeat = true;
					}
					if (isRepeat) {
						break;
					}
				}
			}
			if (isRepeat) {
				break;
			}
		}

		brAns.close();
		brExe.close();

		return isRepeat;
	}

	// 创建文件，若该文件已存在则将其内容清空
	public static File createFile(String name) throws IOException {
		//name = "/Users/yuanziwei/Documents/fourOperationCalculate/src/main/resources/" + name;
		File f = new File(name);
		if (f.exists()) {
			f.delete();
			f.createNewFile();
		} else {
			f.createNewFile();
		}
		return f;
	}

	// 向文件追加写入数据
	public static void outputData(ArrayList<String> data, File file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
		String str = arrToString(data);
		bw.write(str);
		bw.newLine();
		bw.flush();
		bw.close();
	}

	public static String arrToString(ArrayList<String> arr) {
		StringBuilder str = new StringBuilder();
		if (arr != null && !arr.isEmpty()) {
			for (String s : arr) {
				str.append(s).append(" ");
			}
		}
		return str.toString();
	}
}
