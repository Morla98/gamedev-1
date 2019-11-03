#!/bin/bash

set -e

# When container is started with "php -S ...", execute initialization script before to test connection, register connector etc.
if [ "$1" = 'php' ]; then
    php -f /app/internal/Initialization.php
    echo "" # Flush output cache
fi

# Continue with the original command
exec "$@"
