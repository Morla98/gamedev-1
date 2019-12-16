<?php
/**
 * Script for generating or updating user-achievements
 * To be called by cron daemon
 */
namespace DevGame\TestConnector;

require __DIR__ . '/../vendor/autoload.php';

use DevGame\TestConnector\Logic\AchievementLogicInterface;
use DevGame\TestConnector\Logic\JiraNoob;
use DevGame\TestConnector\Logic\NotMyJob;
use DevGame\TestConnector\Util\DatabaseConnector;

// Connect to database
$dbHandle = DatabaseConnector::connectToDatabase();
if (null === $dbHandle) {
    throw new \RuntimeException('Error connecting to the database!');
}

/**
 * For each achievement: Call its specific generateOrUpdate user-achievement logic
 *
 * @var $achievementClass AchievementLogicInterface
 */
foreach([JiraNoob::class, NotMyJob::class] as $achievementClass) {
    print(sprintf("Updating user-achievements for achievement %s ... ", $achievementClass));
    $ret = $achievementClass::generateOrUpdate($dbHandle);
    print(sprintf("done. %d affected. \n", $ret));
}
