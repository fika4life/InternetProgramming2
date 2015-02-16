<?php
/**
 * Created by IntelliJ IDEA.
 * User: fiona
 * Date: 2/16/15
 * Time: 4:29 PM
 */
function connect(){
    $db = new PDO('mysql:host=localhost; dbname=bostad; charset=utf8;' , 'fredde' , 'fredde');
    return $db;

}


