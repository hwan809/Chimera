/*
 * Copyright (C) 2017 Karus Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.karuslabs.commons.commands;

import com.karuslabs.commons.commands.xml.CommandParser;
import com.karuslabs.commons.commands.xml.CommandsComponent;
import com.karuslabs.commons.xml.SetterParser;

import java.io.File;
import java.util.Map;

import org.bukkit.plugin.Plugin;


public class Manager {
    
    private CommandMapProxy proxy;
    private SetterParser<Map<String, Command>> parser;
    
    
    public Manager(Plugin owningPlugin) {
        this(new CommandMapProxy(owningPlugin.getServer()), new CommandParser(new CommandsComponent()));
    }
    
    public Manager(CommandMapProxy proxy, SetterParser<Map<String, Command>> parser) {
        this.proxy = proxy;
        this.parser = parser;
    }

    
    public void load(File commandsFile, Map<String, Command> commands) {
        parser.parse(commandsFile, commands);
        proxy.register(commands);
    }

    
    public CommandMapProxy getProxy() {
        return proxy;
    }

    
    public SetterParser<Map<String, Command>> getParser() {
        return parser;
    }

}
