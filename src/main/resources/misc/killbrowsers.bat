@echo off
:loop
rem kill the task
taskkill /F /IM firefox.exe 2> nul
taskkill /F /IM firefox.exe 2> nul
rem check the exit status
if errorlevel 128 goto done
goto loop
:done

:loop
rem kill the task
taskkill /F /IM chrome.exe 2> nul
taskkill /F /IM chrome.exe 2> nul
rem check the exit status
if errorlevel 128 goto done
goto loop
:done

:loop
rem kill the task
taskkill /F /IM safari.exe 2> nul
taskkill /F /IM safari.exe 2> nul
rem check the exit status
if errorlevel 128 goto done
goto loop
:done

:loop
rem kill the task
taskkill /F /IM iexplore.exe 2> nul
taskkill /F /IM iexplore.exe 2> nul
rem check the exit status
if errorlevel 128 goto done
goto loop
:done



