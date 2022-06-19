<?php

function getproduit($bdd, $idproduit)
{

    $query = oci_parse($bdd, "select * from CPOA_PRODUIT where ID_PRODUIT=" . $idproduit);
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}

function search($bdd, $nomcat)
{
    $query = oci_parse($bdd, "Select ID_PRODUIT From CPOA_PRODUIT Where lower(LIBELLE_PRODUIT) Like lower('%" . urldecode($nomcat) . "%')");
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}
