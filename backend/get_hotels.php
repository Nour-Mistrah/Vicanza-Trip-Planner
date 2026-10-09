
<?php

header('Content-Type: application/json; charset=utf-8');

error_reporting(E_ALL);
ini_set('display_errors', 0);
try {

    require_once __DIR__ . "/db_config.php";

    // Retrieve all hotels
    $sql = "SELECT * FROM hotels";
    $result = $conn->query($sql);

    $hotels = array();

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $hotels[] = $row;
        }

        echo json_encode($hotels);
    } else {
        echo json_encode([
            "error" => "No hotels found"
        ]);
    }

    $conn->close();

} catch (mysqli_sql_exception $e) {

    error_log("Hotels API error: " . $e->getMessage());

    http_response_code(500);

    echo json_encode([
        "error" => "Unable to load hotels"
    ]);
}
?>
