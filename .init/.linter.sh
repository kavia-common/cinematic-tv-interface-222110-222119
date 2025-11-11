#!/bin/bash
cd /home/kavia/workspace/code-generation/cinematic-tv-interface-222110-222119/tv_app_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

