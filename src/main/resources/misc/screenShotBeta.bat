@echo off

for %%T in (
172.16.56.161
172.16.56.162
172.16.56.163
) do (
net use \\%%T\ipc$ /user:cleartrip itsme123
robocopy "\\%%T\ScreenShots" "C:\Program Files\Go Agent\pipelines\beta_sanity\ScreenShots" /V /NP /R:10 /W:30 /B
)
::timeout /t 10
::net use \\Betanode2\ipc$ /user:administrator itsme123
::robocopy "\\Betanode2\ScreenShots" "C:\bac" /V /NP /R:10 /W:30 /B
::pause