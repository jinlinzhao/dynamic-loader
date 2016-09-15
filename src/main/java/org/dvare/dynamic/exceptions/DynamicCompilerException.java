package org.dvare.dynamic.exceptions;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.*;


public class DynamicCompilerException extends Exception {

    private List<Diagnostic<? extends JavaFileObject>> diags;
    private int line;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public DynamicCompilerException(List<Diagnostic<? extends JavaFileObject>> diags) {
        this.diags = diags;
        line = -1;
    }

    public List<Map<String, Object>> getErrorList() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Diagnostic diag : diags) {
            Map<String, Object> diagnostic = new HashMap<>();

            diagnostic.put("kind", diag.getKind());
            diagnostic.put("line", diag.getLineNumber() - line + 1);
            diagnostic.put("message", diag.getMessage(Locale.US));

            list.add(diagnostic);
        }

        return list;
    }

    public void setInsertLine(int line) {
        this.line = line;
    }
}