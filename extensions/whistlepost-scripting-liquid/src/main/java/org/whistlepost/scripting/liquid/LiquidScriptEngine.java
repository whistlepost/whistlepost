package org.whistlepost.scripting.liquid;

import liqp.RenderSettings;
import liqp.Template;
import org.apache.sling.scripting.api.AbstractSlingScriptEngine;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class LiquidScriptEngine extends AbstractSlingScriptEngine {

    private final LiquidScriptEngineFactory scriptEngineFactory;

    public LiquidScriptEngine(LiquidScriptEngineFactory factory) {
        super(factory);
        this.scriptEngineFactory = factory;
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        Template template;
        try (BufferedReader in = new BufferedReader(reader)) {
            String script = in.lines().collect(Collectors.joining());
            template = Template.parse(script).withRenderSettings(
                    new RenderSettings.Builder().withStrictVariables(true).build());
        } catch (Exception e) {
            throw new ScriptException(format("Unable to compile Liquid script: %s", e.getMessage()));
        }

        final Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        final String result = template.render(bindings);

        try (BufferedWriter out = new BufferedWriter(context.getWriter())) {
            out.write(result);
        } catch (IOException e) {
            throw new ScriptException(format("Unable to write results of script execution: %s", e.getMessage()));
        }

        return null;
    }
}
