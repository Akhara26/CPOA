<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="index.php?target=accueil">Jardin d'enfants</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.php?target=accueil">Accueil <span class="sr-only">(current)</span></a>
      </li>

      <?php
      if (!isset($_SESSION["user"])) {
        echo
        '<li class="nav-item">
        <a class="nav-link" href="index.php?target=connexion">Connexion</a>
      </li>';
      }
      if (isset($_SESSION["user"])) {
        echo
        '<li class="nav-item">
        <a class="nav-link" href="index.php?deco=true">Deconnexion</a>
      </li>';
      }
      if (isset($_SESSION["user"])) {
        echo
        '<li class="nav-item">
        <a class="nav-link" href="index.php?target=profil">Profil</a>
      </li>';
      }
      if (isset($_SESSION["user"])) {
        echo
        '<li class="nav-item">
        <a class="nav-link" href="index.php?target=commandes">Commandes</a>
      </li>';
        if (isset($_SESSION["user"])) {
          echo
          '<li class="nav-item">
        <a class="nav-link" href="index.php?target=panier">Panier</a>
      </li>';
        }
      }

      ?>

    </ul>

  </div>
</nav>