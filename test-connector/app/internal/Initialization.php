<?php
namespace DevGame\TestConnector;

require __DIR__ . '/../vendor/autoload.php';

use DevGame\TestConnector\Model\Configuration;
use DevGame\TestConnector\Util\DatabaseConnector;

// Maybe do not fire up the kernel here?

print("\n");
print("This is the connector initialization script.\n");
print("\n");

print("\n");
print("Loaded configuration:\n");
var_dump((new \ReflectionClass(Configuration::class))->getConstants());

print("\n");
print("Testing database connection...\n");

$dbHandle = DatabaseConnector::connectToDatabase();

if (false !== $dbHandle) {
    print("Connection established successfully.\n");
} else {
    print("Could not establish connection to database!\n");
    print("Exiting.\n");
    print("If started inside a conainter with 'restart:always', this will cause the container to restart.\n");
    exit(1);
}

// Register myself
print("\n");
print(sprintf("Self-registering as '%s' connector...\n", Configuration::NAME));
$result = \pg_query(sprintf("SELECT main.\"UPDATE_CONNECTOR\"('%s');", Configuration::NAME)) or die('Self-registering failed: '. pg_last_error());

// Create own tables
print("\n");
print("Creating my metrics table...");
$sql = "CREATE TABLE IF NOT EXISTS metrics.\"test_connector\" (id SERIAL NOT NULL PRIMARY KEY);";
$result = \pg_query($sql) or die('Creating my metrics table failed: ' . pg_last_error());

print("\n");
print("All checks and setup done, proceeding with starting the webserver.\n");
