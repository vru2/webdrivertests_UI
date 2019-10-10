@echo off
move C:\Windows\System32\drivers\etc\hosts C:\Windows\System32\drivers\etc\hosts.bak
echo 127.0.0.1	localhost >>  %systemroot%\system32\drivers\etc\hosts
exit