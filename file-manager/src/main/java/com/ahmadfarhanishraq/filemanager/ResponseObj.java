package com.ahmadfarhanishraq.filemanager;

public class ResponseObj {
    public String cur_dir, root_dir;
    public int success = -1;
    public String[] message;

    public ResponseObj(String root_dir, String cur_dir, String[] message, int success){
        this.root_dir = root_dir;
        this.cur_dir = cur_dir;
        this.message = message;
        this.success = success;
    }
    public String getRoot_dir(){
        return root_dir;
    }
    public String getCur_dir(){
        return cur_dir;
    }
    public String[] getMessage(){
        return message;
    }
    public int getSuccess(){
        return success;
    }
}
