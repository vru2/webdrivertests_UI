@echo off
move C:\Windows\System32\drivers\etc\hosts  C:\Windows\System32\drivers\etc\hosts.bak
echo 127.0.0.1	localhost >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.231 automationstg.cleartripforbusiness.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.232 automationstg1.agentbox.com >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.231 demo.cleartripforbusiness.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.231 automationstg1.cleartripforbusiness.ae >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.232 traveldemo.agentbox.com >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.232 suvidhaawl.agentbox.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.50.232 suvidhaa-ctagwl-staging631.agentbox.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.50.238 www.cleartrip.ae >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.50.230 om.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts  
echo 172.16.50.230 bh.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.50.230 kw.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.50.230 qa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.50.230 sa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
exit