<?php

function addpanier($bdd, $idprod)
{
    $query = oci_parse($bdd, "insert into CPOA_PANIER (CLIENT_ID, PRODUIT_ID) values(" . $_SESSION['user'] . "," . $idprod . ")");
    oci_execute($query);
}

function affichepanier($bdd, $iduser)
{
    $query = oci_parse($bdd, "select * from CPOA_PANIER where CLIENT_ID =" . $_SESSION['user']);
    oci_execute($query);
    $nrows = oci_fetch_all($query, $data);
    return (array($nrows, $data));
}

function getproduit($bdd, $idprod)
{
    $query = oci_parse($bdd, "Select * From CPOA_PRODUIT where ID_PRODUIT=" . $idprod);
    oci_execute($query);
    $nrows = oci_fetch_all($query, $data);
    return (array($nrows, $data));
}

function removeitem($bdd, $idprod)
{
    $query = oci_parse($bdd, "Delete from CPOA_PANIER Where ID_PRODPAN =" . $idprod);
    oci_execute($query);
}
