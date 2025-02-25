#!/bin/bash

# Check if .env file exists
if [[ ! -f .env ]]; then
    echo "Error: .env file not found!"
    exit 1
fi

TEST_SUBJ=${1:""}

# Backup the original Dockerfile
cp Dockerfile.test Dockerfile.testbak

# Loop through each line in the .env file
while IFS='=' read -r key value; do
    # Skip empty lines or comments
    [[ -z "$key" || "$key" =~ ^# ]] && continue

    # Escape special characters in the value for safe replacement
    value=$(echo "$value" | sed -e 's/[\/&]/\\&/g')

    # Replace the value in Dockerfile (assumes ENV VAR_NAME=...)
    sed -i "s|^ENV $key=\${$key}|ENV $key=$value|g" Dockerfile.test
done < .env

echo "Dockerfile updated with values from .env!"

docker build -t kitotesti -f Dockerfile.test .
# docker build -t kitotesti -f Dockerfile.test --build-arg TEST_SUBJ="$TEST_SUBJ" .

cp Dockerfile.testbak Dockerfile.test

rm Dockerfile.testbak

docker image rm kitotesti

exec bash

# mvn test -Dtest=ClassName#method