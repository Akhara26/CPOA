<?php
require_once("lib/connectdb.php");
require_once("model/m_produit.php");

if (isset($_GET["searchproduit"])) {
    if ($_POST["searcharea"] != "") {

        $resultsearch = search($bdd, urlencode(substr(substr($_POST["searcharea"], 1), 0, -1)));
        $idproduit = $resultsearch["ID_PRODUIT"][0];
        require_once("view/v_produit.php");
    }
}
if (isset($_GET["id_produit"]) and $_GET["id_produit"] != null) {
    $idproduit = $_GET["id_produit"];
    require_once("view/v_produit.php");
}
