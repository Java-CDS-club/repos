<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Facultyhome.php
 * @author Manorama Pal (palseema30@gmail.com)
 */

class Facultyhome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "login");
        $this->load->model("Common_model", "cmodel");
        $this->load->model("User_model", "usrmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
        /* set role id in session*/
	$data = [ 'id_role' => 2 ];
        $this->session->set_userdata($data);
        /* get logged user detail from different tables (firstname, lastname, email, campus name, org name, department name)
         * using login model and common model
         */
        $this->name=$this->login->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'));
        $this->lastn=$this->login->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'));
        $this->email=$this->login->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'));
        $this->campusid=$this->cmodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
        $this->campusname=$this->cmodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid);
        $this->orgcode=$this->cmodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid);
        $this->orgname=$this->cmodel->get_listspfic1('org_profile','org_name','org_code',$this->orgcode->org_code);
        $this->dptid=$this->cmodel->get_depid('user_role_type',$this->session->userdata('id_user'),2);
        $this->deptname=$this->cmodel->get_listspfic1('Department','dept_name','dept_id',$this->dptid->deptid);
        /*get course Detail*/
        $selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        $this->admcyear=$this->usrmodel->getcurrentAcadYear();
        $data=array(
            'pstp_scid' =>$this->campusid,
            'pstp_teachid' => $this->session->userdata('id_user'),
            'pstp_acadyear' => $this->admcyear
           
        );
        $this->cdetail=$this->cmodel->get_listspficemore('program_subject_teacher',$selectfield,$data);
        $this->load->view('facultyhome');
    }
 
}

