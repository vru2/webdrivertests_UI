:: Set primary and alternate DNS for Windows XP
@ECHO OFF
netsh interface ip delete dns "Local Area Connection" all
echo Remove DNS
netsh interface ip set dns "Local Area Connection" static 172.16.56.200 primary
echo Primary DNS is Set
)
ipconfig /flushdns
netsh interface ip show config 
:EOF 
