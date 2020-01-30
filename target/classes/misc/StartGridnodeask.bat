@echo OFF
Set "startPath=%userprofile%
cd ..
cd ..
cd ..
dir
Set /p "IP= Please enter hub IP address (172.16.X.X) : "
Set /p "Session= Please enter Number browser instances (Max 2) : "
echo java -jar selenium-server-standalone-2.24.1.jar -role node -hub http://%IP%:4444/grid/register 
java -jar selenium-server-standalone-2.24.1.jar -role node -hub http://%IP%:4444/grid/register -maxSession %Session%
echo.
echo Done.

pause
goto :EOF ______________________________________________________________


goto :EOF