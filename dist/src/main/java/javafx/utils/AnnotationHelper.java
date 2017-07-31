package javafx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author by Nandem on 17-4-10.
 */
public class AnnotationHelper
{
    private String basePackage;
    private ClassLoader cl;

    public AnnotationHelper()
    {
        this.cl = getClass().getClassLoader();
    }

    /**
     * Construct an instance and specify the base package it should scan.
     * @param basePackage The base package to scan.
     */
    public AnnotationHelper(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }

    /**
     * Construct an instance with base package and class loader.
     * @param basePackage The base package to scan.
     * @param cl Use this class load to locate the package.
     */
    public AnnotationHelper(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }

    /**
     * Get all fully qualified names located in the specified package
     * and its sub-package.
     *
     * @return A list of fully qualified names.
     * @throws IOException
     */
    public List<String> getFullyQualifiedClassNameList()
    {
//        System.out.println("开始扫描包{}下的所有类"+ basePackage);

        try {
            return doScan(basePackage, new ArrayList<>());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getFullyQualifiedClassNameWithAnnotation(String p, Class<? extends Annotation> annotation)
    {
        try {
            List<String> temClassNameList = doScan(p, new ArrayList<>());
            List<String> classNameList = new ArrayList<>();

            for (String temClassName :
                    temClassNameList) {
                Class clazz = Class.forName(temClassName);
                if(clazz.getAnnotation(annotation) != null)
                    classNameList.add(temClassName);
            }

            return classNameList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Actually perform the scanning procedure.
     *
     * @param basePackage
     * @param nameList A list to contain the result.
     * @return A list of fully qualified names.
     *
     * @throws IOException
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException, ClassNotFoundException {
        String splashPath = StringUtil.dotToSplash(basePackage);

        URL url = cl.getResource(splashPath);
        String filePath = StringUtil.getRootPath(url);

        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {
//                System.out.println("{} 是一个JAR包"+filePath);

            names = readFromJarFile(filePath, splashPath);
        } else {
//                System.out.println("{} 是一个目录"+filePath);

            names = readFromDirectory(filePath);
        }

        for (String name : names) {
            if (isClassFile(name)) {
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                doScan(basePackage + "." + name, nameList);
            }
        }

//            for (String n : nameList)
//            {
//                System.out.println("该包下所有类："+ n);
//            }

        return nameList;
    }

    /**
     * Convert short class name to fully qualified name.
     * e.g., String -> java.lang.String
     */
    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtil.trimExtension(shortName));

        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
//            System.out.println("从JAR包中读取类: {}"+jarPath);

        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();

        List<String> nameList = new ArrayList<>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    /**
     * For test purpose.
     */
    public static void main(String[] args) throws Exception {
        AnnotationHelper scan = new AnnotationHelper("filter.impl");
        try
        {
            scan.getFullyQualifiedClassNameList();
        }
        catch (Exception e)
        {
            System.out.println("包下无类");
        }
    }

}