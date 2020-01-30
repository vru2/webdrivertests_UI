@echo off

Set "ExecSqlDir=%userprofile%
cd ..
cd ..
Set /p "IP= Please the hub IP address : "
Set /p "Session= Please the Number browser instances : "

echo java -jar selenium-server-standalone-2.20.0.jar -role node -hub http://%IP%:4444/grid/register 
java -jar selenium-server-standalone-2.20.0.jar -role node -hub http://%IP%:4444/grid/register -maxSession %Session%




echo.
echo Done.

pause
goto :EOF ______________________________________________________________


goto :EOF