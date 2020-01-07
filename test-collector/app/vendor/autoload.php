<?php

class Psr4AutoloaderClass
{
    protected static $prefixes = [
        'DevGame\\TestConnector\\' => [ __DIR__ . '/../src/' ]
    ];

    /**
     * Register loader with SPL autoloader stack.
     *
     */
    public static function register(): void
    {
        spl_autoload_register(array(self::class, 'loadClass'));
    }

    /**
     * Loads the class file for a given class name.
     */
    public static function loadClass(string $className): void
    {
        foreach(self::$prefixes as $namespacePrefix => $possibleBaseDirectories) {

            // Check if current {$namespacePrefix} matches
            if (strncmp($namespacePrefix, $className, strlen($namespacePrefix)) !== 0) {
                continue;
            }

            // Cut off the prefix
            $relativeClassName = substr($className, strlen($namespacePrefix));

            // Try out the possible base include directories for this namespace prefix
            foreach($possibleBaseDirectories as $baseDirectory) {
                // Concatenate expected filename
                $expectedClassFile = $baseDirectory . str_replace('\\', DIRECTORY_SEPARATOR, $relativeClassName) . '.php';

                // Check and require file, if exists
                if (file_exists($expectedClassFile)) {
                    require($expectedClassFile);
                    return; // We do not have to search in other possible base directories nor for other prefix matches
                }
            }
        }
    }
}

Psr4AutoloaderClass::register();
