
<!--
	Joseph Gauthier, 010654274
	Jenifer Sandoval, 010659117
-->

<head>
	<title> All Applications </title>
	<link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/File_alt_font_awesome.svg/2000px-File_alt_font_awesome.svg.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Megrim" rel="stylesheet">

</head>

<style>
	body  {
		background-image: url("Background.jpg");
		background-repeat: no-repeat;
		background-size: cover;
	}

	.container {
		padding-left: 330px;
		padding-top: 95px;
	}

	.jumbotron {
		width: 60%;
		background: rgba(255, 255, 255, 0.75);
		font-weight: bold;
	}

	h1{
		font-family: 'Megrim', cursive;
		font-weight: bold;
		text-align: center;
	}

	td,th {
		border: 1px solid #000000;
		text-align: left;
		padding: 8px;
	}
	tr:nth-child(even) {
		background-color: #dddddd;
	}

</style>

<div class="container">
<div  class="jumbotron">
		<h1>All Apps</h1>

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

	$sql = "SELECT * FROM Applications";
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		// output data of each row
		?>

		<table align="center">

		<tr>
	    <th>Student ID</th>
	    <th>Job ID</th>

	  </tr>

		<?php
		while($row = $result->fetch_assoc()) {
		?>

		<tr>
			<td><?php echo $row['StudentID']?></td>
			<td><?php echo $row['JobID']?></td>
		</tr>

	<?php
		}
	} else {
		echo "0 results<br>";
	}
	$conn->close();
	?>
</table>
<a href="mainMenu.php">Back to main menu</a>
</div>
</div>
