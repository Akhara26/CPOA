<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Titre de la page</title>
    <link rel="stylesheet" href="style/s_panier.css">
    <link rel="stylesheet" href="style/s_general.css">

    <script src="script.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <?php

    require_once("view/v_header.php");
    $table = "";
    $affichage = affichepanier($bdd, $_SESSION["user"]);
    $table = $affichage[0];
    ?>
    <h1>Panier</h1>

    <div class="showtable">


        <table>
            <?php
            $commandable = true;
            for ($i = 0; $i < $affichage[0]; $i++) {
                if (verifystock($bdd, $affichage[1]["PRODUIT_ID"][$i])) {
                    echo "<tr><td>disponible</td>";
                    $commandable = ($commandable and true);
                } else {
                    echo  "<tr><td>indisponible</td>";
                    $commandable = ($commandable and false);
                }
                foreach ($affichage[1] as $key => $row) {
                    if ($key == "PRODUIT_ID") {
                        $affichage2 = getproduit($bdd, $row[$i]);
                        foreach ($affichage2[1] as $key => $row) {
                            if ($key == "LIBELLE_PRODUIT") {
                                echo "<td>" . $row[0] . "</td>";
                            }
                        }
                        foreach ($affichage2[1] as $key => $row) {
                            if ($key == "LIBELLE_PRODUIT") {
                                echo "<td>" . $row[0] . "</td>";
                            }
                        }
                        foreach ($affichage[1] as $key => $row) {
                            if ($key == "ID_PRODPAN") {
                                echo "<td><a href=index.php?target=panier&idsuppr=" . $row[$i] . ">Supprimer</a></td>";
                            }
                        }
                    }
                }
            }

            ?>

        </table>
    </div>
    <div class="boutons">
        <?php
        if ($table != 0) {
            if ($commandable) {
                echo '<form method="post" action="index.php?target=panier&validcommand"><button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" id="commander" name="commander"> Valider </button></form>';
                echo '<form method="post" action="index.php?target=paiement&validandpay"><button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" id="commander" name="commander"> Valider et payer </button></form>';
            } else {
                echo '<form method="post" action="index.php?target=panier&validcommand"><button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" id="commander" name="commander" disabled> Valider </button></form>';
                echo '<form method="post" action="index.php?target=paiement&validandpay"><button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" id="commander" name="commander" disabled> Valider et payer </button></form>';
                echo '<div>Attention, certains produits ne sont plus disponibles, pour passer votre commande, veuillez les retirer de votre panier</div>';
            }
        }
        ?>
    </div>
</body>