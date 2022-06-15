<!doctype html>
<html lang="fr">

<head>
  <meta charset="utf-8">
  <title>Titre de la page</title>
  <link rel="stylesheet" href="style/accueil.css">
  <script src="script.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
  <header>
    <?php
    require_once("view/v_header.php")
    ?>
  </header>
  <div class="produit">
    <h1>
      <?php
      $array = getproduit($bdd, $_GET["id_produit"]);
      echo $array["LIBELLE_PRODUIT"][0];

      ?>
    </h1>
    <img src="https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png">
    <section class="description">
      <?php
      echo $array["DESCRIPTION"][0];
      ?>
    </section>
  </div>
</body>