<?php

function addpanier($bdd, $idprod)
{
    $query = oci_parse($bdd, "insert into CPOA_PANIER (CLIENT_ID, PRODUIT_ID) values(" . $_SESSION['user'] . "," . $idprod . ")");
    oci_execute($query);
}

function affichepanier($bdd)
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

function montantcommande($bdd)
{
    $query = oci_parse($bdd, "select sum(PRIX) from CPOA_PRODUIT inner join CPOA_PANIER on CPOA_PRODUIT.ID_PRODUIT = CPOA_PANIER.PRODUIT_ID Where CPOA_PANIER.CLIENT_ID =" . $_SESSION["user"]);
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}

function getadd($bdd)
{
    $query = oci_parse($bdd, "Select ADRESSE_CLIENT From CPOA_CLIENT where ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}

function createcommande($bdd, $prix, $addresse)
{
    $query = oci_parse($bdd, "Insert into CPOA_COMMANDE (MONTANT_COMMANDE, DATE_COMMANDE, ETAT_COMMANDE, ADRESSE_COMMANDE, ID_CLIENT) values('" . $prix . "',sysdate, 0, '" . $addresse . "'," . $_SESSION["user"] . ")");
    oci_execute($query);
}
