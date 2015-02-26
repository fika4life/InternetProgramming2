<?php
/**
 * Created by IntelliJ IDEA.
 * User: fiona
 * Date: 2/16/15
 * Time: 5:13 PM
 */

error_reporting(E_ALL);
ini_set("display_errors", 1);

require('db_connect.php');
require('DatabaseHandler.php');

$county = $_GET['lan'];
$type = $_GET['objekttyp'];
$area = $_GET['area'];
$room = $_GET['rum'];
$price = $_GET['pris'];
$fee = $_GET['avgift'];
$orderbyCOL = $_GET['orderbyCOL'];
$orderbyASC = $_GET['orderbyASC']==='ASC';


//protect us from SQL interjections. Sets default value to price in that case.
$columns=array("lan" , "objekttyp" , "adress", "area", "rum", "pris", "avgift");
if (!in_array($orderbyCOL, $columns)){
    $orderbyCOL = "pris";
}

setcookie("userInput" ,json_encode(array("lan"=>$county,"objekttyp"=>$type, "area"=>$area, "rum"=>$room, "pris"=>$price, "avgift"=>$fee)));

$conn = connect();


$db = new DatabaseHandler($conn);
$result = $db->searchDatabase($county,$type,$area,$room,$price,$fee,$orderbyCOL, $orderbyASC);



?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Blocket Results</title>
</head>
<body>

<h1>Blocket Results</h1>

<table>
    <thead>
        <td>Län</td>
        <td>Objekttyp</td>
        <td>Area</td>
        <td>Rum</td>
        <td>Pris</td>
        <td>Avgift</td>
    </thead>
    <tbody>
        <?php
            for ($i=0; $i<count($result); $i++){
                $array = $result[$i];
                echo "<tr>
                    <td>" . $array["lan"] . "</td>";
                echo "<td>" . $array["objekttyp"] . "</td>";
                echo "<td>" . $array["area"] . "</td>";
                echo "<td>" . $array["rum"] . "</td>";
                echo "<td>" . $array["pris"] . "</td>";
                echo "<td>" . $array["avgift"] . "</td> </tr>";

            }


        ?>
    </tbody>
</table>


</body>
</html>

