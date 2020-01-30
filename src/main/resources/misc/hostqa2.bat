@echo off
move C:\Windows\System32\drivers\etc\hosts  C:\Windows\System32\drivers\etc\hosts.bak
echo 127.0.0.1	localhost >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.231 automationqa2.cleartripforbusiness.com  >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.231 automationqa2ae.cleartripforbusiness.ae  >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.232 automationqa_2.agentbox.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.232  qa2.agentbox.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.232 suvidhaawl.agentbox.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.232 suvidhaa-ctagwl-phase2retest.agentbox.com >>  %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.238 www.cleartrip.ae >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.230 om.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts  
echo 172.16.53.230 bh.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.53.230 kw.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.53.230 qa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.53.230 sa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts 
echo 172.16.53.134 apiqa.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts
::echo 172.16.53.230 www.cleartrip.com >> %systemroot%\system32\drivers\etc\hosts
echo 172.16.53.230 www.cleartrip.com   >>  %systemroot%\system32\drivers\etc\hosts
exit  
