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
package com.karuslabs.commons.item.builders;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.meta.*;

public final class BlockStateBuilder extends Builder<BlockStateMeta, BlockStateBuilder> {
    
    public static BlockStateBuilder of(Material material) {
        return new BlockStateBuilder(material);
    }
    
    BlockStateBuilder(Material material) {
        super(material);
    }
    
    BlockStateBuilder(Builder<ItemMeta, ?> source) {
        super(source);
    }
    
    public BlockStateBuilder state(BlockState state) {
        meta.setBlockState(state);
        return this;
    }
    
    @Override
    BlockStateBuilder self() {
        return this;
    }
    
}
