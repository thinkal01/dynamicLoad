package com.note.bak;

import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

class MyDiagnosticListener implements DiagnosticListener<JavaFileObject> {
    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
        System.out.println("Line Number->" + diagnostic.getLineNumber());
        System.out.println("code->" + diagnostic.getCode());
        System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
        System.out.println("Source->" + diagnostic.getSource());
        System.out.println(" ");
    }
}