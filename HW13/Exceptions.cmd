@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "..\tests\java\base\*.java" && ^
xcopy /s /y /q "..\tests\java\expression\common\" "C:\Cache\.src\" > nul && ^
xcopy /s /y /q "..\tests\java\expression\" "C:\Cache\.src\" > nul && ^
xcopy /s /y /q "..\HW11\expression\" "C:\Cache\.src\" > nul && ^
xcopy /s /y /q "..\HW12\expression\parser\" "C:\Cache\.src\parser\" > nul && ^
xcopy /s /y /q "expression\" "C:\Cache\.src\" > nul && ^
xcopy /s /y /q "expression\exceptions\" "C:\Cache\.src\exceptions\" > nul && ^
xcopy /s /y /q "..\myclasses\" "C:\Cache\.src\" > nul && ^
javac -cp "C:\Cache" -d "C:\Cache" "C:\Cache\.src\*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "C:\Cache\.src\parser\*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "C:\Cache\.src\exceptions\*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "Main.java" && ^
java -cp "C:\Cache" Main
