<?php
function showcommande($bdd)
{
    $query = oci_parse($bdd, "select * from CPOA_COMMANDE WHERE ID_CLIENT=" . $_SESSION["user"]);
    oci_execute($query);
    $nrows = oci_fetch_all($query, $data);
    return (array($nrows, $data));
}
