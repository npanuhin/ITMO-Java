@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "game/*.java" && ^
java -cp "C:\Cache" game.Main
