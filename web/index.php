<?php
session_start();
$target = (isset($_GET["target"])) ? $_GET["target"] : "accueil";
if (isset($_GET["deco"])) {
    unset($_SESSION["user"]);
}
require_once("controller/c_" . $target . ".php");

if (isset($_GET["prodpanier"])) {
    addpanier($bdd, $_GET["prodpanier"]);
}
