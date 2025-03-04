<?php
/**
   * This file contains common functions used in different pages
   * @author Mukesh Kumar M, Sreejith S R
   * @version 04-08-2010
   * 
   *
 */
 

/**
* Displays header defined in the active template
* @author Mukesh Kumar M
* @version 05-08-2010 
*/
function getHeader()
{
	if(isThemeFileExists('header'))
	{
		include(THEME_ROOT.$_SESSION['sess_current_theme'].'/header.php');
	}
}
/**
* Displays footer defined in the active template
* @author Mukesh Kumar M
* @version 05-08-2010 
*/
function getFooter()
{
	if(isThemeFileExists('footer'))
	{
		include(THEME_ROOT.$_SESSION['sess_current_theme'].'/footer.php');
	}
}

/**
* Displays the content part
* @author Mukesh Kumar M
* @version 05-08-2010 
*/
function displayContent()
{
	
}
/**
* Displays the topic menu
* @author Mukesh Kumar M
* @version 05-08-2010 
*/
function displayTopicMenu($subId)
{
	$arrTopics=getAllTopics($subId);
	echo "<table id='tblTopic'>";
/*	echo "<tr><td colspan='2'>".getSubName($subId)."</td></tr>";*/
	foreach($arrTopics as $valTopic)
	{		
		$arrTopicDetails=getTopicDetails($valTopic[0]);
		//to give link to all subtopics
		$arrSubTopic=getAllSubTopics($valTopic[0]);
		$str_topic="";
		foreach($arrSubTopic as $valSubTopic)
		{
			$arrExperiments=getExperimentDetails($valSubTopic[0]);
			foreach($arrExperiments as $valExperiments)
				$str_topic.="<a href='?sub=$subId&brch=$valTopic[0]&sim=$valExperiments[0]&cnt=1'>".$valExperiments[1]."</a>"." || ";
		}
		//
			
		echo "<tr><td valign=\"top\"><img src='".getMenuIcons('topic_'.$valTopic[0])."' width='48' height='48' /></td>";
		echo "<td><a href='?sub=$subId&brch=$valTopic[0]'>".$valTopic[1]."</a><br><span class=\"clsDescription\">".$arrTopicDetails[0][1]."<br /><br />".substr($str_topic, 0, -3)."</span></td></tr>";
	}
	echo "</table>";
}

