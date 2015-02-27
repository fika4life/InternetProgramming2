<?php
/**
 * Created by IntelliJ IDEA.
 * User: fiona
 * Date: 2/16/15
 * Time: 4:37 PM
 */
error_reporting(E_ALL);
ini_set('display_errors', 1);


class DatabaseHandler{

    function DatabaseHandler(PDO $PDO){
        $this-> conn=$PDO;
    }

    function getAllValuesInColumn($column){
        $res = $this->conn->query(
            "SELECT DISTINCT " . $column .  " FROM bostader"
        );

        return $res -> fetchAll();


    }

    function searchDatabase($aCounty, $aType=false, $minArea=false, $minRoom=false, $maxPrice=false, $maxFee=false, $orderbyCOL="pris", $orderbyASC = true){
        $query = "SELECT * FROM bostader WHERE ";


        $query.= "lan=:lan AND ";

        if($aType){
            $query.= "objekttyp=:typ AND ";
        }
        if($minArea){
            $query.= "area>=:area AND ";
        }
        if($minRoom){
            $query.= "rum>=:rum AND ";
        }
        if($maxPrice){
            $query.= "pris<=:pris AND ";
        }
        if($maxFee){
            $query.= "avgift<=:avgift";
        }
        if(substr($query,strlen($query)-5,strlen($query))==" AND "){
            $query = substr($query,0,strlen($query)-5);
        }

//        $query.= " ORDER BY :orderbyCOL";
        $query.= " ORDER BY " . $orderbyCOL ;

        if ($orderbyASC){
            $query.= " ASC";
        }else{
            $query.= " DESC";
        }



        $stmt =  $this->conn->prepare($query);
        $stmt -> bindParam(':lan', $aCounty);
        if ($aType){
            $stmt -> bindParam(':typ', $aType);
        }
        if ($minArea){
            $stmt -> bindParam(':area', $minArea);
        }
        if($minRoom){
            $stmt -> bindParam(':rum', $minRoom);
        }
        if($maxPrice){
            $stmt -> bindParam(':pris', $maxPrice);
        }
        if($maxFee){
            $stmt -> bindParam(':avgift', $maxFee);
        }
//        $stmt -> bindParam(':orderbyCOL', $orderbyCOL);


        $stmt->execute();

        $result= $stmt->fetchAll();

        return $result;


    }

}
