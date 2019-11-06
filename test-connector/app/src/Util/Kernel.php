<?php
namespace DevGame\TestConnector\Util;

use DevGame\TestConnector\Util\DatabaseConnector;

/*
 * Simple 'kernel' to match requests to the corresponding controller actions
 */
class Kernel
{
    public static function main(): void
    {
        $db_handle = DatabaseConnector::connectToDatabase();
        if (null !== $db_handle) {
            error_log("Connection to database established successfully.");
        } else {
            error_log("Connection to database failed!");
            exit(1);
        }

        // TODO: Add route '/api/hook' and insert the metrics into the database
    }
}
