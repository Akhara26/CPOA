<?php
function create_user($bdd, $user, $pwd)
{
    if ($user != "" && $pwd != "") {
        $query = oci_parse($bdd, "insert into CPOA_CLIENT (EMAIL_CLIENT, MDP_CLIENT) values('" . $user . "','" . $pwd . "')");
        oci_execute($query);
    }
}

function exist_user($bdd, $user)
{
    $query = oci_parse($bdd, "Select EMAIL_CLIENT From CPOA_CLIENT where EMAIL_CLIENT = '" . $user . "'");
    oci_execute($query);
    oci_fetch_all($query, $data);
    oci_close($bdd);
    if (empty($data["EMAIL_CLIENT"])) {
        return (false);
    }

    if ($data["EMAIL_CLIENT"][0] == $user) {
        return (true);
    } else {
        return (false);
    }
}

function couple_mdp_pwd($bdd, $user, $pwd)
{
    $query = oci_parse($bdd, "Select ID_CLIENT, EMAIL_CLIENT, MDP_CLIENT from CPOA_CLIENT where EMAIL_CLIENT ='" . $user . "' and MDP_CLIENT ='" . $pwd . "'");
    oci_execute($query);
    oci_fetch_all($query, $data);
    oci_close($bdd);

    if ($data["EMAIL_CLIENT"][0] == $user && $data["MDP_CLIENT"][0]) {
        return (array(true, $data["ID_CLIENT"][0]));
    } else {
        return (array(false, -1));
    }
}




if (isset($_POST["inscriptionFormSubmit"])) {
    $error = "";
    if ($_POST["inscriptionFormSubmit"] != 1) {
        $error .= "Erreur sur le bouton\n";
    }
    if (exist_user($bdd, $_POST["new_user"])) {
        $userexist = true;
    } else {
        create_user($bdd, $_POST["new_user"], $_POST["new_pwd"]);
    }
    if (isset($_POST["new_user"]) && isset($_POST["new_pwd"])) {
        $newUserCreated = true;
    } else {
        $newUserNotCreated = true;
    }
}


if (isset($_POST["connexionFormSubmit"])) {
    $error = "";
    if ($_POST["connexionFormSubmit"] != 1) {
        $error .= "Erreur sur le bouton\n";
    }
    $var_couple = couple_mdp_pwd($bdd, $_POST["nom_user"], $_POST["mdp_user"]);
    if ($var_couple[0]) {
        $_SESSION["user"] = $var_couple[1];

        $connecte = true;
    } else {
        $rate = true;
    }
}
