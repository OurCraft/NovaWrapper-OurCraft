package nova.wrapper.ourcraft;

import nova.bootstrap.DependencyInjectionEntryPoint;
import nova.core.game.Game;
import nova.core.loader.NovaMod;
import nova.internal.NovaLauncher;
import nova.wrapper.ourcraft.forward.BlockWrapperRegistry;
import org.craft.OurClassLoader;
import org.craft.modding.AddonsLoader;
import org.craft.modding.Mod;
import org.craft.modding.ModEventHandler;
import org.craft.modding.events.state.ModInitEvent;
import org.craft.modding.events.state.ModPostInitEvent;
import org.craft.modding.events.state.ModPreInitEvent;
import org.reflections.Reflections;
import org.slf4j.Logger;

import java.util.*;

@Mod(id = NovaOurCraft.id, name = NovaOurCraft.name, version = "0.1")
public class NovaOurCraft
{
    public static final String id   = "nova";
    public static final String name = "NOVA";
    private static NovaLauncher launcher;

    private Logger         logger;

    @ModEventHandler
    public void onPreInit(ModPreInitEvent evt)
    {

        logger = evt.getLogger();
        logger.info("Loading Nova Imp...");
        /**
         * Search through all classes with @NovaMod
         */
        DependencyInjectionEntryPoint diep = new DependencyInjectionEntryPoint();
        Game.instance = Optional.of(diep.init());


        AddonsLoader loader = evt.getOurCraftInstance().getAddonsLoader();
        launcher = new NovaLauncher(diep, this.searchMods());
        launcher.preInit();
        BlockWrapperRegistry.instance.registerBlocks(evt.getOurCraftInstance(), logger);
        logger.debug("PreInit: Done.");

    }

    private Set<Class<?>> searchMods() {
        Reflections reflection = new Reflections(OurClassLoader.instance);
        Set<Class<?>> set = reflection.getTypesAnnotatedWith(NovaMod.class);
        return set;
    }

    @ModEventHandler
    public void onPostInit(ModPostInitEvent evt)
    {
        launcher.postInit();
    }

    @ModEventHandler
    public void onInit(ModInitEvent evt)
    {
        launcher.init();
    }
}
