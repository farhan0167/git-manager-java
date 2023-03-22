package com.ahmadfarhanishraq.filemanager;
import java.io.File;
import java.net.URI;

public class GithubManager {
    public int git_clone(String remote, String cur_dir){
        String[] cmd = {"git", "clone", remote};
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(cmd);
        pb.directory(new File(cur_dir));
        try {
            Process p = pb.start();
            p.waitFor();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int push_changes_to_github(String message, String cur_dir){
        String[][] commands = {
                {"git", "add", "."},
                {"git", "commit", "-m", message},
                {"git", "push","origin"}
        };
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(cur_dir));
        for (int cmd=0; cmd<commands.length; cmd++){
            pb.command(commands[cmd]);
            try {
                Process p = pb.start();
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 1;
    }
    public int create_new_repo(String remote, String cur_dir){
        String readme_header = "# Repo README";
        String[][] commands = {
                {"echo", readme_header, ">>README.md"},
                {"git", "init"},
                {"git", "add README.md"},
                {"git", "add", "."},
                {"git", "commit", "-m", "first commit"},
                {"git", "branch", "-M", "main"},
                {"git", "remote", "add", "origin", remote},
                {"git", "push", "-u", "origin", "main"},
        };
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(cur_dir));
        for (int cmd=0; cmd<commands.length; cmd++){
            pb.command(commands[cmd]);
            try {
                Process p = pb.start();
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 1;
    }
}
