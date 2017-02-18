
<!--
	Joseph Gauthier, 010654274
	Jenifer Sandoval, 010659117
-->
<head>
<title>Add Application</title>
	<link rel="icon" href="https://camo.githubusercontent.com/500bc11adbb0ac8c0902cb519406a8856bf49a1b/68747470733a2f2f63646e322e69636f6e66696e6465722e636f6d2f646174612f69636f6e732f64657369676e6572732d616e642d646576656c6f706572732d69636f6e2d7365742f33322f6164645f66696c652d3235362e706e67">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Megrim" rel="stylesheet">
	<link rel="stylesheet"  type="text/css" href="add.css">
</head>

<?php
	$successSubmitted = false;
	if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($_POST["studentid"]) && !empty($_POST["jobid"])) {

		$studentid = $_POST["studentid"];
		$jobid = $_POST["jobid"];
		$servername = "turing.csce.uark.edu";
		$username = "ylsandov";
		$password = "Toofaced07";
		$dbname = "ylsandov";

		// Create connection
		$conn = new mysqli($servername, $username, $password, $dbname);
		// Check connection
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}

		$sql = "INSERT INTO Applications (StudentID,JobID)
			VALUES ('$studentid', '$jobid')";


		if ($conn->query($sql)  === TRUE) {
			$successSubmitted = true;
		}
		else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}

		$conn->close();
	}
?>

<!-- form to add student information -->
<div class="container">
  <div  class="jumbotron">
		<h1>Add an Application</h1>

		<form method="post">
			Student ID:<input type="number" name="studentid" required="true">
			<br>
			Job ID: <input type="number" name="jobid" required="true">
			<br>
			<input type="submit" class="btn btn-info" value="Submit">
		</form>
		<br>
		<?php
		if ($successSubmitted) {
			echo "Your information has been submitted.<br>";
		}
		else{
			echo "Nothing has been submitted yet..<br>";
		}
		?>
		<a href="mainMenu.php"  >Back to main menu</a>
  </div>
</div>
