@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" ReverseSum2.java && (
    (
        echo 1 2
        echo 3
    ) | java -cp "C:\Cache" ReverseSum2
    (
        echo 3
        echo 2 1
    ) | java -cp "C:\Cache" ReverseSum2
    (
        echo 1
        echo 
        echo 2 -3
    ) | java -cp "C:\Cache" ReverseSum2
    (
        echo 1     2
        echo 3     4
    ) | java -cp "C:\Cache" ReverseSum2
)
