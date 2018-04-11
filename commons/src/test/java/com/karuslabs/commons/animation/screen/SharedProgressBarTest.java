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

import com.karuslabs.commons.animation.screen.SharedProgressBar.ScheduledTask;

import java.util.function.BiConsumer;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import org.junit.jupiter.api.Test;

import static com.karuslabs.commons.animation.screen.SharedProgressBar.builder;
import static java.util.Collections.singleton;
import static org.mockito.Mockito.*;


class SharedProgressBarTest {
    
    Player player = mock(Player.class);
    BossBar boss = mock(BossBar.class);
    BiConsumer<BossBar, Context> format = mock(BiConsumer.class);
    SharedProgressBar bar = builder(null).supplier(() -> boss).format(() -> format).build();
    
    
    @Test
    void process() {
        ScheduledTask task = (ScheduledTask) bar.newTask(singleton(player));
        
        task.process();
        
        verify(boss).addPlayer(player);
        verify(format).accept(boss, task);
    }
    
    @Test
    void callback() {
        ScheduledTask task = (ScheduledTask) bar.newTask(singleton(player));
        
        task.callback();
        
        verify(boss).removeAll();
    }
    
}
