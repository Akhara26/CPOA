<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Titre de la page</title>
    <link rel="stylesheet" href="style/s_paiement.css">
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

    <div class="row">
        <div class="col-75">
            <div class="container">
                <?php
                if (isset($_GET["validandpay"])) {
                    echo '<form action="index.php?target=paiement&validandpay=1" method="post">';
                }
                if (isset($_GET["pay"])) {
                    echo '<form action="index.php?target=paiement&pay=' . $_GET["pay"] . '" method="post">';
                }
                ?>


                <div class="row">
                    <div class="col-50">
                        <h3>Adresse de facturation</h3>
                        <label for="fname"><i class="fa fa-user"></i> Nom compet</label>
                        <input type="text" id="fname" name="firstname" placeholder="John M. Doe">
                        <label for="email"><i class="fa fa-envelope"></i> Email</label>
                        <input type="text" id="email" name="email" placeholder="john@example.com">
                        <label for="adr"><i class="fa fa-address-card-o"></i> Adresse</label>
                        <input type="text" id="adr" name="address" placeholder="1 Rue de la République">
                        <label for="city"><i class="fa fa-institution"></i> Pays</label>
                        <input type="text" id="country" name="country" placeholder="France">

                        <div class="row">
                            <div class="col-50">
                                <label for="state">Ville</label>
                                <input type="text" id="city" name="city" placeholder="Lyon">
                            </div>
                            <div class="col-50">
                                <label for="zip">code postal</label>
                                <input type="text" id="zip" name="zip" placeholder="69001">
                            </div>
                        </div>
                    </div>

                    <div class="col-50">
                        <h3>Payment</h3>

                        <label for="cname">Propriétaire de la carte</label>
                        <input type="text" id="cname" name="cardname" placeholder="John More Doe">
                        <label for="ccnum">Numéro de la carte de crédit</label>
                        <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
                        <label for="expmonth">Exp Month</label>
                        <input type="text" id="expmonth" name="expmonth" placeholder="09">

                        <div class="row">
                            <div class="col-50">
                                <label for="expyear">Année d'expiration</label>
                                <input type="text" id="expyear" name="expyear" placeholder="2018">
                            </div>
                            <div class="col-50">
                                <label for="cvv">CVV</label>
                                <input type="text" id="cvv" name="cvv" placeholder="352">
                            </div>
                        </div>
                    </div>

                </div>

                <input type="submit" value="Continue to checkout" class="btna" name="submitpaiement">
                </form>
            </div>
        </div>
</body>