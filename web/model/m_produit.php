<?php

function getproduit($bdd, $idproduit)
{

    $query = oci_parse($bdd, "select * from CPOA_PRODUIT where ID_PRODUIT=" . $idproduit);
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}
