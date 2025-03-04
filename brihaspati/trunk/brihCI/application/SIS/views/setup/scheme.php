<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name scheme.php 
  @author Rekha Devi Pal(rekha20july@gmail.com)
  @author: Om Prakash (omprakashkgp@gmail.com), Dec-2017 Modification
 -->


<html>
<title>Add Scheme</title>
 <head>    
	<?php $this->load->view('template/header'); ?>

 </head>    
   <body>

     <table width="100%"> 
       <tr><td>
       	<?php
           echo anchor('setup/displayscheme', 'Scheme List', array('class' => 'top_parent'));
           echo "<td align=\"right\">";
	   $help_uri = site_url()."/help/helpdoc#Scheme";
	   echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
           echo "</td>";
       	?>
        
	<div align="left" style="margin-left:0%;width:95%;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
	?>  
      </div>
    </td></tr>    
   </table> 
    <tr>  
        <form action="<?php echo site_url('setup/scheme');?>" method="POST" class="form-inline">
          <table>   
            <tr>
                 <td>Department Name:</td>
                 <td>
                    <select name="dept_name" id="deptid" class="my_dropdown" style="width:100%;">
                    <option value="">-------------Select Department---------------</option>
                    <?php foreach($this->deptresult as $datas): ?>
                    <option value="<?php echo $datas->dept_id; ?>"><?php echo $datas->dept_name; ?></option>
                    <?php endforeach; ?>
                    </select>
                   </td></tr>
            <tr>  
                <td><label for="sname" class="control-label"> Scheme Name :</label></td>
                <td>
                <input type="text" name="sname" class="form-control" size="40" placeholder="Scheme Name.."/><br>
                </td>
 		<!--<td><?php //echo form_error('sname')?></td>--> 
            </tr>
            <tr> 
                <td>    
                <label for="scode" class="control-label">Scheme Code :</label>
                </td>
                <td>
                    <input type="text" name="scode" size="40" class="form-control" placeholder="Scheme Code.."/> <br>
                </td>
 		<!--<td><?php //echo form_error('scode')?></td>-->
            </tr>
            <tr>
                <td>   
                    <label for="ssname" class="control-label">Scheme Short Name :</label>
                </td>
                <td>
                    <input type="text" name="ssname" size="40"  class="form-control" placeholder="Scheme Short Name.."/> <br>
                </td>
		<!-- <td><?php //echo form_error('ssname')?></td>-->
            </tr>
            <tr>
                <td>   
                <label for="sdesc" class="control-label">Scheme Description :</label>
                </td>
                <td>
                    <input type="text" name="sdesc"  size="40" placeholder="Scheme Description.."/> <br>
                </td>
 		<!--<td><?php //echo form_error('sdesc')?></td>-->
            </tr>
            <tr><td></td>
                <td colspan="2">   
                <button name="scheme" >Add Scheme </button>
		<input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
           </table>
        </form>
     </tr>
  <p><br></p>     
  </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
