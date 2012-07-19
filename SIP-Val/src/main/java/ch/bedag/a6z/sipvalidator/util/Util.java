package ch.bedag.a6z.sipvalidator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
/**
 * - Some of the third party libraries we use in this project write stuff into the console with System.out
 *   This Utility can switch this on and off.
 * - Java cannot delete directories that are not empty. Here's a method to do that.
 * 
 * @author razm Daniel Ludin, Bedag AG @version 2.0
 *
 */

public class Util {
    
    static String pathToReportJHove;
    static String pathToReportPdftron;
    
    static PrintStream original;
    static String originalPath;
    static Map<String, File> fileMap = new HashMap<String, File>();
    
    
    public static String getPathToReportJHove() {
        return pathToReportJHove;
    }

    public static void setPathToReportJHove(String pathToReportJHove) {
        Util.pathToReportJHove = pathToReportJHove;
    }

    public static String getPathToReportPdftron() {
        return pathToReportPdftron;
    }

    public static void setPathToReportPdftron(String pathToReportPdftron) {
        Util.pathToReportPdftron = pathToReportPdftron;
    }

    /**
     * Schaltet die Konsolen-Ausgabe aus durch Umleitung in ein Null-Device.
     */
    public static void switchOffConsole() {
        // Keep a copy of the original out stream.
        original = new PrintStream(System.out);

        // replace the System.out, redirect to NUL
        try {
            System.setOut(new PrintStream(new FileOutputStream("NUL:")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Schaltet die mit switchOffConsole ausgeschaltete Konsole wieder ein.
     */
    public static void switchOnConsole() {
        System.setOut(original);
    }
    
    /**
     * L�scht ein Verzeichnis rekursiv.
     * @param dir das zu l�schende Verzeichnis
     * @return true wenn alle Files und Verzeichnisse gel�scht werden konnten
     */
    public static boolean deleteDir(File dir) { 
        if (dir.isDirectory()) { 
            String[] children = dir.list(); 
            for (int i=0; i<children.length; i++) { 
                boolean success = deleteDir(new File(dir, children[i])); 
                if (!success) { 
                    return false;                      
                } 
            } 
        }
        // The directory is now empty so delete it 
        return dir.delete(); 
    }

    
    public static Map<String, File> getFileMap(File dir, boolean nurPrimaerDateien) {
        originalPath = dir.getAbsolutePath();
        fileMap = new HashMap<String, File>();
        visitAllDirsAndFiles(dir, nurPrimaerDateien);
        return fileMap;
    }
    
    
    // Process all files and directories under dir
    public static void visitAllDirsAndFiles(File dir, boolean nurPrimaerDateien) {
        
        String filePath = dir.getAbsolutePath();
        filePath = filePath.replace(originalPath, "");
        
        if (filePath.length() > 0) {
            filePath = filePath.replaceAll("\\\\", "/");
            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }
            if (dir.isDirectory() && !filePath.endsWith("/")) {
                filePath += "/";
            }
            
            if (nurPrimaerDateien) {
                if (filePath.contains("content/") && !dir.isDirectory()) {
                    fileMap.put(filePath, dir);
                }
                
            } else {
                fileMap.put(filePath, dir);
            }
            
            
        }
               
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]), nurPrimaerDateien);
            }
        }
    }

    
    
}