/**
* Displays the menu in the top
* @author Mukesh Kumar M
* @version 07-08-2010 
*/
function displayTopMenu($arr_item,$arr_link)
{
	echo "<ul id=\"menuTop\">";
	for($i=0;$arr_item[$i];$i++)
			echo "<li><a href=\"$arr_link[$i]\" target=\"_self\">$arr_item[$i]</a></li>";
	echo "</ul>";
}
/**
* Displays the breadcum
* @author Mukesh Kumar M
* @version 09-08-2010 
* @param array $arrItems (items to be displayed)
* @param array $arrLink (links of each item)
* @param array $breadcumStartText
* @param array $seperator
*/
function displayBreadcum($arrItems,$arrLink,$breadcumStartText,$seperator)
{
	if($imgSep=getThemeImage($seperator) && $seperator!='' )
	{
		$seperator="<img src=\"$imgSep\" />";
	}
	echo "<div class=\"breadcum\">$breadcumStartText";
	for($i=0;$i<count($arrItems);$i++)
	{
		if($arrItems[$i]!="")
			echo $seperator."<a href=\"".$arrLink[$i]."\">".$arrItems[$i]."</a>";
	}
	echo "</div>";
}
/**
* Displays the experiment menu
* @author Mukesh Kumar M
* @version 11-08-2010 
* @param $subId, $topicId
*/
function displayExperimentMenu($subId,$topicId)
{
	$arrSubTopics=getAllSubTopics($topicId);
	echo "<table id='tblExperiment'>";
	/*echo "<tr><td colspan='2'>".getSubName($subId)."</td></tr>";*/
	foreach($arrSubTopics as $valSubTopic)
	{		
		$arrExperiments=getExperimentDetails($valSubTopic[0]);
		foreach($arrExperiments as $valExperiments)
		{		
			echo "<tr><td valign=\"top\"><img src='".getMenuIcons('experiment_'.$valExperiments[0])."' width='48' height='48' /></td>";
			echo "<td><a href='?sub=$subId&brch=$topicId&sim=$valExperiments[0]&cnt=1'>".$valExperiments[1]."</a><br><span class=\"clsDescription\">".$valExperiments[2]."</span></td></tr>";
		}
	}
	echo "</table>";
}
//
/**
* Displays the search box with its functionalities
* @author Mukesh Kumar M
* @version 17-08-2010 
* 
*/
function displaySearchBox()
{
	echo "<script type=\"text/javascript\" src=\"http://www.google.com/jsapi\"></script> 
	<div class=\"searchbox\">								
		<form action=\"".ROOT_URL."\" id=\"cse-search-box\">
		<div>
		<input type=\"hidden\" name=\"pg\" value=\"bindex\" />
		<input type=\"hidden\" name=\"bsub\" value=\"search\" />
		<input type=\"hidden\" name=\"cx\" value=\"008976432297318053942:3gnilsveije\" />
		<input type=\"hidden\" name=\"cof\" value=\"FORID:11\" />
		<input type=\"hidden\" name=\"ie\" value=\"UTF-8\" />
		<input type=\"text\" name=\"q\" size=\"31\" />
		<input type=\"submit\" name=\"sa\" value=\"Search\" />
		</div>
		</form>
		</div>";
}
/**
* Displays the head text
* @author Mukesh Kumar M
* @version 18-08-2010 
* 
*/
function displayHeadText($headText)
{
	echo "<span class=\"title\">$headText</span>";
}
/**
* Displays the subject menu
* @author Mukesh Kumar M
* @version 18-08-2010 
* 
*/
function displaySubjectMenu($universityId)
{
	$arrSubject=getAllSubject($universityId);
	//print_r($arrSubject);
	echo "<table id='tblSubject'>";
	/*echo "<tr><td colspan='2'>".getSubName($subId)."</td></tr>";*/
	foreach($arrSubject as $valSubject)
	{
		echo "<tr><td><img src='".getMenuIcons('subject_'.$valSubject[0])."' width='62' height='52' /></td>";
		echo "<td><a href='?sub=$valSubject[0]'>".$valSubject[1]."</a><br><span class=\"subjectDescription\">".$valSubject[2]."</span></td></tr>";	
	}
	echo "</table>";
}
/**
* Displays the tab menu
* @author Mukesh Kumar M
* @version 26-08-2010 
* 
*/
function displayTabMenu($arrItems,$arrLinks,$arrImages,$currentItem)
{
	/*echo "eneterd";*/
	echo "<div id=\"ddtabs2\" class=\"glowingtabs\"><ul>";
	 for($i=0;$i<count($arrItems);$i++)
	 {
		$current="";
		if($currentItem===$i)
		{
			$current="current";
		}
		if(count($arrImages)>0 and $arrImages[$i]!="")
		{
			$tabImage="<img src=\"$arrImages[$i]\" alt=\"\" width=\"48\" height=\"48\" style=\"margin-top:10px; margin-bottom:0px; border:none\" /><br>";
		}
	 	echo "<li class=\"$current\" style=\"line-height:5px\"><a href=\"$arrLinks[$i]\"><span><center>".$tabImage.$arrItems[$i]."<br />&nbsp;</center></span></a></li>";
	 }
     echo "</ul></div>";
}
/**
* Displays the background of tab menu
* @author Mukesh Kumar M
* @version 26-08-2010 
* 
*/
function displayTabEnvironment($arrItems,$arrLinks,$arrImages,$currentItem,$contentText,$externalLink)
{
	echo "<div class=\"div1Style\" >";
	displayTabMenu($arrItems,$arrLinks,$arrImages,$currentItem);
	echo "<div class=\"divContent\" >";
	if($externalLink!="")
	{
		displayLink($externalLink);
		if($_GET['sim']==41)
		{
			include('work_sheet.php');
		}
	}
	else
	{
		echo $contentText;
	}
	echo "</div></div>";
}
//
/**
* Displays external link of simulation/animation
* @author Mukesh Kumar M
* @version 26-08-2010 
* 
*/
function displayLink($linkAddress)
{
	echo "<p align=\"center\"><iframe src=\"$linkAddress\" class=\"iframeStyle\" align=\"middle\" id=\"frExperiment\" name=\"frExperiment\" frameborder=\"0\"> </iframe></p>";
}
/*
*/
/*function displayExperimentItems($arrItems,$arrLinks,$arrImages,$currentItem)
{
	getAllContentItems($experimentId,$getId=true);
	$current="";
	echo "<div id=\"ddtabs2\" class=\"glowingtabs\"><ul>";
	 for($i=0;$i<count($arrItems);$i++)
	 {
		
	 echo "<li class=\"\" style=\"line-height:5px\"><a href=\"\"><span class=\"active\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:13px\"><center><img src=\"temp.gif\" alt=\"\" width=\"48\" height=\"48\" style=\"margin-top:10px; margin-bottom:0px; border:none\" /><br>Theory<br />&nbsp;</center></span></a></li>";
	 }
     echo "</ul></div>";
}*/
/**
* Displays the welcome message for logged users
* @author Mukesh Kumar M
* @version 13-10-2010 
* 
*/
function displayWelcomeMessage($name,$userType,$pg='')
{
	if($name)
	{
		if($pg==HOME)
		{			
			$roleName=getUserRoleName($userType);
			echo "<p class=\"featuredTitle\">Welcome <span class=\"name\">$name</span>, you are logged in as <span class=\"utype\">$roleName</span></p>";
			if(($userType==ROLE_ID_ADMIN)  || ($userType==ROLE_ID_UNI_ADMIN) || ($userType==ROLE_ID_HOD) || ($userType==ROLE_ID_TEACHER))
			{
				echo "<p class=\"text-yellowBox\">You can visit the <a href='?pg=HOME'>$roleName panel</a> or continue to go through Virtual Lab experiments</p>";
			}
			else
			{
				echo "<p class=\"text-yellowBox\"> You can continue to go through Virtual Lab experiments</p>";
			}
			echo "<p class=\"text-yellowBox\"><a href='logout.php'>Log out</a></p><br>";
		}
		else
		{
			echo "<p align=\"right\" class=\"clsWelcome\">You are logged in as <span class=\"clsName\">$name</span>||<span class=\"clsHelp\"><a href=\"help\" target=\"_blank\">Help</a></span>||<span class=\"clsLogout\"><a href='logout.php'>Log out</a> </span> </p>";
			
		}
	}
	
}
/**
* Function to display logout button
* @author Mukesh Kumar M
* @version 13-10-2010 
* 
*/
function displayLogOutButton()
{	
	if($_SESSION['sessUserRoleID'])
		echo "<p class=\"clsLogout\"><a title=\"Logout\" href=\"logout.php\"><img alt=\"Logout\" src=\"".getThemeImage('logout.png')."\" /></a></p>";	
}
?>
