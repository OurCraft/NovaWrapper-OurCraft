package nova.wrapper.ourcraft.loader;

import nova.core.loader.Loadable;
import nova.core.loader.NovaMod;
import org.craft.modding.AddonContainer;

/**
 * Created by Thog92 on 01/02/2015.
 */
public class NovaContainer extends AddonContainer<NovaMod>
{
    public NovaContainer(NovaMod annot, Loadable object)
    {
        super(annot, object);
    }

    @Override public String getId()
    {
        return this.getAddonAnnotation().id();
    }

    @Override public String getName()
    {
        return this.getAddonAnnotation().name();
    }

    @Override public String getVersion()
    {
        return this.getAddonAnnotation().version();
    }

    @Override public String getAuthor()
    {
        return "unknown";
    }
}
