@echo off
move C:\Windows\System32\drivers\etc\hosts C:\Windows\System32\drivers\etc\hosts.bak
echo 127.0.0.1	localhost >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.6.240	beta.bh.cleartrip.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.6.240	beta.kw.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.6.240	beta.qa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.6.240	beta.om.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts  
echo 172.16.6.240	beta.sa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts  
echo 172.16.53.134 apiqa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts  
exit