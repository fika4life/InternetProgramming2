<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    require('db_connect.php');
    require('DatabaseHandler.php');

    $conn = connect();
    $db = new DatabaseHandler($conn);

    //
    if (isset($_COOKIE['userInput'])){
        $userInputCookie = (array)json_decode($_COOKIE['userInput']);

    }
        $counties = $db->getAllValuesInColumn("lan");
        $types = $db->getAllValuesInColumn("objekttyp");
?>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>BostadBlocket </title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
    <script src="ajax.js"></script>
    <script src="jquery.cookie.js"></script>
</head>
<body>

<h1>BostadBlocket</h1>
<form id="form" method="get" action="">
    Län <select id='county' name="lan">

        <?php foreach($counties as $county){
            //fill dropdown menu with all counties
            echo ('<option value="' . $county['lan']. '" ');
            //checks cookie for last selected value and sets to selected on back
            if(isset($userInputCookie) && $userInputCookie["lan"]===$county['lan']){echo "selected";}
            echo('>'.$county['lan'].'</option>');
        }
        ?>
    </select>
    Objekttyp <select id='type' name="objekttyp">
        <?php foreach($types as $typ){
            echo ('<option value="' . $typ['objekttyp']. '" ');
            if(isset($userInputCookie) && $userInputCookie["lan"]===$typ['objekttyp']){echo "selected";}
            echo('>'.$typ['objekttyp'].'</option>');
        }
        ?>
    </select>
    Min area
    <input type="number" name="area" value="<?php if(isset($userInputCookie)){echo $userInputCookie['area'];} ?>">
    Min rum
    <input type="number" name="rum" value="<?php if(isset($userInputCookie)){echo $userInputCookie['rum'];} ?>">

    Max Pris
    <input type="number" name="pris" value="<?php if(isset($userInputCookie)){echo $userInputCookie['pris'];} ?>">
    Max Avgift
    <input type="number" name="avgift" value="<?php if(isset($userInputCookie)){echo $userInputCookie['avgift'];} ?>">

    <br>
    <br>
    <input id="submit" type="submit" value="Search">
</form>

<br/>
<br/>
<h1>Blocket Results</h1>


<table>
    <thead>
    <td id="lan">Län</td>
    <td id="objekttyp">Objekttyp</td>
    <td id="area">Area</td>
    <td id="rum">Rum</td>
    <td id="pris">Pris</td>
    <td id="avgift">Avgift</td>
    </thead>
    <tbody id="results">



    </tbody>
</table>


</body>
</html>
