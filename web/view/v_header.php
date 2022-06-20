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
    <form class="form-inline my-2 my-lg-0" method="post" action="index.php?&target=produit&searchproduit">
      <input class=" form-control mr-sm-2" type="search" placeholder="Cherchez un produit !" aria-label="Search" id="searcharea" name="searcharea">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="seachsubmit" name="searchsubmit" value=1>Search</button>
    </form>
  </div>
</nav>