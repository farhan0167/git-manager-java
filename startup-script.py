import subprocess
import threading
import time
import os

def startReact():
    print("Starting React")
    path = os.getcwd()+"/my-app"
    print("React Server running...")
    while True:
        #subprocess.run(['npm','install', 'serve'], capture_output=True, cwd=path)
        subprocess.run(['serve', 'build', '-p', '3006'], capture_output=True, cwd=path)
def startFlask():
    print("Starting Server")
    path = os.getcwd()+"/file-manager/target"

    while True:
        subprocess.run(["java", "-jar", "file-manager-0.0.1-SNAPSHOT.jar"], capture_output=True, cwd=path)

Thread1 = threading.Thread(target=startReact)
Thread2 = threading.Thread(target=startFlask)

Thread1.start()
Thread2.start()
