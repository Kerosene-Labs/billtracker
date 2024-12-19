#!/bin/bash

npx openapi-generator-cli generate -i http://localhost:8080/v3/api-docs.yaml -g typescript-fetch -o src/lib/sdk