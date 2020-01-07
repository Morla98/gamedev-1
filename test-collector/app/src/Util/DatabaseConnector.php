<?php
namespace DevGame\TestConnector\Util;

use DevGame\TestConnector\Model\Configuration;

class DatabaseConnector
{
    public static function connectToDatabase()
    {
        $connection_string = sprintf(
            "host='%s' port='%d' dbname='%s' user='%s' password='%s'",
            Configuration::DB_HOST,
            Configuration::DB_PORT,
            Configuration::DB_NAME,
            Configuration::DB_USER,
            Configuration::DB_PASS
        );

        // error_log(sprintf("Connecting to database with connection string: %s.", $connection_string));

        return @\pg_connect($connection_string);
    }
}
