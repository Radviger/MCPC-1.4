package cpw.mods.fml.common.asm.transformers;

import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.relauncher.FMLRelauncher;
import cpw.mods.fml.relauncher.IClassTransformer;
import java.util.Iterator;
import java.util.List;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class SideTransformer implements IClassTransformer
{
    private static String SIDE = FMLRelauncher.side();
    private static final boolean DEBUG = false;

    public byte[] transform(String var1, byte[] var2)
    {
        if (var2 == null)
        {
            return null;
        }
        else
        {
            ClassNode var3 = new ClassNode();
            ClassReader var4 = new ClassReader(var2);
            var4.accept(var3, 0);

            if (this.remove(var3.visibleAnnotations, SIDE))
            {
                throw new RuntimeException(String.format("Attempted to load class %s for invalid side %s", new Object[] {var3.name, SIDE}));
            }
            else
            {
                Iterator var5 = var3.fields.iterator();

                while (var5.hasNext())
                {
                    FieldNode var6 = (FieldNode)var5.next();

                    if (this.remove(var6.visibleAnnotations, SIDE))
                    {
                        var5.remove();
                    }
                }

                Iterator var8 = var3.methods.iterator();

                while (var8.hasNext())
                {
                    MethodNode var7 = (MethodNode)var8.next();

                    if (this.remove(var7.visibleAnnotations, SIDE))
                    {
                        var8.remove();
                    }
                }

                ClassWriter var9 = new ClassWriter(1);
                var3.accept(var9);
                return var9.toByteArray();
            }
        }
    }

    private boolean remove(List var1, String var2)
    {
        if (var1 == null)
        {
            return false;
        }
        else
        {
            Iterator var3 = var1.iterator();

            while (var3.hasNext())
            {
                AnnotationNode var4 = (AnnotationNode)var3.next();

                if (var4.desc.equals(Type.getDescriptor(SideOnly.class)) && var4.values != null)
                {
                    for (int var5 = 0; var5 < var4.values.size() - 1; var5 += 2)
                    {
                        Object var6 = var4.values.get(var5);
                        Object var7 = var4.values.get(var5 + 1);

                        if (var6 instanceof String && var6.equals("value") && var7 instanceof String[] && !((String[])((String[])var7))[1].equals(var2))
                        {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }
}
