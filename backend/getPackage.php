<?php
require_once __DIR__ . "/db_config.php"; $connection = $conn;

if ($connection->connect_error) {
    die("Database connection failed");
}

if (isset($_GET['package_id'])) {
    $package_id = intval($_GET['package_id']);  // Convert to int for security
    $query = "SELECT * FROM packages WHERE Packageid = ?";
    $stmt = $connection->prepare($query);
    $stmt->bind_param("i", $package_id);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        echo json_encode($result->fetch_assoc());
    } else {
        echo json_encode(["error" => "No package found"]);
    }

    $stmt->close();
}

$connection->close();
?>