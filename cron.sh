#!/bin/bash
#
# Register cron entries here
#

# test-collector
docker-compose exec test-collector php -f /app/internal/GenerateAchievements.php
