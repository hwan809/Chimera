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

import com.karuslabs.puff.Texts;
import com.karuslabs.puff.assertion.SkeletonAssertion;
import com.karuslabs.puff.type.TypeMirrors;

import java.util.*;
import javax.lang.model.element.*;

import static com.karuslabs.puff.Texts.*;

public abstract class ModifierMatch extends SkeletonAssertion implements Match<Set<Modifier>> {
    
    public static final Match<Set<Modifier>> ANY = new AnyModifier();
    
    public static final Match<Set<Modifier>> contains(Modifier... modifiers) {
        return new ContainsModifier(modifiers);
    }
    
    public static final Match<Set<Modifier>> match(Modifier... modifiers) {
        return new MatchModifier(modifiers);
    }
    
    
    public static List<Modifier> sort(Collection<Modifier> modifiers) {
        var list = new ArrayList<>(modifiers);
        list.sort((a, b) -> Integer.compare(order(a), order(b)));
        return list;
    }
    
    public static Modifier[] sort(Modifier... modifiers) {
        Arrays.sort(modifiers, (a, b) -> Integer.compare(order(a), order(b)));
        return modifiers;
    }
    
    static int order(Modifier modifier) {
        switch (modifier) {
            case PUBLIC:
            case PROTECTED:
            case PRIVATE:
                return 0;
            case STATIC:
                return 1;
            case ABSTRACT:
                return 2;
            default:
                return 3;
        }
    }
    
    
    final Set<Modifier> modifiers;
    
    public ModifierMatch(Set<Modifier> modifiers, String condition) {
        super(condition);
        this.modifiers = modifiers;
    }

    @Override
    public boolean test(TypeMirrors types, Element element) {
        return test(types, element.getModifiers());
    }

    @Override
    public String describe(Element element) {
        return describe(element.getModifiers());
    }

    @Override
    public String describe(Set<Modifier> modifiers) {
        return Texts.join(sort(modifiers), SCREAMING_CASE, " ");
    }

}

class AnyModifier extends ModifierMatch {

    public AnyModifier() {
        super(Set.of(), "");
    }

    @Override
    public boolean test(TypeMirrors types, Set<Modifier> modifiers) {
        return true;
    }
    
}

class ContainsModifier extends ModifierMatch {

    ContainsModifier(Modifier... modifiers) {
        super(Set.of(modifiers), Texts.join(sort(modifiers), SCREAMING_CASE, " "));
    }
    
    @Override
    public boolean test(TypeMirrors types, Set<Modifier> modifiers) {
        return modifiers.containsAll(this.modifiers);
    }
    
}

class MatchModifier extends ModifierMatch {

    MatchModifier(Modifier... modifiers) {
        super(Set.of(modifiers), Texts.join(sort(modifiers), SCREAMING_CASE, " "));
    }
    
    @Override
    public boolean test(TypeMirrors types, Set<Modifier> modifiers) {
        return this.modifiers.equals(modifiers);
    }
    
}