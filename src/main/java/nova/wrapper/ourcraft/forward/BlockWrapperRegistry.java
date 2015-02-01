package nova.wrapper.ourcraft.forward;

import nova.core.block.Block;
import nova.core.game.Game;
import org.craft.OurCraftInstance;
import org.slf4j.Logger;

import java.util.HashMap;

/**
 * Created by Thog92 on 01/02/2015.
 */
public class BlockWrapperRegistry
{
    public static final BlockWrapperRegistry instance = new BlockWrapperRegistry();

    /**
     * A map of all blocks registered
     */
    public final HashMap<Block, org.craft.blocks.Block> blockWrapperMap = new HashMap<>();

    /**
     * Register all Nova blocks
     */
    public void registerBlocks(OurCraftInstance ourCraft, Logger logger) {
        Game.instance.get().blockManager.registry.forEach(b -> {
            BlockWrapper blockWrapper = new BlockWrapper(b);
            blockWrapperMap.put(blockWrapper.block, blockWrapper);
            ourCraft.registerBlock(blockWrapper);
            logger.debug("[NOVA]: Registered '" + b.getID() + "' block.");
        });
    }
}
