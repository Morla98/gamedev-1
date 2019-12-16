<?php
namespace DevGame\TestConnector\Logic;

interface AchievementLogicInterface
{
    /**
     * Generates or updates user-achievements
     *
     * @param $dbHandle object Handle to the database
     * @return int The amount of generated or updated user-achievements
     */
    public static function generateOrUpdate($dbHandle): int;
}
