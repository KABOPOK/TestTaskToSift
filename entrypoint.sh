#!/bin/bash
if [[ -f args.txt ]]; then
  ARGS=$(cat args.txt)
  echo "Запущено с аргументами: $ARGS"
  java -jar /app/byteProgramm.jar $ARGS
else
  echo "Файл args.txt не найден!"
  exit 1
fi
