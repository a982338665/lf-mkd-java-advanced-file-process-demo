package pers.li.io;

import java.util.*;
import java.io.*;  
public class JavaFilter implements FilenameFilter{  
  
    public boolean containUDDI(String file){  
    return file.contains(".java");  
    }  
  
    public boolean accept(File dir, String name) {
        return containUDDI(name);
    }
}  