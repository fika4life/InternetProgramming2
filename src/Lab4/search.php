<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    require('db_connect.php');
    require('DatabaseHandler.php');

    $conn = connect();
    $db = new DatabaseHandler($conn);


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
</head>
<body>

<h1>BostadBlocket</h1>
<form method="get" action="results.php">
    Län <select name="lan">
        <?php foreach($counties as $county){
             echo ('<option value="' . $county['lan']. '" ');
            if(isset($userInputCookie) && $userInputCookie["lan"]===$county['lan']){echo "selected";}
            echo('>'.$county['lan'].'</option>');
        }
        ?>
    </select>
    Objekttyp <select name="objekttyp">
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
    <br>
    Sort by
    <select name="orderbyCOL">
        <option value="lan">Län</option>
        <option value="typ">Objekttyp</option>
        <option value="area">Area</option>
        <option value="rum">Rum</option>
        <option value="pris">Pris</option>
        <option value="avgift">Avgift</option>
    </select>

    Stigande/Fallande
    <select name="orderbyASC">
        <option value="ASC">Stigande</option>
        <option value="DESC">Fallande</option>

    </select>

    <br>
    <br>
    <input type="submit" value="Search">
</form>



</body>
</html>
