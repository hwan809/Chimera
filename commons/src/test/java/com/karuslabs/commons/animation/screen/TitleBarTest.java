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
package com.karuslabs.commons.animation.screen;

import com.karuslabs.commons.animation.screen.TitleBar.ScheduledTask;

import java.util.function.BiConsumer;

import org.bukkit.entity.Player;

import org.junit.jupiter.api.*;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.*;


@TestInstance(PER_CLASS)
class TitleBarTest {
    
    BiConsumer<Player, TitleContext> consumer = mock(BiConsumer.class);
    TitleBar bar = TitleBar.builder(null).format(() -> consumer).fadeIn(1).stay(2).fadeOut(3).build();
    Player player = mock(Player.class);
    
    
    @Test
    void task() {
        ScheduledTask task = (ScheduledTask) bar.newTask(singleton(player));
        
        assertEquals(1, task.getFadeIn());
        assertEquals(2, task.getStay());
        assertEquals(3, task.getFadeOut());
    }
    
    
    @Test
    void setters() {
        ScheduledTask task = (ScheduledTask) bar.newTask(singleton(player));
        
        task.setFadeIn(4);
        task.setStay(5);
        task.setFadeOut(6);
        
        assertEquals(4, task.getFadeIn());
        assertEquals(5, task.getStay());
        assertEquals(6, task.getFadeOut());
    }
    
    
    @Test
    void process() {
        ScheduledTask task = (ScheduledTask) bar.newTask(singleton(player));
        
        task.process();
        
        verify(consumer).accept(player, task);
    }
    
}
