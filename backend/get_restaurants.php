<?php



error_reporting(E_ALL);
ini_set('display_errors', 0);

require_once __DIR__ . "/db_config.php";

if ($conn->connect_error) {
    die(json_encode(["error" => "Database connection failed"]));
}


$sql = "SELECT * FROM restaurants";
$result = $conn->query($sql);

$restaurants = array();

if ($result->num_rows > 0) {
    // Fetch all rows
    while ($row = $result->fetch_assoc()) {
        $restaurants[] = $row;
    }
    // Encode and output the hotels data as JSON
    echo json_encode($restaurants);
} else {
    // Return a message if no records are found
    echo json_encode(["error" => "No restaurants found"]);
}

$conn->close();
?>