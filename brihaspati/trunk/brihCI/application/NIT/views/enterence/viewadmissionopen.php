
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>                                                    
<html>
<title>View admission Open</title>

  <head>
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   	<?php $this->load->view('template/header'); ?>
   	 <?php //$this->load->view('template/menu');?>
</head>
<body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
	<table width="100%">
            <tr>
                <?php 
                 echo "<td align=\"left\" width=\"33%\">";
                 echo anchor('enterence/addadmissionopen/', "Add Admission Open", array('title' => 'Add Admission Open','class' =>'top_parent')); 
                 echo "</td>";
                 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b>Entrance Admission Open Details</b>";
                 echo "</td>";
                 echo "<td align=\"right\" width=\"33%\">";
                 $help_uri = site_url()."/help/helpdoc#viewadmissionopen";
                 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
	</tr>
</table>
                <table width="100%">
		<tr><td>
                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
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
<div class="scroller_sub_page">
<table class="TFtable" >
<tr>
<thead><th>Sr.No</th><th>Academic Year</th><th>Program Category</th><th>Program Name </th><!--<th>Entrance Exam Fees </th>--> <th>Minimum Qualification </th><th>Entrance Exam Pattern</th><th>Entrance Exam Date</th><th>Start Date Of Online Application </th><th>Last Date Of Online Application</th><th>Last Late Of Application Received</th><th>Action</th></tr></thead>
<?php
        $count =0;
        if( count($this->result) ):
        foreach ($this->result as $row)
        {
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td>
	    <td> <?php echo $row->admop_acadyear;?></td>
       	    <td> <?php 
			echo $row->admop_prgcat; ?>
	    </td>
	    <td> <?php 
			echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->admop_prgname_branch)->prg_name ;
			echo "(";
			echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->admop_prgname_branch)->prg_branch ;
			echo ")";
		?>
	    </td>
<!--	    <td> <?php //echo $row->admop_entexam_fees?></td>-->
            <td> <?php echo $row->admop_min_qual ?></td>
	    <td> <?php echo $row->admop_entexam_patt ?></td>
	    <td> <?php echo $row->admop_entexam_date ?></td>
            <td> <?php echo $row->admop_startdate ?></td>
	    <td> <?php echo $row->admop_lastdate ?></td>
  	    <td> <?php echo $row->admop_app_received ?></td>
            <td>

        <?php	echo anchor('enterence/editadmissionopen/' . $row->admop_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
       
            echo "</td>";
}
        
        else :
        echo "<tr>";
            echo "<td colspan= \"13\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;
           ?>

</tr>
</table>
</div><!------scroller div------>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



