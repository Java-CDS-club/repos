<?php

/*

This is one of the free scripts from www.dhtmlgoodies.com
You are free to use this script as long as this copyright message is kept intact

(c) Alf Magne Kalleland, http://www.dhtmlgoodies.com - 2005

*/

class dhtmlgoodies_tree{
	
	var $elementArray = array();
	var $nameOfCookie = "dhtmlgoodies_expanded"; // Name of the cookie where the expanded nodes are stored.
	var $defaultImage;
	function dhtmlgoodies_tree($imgName="") //constructor
	{
		
		$this->defaultImage=$imgName;	
		//echo "def img:".$defaultImage;
	}

	function writeCSS()
	{
		?>
		<style type="text/css">
		/*
		
		This is one of the free scripts from www.dhtmlgoodies.com
		You are free to use this script as long as this copyright message is kept intact
		
		(c) Alf Magne Kalleland, http://www.dhtmlgoodies.com - 2005
		
		*/		
		#dhtmlgoodies_tree li{
			list-style-type:none;	
			font-family:Verdana, Geneva, sans-serif;
			font-size:12px;
			font-weight:bold;
			vertical-align:middle;
			padding-top:5px;
		}
		#dhtmlgoodies_topNodes{
			margin-left:0px;
			padding-left:0px;
			
		}
		#dhtmlgoodies_topNodes ul{
			margin-left:20px;
			padding-left:0px;
			display:none;
			
		}
		#dhtmlgoodies_tree .tree_link{
			line-height:13px;
			padding-left:2px;

		}
		#dhtmlgoodies_tree img{
			vertical-align:middle;
		}
		#dhtmlgoodies_tree a{
			color: #000000;
			text-decoration:none;
			vertical-align:middle;
		}
		.activeNodeLink{
			background-color: #316AC5;
			color: #000000;
			font-weight:bold;
			
		}
		</style>		
		<?php		
	}
	
	function writeJavascript()
	{
		?>
		<script type="text/javascript">
/************************************************************************************************************
Folder tree - PHP
Copyright (C) 2005 - 2009  DTHMLGoodies.com, Alf Magne Kalleland

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

Dhtmlgoodies.com., hereby disclaims all copyright interest in this script
written by Alf Magne Kalleland.

Alf Magne Kalleland, 2005 - 2009
Owner of DHTMLgoodies.com
	
************************************************************************************************************/	
	
		var plusNode = '<?php echo getThemeImage('expand.gif');?>';
		var minusNode = '<?php echo getThemeImage('collapse.gif');?>';
		var nameOfCookie = '<?php echo $this->nameOfCookie; ?>';
		<?php
		$cookieValue = "";
		if(isset($_COOKIE[$this->nameOfCookie]))$cookieValue = $_COOKIE[$this->nameOfCookie];		
		echo "var initExpandedNodes =\"".$cookieValue."\";\n";
		?>		
		/*
		These cookie functions are downloaded from 
		http://www.mach5.com/support/analyzer/manual/html/General/CookiesJavaScript.htm
		*/
		function Get_Cookie(name) { 
		   var start = document.cookie.indexOf(name+"="); 
		   var len = start+name.length+1; 
		   if ((!start) && (name != document.cookie.substring(0,name.length))) return null; 
		   if (start == -1) return null; 
		   var end = document.cookie.indexOf(";",len); 
		   if (end == -1) end = document.cookie.length; 
		   return unescape(document.cookie.substring(len,end)); 
		} 
		// This function has been slightly modified
		function Set_Cookie(name,value,expires,path,domain,secure) { 
			expires = expires * 60*60*24*1000;
			var today = new Date();
			var expires_date = new Date( today.getTime() + (expires) );
		    var cookieString = name + "=" +escape(value) + 
		       ( (expires) ? ";expires=" + expires_date.toGMTString() : "") + 
		       ( (path) ? ";path=" + path : "") + 
		       ( (domain) ? ";domain=" + domain : "") + 
		       ( (secure) ? ";secure" : ""); 
		    document.cookie = cookieString; 
		} 
		/*
		End downloaded cookie functions
		*/
		
		function expandAll()
		{
			var treeObj = document.getElementById('dhtmlgoodies_tree');
			var images = treeObj.getElementsByTagName('img');
			for(var no=0;no<images.length;no++){
				if(images[no].className=='tree_plusminus' && images[no].src.indexOf(plusNode)>=0)expandNode(false,images[no]);
			}
		}
		function collapseAll()
		{
			var treeObj = document.getElementById('dhtmlgoodies_tree');
			var images = treeObj.getElementsByTagName('img');
			for(var no=0;no<images.length;no++){
				if(images[no].className=='tree_plusminus' && images[no].src.indexOf(minusNode)>=0)expandNode(false,images[no]);
			}
		}
		
		
		function expandNode(e,inputNode)
		{
			if(initExpandedNodes.length==0)initExpandedNodes=",";
			if(!inputNode)inputNode = this; 
			if(inputNode.tagName.toLowerCase()!='img')inputNode = inputNode.parentNode.getElementsByTagName('IMG')[0];	
			
			var inputId = inputNode.id.replace(/[^\d]/g,'');			
			
			var parentUl = inputNode.parentNode;
			var subUl = parentUl.getElementsByTagName('UL');

			if(subUl.length==0)return;
			if(subUl[0].style.display=='' || subUl[0].style.display=='none'){
				subUl[0].style.display = 'block';
				inputNode.src = minusNode;
				initExpandedNodes = initExpandedNodes.replace(',' + inputId+',',',');
				initExpandedNodes = initExpandedNodes + inputId + ',';
				
			}else{
				subUl[0].style.display = '';
				inputNode.src = plusNode;	
				initExpandedNodes = initExpandedNodes.replace(','+inputId+',',',');			
			}
			Set_Cookie(nameOfCookie,initExpandedNodes,60);
			
			
			
		}
		
		function initTree()
		{
			// Assigning mouse events
			var parentNode = document.getElementById('dhtmlgoodies_tree');
			var lis = parentNode.getElementsByTagName('LI'); // Get reference to all the images in the tree
			for(var no=0;no<lis.length;no++){
				var subNodes = lis[no].getElementsByTagName('UL');
				if(subNodes.length>0){
					lis[no].childNodes[0].style.visibility='visible';	
				}else{
					lis[no].childNodes[0].style.visibility='hidden';
				}
			}	
			
			var images = parentNode.getElementsByTagName('IMG');
			for(var no=0;no<images.length;no++){
				if(images[no].className=='tree_plusminus')images[no].onclick = expandNode;				
			}	

			var aTags = parentNode.getElementsByTagName('A');
			var cursor = 'pointer';
			if(document.all)cursor = 'hand';
			for(var no=0;no<aTags.length;no++){
				aTags[no].onclick = expandNode;		
				aTags[no].style.cursor = cursor;		
			}
			var initExpandedArray = initExpandedNodes.split(',');

			for(var no=0;no<initExpandedArray.length;no++){
				if(document.getElementById('plusMinus' + initExpandedArray[no])){
					var obj = document.getElementById('plusMinus' + initExpandedArray[no]);	
					expandNode(false,obj);
				}
			}				
		}
		//collapseAll();
		window.onload = initTree;
		
		</script>	
<?php
		
	}
	
	
	
	/*
	This function adds elements to the array
	*/
	
	function addToArray($id,$name,$parentID,$url="",$target="",$imageIcon=""){
		if($imageIcon=="")
			$imageIcon=$this->defaultImage;
		//echo "image Icon:".$imageIcon;
		if(empty($parentID))$parentID=0;
		$this->elementArray[$parentID][] = array($id,$name,$url,$target,$imageIcon);
	}
	
	function drawSubNode($parentID){
		//echo "inside img:".$this->defaultImage;
		if(isset($this->elementArray[$parentID])){			
			echo "<ul>";
			for($no=0;$no<count($this->elementArray[$parentID]);$no++){
				$urlAdd = "";
				if($this->elementArray[$parentID][$no][2]){
					$urlAdd = " href=\"".$this->elementArray[$parentID][$no][2]."\"";
					if($this->elementArray[$parentID][$no][3])$urlAdd.=" target=\"".$this->elementArray[$parentID][$no][3]."\"";	
				}
				
				echo "<li class=\"tree_node\"><img class=\"tree_plusminus\" id=\"plusMinus".$this->elementArray[$parentID][$no][0]."\" src=\"".$this->defaultImage."\"><img src=\"".$this->elementArray[$parentID][$no][4]."\"><a class=\"tree_link\"$urlAdd>".$this->elementArray[$parentID][$no][1]."</a>";	
				$this->drawSubNode($this->elementArray[$parentID][$no][0]);
				echo "</li>";
			}			
			echo "</ul>";			
		}		
	}
	
	function drawTree(){
		echo "<div id=\"dhtmlgoodies_tree\">";
		echo "<ul id=\"dhtmlgoodies_topNodes\">";
		for($no=0;$no<count($this->elementArray[0]);$no++){
			$urlAdd = "";
			if($this->elementArray[0][$no][2]){
				$urlAdd = " href=\"".$this->elementArray[0][$no][2]."\"";
				if($this->elementArray[0][$no][3])$urlAdd.=" target=\"".$this->elementArray[0][$no][3]."\"";	
			}
			echo "<li class=\"tree_node\" id=\"node_".$this->elementArray[0][$no][0]."\"><img id=\"plusMinus".$this->elementArray[0][$no][0]."\" class=\"tree_plusminus\" src=\"".$this->defaultImage."\"><img src=\"".$this->elementArray[0][$no][4]."\"><a class=\"tree_link\"$urlAdd>".$this->elementArray[0][$no][1]."</a>";		
			$this->drawSubNode($this->elementArray[0][$no][0]);
			echo "</li>";	
		}	
		echo "</ul>";	
		echo "</div>";
		/*echo "<script type=\"text/javascript\">collapseAll();</script>";	*/
	}
}


?>