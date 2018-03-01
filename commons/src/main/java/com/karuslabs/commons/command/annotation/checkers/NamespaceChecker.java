/*
 * The MIT License
 *
 * Copyright 2018 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.karuslabs.commons.command.annotation.checkers;

import com.karuslabs.commons.command.annotation.*;

import java.util.*;
import javax.annotation.processing.*;
import javax.lang.model.element.*;

import static com.karuslabs.commons.annotation.checkers.Elements.annotated;
import static javax.tools.Diagnostic.Kind.ERROR;


/**
 * Represents a checker for the annotations specified in {@code SupportedAnnotationTypes}.
 * 
 * Checks if the namespaces of each annotations for each annotated targets are unique and not empty.
 */
@SupportedAnnotationTypes({"com.karuslabs.commons.command.annotation.Namespace", "com.karuslabs.commons.command.annotation.Namespaces"})
public class NamespaceChecker extends AbstractProcessor {
    
    Map<String, String> namespaces;
    
    
    /**
     * Constructs a {@code NamesapceChecker}.
     */
    public NamespaceChecker() {
        namespaces = new HashMap<>();
    }
    
    
    /**
     * Checks if the namespaces of each annotations for each annotated targets are unique and not empty.
     * 
     * @param annotations the annotation types requested to be processed
     * @param environment the environment for information about the current and prior round
     * @return false
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        for (Element element : annotated(annotations, environment))  {
            for (Namespace namespace : element.getAnnotationsByType(Namespace.class)) {
                check(element, namespace.value());
            }
        }
        
        return false;
    }
    
    /**
     * Checks if the specified namespace is not empty and unqiue.
     * 
     * @param element the element
     * @param namespace the namespace
     */
    protected void check(Element element, String[] namespace) {
        if (namespace.length == 0) {
            processingEnv.getMessager().printMessage(ERROR, "Invalid namespace, namespace cannot be empty", element);
            return;
        }
        
        String name = String.join(".", namespace);
        String type = element.asType().toString();
        
        String other = namespaces.get(name);
        if (other == null) {
            namespaces.put(name, type);

        } else {
            processingEnv.getMessager().printMessage(ERROR, "Conflicting namespaces: " + type + " and " + other + ", namespaces must be unique", element);
        }
    }

}
