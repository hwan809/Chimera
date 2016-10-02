/*
 * Copyright (C) 2016 PanteLegacy @ karusmc.com
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
package com.karusmc.xmc.core;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public enum ListType {
    
    WHITELIST("whitelist"), BLACKLIST("blacklist");
    
    public final String text;
    
    
    private ListType(String text) {
        this.text = text;
    }
    
    
    public static ListType fromString(String text) {
        if (text.equalsIgnoreCase("whitelist")) {
            return WHITELIST;
            
        } else if (text.equalsIgnoreCase("blacklist")){
            return BLACKLIST;
            
        } else throw new IllegalArgumentException("Invalid list type");
    }
    
}
