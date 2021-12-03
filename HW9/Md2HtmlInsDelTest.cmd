@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "../HW7/markup/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "md2html/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "../tests/java/md2html/Md2HtmlTester.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding "utf-8" "../tests/java/md2html/Md2HtmlTest.java" && ^
java -cp "C:\Cache" md2html.Md2HtmlTest InsDel
