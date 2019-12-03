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
        


        switch($_SERVER['REQUEST_URI']) {
            case '/hook/jira':
                new JiraHookController($dbhandle)->main();
                return;
            default:
                throw new \RuntimeException('Not found!');
        }
    }
}
