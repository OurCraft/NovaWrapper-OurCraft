package nova.wrapper.ourcraft;

import nova.bootstrap.DependencyInjectionEntryPoint;
import nova.core.game.Game;
import nova.core.loader.NovaMod;
import nova.internal.NovaLauncher;
import nova.wrapper.ourcraft.forward.BlockWrapperRegistry;
import nova.wrapper.ourcraft.loader.NovaModManager;
import org.craft.blocks.Block;
import org.craft.modding.AddonsLoader;
import org.craft.modding.IAddonManager;
import org.craft.modding.Mod;
import org.craft.modding.OurModEventHandler;
import org.craft.modding.events.state.ModInitEvent;
import org.craft.modding.events.state.ModPostInitEvent;
import org.craft.modding.events.state.ModPreInitEvent;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Mod(id = NovaOurCraft.id, name = NovaOurCraft.name, version = "0.1")
public class NovaOurCraft
{
    public static final String id   = "nova";
    public static final String name = "NOVA";
    private static NovaLauncher launcher;

    private Logger         logger;
    private NovaModManager novaManager;

    @OurModEventHandler
    public void onPreInit(ModPreInitEvent evt)
    {
        logger = evt.getLogger();
        logger.info("Loading Nova Imp...");
        /**
         * Search through all classes with @NovaMod
         */
        DependencyInjectionEntryPoint diep = new DependencyInjectionEntryPoint();
        Game.instance = Optional.of(diep.init());

        novaManager = new NovaModManager();

        AddonsLoader loader = evt.getOurCraftInstance().getAddonsLoader();
        loader.registerAddonAnnotation(NovaMod.class, novaManager);
        launcher = new NovaLauncher(diep, novaManager.getAllAddons());
        launcher.preInit();
        BlockWrapperRegistry.instance.registerBlocks(evt.getOurCraftInstance(), logger);
        logger.debug("PreInit: Done.");

    }

    @OurModEventHandler
    public void onPostInit(ModPostInitEvent evt)
    {
        launcher.postInit();
    }

    @OurModEventHandler
    public void onInit(ModInitEvent evt)
    {
        launcher.init();
    }
}
