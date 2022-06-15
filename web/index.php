<?php
session_start();
$target = (isset($_GET["target"])) ? $_GET["target"] : "accueil";
require_once("controller/c_" . $target . ".php");

if (isset($_GET["deco"])) {
    unset($_SESSION["user"]);
}
