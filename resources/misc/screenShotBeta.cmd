net use Z: \\172.16.56.161\ScreenShots /user:172.16.56.161\administrator itsme123
robocopy "\\172.16.56.161\ScreenShots" "C:\Program Files\Go Agent\pipelines\beta_sanity\ScreenShots" /V /NP /R:1 /W:3

net use Y: \\172.16.56.162\ScreenShots /user:172.16.56.162\administrator itsme123
robocopy "\\172.16.56.162\ScreenShots" "C:\Program Files\Go Agent\pipelines\beta_sanity\ScreenShots" /V /NP /R:1 /W:3

net use X: \\172.16.56.163\ScreenShots /user:172.16.56.163\administrator itsme123
robocopy "\\172.16.56.163\ScreenShots" "C:\Program Files\Go Agent\pipelines\beta_sanity\ScreenShots" /V /NP /R:1 /W:3
