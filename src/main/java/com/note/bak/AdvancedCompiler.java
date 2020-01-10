package com.note.bak;

import javax.tools.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.Arrays;

public class AdvancedCompiler {
    public static void main(String[] args) {
        // Steps used to compile Calculator
        // Steps used to compile StringObject
        // construct CalculatorTest in memory
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileObject file = constructTestor();

        Iterable<? extends JavaFileObject> files = Arrays.asList(file);
        //  DiagnosticCollector<JavaFileObject> collector =
        new DiagnosticCollector<JavaFileObject>();
        MyDiagnosticListener c = new MyDiagnosticListener();
        StandardJavaFileManager fileManager =
                compiler.getStandardFileManager(c, null, null);
        Iterable options = Arrays.asList("-d", "d:\\");
        JavaCompiler.CompilationTask task = compiler.getTask(
                null, fileManager, c, options, null, files);
        Boolean result = task.call();
        if (result == true) {
            System.out.println("Succeeded");
        }
    }

    private static SimpleJavaFileObject constructTestor() {
        StringBuilder contents = new StringBuilder(
                "package dynamic;" +
                        "class CalculatorTest { " +
                        "  public void testMultiply() { " +
                        "    Calculator c = new Calculator(); " +
                        "    System.out.println(c.multiply(2, 4)); " +
                        "  } " +
                        "  public static void main(String[] args) { " +
                        "    CalculatorTest ct = new CalculatorTest(); " +
                        "    ct.testMultiply(); " +
                        "  } " +
                        "} ");
        StringObject so = null;
        try {
            so = new StringObject("math.CalculatorTest", contents.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return so;
    }
}