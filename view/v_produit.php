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
    <img src="https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png">
    <section class="description">
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestias porro totam officia quis iusto optio tempora impedit itaque sit iure ipsam est accusantium, a veniam. Asperiores tenetur sapiente culpa ullam!
        <?= $get_id_produit ?>
    </section>
</div>
</body>