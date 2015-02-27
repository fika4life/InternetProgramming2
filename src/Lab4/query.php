<?php
/**
 * Created by IntelliJ IDEA.
 * User: fiona
 * Date: 2/25/15
 * Time: 10:16 AM
 */

require('db_connect.php');
require('DatabaseHandler.php');

$county = $_GET['lan'];
$type = $_GET['objekttyp'];
$area = $_GET['area'];
$room = $_GET['rum'];
$price = $_GET['pris'];
$fee = $_GET['avgift'];
$orderbyCOL = $_GET['orderbyCOL'];
$orderbyASC = $_GET['orderbyASC']==='1';


//protect us from SQL interjections. Sets default value to price in that case.
$columns=array("lan" , "objekttyp" , "adress", "area", "rum", "pris", "avgift");
if (!in_array($orderbyCOL, $columns)){
    $orderbyCOL = "pris";
}

$conn = connect();


$db = new DatabaseHandler($conn);

$result = $db->searchDatabase($county,$type,$area,$room,$price,$fee,$orderbyCOL, $orderbyASC);

echo json_encode($result);