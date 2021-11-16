@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "markup/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/markup/AbstractTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/markup/MarkdownTest.java" && ^
java -cp "C:\Cache" markup.MarkdownTest
