<?php
include("model/m_paiement.php");
include("lib/connectDb.php");
include("model/m_panier.php");

if (isset($_POST["submitpaiement"]) and isset($_GET["validandpay"])) {
    if ($_POST["firstname"] != "" and $_POST["email"] != "" and $_POST["address"] != "" and $_POST["country"] != "" and $_POST["city"] != "" and $_POST["zip"] != "" and isDigits($_POST["zip"]) and strlen($_POST["zip"]) == 5 and $_POST["cardname"] != "" and $_POST["cardnumber"] != "" and isDigits($_POST["cardnumber"]) and strlen($_POST["cardnumber"]) == 16 and $_POST["expmonth"] != "" and isDigits($_POST["expmonth"]) and strlen($_POST["expmonth"]) == 2 and ["expyear"] != "" and isDigits($_POST["expyear"]) and $_POST["expyear"] >= 2022 and ["cvv"] != "" and isDigits($_POST["cvv"]) and strlen($_POST["cvv"]) == 3) {
        $paid = true;
        $prixcommande = montantcommande($bdd);
        $adresse = getadd($bdd);
        createpaidcommande($bdd, $prixcommande["SUM(PRIX)"][0], $adresse["ADRESSE_CLIENT"][0]);
    } else {
        $error = true;
    }
}
if (isset($_POST["submitpaiement"]) and isset($_GET["pay"])) {
    if ($_POST["firstname"] != "" and $_POST["email"] != "" and $_POST["address"] != "" and $_POST["country"] != "" and $_POST["city"] != "" and $_POST["zip"] != "" and isDigits($_POST["zip"]) and strlen($_POST["zip"]) == 5 and $_POST["cardname"] != "" and $_POST["cardnumber"] != "" and isDigits($_POST["cardnumber"]) and strlen($_POST["cardnumber"]) == 16 and $_POST["expmonth"] != "" and isDigits($_POST["expmonth"]) and strlen($_POST["expmonth"]) == 2 and ["expyear"] != "" and isDigits($_POST["expyear"]) and $_POST["expyear"] >= 2022 and ["cvv"] != "" and isDigits($_POST["cvv"]) and strlen($_POST["cvv"]) == 3) {
        setPaidState($bdd, $_GET["pay"]);
        $paid = true;
    } else {
        $error = true;
    }
}

if (!isset($paid)) {
    require_once("view/v_paiement.php");
} else {
    require_once("controller/c_commandes.php");
}
