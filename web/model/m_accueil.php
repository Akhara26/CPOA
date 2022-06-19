<?php


function produit($bdd, $idcat)
{
    $query0 = oci_parse($bdd, 'Select * from CPOA_PRODUIT where ID_CAT=' . $idcat);
    oci_execute($query0);
    $nrows = oci_fetch_all($query0, $data0);
    oci_close($bdd);
    return (array($data0, $nrows));
}

$query2 = oci_parse($bdd, 'Select * from CPOA_CATEGORIE');
oci_execute($query2);
$nrows = oci_fetch_all($query2, $data2);
oci_close($bdd);

function gettitle($bdd, $idcat)
{
    $query = oci_parse($bdd, "select NOM_CAT from CPOA_CATEGORIE where ID_CAT=" . $idcat);
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}
