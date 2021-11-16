@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" "markup/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "Markup.java" && (
    java -cp "C:\Cache" Markup
)
