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
<section class="firstsection" id="con" >
        <h1>Se connecter</h1>
        <section class="formulaires">
            <form class="form" method="post">
                <h2>Nous rejoindre ?</h2>

                <p type="Name:"><input type="text" name="new_user"  placeholder="Email"></input></p>
                <p type="password:"><input type="password" name="new_pwd" placeholder="Mot de passe"></input></p>

                <button type="submit" value="Submit_inscription" name="inscriptionFormSubmit">Envoyer</button>
            </form>

            
            <form class="form" method="post">
                <h2>Déjà inscrit ?</h2>

                <p type="Name:"><input type="text" name="nom_user"  placeholder="Email"></input></p>
                <p type="password:"><input type="password" name="mdp_user" placeholder="Mot de passe"></input></p>

                <button type="submit" value="Submit_connexion" name="connexionFormSubmit">Connexion</button>
            </form>
        </section>  

        <?php 
        if (isset($connecte)){
        echo "<p style=\"color:green; font-size:20px; text-align:center;\">Connecté !</p>";
        }
        if (isset($userexist)){
        echo "<p id=messagesign style=\"color:red; font-size:20px; text-align:center;\">Le nom d'utilisateur est déjà pris</p>";
        }
        if (isset($rate)){
			echo "<p style=\"color:red; font-size:20px; text-align:center;\">Nom d'utilisateur ou mot de passe incorect</p>";
        }
        if (isset($newUserCreated)){
            echo "<p id=messagesign style=\"color:green; font-size:20px; text-align:center;\">Nouvel utilisateur crée avec succès</p>";
        }
        if (isset($newUserNotCreated)){
            echo "<p id=messagesign style=\"color:red; font-size:20px; text-align:center;\">Veuillez renseigner tous les champs</p>";
        }
 
        ?>




</body>