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
package com.karuslabs.commons.command.annotation.processors;

import com.karuslabs.commons.command.*;
import com.karuslabs.commons.command.annotation.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * Represents a resolver for {@code CommandExecutor} with {@code Namespace} annotationss.
 */
public class NamespaceResolver implements Resolver {

    /**
     * Resolves the {@code Namespace} annotations for the {@code CommandExecutor} using the specified {@code ProxiedCommandMap} and returns 
     * a list of commands to which the executor will be registered to.
     * 
     * @param map the map
     * @param executor the executor
     * @return a list of commands
     */
    @Override
    public List<Command> resolve(ProxiedCommandMap map, CommandExecutor executor) {
        return Stream.of(executor.getClass().getAnnotationsByType(Namespace.class)).map(namespace -> resolve(map , executor, namespace)).collect(toList());
    } 
    
    /**
     * Resolves the specified {@code Namespace} annotation.
     * 
     * @param map the map
     * @param executor the executor
     * @param namespace the namespace
     * @return the command
     * @throws IllegalArgumentException if the namespace is empty or the command the namespace resolves to is invalid
     */
    protected Command resolve(ProxiedCommandMap map, CommandExecutor executor, Namespace namespace) {
        String[] names = namespace.value();
        if (names.length == 0) {
            throw new IllegalArgumentException("Invalid namespace for: " + executor.getClass().getName() + ", namespace cannot be empty");
        }
        
        Command command = map.getCommand(names[0]);
        check(command, executor, names, names[0]);
        
        for (int i = 1; i < names.length; i++) {
            command = command.getSubcommands().get(names[i]);
            check(command, executor, names, names[i]);
        }
        
        return command;
    }
    
    /**
     * Checks if the specified command is {@code null}.
     * 
     * @param command the command
     * @param executor the executor
     * @param names the namespace
     * @param name the name
     * @throws IllegalArgumentException if the specified command is null
     */
    protected void check(Command command, CommandExecutor executor, String[] names, String name) {
        if (command == null) {
            throw new IllegalArgumentException("Unresolvable name: \"" + name + "\" in namespace: \"" + String.join(".", names) + "\" for " + executor.getClass().getName());
        }
    } 

    
    /**
     * Checks if the {@code CommandExecutor} has {@code Namespace} annotations.
     * 
     * @param executor the CommandExecutor to resolve
     * @return true if the CommandExecutor has Namespace annotations; else false
     */
    @Override
    public boolean isResolvable(CommandExecutor executor) {
        return executor.getClass().getAnnotationsByType(Namespace.class).length != 0;
    }
    
}
