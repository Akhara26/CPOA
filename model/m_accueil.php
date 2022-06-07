<?php 

$query = oci_parse($bdd, 'Select * from CPOA_PRODUIT');
oci_execute($query);
$nrows = oci_fetch_all($query, $data);
oci_close($bdd);

