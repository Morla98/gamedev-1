#!/bin/bash

set -e

# When container is started with "php -S ...", execute initialization script before
if [ "$1" = 'php' ]; then
  php -f /app/internal/Initialization.php
  echo "" # Flush output cache, kinda mystery
fi

# Continue with the original command
exec "$@"
