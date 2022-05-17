#!/bin/bash

rm -rf dist/

npm install

npm run build || {
  echo "Frontend build failed, exiting!"
  exit 1
}

rm -r ../backend/src/main/resources/dist/

mv dist ../backend/src/main/resources/

exit 0