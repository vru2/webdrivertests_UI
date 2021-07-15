@echo OFF
cd \
java -jar selenium-server-standalone-2.24.1.jar -role node -hub http://172.16.63.115:4444/grid/register -maxSession 1
echo.
echo Done.

pause
goto :EOF ______________________________________________________________


goto :EOF