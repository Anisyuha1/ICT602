<?php
$link = mysqli_connect("localhost", "root", "", "comments");

// Check connection
if ($link === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate form data
    $errors = array();

    // Check for empty fields
    if (empty($_POST['name'])) {
        $errors[] = "Name is required.";
    }
    if (empty($_POST['email'])) {
        $errors[] = "Email is required.";
    }
    if (empty($_POST['longitude'])) {
        $errors[] = "Longitude is required.";
    }
    if (empty($_POST['latitude'])) {
        $errors[] = "Latitude is required.";
    }
    if (empty($_POST['location'])) {
        $errors[] = "Location is required.";
    }
    if (empty($_POST['hospital'])) {
        $errors[] = "Hospital name is required.";
    }
    if (empty($_POST['date'])) {
        $errors[] = "Date is required.";
    }

    // If there are no errors, insert data into the database
    if (empty($errors)) {
        // Prepare a SQL statement
        $sql = "INSERT INTO comments (name, email, longitude, latitude, location, hospital, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Prepare the SQL statement
        if ($stmt = mysqli_prepare($link, $sql)) {
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "ssddsss", $param_name, $param_email, $param_longitude, $param_latitude, $param_location, $param_hospital, $param_date);

            // Set parameters
            $param_name = $_POST['name'];
            $param_email = $_POST['email'];
            $param_longitude = $_POST['longitude'];
            $param_latitude = $_POST['latitude'];
            $param_location = $_POST['location'];
            $param_hospital = $_POST['hospital'];
            $param_date = $_POST['date'];

            // Attempt to execute the prepared statement
            if (mysqli_stmt_execute($stmt)) {
                echo "Records inserted successfully.";
            } else {
                echo "ERROR: Could not execute query: $sql. " . mysqli_error($link);
            }

            // Close statement
            mysqli_stmt_close($stmt);
        } else {
            echo "ERROR: Could not prepare query: $sql. " . mysqli_error($link);
        }
    } else {
        // If there are errors, display them
        foreach ($errors as $error) {
            echo $error . "<br>";
        }
    }

    // Close connection
    mysqli_close($link);
}
?>
