<!doctype html>
<html lang="fr">

<head>
  <meta charset="utf-8">
  <title>Titre de la page</title>
  <link rel="stylesheet" href="style/accueil.css">
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
  foreach ($data2["ID_CAT"] as $cat) {
    $v_array = produit($bdd, $cat);
    $data0 = $v_array[0];

  ?>


    <div class="catitem">
      <?php
      $a = gettitle($bdd, $cat[0]);
      echo "<h1 class='titrecat'>" . $a["NOM_CAT"][0] . "</h1>";
      ?>
      <div class="items">
        <?php
        for ($i = 0; $i < $v_array[1]; $i++) {
        ?>
          <div class="card" style="width: 18rem;">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">
                <?php
                foreach ($data0 as $key => $row) {
                  if ($key == "LIBELLE_PRODUIT") {
                    echo $row[$i];
                  }
                }
                ?>
              </h5>
              <p class="card-text">
                <?php
                foreach ($data0 as $key => $row) {
                  if ($key == "DESCRIPTION") {
                    echo $row[$i];
                  }
                }
                foreach ($data0 as $key => $row) {
                  if ($key == "ID_PRODUIT") {
                    $id = $row[$i];
                  }
                }
                ?>
              </p>
              <section class="infocard">
                <form action=<?= "index.php?target=produit&id_produit=" . $id ?> method="post">
                  <input type="submit" class="btn btn-primary" value="En savoir plus" name="buttonPrecision">
                </form>
                <div class="restant">
                  <?php
                  foreach ($data0 as $key => $row) {
                    if ($key == "STOCK_PRO") {
                      echo "<div>" . $row[$i] . " restant(s) </div>";
                    }
                  }
                  ?>
                </div>
              </section>
            </div>
          </div>
        <?php
          echo "<br>";
        }
        ?>
      </div>
    </div>
  <?php
  }

  ?>

  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>