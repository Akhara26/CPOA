<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Titre de la page</title>
    <link rel="stylesheet" href="style/s_panier.css">
    <script src="script.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

    <?php

    require_once("view/v_header.php");
    $affichage = affichepanier($bdd, $_SESSION["user"]);
    ?>
    <table>
        <?php
        for ($i = 0; $i < $affichage[0]; $i++) {
            foreach ($affichage[1] as $key => $row) {
                if ($key == "PRODUIT_ID") {
                    $affichage2 = getproduit($bdd, $row[$i]);
                    foreach ($affichage2[1] as $key => $row) {
                        if ($key == "LIBELLE_PRODUIT") {
                            echo "<tr><td>" . $row[0] . "</td>";
                        }
                    }
                    foreach ($affichage2[1] as $key => $row) {
                        if ($key == "DESCRIPTION") {
                            echo "<td>" . $row[0] . "</td>";
                        }
                    }
                    foreach ($affichage[1] as $key => $row) {
                        if ($key == "ID_PRODPAN") {
                            echo "<td><a href=index.php?target=panier&idsuppr=" . $row[$i] . ">Supprimer</a></td></tr>";
                        }
                    }
                }
            }
        }
        ?>
    </table>