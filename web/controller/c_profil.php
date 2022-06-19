<?php
include("lib/connectDb.php");
include("model/m_profil.php");

if (isset($_POST["submitname"]) and $_POST["textname"] != "") {
    changename($bdd, $_POST["textname"]);
}

if (isset($_POST["submitprenom"]) and $_POST["textprenom"] != "") {
    changeprenom($bdd, $_POST["textprenom"]);
}

if (isset($_POST["submitmail"]) and $_POST["textmail"] != "") {
    changemail($bdd, $_POST["textmail"]);
}

if (isset($_POST["submitaddliv"]) and $_POST["textaddliv"] != "") {
    changeadd($bdd, $_POST["textaddliv"]);
}

require_once("view/v_profil.php");
