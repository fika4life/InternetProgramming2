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




$conn = connect();


$db = new DatabaseHandler($conn);
$result = $db->searchDatabase($county,$type,$area,$room,$price,$fee);

var_dump($result);


