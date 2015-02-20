<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    if (isset($_COOKIE['userInput'])){
        $userInputCookie = (array)json_decode($_COOKIE['userInput']);

    }
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
            <option value="Stockholm" <?php if(isset($userInputCookie) && $userInputCookie["lan"]==="Stockholm"){echo "selected";}  ?> >Stockholm</option>
            <option value="Uppsala"<?php if(isset($userInputCookie) && $userInputCookie["lan"]==="Uppsala"){echo "selected";}  ?>>Uppsala</option>
             </select>
    Objekttyp <select name="objekttyp">
        <option value="Bostadsrätt" <?php if(isset($userInputCookie) && $userInputCookie["objekttyp"]==="Bostadsrätt"){echo "selected";}  ?>>Bostadsrätt</option>
        <option value="Villa" <?php if(isset($userInputCookie) && $userInputCookie["objekttyp"]==="Villa"){echo "selected";}  ?>>Villa</option>
    </select>
    Min area
        <input type="number" name="area" value="<?php if(isset($userInputCookie)){echo $userInputCookie['area'];} ?>">
    Min rum
    <input type="number" name="rum" value="<?php if(isset($userInputCookie)){echo $userInputCookie['rum'];} ?>">

    Max Pris
    <input type="number" name="pris" value="<?php if(isset($userInputCookie)){echo $userInputCookie['pris'];} ?>">
    Max Avgift
    <input type="number" name="avgift" value="<?php if(isset($userInputCookie)){echo $userInputCookie['avgift'];} ?>">
    <input type="submit" value="Search">
</form>

</body>
</html>