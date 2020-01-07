<?php
namespace DevGame\TestConnector\Util;

/*
 * Simple 'kernel' to match requests to the corresponding controller actions
 */

use DevGame\TestConnector\Controller\JiraHookController;

class Kernel
{
    public static function main(): void
    {
        $dbHandle = DatabaseConnector::connectToDatabase();
        if (null === $dbHandle) {
            throw new \RuntimeException('Error connecting to the database!');
        }

        switch($_SERVER['PATH_INFO']) {
            case '/hook/jira':
                (new JiraHookController($dbHandle))->main();
                return;
            default:
                throw new \RuntimeException('Not found!');
        }
    }
}
