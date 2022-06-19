<!doctype html>
<html lang="fr">

<head>
  <meta charset="utf-8">
  <title>Titre de la page</title>
  <link rel="stylesheet" href="style/s_produit.css">
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
      $array = getproduit($bdd, $idproduit);
      echo $array["LIBELLE_PRODUIT"][0];

      ?>
    </h1>
    <img src="https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png">
    <section class="description">
      <?php
      echo $array["DESCRIPTION"][0];
      ?>
    </section>

    <?php
    if (isset($_SESSION["user"])) {
      echo "<a href='index.php?&prodpanier=" . $array["ID_PRODUIT"][0] . "'" . ' class="btn btn-primary btn-lg active" role="button" aria-pressed="true" >Ajouter au panier</a>';
    } else {
      echo '<button type="button" class="btn btn-lg btn-primary" disabled title="Veuillez vous connecter pour ajouter au panier">Ajouter au panier</button>';
    }
    echo $array["ID_PRODUIT"][0];
    ?>



  </div>
</body>