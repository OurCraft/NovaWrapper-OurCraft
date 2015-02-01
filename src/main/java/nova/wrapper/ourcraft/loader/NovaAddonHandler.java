package nova.wrapper.ourcraft.loader;

import nova.core.loader.Loadable;
import nova.core.loader.NovaMod;
import org.craft.OurCraftInstance;
import org.craft.modding.AddonContainer;
import org.craft.modding.IAddonHandler;

/**
 * Created by Thog92 on 01/02/2015.
 */
public class NovaAddonHandler implements IAddonHandler<NovaMod>
{
    @Override public AddonContainer<NovaMod> createContainer(NovaMod annot, Object object)
    {
        return new NovaContainer(annot, (Loadable) object);
    }

    @Override public void onCreation(OurCraftInstance instance, AddonContainer<NovaMod> container)
    {
        ((Loadable)((NovaContainer)container).getInstance()).preInit();
    }
}
