<?php
/**
   * This file defines configuration constants
   * @author Mukesh Kumar M, Sreejith S R
   * @version 04-08-2010
*/


/* defined */
define('THEME_ROOT','theme/');
ini_set('error_reporting', E_COMPILE_ERROR|E_ERROR|E_CORE_ERROR|E_PARSE|E_WARNING);
//ini_set('error_reporting', E_ALL & ~E_NOTICE);
ini_set('display_errors','Off');
//ini_set('output_buffering', 'on');
define('LANG','en_in');
define('SIMULATOR_ID','4');
define('REMOTETRIGGER_ID','3');
define('DEFAULT_THEME','amrita');
/*Project root folder name*/
define('PROJECT_ROOT','vladmin');
define('DB_FOLDER_NAME','DATABASEPROJECTROOTFOLDERNAME');
define('ROOT_URL','http://'.$_SERVER['HTTP_HOST'].'/');
define('PROJECT_EMAILID','virtual_labs@amrita.edu');
define('PROJECT_NAME','Amrita Virtual Lab');
define('UNIVERSITY_ID','1');
//define('ROOT_URL','http://vladmin.balavidya.com/');

?>