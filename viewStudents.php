
<!--
	Joseph Gauthier, 010654274
	Jenifer Sandoval, 010659117
-->
<head>
	<title> All Students </title>
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Group_font_awesome.svg/2000px-Group_font_awesome.svg.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Megrim" rel="stylesheet">
	<link rel="stylesheet"  type="text/css" href="view.css">
</head>


<div class="container">
	<div  class="jumbotron">
			<h1>All Students</h1>

<?php
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

	$sql = "SELECT * FROM Students";
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		// output data of each row
?>

			<table align="center">

			<tr>
				<th>Student ID</th>
				<th>Student Name</th>
				<th>Student Major</th>

			</tr>



						<?php

			while($row = $result->fetch_assoc()) {

				?>
				<tr>
														<td><?php echo $row['StudentID']?></td>
														<td><?php echo $row["StudentName"]?></td>
														<td><?php echo $row["Major"]?></td>

				</tr>
				<?php
			}
	}
	else {
		echo "0 results<br>";
	}
	?>
</table>
<a href="mainMenu.php">Back to main menu</a>
</div>
</div>
<?php
	$conn->close();
?>
