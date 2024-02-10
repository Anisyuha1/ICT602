<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HEALTHCARE REPORT</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #CFECEC;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #007bff;
        }
        label {
            font-weight: bold;
            color: #333;
        }
        input[type="text"],
        input[type="date"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .submit-btn {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 15px 30px;
            cursor: pointer;
            width: 100%;
            box-sizing: border-box;
            display: inline-block;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
        .view-report-btn {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 15px 30px;
            cursor: pointer;
            width: 100%;
            box-sizing: border-box;
            display: inline-block;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .view-report-btn:hover {
            background-color: #2d862d;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>HEALTHCARE REPORT</h2>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <div>
                <input type="text" id="name" name="name" placeholder="Enter full name" required>
            </div>
            <div>
            <input type="email" id="email" name="email" placeholder="Enter email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
    </div>
            <div>
                <input type="text" id="longitude" name="longitude" placeholder="Enter the longitude value" required>
            </div>
            <div>
                <input type="text" id="latitude" name="latitude" placeholder="Enter the latitude value" required>
            </div>
            <div>
                <input type="text" id="location" name="location" placeholder="Enter Location" required>
            </div>
            <div>
                <input type="text" id="hospital" name="hname" placeholder="Enter Hospital Name" required>
            </div>
            <div>
                <input type="date" id="date" name="date" required>
            </div>
            <div>
                <input type="submit" class="submit-btn" value="Submit">
            </div>
            <br>
            <div>
                <a href="view.php" class="view-report-btn">View Report</a>
            </div>
        </form>
    </div>
</body>
</html>


<?php
// Database connection
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
    if (empty($_POST['hname'])) {
        $errors[] = "Hospital name is required.";
    }
    if (empty($_POST['date'])) {
        $errors[] = "Date is required.";
    }

    // If there are no errors, insert data into the database
    if (empty($errors)) {
        // Prepare a SQL statement
        $sql = "INSERT INTO comments (name, email, longitude, latitude, location, hname, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Prepare the SQL statement
        if ($stmt = mysqli_prepare($link, $sql)) {
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "ssddsss", $param_name, $param_email, $param_longitude, $param_latitude, $param_location, $param_hname, $param_date);

            // Set parameters
            $param_name = $_POST['name'];
            $param_email = $_POST['email'];
            $param_longitude = $_POST['longitude'];
            $param_latitude = $_POST['latitude'];
            $param_location = $_POST['location'];
            $param_hname = $_POST['hname'];
            $param_date = $_POST['date'];

            // Attempt to execute the prepared statement
            if (mysqli_stmt_execute($stmt)) {
                // Display a pop-up message
                echo '<script>alert("Records inserted successfully.");</script>';
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
