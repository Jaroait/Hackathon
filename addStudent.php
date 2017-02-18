
<!--
	Joseph Gauthier, 010654274
	Jenifer Sandoval, 010659117
-->

<head>
	<title> Add Student </title>
	<link rel="icon" href="http://www.codeproject.com/KB/WPF/1081887/UserPlus.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Megrim" rel="stylesheet">
	<link rel="stylesheet"  type="text/css" href="add.css">
</head>

<?php
	$successSubmitted = false;
	if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($_POST["studentid"]) && !empty($_POST["studentname"]) && !empty($_POST["studentmajor"]) ) {
		$id = $_POST["studentid"];
		$name = $_POST["studentname"];
		$major = $_POST["studentmajor"];
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

		$sql = "SELECT StudentID FROM Students WHERE StudentID = $id";
		$result = $conn->query($sql);
		if ($result->num_rows > 0) { //Checks that the student ID they entered doesn't already exist in the table
			//Dont insert to the table
		}
		else {
			$sql = "INSERT INTO Students (StudentID, StudentName, Major)
				VALUES ('$id', '$name', '$major')";

			if ($conn->query($sql) === TRUE) {
				$successSubmitted = true;
			}
			else {
				echo "Error: " . $sql . "<br>" . $conn->error;
			}
		}
		$conn->close();
	}
?>

<!-- form to add student information -->
<div class="container">
  <div  class="jumbotron">

	<h1>Add Student Info</h1>

	<form method="post">
	  Student ID: <input type="number" name="studentid" required="true">
	  <br>
	  Student name:<input type="text" name="studentname" required="true">
	  <br>
	  Student major:<input type="text" name="studentmajor" required="true">
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
