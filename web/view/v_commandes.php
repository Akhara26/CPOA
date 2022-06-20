<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Titre de la page</title>
    <link rel="stylesheet" href="style/s_commandes.css">
    <link rel="stylesheet" href="style/s_general.css">

    <script src="script.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <header>
        <?php
        require_once("view/v_header.php")
        ?>
    </header>
    <h1>Commandes</h1>
    <?php
    $commande = showcommande($bdd);
    ?>
    <div class="showtable">


        <table>
            <tr>
                <td>ID</td>
                <td>Montant</td>
                <td>Date de commande</td>
                <td>Etat</td>
                <td>Adresse de livraison </td>
            </tr>
            <?php
            for ($i = 0; $i < $commande[0]; $i++) {
                foreach ($commande[1] as $key => $row) {
                    if ($key == "ID_COMMANDE") {
                        echo "<tr><td>" . $row[$i] . "</td>";
                    }
                }
                foreach ($commande[1] as $key => $row) {
                    if ($key == "MONTANT_COMMANDE") {
                        echo "<td>" . $row[$i] . "</td>";
                    }
                }
                foreach ($commande[1] as $key => $row) {
                    if ($key == "DATE_COMMANDE") {
                        echo "<td>" . $row[$i] . "</td>";
                    }
                }
                foreach ($commande[1] as $key => $row) {
                    if ($key == "ETAT_COMMANDE") {
                        if ($row[$i] == "en attente de paiement") {
                            echo "<td><a href=index.php?target=paiement&pay=" . $commande[1]["ID_COMMANDE"][$i] . ">" . $row[$i] . "</a></td>";
                        } else {
                            echo "<td>" . $row[$i] . "</td>";
                        }
                    }
                }
                foreach ($commande[1] as $key => $row) {
                    if ($key == "ADRESSE_COMMANDE") {
                        echo "<td>" . $row[$i] . "</td></tr>";
                    }
                }
            }
            ?>
        </table>
    </div>
</body>