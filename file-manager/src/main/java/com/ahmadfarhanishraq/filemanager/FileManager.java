package com.ahmadfarhanishraq.filemanager;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class FileManager {
    private String root;
    public String cur_dir;

    public FileManager(String root){
        this.root = root;
        this.cur_dir = root;
    }
    public String get_root_dir(){
        return root;
    }
    public String[] list_directory(String path){
        File directory = new File(path);
        String[] fileList = directory.list();
        return fileList;
    }
    public String change_cur_dir(String path){
        /*
        * Given a directory, cd in the directory
        return the directory path
        * */
        if(path == this.root){
            System.setProperty("user.dir", path);
            this.cur_dir = path;
            return path;
        }
        String forward_path = this.cur_dir+'/'+path;
        System.setProperty("user.dir", forward_path);
        this.cur_dir = forward_path;
        return forward_path;
    }
    public int mkdir(String dir_name){
        String cur_dir = this.cur_dir;
        String path_to_dir = cur_dir + "/"+dir_name;
        try{
            new File(path_to_dir).mkdir();
            return 1;
        } catch (Exception i){
            return 0;
        }
    }

    public void launchTerminalAtPath(){
        String cur_dir = this.cur_dir;
        try {
            String cmd = "open -a Terminal  "+cur_dir;
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void launchBroswerWindowAtPath(){
        String cur_dir = this.cur_dir;
        try {
            String cmd = "open "+cur_dir;
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void launchCodeEditor(){
        String cur_dir = this.cur_dir;
        try {
            String cmd = "code "+cur_dir;
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
