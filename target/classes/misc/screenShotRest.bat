@echo off

for %%T in (
172.16.56.152
172.16.56.153
172.16.56.154
172.16.56.155
172.16.56.156
172.16.56.157
172.16.56.158
) do (
net use \\%%T\ipc$ /user:cleartrip itsme123
robocopy "\\%%T\ScreenShots" "C:\Program Files\Go Agent\pipelines\Trunk_SeleniumTests\ScreenShots" /V /NP /R:10 /W:30 /B
)
::timeout /t 10
::net use \\Betanode2\ipc$ /user:administrator itsme123
::robocopy "\\Betanode2\ScreenShots" "C:\bac" /V /NP /R:10 /W:30 /B
::pause
