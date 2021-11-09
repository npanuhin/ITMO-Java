@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" Sum.java && (
    java -cp "C:\Cache" Sum 1 2 3
    java -cp "C:\Cache" Sum 1 2 -3
    java -cp "C:\Cache" Sum "1 2 3"
    java -cp "C:\Cache" Sum "1 2" " 3"
    java -cp "C:\Cache" Sum " "
    java -cp "C:\Cache" Sum -1 +2 3
)
