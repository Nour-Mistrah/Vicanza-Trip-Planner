<?php
$host = getenv('DB_HOST') ?: '127.0.0.1';
$port = (int) (getenv('DB_PORT') ?: 3307);
$dbname = getenv('DB_NAME') ?: 'mydatabase';
$username = getenv('DB_USER') ?: 'root';
$password = getenv('DB_PASSWORD') ?: '';

$conn = new mysqli(
    $host,
    $username,
    $password,
    $dbname,
    $port
);

$conn->set_charset('utf8mb4');
