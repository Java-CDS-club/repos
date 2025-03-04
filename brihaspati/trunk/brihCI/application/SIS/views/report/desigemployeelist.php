
<!--@filename desigemployeelist.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename desigemployeelist.php  @author Neha Khullar(nehukhullar@gmail.com)
    @author Akash Rathi(akash92y@gmail.com) Genrate pdf report 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Employee List Designation Wise</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/multiselect/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/multiselect/bootstrap-multiselect.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap3.3.6/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap3.3.6/bootstrap-multiselect.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jspdf.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jspdf.plugin.autotable.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/pdfps.js" ></script>
        
	
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
          <script>
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo'  style='width:100%;height:100px;'>"+" <div style='width:100%;height:100%;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }
            
            $(document).ready(function(){
                /****************************************** start of designation********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                           // alert("data==0="+workt);
                    if(workt == ''){
                        $('#desig').prop('disabled',true);
                   
                    }
                    else{
                           // alert("data==1="+workt);
                        $('#desig').prop('disabled',false);
			 $('#desig').html('');
                        $('#desig').multiselect('rebuild');
                           // alert("data==1=");
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getwdesiglist_mulsel",
                            type: "POST",
                            data: {"wtype" : workt},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#desig').html(data.replace(/^"|"$/g, ' '));
                                $('#desig').html(data);
                                $('#desig').multiselect('rebuild');                
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of designation********************************/
                
                /****************************************** start of uo list********************************/
                $('#desig').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var desigid = $('#desig').val();
                    var wtdesig = wtcode+","+desigid;
                    if(desigid == ''){
                        $('#uoff').prop('disabled',true);
                   
                    }
                    else{
                        $('#uoff').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getuodesiglist",
                            type: "POST",
                            data: {"wtdesig" : wtdesig},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#uoff').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of uo list********************************/
                
                /******************************************start Department********************************/
                 $('#uoff').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var desigid = $('#desig').val();
                    var uoid = $('#uoff').val();
                    //alert(sc_code);
                    var wtdesiguo = wtcode+","+desigid+","+uoid;
                    if(uoid == ''){
                      //  $('#dept').prop('disabled',true);
                   
                    }
                    else{
                        $('#dept').prop('disabled',false);
			$('#dept').html('');
                        $('#dept').multiselect('rebuild');
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getdeptuodesiglist",
                            type: "POST",
                            data: {"wtdesiguo" : wtdesiguo},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#dept').html(data.replace(/^"|"$/g, ' '));
				$('#dept').html(data);
                                $('#dept').multiselect('rebuild'); 
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                });                 
                /******************************************end Department********************************/
                           
               
            });
            function verify(){
                    var w=document.getElementById("desig").value;
                    var x=document.getElementById("wtype").value;
                    var y=document.getElementById("uoff").value;
                    var z=document.getElementById("dept").value;
                    if((x == 'null' && w == 'null') || (x == '' && w == '')||(w == 'null')||(x == 'null')){
                     
                        alert("please select at least any two combination for search !!");
                        return false;
                    };
                    
            }
        </script>        
               
    </head>
    <body>
    <?php $this->load->view('template/header'); ?> 
    <form action="<?php echo site_url('report/desigemployeelist');?>" id="myForm" method="POST" class="form-inline">
         <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Type<br>
                    <select name="wtype" id="wtype" style="width:200px;">
			 <?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?> 
                      <option value="" disabled selected>- Select Working Type -</option>
			  <?php  } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching">Non Teaching</option>
                    </select> 
                                    
                </td> 

                <td> Designation<br>
                    <select name="desig[]" id="desig"  multiple style="width:200px;"> 
			 <?php if  ((!empty($this->desigm))&&($this->desigm != 'All')){ ?>
                        <option value="<?php echo $this->desigm; ?>" > <?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desigm)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desigm)->desig_code ." )"; ?></option> 
                        <?php  }else{ ?>
                      <option value="" > Select Designation </option>
			 <?php  } ?>
                      <!--<option value="All" >All</option> -->
                    </select> 
                </td>
                <!--<td></td>
                </tr>
                <tr style="font-weight:bold;">-->
                <td>   University Officer<br>
                    <select name="uoff" id="uoff" style="width:200px;"> 
			<?php if  ((!empty($this->uolt))&&($this->uolt != 'All')){ ?>
                        <option value="<?php echo $this->uolt; ?>" > <?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$this->uolt)->name ." ( ". $this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$this->uolt)->code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>- Select University officer -</option>
			  <?php  } ?>
                     <!-- <option value="All" >All</option> -->
                    </select> 
                </td> 
                <td> Department<br>
                    <select name="dept[]" id="dept" style="width:350px;" title="You have to choose multiple subject by pressing Ctrl " multiple> 
			 <?php if ( (!empty($this->deptmt))&&($this->deptmt != 'All')){ ?>
                        <option value="<?php echo $this->deptmt; ?>" > <?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id' ,$this->deptmt)->dept_name ." ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id' ,$this->deptmt)->dept_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value=""> Select Department </option>
			  <?php  } ?>
                      <!--<option value="All" >All</option> -->
                    </select> 
                    
                </td>
<!--		<td>
                   You have to<br> choose multiple <br>subject by<br> pressing Ctrl

                </td>
-->
                <td><br>
                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>    
        </table>       
<script>
                $('document').ready(function(){
                        $('#dept').multiselect({
                                columns:1,
                                placeholder: 'Select Department',
                                search: true,
                                selectAll: true
                        });

			$('#desig').multiselect({
	           //        nonSelectedText: '----Select Designation---',
        	    //        buttonWidth:'200px'
                	    columns:1,
	                    placeholder: 'Select Designation',
        	            search: true,
                	    selectAll: true
	                });
                });
        </script>

    <table width="100%">
       <tr style=" background-color: graytext;"> 
        <td valign="top">
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;float: right;padding:5px; margin-right:10px;' title="Click for print" >  
            <img src='<?php echo base_url(); ?>assets/sis/images/pdf.jpeg' alt='pdf'  onclick="javascript:akash1('printme1')" style='width:30px;height:30px;float: right;padding:5px;' title="Click for pdf" >  
            <div style="margin-left:500px;valign:top"><b>Designation Wise Staff List Details</b></div>
        </td>
       
       <!--<//?php
       //echo "<td align=\"center\" width=\"100%\">";
       //echo "<b>Designation Wise Teaching Staff List Details</b>";
       //echo "</td>";
       ?> -->
    
        </tr></table>
        
         <div >
            <input type="hidden" id="title" name="title" value="Designation Wise Staff List" >
         </div>
        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" id="printme1" >
                <thead>
                <tr>
                    <th>Sr.No</th>
                   
                    <th>Employee Name</th>
                
                    <th>Department</th>
                   
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		
               if( count($records) ):  ?>
                    <?php foreach($records as $record){
//                      
			if($odid !=$record->emp_desig_code){
                        echo "<tr><td colspan=4 align=left><b> Designation : ";
			echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;
			echo " ( ". $this->commodel->get_listspfic1('designation','desig_code','desig_id',$record->emp_desig_code)->desig_code ." )";
                        echo "</b></td></tr>";
			$odid = $record->emp_desig_code;
			$serial_no = 1;
                        }
			echo "<tr>";
			echo "<td>". $serial_no++ ." </td>";
			echo "<td>";
			echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name." ( "."PF No:".$record->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
			// $record->emp_name
			echo "</td>";
			echo "<td> ";
                        echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
                        echo "</td>";
?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
                </tbody>
        </table>

        </div><!------scroller div------>
        </div><!------print div------>
        </form>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>

