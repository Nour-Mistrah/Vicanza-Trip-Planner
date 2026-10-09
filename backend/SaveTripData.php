
<?php
header('Content-Type: application/json; charset=utf-8');

error_reporting(E_ALL);
ini_set('display_errors', 0);
try {

    require_once __DIR__ . "/db_config.php";

    if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
        http_response_code(405);
        echo json_encode([
            "status" => "error",
            "message" => "Method not allowed"
        ]);
        exit;
    }

    $date = trim($_POST['date'] ?? '');
    $duration = trim($_POST['duration'] ?? '');

    if ($date === '' || $duration === '') {
        echo json_encode([
            "status" => "error",
            "message" => "Date and duration are required"
        ]);
        exit;
    }

    $stmt = $conn->prepare(
        "INSERT INTO trip (date, duration) VALUES (?, ?)"
    );

    $stmt->bind_param("ss", $date, $duration);
    $stmt->execute();

    echo json_encode([
        "status" => "success",
        "message" => "Trip details have been saved successfully"
    ]);

    $stmt->close();

} catch (mysqli_sql_exception $e) {

    error_log("SaveTripData error: " . $e->getMessage());

    http_response_code(500);

    echo json_encode([
        "status" => "error",
        "message" => "Saving trip details failed"
    ]);

} finally {
    if (isset($conn)) {
        $conn->close();
    }
}
?>
