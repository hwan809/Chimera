/*
 * The MIT License
 *
 * Copyright 2020 Karus Labs.
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
package com.karuslabs.puff.assertion.sequences;

import com.karuslabs.puff.assertion.matches.Variable;
import com.karuslabs.puff.type.*;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.Collection;

import static com.karuslabs.puff.Texts.and;
import static com.karuslabs.puff.assertion.Assertions.variable;

class AnyParameter extends Sequence<VariableElement> {
    
    static final Variable variable = variable().get();
    
    AnyParameter() {
        super("");
    }

    @Override
    public boolean test(TypeMirrors types, Collection<? extends VariableElement> variables) {
        return true;
    }

    @Override
    public String describe(Collection<? extends VariableElement> values) {
        return and(values, (value, builder) -> builder.append(variable.describe(value)));
    }

    @Override
    public void reset() {}
    
}

class AnyType extends Sequence<TypeMirror> {
    
    AnyType() {
        super("");
    }

    @Override
    public boolean test(TypeMirrors types, Collection<? extends TypeMirror> values) {
        return true;
    }

    @Override
    public String describe(Collection<? extends TypeMirror> values) {
        return and(values, (value, builder) -> value.accept(TypePrinter.SIMPLE, builder));
    }

    @Override
    public void reset() {}
    
}
