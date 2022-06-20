<?php
include("model/m_panier.php");
include("lib/connectDb.php");
$commandable = true;

if (isset($_GET["idsuppr"])) {
    removeitem($bdd, $_GET["idsuppr"]);
}



if (isset($_GET["validcommand"])) {
    $prixcommande = montantcommande($bdd);
    $adresse = getadd($bdd);
    createcommande($bdd, $prixcommande["SUM(PRIX)"][0], $adresse["ADRESSE_CLIENT"][0]);
}
require_once("view/v_panier.php");
