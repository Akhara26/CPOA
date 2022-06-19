<?php

function getprofil($bdd)
{
    $query = oci_parse($bdd, "Select * from CPOA_CLIENT Where ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
    oci_fetch_all($query, $data);
    return ($data);
}

function changename($bdd, $nom)
{
    $query = oci_parse($bdd, "update CPOA_CLIENT set NOM_CLIENT ='" . $nom . "' where ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
}

function changeprenom($bdd, $prenom)
{
    $query = oci_parse($bdd, "update CPOA_CLIENT set PRENOM_CLIENT ='" . $prenom . "' where ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
}

function changemail($bdd, $mail)
{
    $query = oci_parse($bdd, "update CPOA_CLIENT set EMAIL_CLIENT ='" . $mail . "' where ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
}

function changeadd($bdd, $add)
{
    $query = oci_parse($bdd, "update CPOA_CLIENT set ADRESSE_CLIENT ='" . $add . "' where ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
}
