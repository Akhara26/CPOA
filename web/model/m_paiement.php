<?php
function isDigits($element)
{
    return !preg_match("/[^0-9]/", $element);
}
