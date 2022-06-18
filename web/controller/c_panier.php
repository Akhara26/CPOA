<?php
include("model/m_panier.php");
include("lib/connectDb.php");
if (isset($_GET["idsuppr"])) {
    removeitem($bdd, $_GET["idsuppr"]);
}

require_once("view/v_panier.php");
