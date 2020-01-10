package com.note.bak;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class StringObject extends SimpleJavaFileObject {
    private String contents = null;

    public StringObject(String className, String contents) throws Exception {
        super(new URI(className), Kind.SOURCE);
        this.contents = contents;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors)
            throws IOException {
        return contents;
    }
}