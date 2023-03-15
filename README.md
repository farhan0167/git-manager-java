# Git Manager

Ever found yourself managing multiple projects spread across your computer and writing multiple git commands on the terminal when all you want is to push
your code on Github? As a software engineer and graduate student, I'm always working on projects and I often place these projects in the first directory I find. This 
leads to a decrease in productivity searching within your file system for your projects. While this can be mitigated by trying to be more organized but then we're hit
with another drag- writing git commands. For instance, to push changes you'd need to type the following commands:
```bash
git add .
git commit -m "commit msg"
git push origin
```
As a result, I developed a file/git manager that streamlines this process for you. This is a web application that:
1. Organizes all your projects in one directory
2. Makes interacting with Git as simple as clicking a button

To demo the application, please follow the steps below:
1. Clone the repository in your local machine:
   ```bash
   git clone https://github.com/farhan0167/git-manager-java
   ```
2. There are two directories, my-app and file-manager.
3. You want to install npm serve, this will let you serve the static build of my React application
   ```bash
   npm install --global serve
   ```
   Note: Make sure you have node installed in your machine.
4. The application backend, aka file-manager, runs on Java so make sure to have installed Java SDK.
5. Once done, cd in to the cloned repository, and run the following command:
    ```bash
   python3 startup-script.py
   ```
   This will start both the React application and the Java Spring Boot server.
6. Head over to `http:localhost:3006` to check out the application.

