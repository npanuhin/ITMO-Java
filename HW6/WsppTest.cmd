@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wordStat/WordStatChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wspp/WsppTester.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/wspp/WsppTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" Wspp.java && ^
java -cp "C:\Cache" wspp.WsppTest Base
