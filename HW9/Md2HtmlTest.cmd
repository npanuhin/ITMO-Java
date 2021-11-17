@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../HW7/markup/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "md2html/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "../tests/java/md2html/Md2HtmlTest.java" && ^
java -cp "C:\Cache" md2html.Md2HtmlTest
