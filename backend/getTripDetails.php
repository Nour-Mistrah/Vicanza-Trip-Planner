<?php
require_once __DIR__ . "/db_config.php";

if ($conn->connect_error) {
    die("Database connection failed");
}

$sql = "SELECT * FROM trip"; // Modify this query as needed
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $data = array();
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    echo json_encode($data); // Convert data to JSON
} else {
    echo json_encode([]);
}

$conn->close();
?>