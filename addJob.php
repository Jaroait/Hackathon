
<!--
	Joseph Gauthier, 010654274
	Jenifer Sandoval, 010659117
-->

<head>
	<title> Add Job </title>
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Suitcase_font_awesome.svg/512px-Suitcase_font_awesome.svg.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Megrim" rel="stylesheet">
	<link rel="stylesheet"  type="text/css" href="add.css">
</head>

<?php
	$successSubmitted = false;
	if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($_POST["jobid"]) && !empty($_POST["companyname"]) && !empty($_POST["jobtitle"]) && !empty($_POST["salary"])) {
		$id = $_POST["jobid"];
		$name = $_POST["companyname"];
		$title = $_POST["jobtitle"];
		$salary = $_POST["salary"];
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

		$sql = "SELECT JobID FROM Jobs WHERE JobID = $id";
		$result = $conn->query($sql);
		if ($result->num_rows > 0) { //Checks that the job ID they entered doesn't already exist in the table
			//Dont insert to the table
		}
		else{
			$sql = "INSERT INTO Jobs (JobID, CompanyName, JobTitle, Salary)
				VALUES ('$id', '$name', '$title','$salary')";

			if ($conn->query($sql)  === TRUE) {
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

	<h1>Add a Job</h1>

	<form method="post">
		Job ID: <input type="number" name="jobid" required="true">
		<br>
		Company Name:<input type="text" name="companyname" required="true">
		<br>
		Job Title:<input type="text" name="jobtitle" required="true">
		<br>
		Salary:<input type="number" name="salary" required="true">
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
