<?php
function isDigits($element)
{
    return !preg_match("/[^0-9]/", $element);
}

function createpaidcommande($bdd, $prix, $addresse)
{
    $query = oci_parse($bdd, "Insert into CPOA_COMMANDE (MONTANT_COMMANDE, DATE_COMMANDE, ETAT_COMMANDE, ADRESSE_COMMANDE, ID_CLIENT) values('" . $prix . "',sysdate, 'En preparation', '" . $addresse . "'," . $_SESSION["user"] . ")");
    oci_execute($query);
}

function setPaidState($bdd, $idcommande)
{
    $query = oci_parse($bdd, "update CPOA_COMMANDE set ETAT_COMMANDE = 'En cours de preparation' where ID_COMMANDE=" . $idcommande);
    oci_execute($query);
}
