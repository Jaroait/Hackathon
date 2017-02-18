
<!--
	Joseph Gauthier, 010654274
	Jenifer Sandoval, 010659117
-->

<head>
	<title> Admin Login </title>
	<link rel="icon" href="http://www.codeproject.com/KB/WPF/1081887/UserPlus.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Megrim" rel="stylesheet">
	<link rel="stylesheet"  type="text/css" href="add.css">
</head>

<?php
	$wrongLogin = false;
	if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($_POST["name"]) && !empty($_POST["pass"]) ) {
		$servername = "turing.csce.uark.edu";
		$username = "ylsandov";
		$password = "Toofaced07";
		$dbname = "ylsandov";
		$name = $_POST["name"];
		$pass = $_POST["pass"];

		// Create connection
		$conn = new mysqli($servername, $username, $password, $dbname);
		// Check connection
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}

		$sql = "SELECT username, password FROM Accounts WHERE username = '$name' AND password = '$pass'";
		echo $sql;
		$result = $conn->query($sql);
		if ($result->num_rows > 0) { //Checks that the account exists in the table
			header("Location:viewApplications.php");
		}
		else {
			$wrongLogin = true;
		}
		$conn->close();
	}
?>

<!-- form to add student information -->
<div class="container">
  <div  class="jumbotron">

	<h1>Admin Login</h1>

	<form method="post">
	  Username: <input type="text" name="name" required="true">
	  <br>
	  Password:<input type="password" name="pass" required="true">
	  <br>
	  <input type="submit" class="btn btn-info" value="Submit">
	</form>
	<br>
	<?php
	if ($wrongLogin) {
	  echo "That account doesn't exist.<br>";
	}
	else{
	  echo "Please log in to add a job.<br>";
	}
	?>
	<a href="mainMenu.php"  >Back to main menu</a>

  </div>
</div>
