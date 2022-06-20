<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Titre de la page</title>
    <link rel="stylesheet" href="style/s_profil.css">
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

    <?php
    $afficheprofil = getprofil($bdd);
    ?>

    <h1>Paramètres du compte</h1>
    <form action="" class="form" method="post">
        <input type="text" name="textname" id="textname" placeholder="<?php echo $afficheprofil["NOM_CLIENT"][0] . '"'  ?>></text>
        <button type=" submit" value="submitname" name="submitname">changer le nom</button>
    </form>
    <form class="form" method="post">
        <input type="text" name="textprenom" id="textprenom" placeholder="<?php echo $afficheprofil["PRENOM_CLIENT"][0] . '"'   ?>></text>
        <button type=" submit" value="submitprenom" name="submitprenom">changer le prénom</button>
    </form>
    <form class="form" method="post">
        <input type="text" name="textmail" id="textmail" placeholder="<?php echo $afficheprofil["EMAIL_CLIENT"][0] . '"'  ?>></text>
        <button type=" submit" value="submitmail" name="submitmail">changer l'addresse mail</button>
    </form>
    <form class="form" method="post">
        <input type="text" name="textaddliv" id="textaddliv" placeholder="<?php echo $afficheprofil["ADRESSE_CLIENT"][0] . '"'  ?>></text>
        <button type=" submit" value="submitaddliv" name="submitaddliv">changer l'addresse de livraison</button>
    </form>

</body>