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
package com.karuslabs.puff.assertion.matches;

import com.karuslabs.puff.type.TypeMirrors;

import javax.lang.model.type.*;

import org.junit.jupiter.api.*;

import static com.karuslabs.puff.assertion.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimeableTest {

    Timeable<TypeMirror> match = is(int.class);
    TypeMirror integer = when(mock(TypeMirror.class).getKind()).thenReturn(TypeKind.INT).getMock();
    TypeMirror bool = when(mock(TypeMirror.class).getKind()).thenReturn(TypeKind.BOOLEAN).getMock();
    TypeMirrors types = mock(TypeMirrors.class);
    
    @Test
    void and_supplier() {
        assertTrue(match.and(() -> is(int.class)).test(types, integer));
    }
    
    @Test
    void and_timeable() {
        assertTrue(match.and(is(int.class)).test(types, integer));
    }
    
    
    @Test
    void or_supplier() {
        assertTrue(match.or(() -> is(boolean.class)).test(types, bool));
    }
    
    @Test
    void or_timeable() {
        assertTrue(match.or(is(boolean.class)).test(types, bool));
    }
    
}
