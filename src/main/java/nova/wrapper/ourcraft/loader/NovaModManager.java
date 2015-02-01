package nova.wrapper.ourcraft.loader;

import nova.core.block.BlockManager;
import nova.core.game.Game;
import nova.core.item.ItemManager;
import nova.core.loader.NovaMod;
import nova.core.render.RenderManager;
import org.craft.modding.AddonContainer;
import org.craft.modding.IAddonHandler;
import org.craft.modding.IAddonManager;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thog92 on 01/02/2015.
 */
public class NovaModManager implements IAddonManager<NovaMod>
{
    private NovaAddonHandler handler;

    private HashMap<String, AddonContainer<NovaMod>> mods;

    public NovaModManager()
    {
        this.handler = new NovaAddonHandler();
        this.mods = new HashMap<>();
    }

    @Override public AddonContainer<NovaMod> getAddon(String id)
    {
        return mods.get(id);
    }

    @Override public Collection<AddonContainer<NovaMod>> getAddons()
    {
        return mods.values();
    }

    public Set<Class<?>> getAllAddons()
    {
        Set<Class<?>> result = new HashSet<Class<?>>();
        for(AddonContainer<NovaMod> nova : mods.values())
        {
            result.add(nova.getInstance().getClass());
        }
        return result;
    }

    @Override public void loadAddon(AddonContainer<NovaMod> container)
    {
        mods.put(container.getId(), container);
    }

    @Override public IAddonHandler<NovaMod> getHandler()
    {
        return this.handler;
    }

    @Override public Constructor getAddonConstructor(Class<?> clazz) throws NoSuchMethodException
    {
        return clazz.getConstructor(BlockManager.class, ItemManager.class, RenderManager.class);
    }

    @Override public Object[] getConstructorArgs()
    {
        return new Object[] {Game.instance.get().blockManager, Game.instance.get().itemManager, Game.instance.get().renderManager};
    }
}
