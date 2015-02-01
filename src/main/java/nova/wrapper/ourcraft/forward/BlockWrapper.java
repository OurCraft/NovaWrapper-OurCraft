package nova.wrapper.ourcraft.forward;

import nova.core.block.Block;
import nova.core.block.BlockFactory;

/**
 * Created by Thog92 on 01/02/2015.
 */
public class BlockWrapper extends org.craft.blocks.Block
{
    public Block block;


    public BlockWrapper(BlockFactory block)
    {
        super(block.getID());
    }
}
