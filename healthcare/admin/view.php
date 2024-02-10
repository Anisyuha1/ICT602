<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Healthcare Reports</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #CFECEC;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 10px;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f0f0f0;
        }
        .add-report-btn {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .add-report-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h2>Healthcare Reports</h2>

<?php
// Database connection parameters
$host = "localhost";
$username = "root";
$password = "";
$dbname = "comments";

// Create connection
$link = mysqli_connect($host, $username, $password, $dbname);

// Check connection
if ($link === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

// Retrieve records from the database
$sql = "SELECT * FROM comments";
$result = mysqli_query($link, $sql);

// Display records in a table
if (mysqli_num_rows($result) > 0) {
    echo "<table>";
    echo "<tr><th>Name</th><th>Email</th><th>Longitude</th><th>Latitude</th><th>Location</th><th>Hospital</th><th>Date</th></tr>";
    while ($row = mysqli_fetch_assoc($result)) {
        echo "<tr>";
        echo "<td>" . htmlspecialchars($row['name']) . "</td>";
        echo "<td>" . htmlspecialchars($row['email']) . "</td>";
        echo "<td>" . htmlspecialchars($row['longitude']) . "</td>";
        echo "<td>" . htmlspecialchars($row['latitude']) . "</td>";
        echo "<td>" . htmlspecialchars($row['location']) . "</td>";
        echo "<td>" . htmlspecialchars($row['hname']) . "</td>";
        echo "<td>" . htmlspecialchars($row['date']) . "</td>";
        echo "</tr>";
    }
    echo "</table>";
} else {
    echo "No records found.";
}

// Close connection
mysqli_close($link);
?>
    
<a href="api.php" class="add-report-btn">Add New Report</a>

</body>
</html>
