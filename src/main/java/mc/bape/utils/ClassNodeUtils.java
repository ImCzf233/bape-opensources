package mc.bape.utils;

import org.objectweb.asm.*;
import java.io.*;
import org.objectweb.asm.tree.*;

public class ClassNodeUtils
{
    public static ClassNode toClassNode(final byte[] bytes) {
        final ClassReader classReader = new ClassReader(bytes);
        final ClassNode classNode = new ClassNode();
        classReader.accept((ClassVisitor)classNode, 0);
        return classNode;
    }
    
    public static byte[] toBytes(final ClassNode classNode) {
        final ClassWriter classWriter = new ClassWriter(1);
        classNode.accept((ClassVisitor)classWriter);
        return classWriter.toByteArray();
    }
    
    public static void writeFile(final ClassNode classNode, final String pathPre) throws FileNotFoundException, IOException {
        String path = classNode.name.replace(".", "\\").replace("/", "\\");
        path = pathPre + path + ".class";
        final File file = new File(path);
        final String folder = file.getParent();
        try {
            if (folder != null && !folder.equals("")) {
                final File file_folder = new File(folder);
                if (!file_folder.isDirectory()) {
                    file_folder.mkdirs();
                }
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(toBytes(classNode));
            Tool.log("save class: %s", path);
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (IOException e2) {
            throw e2;
        }
    }
    
    public static InsnList toNodes(final AbstractInsnNode... nodes) {
        final InsnList insnList = new InsnList();
        for (final AbstractInsnNode node : nodes) {
            insnList.add(node);
        }
        return insnList;
    }
}
