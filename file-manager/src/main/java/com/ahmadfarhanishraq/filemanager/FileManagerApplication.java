package com.ahmadfarhanishraq.filemanager;
//package com.ahmadfarhanishraq.restservicecors;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
@RestController
@CrossOrigin(origins = "http://localhost:3006")
@SpringBootApplication
public class FileManagerApplication {

	String root = System.getProperty("user.dir")+"/Projects";
	FileManager file_manager = new FileManager(root);
	GithubManager github_manager = new GithubManager();
	ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/")
	public String home() {
		String root_path = file_manager.get_root_dir();
		file_manager.change_cur_dir(root_path);
		ResponseObj responseObj = new ResponseObj(root_path, file_manager.cur_dir, file_manager.list_directory(root_path), -1);
		try{
			String response = objectMapper.writeValueAsString(responseObj);
			return response;
		}catch (Exception e){
			return "Error Occured";
		}
	}
	@GetMapping("/forward-nav/{dir}")
	public String forward_nav(@PathVariable("dir") String dir){
		String root_path = file_manager.get_root_dir();
		String cur_dir = file_manager.change_cur_dir(dir);
		ResponseObj responseObj = new ResponseObj(root_path, cur_dir, file_manager.list_directory(cur_dir),-1 );
		try{
			String response = objectMapper.writeValueAsString(responseObj);
			return response;
		}catch (Exception e){
			return "Error Occured";
		}
	}
	@GetMapping("/mkdir/{dir_name}")
	public String mkdir(@PathVariable("dir_name") String dir_name){
		String root_dir = file_manager.get_root_dir();
		String cur_dir = file_manager.cur_dir;
		int mkdir_status = file_manager.mkdir(dir_name);
		ResponseObj responseObj = new ResponseObj(root_dir, cur_dir, file_manager.list_directory(cur_dir), mkdir_status );
		try{
			String response = objectMapper.writeValueAsString(responseObj);
			return response;
		}catch (Exception e){
			return "Error Occured";
		}
	}
	@GetMapping("/launch-terminal")
	public Map<String, String> launchTerminal(){
		Map<String, String> response = new HashMap<>();
		try{
			file_manager.launchTerminalAtPath();
			response.put("message", "success");
			return response;
		} catch (Exception e){
			response.put("message", "failure");
			return response;
		}
	}
	@GetMapping("/launch-vscode")
	public Map<String, String> vsCodeLaunch(){
		Map<String, String> response = new HashMap<>();
		try{
			file_manager.launchCodeEditor();
			response.put("message", "success");
			return response;
		} catch (Exception e){
			response.put("message", "failure");
			return response;
		}
	}
	@GetMapping("/launch-browser")
	public Map<String, String> launchBrowser(){
		Map<String, String> response = new HashMap<>();
		try{
			file_manager.launchBroswerWindowAtPath();
			response.put("message", "success");
			return response;
		} catch (Exception e){
			response.put("message", "failure");
			return response;
		}
	}
	@PostMapping("/clone")
	public String clone_git_repo(@RequestBody Map<String,String> req){
		String remote = req.get("remote");
		String cur_dir = file_manager.cur_dir;
		String root_dir = file_manager.get_root_dir();
		int clone_status = github_manager.git_clone(remote, cur_dir);
		ResponseObj responseObj = new ResponseObj(root_dir, cur_dir, file_manager.list_directory(cur_dir),clone_status );
		try{
			String response = objectMapper.writeValueAsString(responseObj);
			return response;
		}catch (Exception e){
			return "Error Occured";
		}
	}
	@PostMapping("/push-changes")
	public String push_changes_to_git(@RequestBody Map<String,String> req){
		String message = req.get("commit_message");
		String cur_dir = file_manager.cur_dir;
		String root_dir = file_manager.get_root_dir();
		int change_status = github_manager.push_changes_to_github(message, cur_dir);
		ResponseObj responseObj = new ResponseObj(root_dir, cur_dir, file_manager.list_directory(cur_dir),change_status );
		try{
			String response = objectMapper.writeValueAsString(responseObj);
			return response;
		}catch (Exception e){
			return "Error Occured";
		}
	}
	@PostMapping("/create-repo")
	public String create_first_repo(@RequestBody Map<String,String> req){
		String remote = req.get("remote");
		String cur_dir = file_manager.cur_dir;
		String root_dir = file_manager.get_root_dir();
		int git_status = github_manager.create_new_repo(remote, cur_dir);
		ResponseObj responseObj = new ResponseObj(root_dir, cur_dir, file_manager.list_directory(cur_dir),git_status );
		try{
			String response = objectMapper.writeValueAsString(responseObj);
			return response;
		}catch (Exception e){
			return "Error Occured";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(FileManagerApplication.class, args);
	}

}
