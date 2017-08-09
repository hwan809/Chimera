/*
 * The MIT License
 *
 * Copyright 2017 Karus Labs.
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
package com.karuslabs.commons.command.arguments;

import java.util.function.*;


public class Arguments {
    
    private String[] arguments;
    private Matcher matcher;
    private Argument mutable;
    
    
    public Arguments(String[] arguments) {
        this.arguments = arguments;
        matcher = new Matcher(arguments);
        mutable = new Argument();
    }
    
    
    public boolean contains(int index) {
        return 0 <= index && index < arguments.length;
    }
    
    public int length() {
        return arguments.length;
    }
        
    
    public Matcher match() {
        matcher.set(arguments);
        matcher.between(0, arguments.length);
        return matcher;
    }
    
    
    public Argument get(int index) {
        return getOr(index, argument -> { mutable.set(argument); return mutable; }, () -> Argument.NONE);
    }
    
    public Argument getLast() {
        return get(arguments.length - 1);
    }
    
    
    public Argument getImmutable(int index) {
        return getOr(index, argument -> new Argument(argument), () -> Argument.NONE);
    }
    
    public Argument getLastImmutable() {
        return getOr(arguments.length - 1, argument -> new Argument(argument), () -> Argument.NONE);
    }
    
    
    protected Argument getOr(int index, Function<String, Argument> function, Supplier<Argument> supplier) {
        if (contains(index)) {
            return function.apply(arguments[index]);
            
        } else {
            return supplier.get();
        }
    }
    
    
    public String[] getRaw() {
        return arguments;
    }
    
}
