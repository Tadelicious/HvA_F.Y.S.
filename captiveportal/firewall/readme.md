<h1>HOW TO USE</h1>
Place the 2 files in the same folder (I recommend the root folder "/")<br>
You can initialize the script by typing: ```bash /rules.sh initialize```<br>
You can grand an ip access to the internet by typing: ```bash rules.sh grand XXX.XXX.XXX.XXX``` (The ip adrress)<br>
If you want the script to run at system stratup edit the /etc/rc.local and add <strong>before</strong> "exit 0" this code:<br>
```bash /rules.sh initialize```