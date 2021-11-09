@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && javac -cp "C:\Cache" -d "C:\Cache" WsppSortedSecondG.java && "../src/run_jar" "%~dp0/tests/WsppSortedSecondGTest.jar"
