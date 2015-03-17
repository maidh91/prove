<?php
$baseurl = realpath(dirname(__FILE__));
$cmd = $baseurl . '/svn_script.sh ' . $baseurl . ' ' . $baseurl . '/result';
echo exec($cmd);

//echo exec('svn update', $out);
//print_r($out);
//echo exec($cmd);
//echo shell_exec($cmd);
//echo shell_exec($cmd . "> /dev/null 2>/dev/null &" );
?>
