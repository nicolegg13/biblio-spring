@echo off
setlocal enabledelayedexpansion

REM Caminho até a pasta 'main' onde estão os arquivos .java e .jsp
cd /d "C:\Users\Cliente\Desktop\NICOLE\UFSM\spi ufsm\[03] POO WEB I\aula-spring-mvc"

set "PASTA=."
set "ARQUIVO_SAIDA=resultado.txt"

REM Apagar arquivo anterior, se existir
if exist "%ARQUIVO_SAIDA%" del "%ARQUIVO_SAIDA%"

REM Percorrer todos os arquivos .java e .jsp na pasta e subpastas
for /R "%PASTA%" %%F in (*.java *.jsp *.xml *.cmd) do (
    echo - Endereço: %%F:>>"%ARQUIVO_SAIDA%"
    type "%%F" >>"%ARQUIVO_SAIDA%"
    echo.>>"%ARQUIVO_SAIDA%"
    echo.>>"%ARQUIVO_SAIDA%"
)

echo Finalizado. Arquivo gerado: %CD%\%ARQUIVO_SAIDA%
pause
