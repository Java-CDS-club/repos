<!-- -----------------------------------------------------
    -- @name student_step0.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
-- ---------------------------------------------------- -->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome to SLCMS</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;width:120%;height:30px;}
input[type='email']{font-size:17px;width:120%;height:30px;}
input[type='button']{font-size:16px;}
</style>
<script>
/*function change_getcat(){
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET","<?php echo site_url('student/student_step0'); ?>?catbranch="+document.getElementById("register_name").value,false);

	xmlhttp.send(null);
	
	document.getElementById("getbranch").innerHTML = xmlhttp.responseText; 
	}*/

 function getbranchname(branch){
		var branch = branch;
		//alert (branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/student/branchlist",
                data: {"Sprogramname" : branch},
                dataType:"html",
                success: function(data){
                $('#branchname').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }

</script>
</head>
<body>

<center>
<div>
	<div id="body">
	<?php $this->load->view('template/header2'); ?>
	<div class="welcome"><h2>Welcome</h2></div>
	<?php $this->load->view('student/stuStepshead');?>

	<div style="width:100%;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
	?>  
      </div>

		<?php $this->load->view('student/studentCrieteria');?>
	
<?php
echo "<center style='color:#B21f35;text-align:center;font-size:20px;'>";
	 
	if($this->session->flashdata('msg')){
	echo $this->session->flashdata('msg');
	
}

echo "</center>";
?>	


    	<form action="<?php echo site_url('student/student_step0'); ?>" method="POST" >
	   <table>
		<tr><td>
        	<label for="username">Hall Ticket Number / JEE Main No.</label></td>
		</td><td>
		
        	<input type="text" name="Sanumber" placeholder="Enter Your Hall Ticket / JEE Main No." autofocus value="<?php echo isset($_POST["Sanumber"]) ? $_POST["Sanumber"] : ''; ?>"/> <br>
		</td></tr>
			<tr><td>
        	<label for="username">JEE Application No.</label></td>
		</td><td>
		
        	<input type="text" name="Sjeeanumber" placeholder="JEE Application No." autofocus value="<?php echo isset($_POST["Sjeeanumber"]) ? $_POST["Sjeeanumber"] : ''; ?>"/> <br>
		</td></tr>

		<tr ><td>
        	<label for="text">Program/Courses</label>
		</td><td>
			<select name="Sprogramname" id="programname" id="register_name" onchange="getbranchname(this.value)" style="width:120%;height:30px;font-size:18px;font-weight:bold;">
			<option selected="true" disabled="disabled" style="font-size:18px;">programme/courses</option>
				<?php 
				$result=$this->stumodel->showCourse(); 
				if(isset($result)):
					$count = count($result);
					for($i = 0 ; $i<$count ; $i++){
			        ?>
				<option value="<?php echo $result[$i]->course_name;?>"><?php echo $result[$i]->course_name.'('.$result[$i]->branchname.')';?>
			<?php } endif; ?>
	  </select>
		<!---<select name="programname" id="programname"  style="width:122%;height:40px;font-size:18px;font-weight:bold;" onchange="getbranchname(this.value)" >
                <option value="" disabled selected >-------Select Program-------</option>
                <?php foreach($this->pnresult as $dataspt): ?>
               	 	<option value="<?php echo $dataspt->prg_name; ?>"><?php echo $dataspt->prg_name;?></option>
		<?php endforeach; ?>	
		</select>-->	
		</td>
		</tr>
		<!--<tr><td>
			
			<label for="nnumber">Select Branch</label></br></td>
			<td>
				
			<select name="Sbranchname" id="branchname"  style="width:120%;height:30px;font-size:18px;font-weight:bold;">
              		  <option value="" selected="true" disabled="disabled" >Select Branch</option>
			</select>
	
		</td></tr>-->
		<tr><td>
		<label for="text">Email-Id</label>
		</td><td>
		
        	<input type="email" name="Semail" placeholder="Enter Your Email-id" value="<?php echo isset($_POST["Semail"]) ? $_POST["Semail"] : ''; ?>"/><br>
		</td></tr>
		<tr><td>
        <label for="text">Date Of Birth :</label></td>
        <td>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>
        <input type="text" name="Sdateofbirth" placeholder="Select your dob" value="<?php echo isset($_POST["dateofbirth"]) ? $_POST["dateofbirth"] : ''; ?>" id="dob" required="true"/>
        
            <script>
                $('#dob').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-76:c+10',
                });
            </script>
        </td>
        </tr>
		<tr>
        	<td></td>
		<td>
		<input type="submit" value="Submit" name="login" style="font-size:20px;margin-left:0px;">
		<input type="reset" value="Clear" style="font-size:20px;margin-left:0px;">
		</td></tr>
		</table>
    	</form>
	<center>
	</div>
<p> &nbsp; </p>
	<?php  $this->load->view('template/footer'); ?>
	
</div>

</body>
</html>
