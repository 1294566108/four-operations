package www.yuanziwei.com.calculate.core;

import www.yuanziwei.com.calculate.FileOutput;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class JudgeAnswer {
    public JudgeAnswer(File ExeFile, File AnsFile) throws IOException {
        //String prefix = "/Users/yuanziwei/Documents/fourOperationCalculate/src/main/resources/";
        File grade = FileOutput.createFile("Grade.txt");
        String str = null;

        ArrayList<String> trueAns = new ArrayList<String>();
        ArrayList<String> testAns = new ArrayList<String>();

        ArrayList<String> correct = new ArrayList<String>();
        ArrayList<String> wrong = new ArrayList<String>();

        BufferedReader brExe = new BufferedReader(new InputStreamReader(Files.newInputStream(ExeFile.toPath())));
        BufferedReader brAns = new BufferedReader(new InputStreamReader(Files.newInputStream(AnsFile.toPath())));

        while ((str = brExe.readLine()) != null) {
            str = str.trim().replaceAll("\\s", "");
            str = str.substring(str.indexOf("=") + 1);
            testAns.add(str);
        }
        while ((str = brAns.readLine()) != null) {
            str = str.trim().replaceAll("\\s", "");
            str = str.substring(str.indexOf(".") + 1);
            trueAns.add(str);
        }
        for (int num = 1; num <= trueAns.size(); num++) {
            if (trueAns.get(num - 1).equals(testAns.get(num - 1))) {
                if (num == trueAns.size()) {
                    correct.add(String.valueOf(num));
                } else {
                    correct.add(String.valueOf(num) + ", ");
                }
            } else {
                if (num == trueAns.size()) {
                    wrong.add(String.valueOf(num));
                } else {
                    wrong.add(String.valueOf(num) + ", ");
                }
            }
        }
        plus(correct, "Correct");
        plus(wrong, "Wrong");

        FileOutput.outputData(correct, grade);
        FileOutput.outputData(wrong, grade);
        brExe.close();
        brAns.close();
    }

    public void plus(ArrayList<String> jud, String name) {
        jud.add(")");
        jud.add(0, "(");
        jud.add(0, name + ": " + (jud.size() - 2));
    }
}
