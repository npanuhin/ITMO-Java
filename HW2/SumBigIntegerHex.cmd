@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" SumBigIntegerHex.java && (
	java -cp "C:\Cache" SumBigIntegerHex 1 2 3
	java -cp "C:\Cache" SumBigIntegerHex 1 2 -3
	java -cp "C:\Cache" SumBigIntegerHex "1 2 3"
	java -cp "C:\Cache" SumBigIntegerHex "1 2" " 3"
	java -cp "C:\Cache" SumBigIntegerHex " "
	java -cp "C:\Cache" SumBigIntegerHex -1 +2 3
	java -cp "C:\Cache" SumBigIntegerHex " 0X0 0X1 0XF 0XF 0x0 0x1 0xF 0xf"
	java -cp "C:\Cache" SumBigIntegerHex "0x12345678"
)
