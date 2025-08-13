@echo off
echo Compiling Java files...
javac *.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful. Running program...
    java Main
) else (
    echo Compilation failed. Please check your code for errors.
)
pause