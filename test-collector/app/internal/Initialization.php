<?php
namespace DevGame\TestConnector;

require __DIR__ . '/../vendor/autoload.php';

use DevGame\TestConnector\Model\Configuration;
use DevGame\TestConnector\Util\DatabaseConnector;

function println(string $msg) {
    print($msg . "\n");
}

println('');
println("This is the connector initialization script.");
println('');

println('');
println("Loaded configuration:");
var_dump((new \ReflectionClass(Configuration::class))->getConstants());

println('');
println("Testing database connection...");

$dbHandle = DatabaseConnector::connectToDatabase();

if (false !== $dbHandle) {
    println("Connection established successfully.");
} else {
    println("Could not establish connection to database!");
    println("Exiting.");
    println("If started inside a container with 'restart:always', this will cause the container to restart.");
    exit(1);
}

$result = \pg_query(file_get_contents(sprintf('%s/db.sql', __DIR__ ))) or die('Sett up up the database failed: ' . pg_last_error());

println('');
println("All checks and setup done, proceeding with starting the webserver.");
