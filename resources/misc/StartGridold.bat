@echo off

Set "ExecSqlDir=%userprofile%
cd ..
cd ..
rem Set /p "Role=Please enter what role :  "
Set /p "IP= Please the hub IP address : "
Set /p "Session= Please the Number browser instances : "


java -jar selenium-server-standalone-2.17.0.jar -role %Role% -hub http://%IP%:4444/grid/register -maxSession %Session%




echo.
echo Done.

pause
goto :EOF ______________________________________________________________


goto :EOF