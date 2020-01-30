:: Set primary and alternate DNS for Windows XP
@ECHO OFF
netsh interface ip set dns "Local Area Connection" static 172.16.56.30 primary
echo Primary DNS is Set
netsh interface ip  add dns "Local Area Connection" 172.16.56.31 index=2
echo Alternate DNS is Set
)
ipconfig /flushdns
netsh interface ip show config 
:EOF 
