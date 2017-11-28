/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import view.MainView;

/**
 *
 * @author jsantana
 */
public class MainController {

    public static void generateRegistryFile(String protocol, String path) {
        File file = new File("./");
        try {
            String text = getRegistryFileText(protocol,path);
            System.out.println(text);
            BufferedWriter bw = new BufferedWriter(new FileWriter(protocol + ".reg"));
            bw.write(text);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

        private static String getRegistryFileText(String protocol, String path) {
        return "Windows Registry Editor Version 5.00\n" +
                 "\n[HKEY_CLASSES_ROOT\\" + protocol + "]\n" +
                "@=\"URL: " + protocol + " Protocol\"" +
                "\n\"URL Protocol\"=\"\" " +
                /*
                "\n\n\"[HKEY_CLASSES_ROOT\\" + protocol + "\\DefaultIcon]" + 
                "@=\" + " + path + "\""  +     
                */
                
                "\n\n" + "[HKEY_CLASSES_ROOT\\" + protocol + "\\shell]" + 
                "\n\n" + "[HKEY_CLASSES_ROOT\\" + protocol + "\\shell\\open]" +
                "\n\n" + "[HKEY_CLASSES_ROOT\\" + protocol + "\\shell\\open\\command]" +
                "\n" + "@=\"" + path + "   \\\"%1\\\"\"" ; 
    }

    public static void choosePath(JTextField pathTextField, MainView view) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION){
            String path = fileChooser.getSelectedFile().getPath();
            pathTextField.setText(path);
        }    
    }
}
