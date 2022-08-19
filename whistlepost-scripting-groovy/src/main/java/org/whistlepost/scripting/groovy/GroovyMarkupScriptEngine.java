package org.whistlepost.scripting.groovy;

import groovy.lang.Writable;
import groovy.text.Template;
import org.apache.sling.scripting.api.AbstractSlingScriptEngine;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class GroovyMarkupScriptEngine extends AbstractSlingScriptEngine {

    private final GroovyMarkupScriptEngineFactory markupScriptEngineFactory;

    public GroovyMarkupScriptEngine(GroovyMarkupScriptEngineFactory factory) {
        super(factory);
        this.markupScriptEngineFactory = factory;
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        Template template;
        try {
            template = markupScriptEngineFactory.getTemplateEngine().createTemplate(reader);
        } catch (IOException | ClassNotFoundException e) {
            throw new ScriptException("Unable to compile Groovy Markup script: $e.message");
        }

        final Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        final Writable result = template.make(bindings);
        Writer writer = context.getWriter();

        try {
            result.writeTo(writer);
        } catch (IOException e) {
            throw new ScriptException("Unable to write results of script execution: $e.message");
        }

        return null;
    }
}
