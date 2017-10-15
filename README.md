# DriveSafe : HACKUPC-2017


## About
> The project was created during the UPCHackathon-2017. This is an android application which reads user context information to identify the user's activity. The applicaiton was meant for the identification of drivers who uses phone while driving. The current version simply uses the Android Awareness API to identify the user's activity and update the interface (for testing purposes). 

## Bugs
Application also contains some bugs which are needed to be resolved. The current implementation request the context awareness very frequently. The awareness api implements a listener which waits quite a while to return the result. As a result UI faces issues at some points.
