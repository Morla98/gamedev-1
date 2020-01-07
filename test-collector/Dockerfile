FROM debian:latest

# Setup timezone
ENV TZ=Europe/Berlin
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Install apache and php7.3 with required extensions from debian sources
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y \
    php7.3 \
    php7.3-pgsql

# Copy application
COPY ./app /app

# Copy entrypoint wrapper
COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN ln -s /usr/local/bin/docker-entrypoint.sh /

ENTRYPOINT ["docker-entrypoint.sh"]

# Set cmd
CMD ["php", "-S", "0.0.0.0:80", "-t", "/app/public"]
