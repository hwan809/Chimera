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
package com.karuslabs.commons.command.aot.lints;

import com.karuslabs.commons.command.aot.*;
import com.karuslabs.commons.command.aot.Identity.Type;
import com.karuslabs.puff.Logger;

import static com.karuslabs.puff.Texts.quote;

public class ArgumentTypeLint extends TypeLint {

    public ArgumentTypeLint(Logger logger, Types types) {
        super(logger, types);
    }

    @Override
    public void lint(Environment environment, Command command) {
        if (command.identity.type == Type.ARGUMENT) {
            for (var binding : command.bindings.values()) {
                if (binding.pattern == Binding.Pattern.ARGUMENT_TYPE) {
                    return;
                }
            }

            logger.error(command.site, "No ArgumentType<?> is bound to " + quote(command.path()), "an ArgumentType<?> should be bound to it");
            
        } else {
            for (var binding : command.bindings.values()) {
                if (binding.pattern == Binding.Pattern.ARGUMENT_TYPE) {
                    logger.error(command.site, "An ArgumentType<?> is bound to " + quote(command.path()), "should only be bound to an argument");
                    return;
                }
            }
        }
    }

}
