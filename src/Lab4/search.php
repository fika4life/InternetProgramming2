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
            <option value="Stockholm">Stockholm</option>
            <option value="Uppsala">Uppsala</option>
             </select>
    Objekttyp <select name="objekttyp">
        <option value="Bostadsrätt">Bostadsrätt</option>
        <option value="Villa">Villa</option>
    </select>
    Min area
        <input type="number" name="area">
    Min rum
    <input type="number" name="rum">

    Max Pris
    <input type="number" name="pris">
    Max Avgift
    <input type="number" name="avgift">
    <input type="submit" value="Search">
</form>

</body>
</html>