package cpw.mods.fml.common;

import argo.jdom.JsonNode;
import argo.jdom.JsonNodeBuilders;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.ModMetadata$1;
import cpw.mods.fml.common.ModMetadata$JsonStringConverter;
import cpw.mods.fml.common.functions.ModNameFunction;
import cpw.mods.fml.common.versioning.VersionParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class ModMetadata
{
    public String modId;
    public String name;
    public String description;
    public String url = "";
    public String updateUrl = "";
    public String logoFile = "";
    public String version = "";
    public List authorList = Lists.newArrayList();
    public String credits = "";
    public String parent = "";
    public String[] screenshots;
    public ModContainer parentMod;
    public List childMods = Lists.newArrayList();
    public boolean useDependencyInformation;
    public Set requiredMods;
    public List dependencies;
    public List dependants;
    public boolean autogenerated;

    public ModMetadata(JsonNode var1)
    {
        Map var2 = Maps.transformValues(var1.getFields(), new ModMetadata$JsonStringConverter((ModMetadata$1)null));
        this.modId = (String)var2.get(JsonNodeBuilders.aStringBuilder("modid"));

        if (Strings.isNullOrEmpty(this.modId))
        {
            FMLLog.log(Level.SEVERE, "Found an invalid mod metadata file - missing modid", new Object[0]);
            throw new LoaderException();
        }
        else
        {
            this.name = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("name")));
            this.description = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("description")));
            this.url = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("url")));
            this.updateUrl = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("updateUrl")));
            this.logoFile = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("logoFile")));
            this.version = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("version")));
            this.credits = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("credits")));
            this.parent = Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("parent")));
            this.authorList = (List)Objects.firstNonNull((List)var2.get(JsonNodeBuilders.aStringBuilder("authors")), Objects.firstNonNull((List)var2.get(JsonNodeBuilders.aStringBuilder("authorList")), this.authorList));
            this.requiredMods = (Set)this.processReferences(var2.get(JsonNodeBuilders.aStringBuilder("requiredMods")), HashSet.class);
            this.dependencies = (List)this.processReferences(var2.get(JsonNodeBuilders.aStringBuilder("dependencies")), ArrayList.class);
            this.dependants = (List)this.processReferences(var2.get(JsonNodeBuilders.aStringBuilder("dependants")), ArrayList.class);
            this.useDependencyInformation = Boolean.parseBoolean(Strings.nullToEmpty((String)var2.get(JsonNodeBuilders.aStringBuilder("useDependencyInformation"))));
        }
    }

    public ModMetadata() {}

    private Collection processReferences(Object var1, Class var2)
    {
        Collection var3 = null;

        try
        {
            var3 = (Collection)var2.newInstance();
        }
        catch (Exception var6)
        {
            ;
        }

        if (var1 == null)
        {
            return var3;
        }
        else
        {
            Iterator var4 = ((List)var1).iterator();

            while (var4.hasNext())
            {
                String var5 = (String)var4.next();
                var3.add(VersionParser.parseVersionReference(var5));
            }

            return var3;
        }
    }

    public String getChildModCountString()
    {
        return String.format("%d child mod%s", new Object[] {Integer.valueOf(this.childMods.size()), this.childMods.size() != 1 ? "s" : ""});
    }

    public String getAuthorList()
    {
        return Joiner.on(", ").join(this.authorList);
    }

    public String getChildModList()
    {
        return Joiner.on(", ").join(Lists.transform(this.childMods, new ModNameFunction()));
    }

    public String printableSortingRules()
    {
        return "";
    }
}
