
<?php
header('Content-Type: application/json');
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

    $username = trim($_POST['username'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $password = $_POST['password'] ?? '';

    if ($username === '' || $email === '' || $password === '') {
        echo json_encode([
            "status" => "error",
            "message" => "All fields are required"
        ]);
        exit;
    }

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo json_encode([
            "status" => "error",
            "message" => "Invalid email address"
        ]);
        exit;
    }

    $hashedPassword = password_hash($password, PASSWORD_BCRYPT);

    $stmt = $conn->prepare(
        "INSERT INTO users (username, email, password) VALUES (?, ?, ?)"
    );

    $stmt->bind_param(
        "sss",
        $username,
        $email,
        $hashedPassword
    );

    $stmt->execute();

    echo json_encode([
        "status" => "success",
        "message" => "User registered successfully"
    ]);

    $stmt->close();

} catch (mysqli_sql_exception $e) {

    error_log("Registration database error: " . $e->getMessage());

    http_response_code(500);

    echo json_encode([
        "status" => "error",
        "message" => "Registration failed. Please try again."
    ]);

} finally {
    if (isset($conn)) {
        $conn->close();
    }
}
?>
