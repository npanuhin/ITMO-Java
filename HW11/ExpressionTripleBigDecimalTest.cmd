@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "..\tests\java\base\*.java" && ^
xcopy /s /y /q "..\tests\java\expression\common\" "C:\Cache\.src\" > nul && ^
xcopy /s /y /q "..\tests\java\expression\" "C:\Cache\.src\" > nul && ^
xcopy /s /y /q "expression\" "C:\Cache\.src\" > nul && ^
javac -cp "C:\Cache" -d "C:\Cache" "C:\Cache\.src\*.java" && ^
java -cp "C:\Cache" -ea expression.ExpressionTest hard Triple BigDecimal
