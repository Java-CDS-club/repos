/**
 * @(#) CourseLimitAs.as
 * Author:Ashish Mohan
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
 
import common.Mask;
import common.commonFunction;

import flash.events.MouseEvent;

import mx.collections.*;
import mx.controls.*;
import mx.events.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public var courseDetails:XML=new XML;
public var courseGrade:XML=new XML;
public var courseArrCol:ArrayCollection; 
public var params:Object={};

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
[Bindable] private var myNumericStepper:ClassFactory;
[Bindable]public var session:String="";

/** This function executed on initialize numeric stepper limits 
 * */ 
private function init():void {
     myNumericStepper = new ClassFactory(NumericStepper);
     myNumericStepper.properties = {minimum:0, maximum:999};
}




/** This function executed on click of update button 
 * validates credits values
 * */ 
public function onLoad():void 
{ 	
	params["time"]=new Date();
	getDisplayType.send(params);
}


/** This function executed on success of display type request  
 * */
private function getDisplayTypeSuccess(event:ResultEvent):void{
	var Exam:XML=event.result as XML;
	var start:String
	session=String(Exam.Details[3].startDate)+" --- "+String(Exam.Details[3].endDate);
	var examArr:ArrayCollection=new ArrayCollection();
	for each (var o:Object in Exam.Details)
	{		
		if(o.displayType!=''){
			examArr.addItem({displayType:o.displayType});
		}
	}
	examType.dataProvider=examArr;
	getCourseDetails.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
}



/** This function executed on success of course detail request  
 * */
public function getCourseDetailsSuccess(event:ResultEvent):void{
	courseDetails= event.result as XML;
	Mask.close();
	
	//if session times out
	if(courseDetails.sessionConfirm == true){
		Alert.show(resourceManager.getString('Messages','sessionInactive'));
		var url:String="";
		url=commonFunction.getConstants('navigateHome');
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL(urlRequest,"_self");
	}
	
	//if no course come of login employee
	else if(courseDetails.Details.length()==0){
		Alert.show(commonFunction.getMessages('noCourse'),(commonFunction.getMessages('info')),4,null,null,infoIcon);
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
}	

/** This function executed on change 
 * of exam type combobox  
 * */
private function onExamChange():void{
	var params:Object={};
	params['displayType']=examType.text.charAt(0).toUpperCase();
	getCourseGradeLimit.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
}


/*********************************************************************
 * function works on success of getCourseGradeLimit request 
 * sends request to get points of different course of different grades
 * @author:Ashish Mohan
 * *******************************************************************/
protected function getCourseGradeLimitSuccess(event:ResultEvent):void{
	
	Mask.close();
	courseGrade=event.result as XML;
	
	//if session times out
	if(courseGrade.sessionConfirm == true){
		Alert.show(resourceManager.getString('Messages','sessionInactive'));
		var url:String="";
		url=commonFunction.getConstants('navigateHome');
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL(urlRequest,"_self");
	}
	
	courseArrCol= new ArrayCollection();
		
	//setting array collection which is data provider
	for(var i:int=0;i<courseDetails.Details.length();i++){
		courseArrCol.addItem({courseCode:courseDetails.Details[i].courseCode, courseName:courseDetails.Details[i].courseName,
		ownerEntity:courseDetails.Details[i].ownerEntityName,
		marksContEval:courseDetails.Details[i].marksContEval,
		marksEndSemester:courseDetails.Details[i].marksEndSemester,
		totalMarks:courseDetails.Details[i].totalMarks,
		internalActive:courseDetails.Details[i].internalActive,aGrade:courseGrade.Details[i].lowerA,amGrade:courseGrade.Details[i].lowerAM,
		bGrade:courseGrade.Details[i].lowerB,bmGrade:courseGrade.Details[i].lowerBM,cGrade:courseGrade.Details[i].lowerC,
		cmGrade:courseGrade.Details[i].lowerCM,dGrade:courseGrade.Details[i].lowerD,dmGrade:courseGrade.Details[i].lowerDM,
		eGrade:courseGrade.Details[i].lowerE,emGrade:courseGrade.Details[i].lowerEM,fGrade:courseGrade.Details[i].lowerF});	
	}	
	//set collection as data provider of grid
	courseGrid.dataProvider=courseArrCol;

}


//method to handle any fault occurs after sending service
public function fault(event:FaultEvent):void{
	Mask.close();
	Alert.show(""+event.fault,(commonFunction.getMessages('error')),4,null,null,errorIcon);				
}

//method used on delete request success
protected function deleteCourseGradeLimitSuccess(event:ResultEvent):void{
	Mask.close();
	var result:XML=event.result as XML;
	if(Number(result.Details.list_values)>0){
		Alert.show("All "+result.Details.list_values+" grades for selected course deleted successfully",resourceManager.getString('Messages','success'),0,this,onOk,successIcon);
	}
	else if(String(result.Details.list_values)=="0"){
		Alert.show(resourceManager.getString('Messages','recordNotDelete'),resourceManager.getString('Messages','error'),4,null,null,errorIcon);
	}
	else{
		Alert.show(""+result,(commonFunction.getMessages('error')),4,null,null,errorIcon);	
	}
}
									
//if confirm yes for delete
private function deleteOrNot(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		var params:Object={};
		params['courseCode']=courseGrid.selectedItem.courseCode;
		params['displayType']=examType.text.charAt(0);
		deleteCourseGradeLimit.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}


//function for search
protected function onSearchChange():void{
	commonFunction.searchForTwoFields(searchCode.text,'courseCode',searchName.text,'ownerEntity',courseGrid.dataProvider as ArrayCollection)
}	


/**************************************************
 * function works on image button clicks
 * @author:Ashish Mohan
 * ***********************************************/	
public function clickAction(event:MouseEvent):void{
	
	//to remove filter in grid
	courseArrCol.filterFunction=null;
	courseArrCol.refresh();
	
	//if exam type not selected
	if(examType.selectedIndex==-1){
		Alert.show(commonFunction.getMessages('selectExam'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	else{
		
		// fires on edit image button
		if (event.currentTarget.id == "edit"){
			if(examType.text.charAt(0).toUpperCase()=='E' && String(courseDetails.Details[courseGrid.selectedIndex].internalActive)=='0')	
			{ 
				Alert.show("No External Exam for "+courseGrid.selectedItem.courseCode+" Course Code",commonFunction.getMessages('error'),4,null,null,errorIcon);
			}
			else{	
				makeEditable(courseGrid);
				courseGrid.verticalScrollPosition=courseGrid.selectedIndex;
			}
    	}
    	
    	// fires on save image button
   		else if (event.currentTarget.id == "save"){
			makeUnEditable(courseGrid);
			
			params['courseCode']=courseGrid.selectedItem.courseCode;
			params['displayType']=examType.text.charAt(0);
			params['internalActive']=Number(courseDetails.Details[courseGrid.selectedIndex].internalActive);
			params['marksEndSem']=Number(courseDetails.Details[courseGrid.selectedIndex].marksEndSemester);
			params['totalMarks']=Number(courseDetails.Details[courseGrid.selectedIndex].totalMarks);
			
			var grades:String="";
			var lowers:String="";
		
			// checking if point entered are correct
			if(checkMarks())
			{
				//local variables for each grade
				var selaGrade:String="";
				var selamGrade:String="";
				var selbGrade:String="";
				var selbmGrade:String="";
				var selcGrade:String="";
				var selcmGrade:String="";
				var seldGrade:String="";
				var seldmGrade:String="";
				var seleGrade:String="";
				var selemGrade:String="";
				var selfGrade:String="";
				
				//if grades are blank then assigning '-1' to them
				if(String(courseGrid.selectedItem.aGrade).length==0 ){selaGrade="-1";}else{selaGrade=String(courseGrid.selectedItem.aGrade)}
				if(String(courseGrid.selectedItem.amGrade).length==0){selamGrade="-1";}else{selamGrade=String(courseGrid.selectedItem.amGrade)}
				if(String(courseGrid.selectedItem.bGrade).length==0 ){selbGrade="-1";}else{selbGrade=String(courseGrid.selectedItem.bGrade)}
				if(String(courseGrid.selectedItem.bmGrade).length==0){selbmGrade="-1";}else{selbmGrade=String(courseGrid.selectedItem.bmGrade)}
				if(String(courseGrid.selectedItem.cGrade).length==0 ){selcGrade="-1";}else{selcGrade=String(courseGrid.selectedItem.cGrade)}
				if(String(courseGrid.selectedItem.cmGrade).length==0){selcmGrade="-1";}else{selcmGrade=String(courseGrid.selectedItem.cmGrade)}
				if(String(courseGrid.selectedItem.dGrade).length==0 ){seldGrade="-1";}else{seldGrade=String(courseGrid.selectedItem.dGrade)}
				if(String(courseGrid.selectedItem.dmGrade).length==0){seldmGrade="-1";}else{seldmGrade=String(courseGrid.selectedItem.dmGrade)}
				if(String(courseGrid.selectedItem.eGrade).length==0 ){seleGrade="-1";}else{seleGrade=String(courseGrid.selectedItem.eGrade)}
				if(String(courseGrid.selectedItem.emGrade).length==0){selemGrade="-1";}else{selemGrade=String(courseGrid.selectedItem.emGrade)}
				if(String(courseGrid.selectedItem.fGrade).length==0 ){selfGrade="-1";}else{selfGrade=String(courseGrid.selectedItem.fGrade)}
				
				//putting points in lowers string
				lowers=selaGrade+"|"+selamGrade+"|"+selbGrade+"|"+selbmGrade+"|"+selcGrade+"|"+
				selcmGrade+"|"+seldGrade+"|"+seldmGrade+"|"+seleGrade+"|"+selemGrade+"|"+selfGrade+"|";

				//putting grades in grades string
				for each(var dataColumn:DataGridColumn in courseGrid.columns){
					if(dataColumn.dataField=="aGrade" || dataColumn.dataField=="amGrade" || dataColumn.dataField=="bGrade" || dataColumn.dataField=="bmGrade"
				 	|| dataColumn.dataField=="cGrade" || dataColumn.dataField=="cmGrade" || dataColumn.dataField=="dGrade" || dataColumn.dataField=="dmGrade" || dataColumn.dataField=="eGrade"
		 		 	|| dataColumn.dataField=="emGrade" || dataColumn.dataField=="fGrade" ){
					grades+=dataColumn.headerText+"|";
					}
				}
		
				params['grades']=grades;
				params['lowers']=lowers;
				
				//checking if all points are filled or not
				if(lowers.search("-1")>-1){
					Alert.show(commonFunction.getMessages('checkUnfillPoint')+" "+courseGrid.selectedItem.courseCode+" course \n"+ commonFunction.getMessages('continueOrNot'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
				}
				else{
					Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
				}
			}	
		}
		
		// fires on delete image button
    	else if (event.currentTarget.id == "deleteImage"){
    		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);
		}
  		
  		//clearing searching text
  		searchName.text="";
  		searchCode.text="";
  	}
            
}



/**************************************************
 * function works to check entered points
 * @author:Ashish Mohan
 * ***********************************************/	
private function checkMarks():Boolean{
	
	//local variables for each grade
	var selaGrade:Number=0;
	var selamGrade:Number=0;
	var selbGrade:Number=0;
	var selbmGrade:Number=0;
	var selcGrade:Number=0;
	var selcmGrade:Number=0;
	var seldGrade:Number=0;
	var seldmGrade:Number=0;
	var seleGrade:Number=0;
	var selemGrade:Number=0;
	var selfGrade:Number=0;
	
	//if grades are blank then assigning values to them
	if(courseGrid.selectedItem.aGrade==""){selaGrade=0;}else{selaGrade=Number(courseGrid.selectedItem.aGrade)}
	if(courseGrid.selectedItem.amGrade==""){selamGrade=-1;}else{selamGrade=Number(courseGrid.selectedItem.amGrade)}
	if(courseGrid.selectedItem.bGrade==""){selbGrade=-2;}else{selbGrade=Number(courseGrid.selectedItem.bGrade)}
	if(courseGrid.selectedItem.bmGrade==""){selbmGrade=-3;}else{selbmGrade=Number(courseGrid.selectedItem.bmGrade)}
	if(courseGrid.selectedItem.cGrade==""){selcGrade=-4;}else{selcGrade=Number(courseGrid.selectedItem.cGrade)}
	if(courseGrid.selectedItem.cmGrade==""){selcmGrade=-5;}else{selcmGrade=Number(courseGrid.selectedItem.cmGrade)}
	if(courseGrid.selectedItem.dGrade==""){seldGrade=-6;}else{seldGrade=Number(courseGrid.selectedItem.dGrade)}
	if(courseGrid.selectedItem.dmGrade==""){seldmGrade=-7;}else{seldmGrade=Number(courseGrid.selectedItem.dmGrade)}
	if(courseGrid.selectedItem.eGrade==""){seleGrade=-8;}else{seleGrade=Number(courseGrid.selectedItem.eGrade)}
	if(courseGrid.selectedItem.emGrade==""){selemGrade=-9;}else{selemGrade=Number(courseGrid.selectedItem.emGrade)}
	if(courseGrid.selectedItem.fGrade==""){selfGrade=-10;}else{selfGrade=Number(courseGrid.selectedItem.fGrade)}
	
	if(matchMarks(selaGrade,courseGrid.selectedIndex))
	{
		if(selaGrade>selamGrade){
			if(selamGrade>selbGrade){
				if(selbGrade>selbmGrade){
					if(selbmGrade>selcGrade){
						if(selcGrade>selcmGrade){
							if(selcmGrade>seldGrade){
								if(seldGrade>seldmGrade){
									if(seldmGrade>seleGrade){
										if(seleGrade>selemGrade){
											if(selemGrade>selfGrade){
												return true;	
											}
											else{
												
												Alert.show(commonFunction.getMessages('lowerPoint') +" E- "+commonFunction.getMessages('cannotBeSmall')+" F ",commonFunction.getMessages('error'),4,null,null,errorIcon);
												return false;
											}
										}
										else{
											
											Alert.show(commonFunction.getMessages('lowerPoint') +" E "+commonFunction.getMessages('cannotBeSmall')+" E- ",commonFunction.getMessages('error'),4,null,null,errorIcon);
											return false;
										}
									}
									else{
										
										Alert.show(commonFunction.getMessages('lowerPoint') +" D- "+commonFunction.getMessages('cannotBeSmall')+" E ",commonFunction.getMessages('error'),4,null,null,errorIcon);
										return false;
									}
								}
								else{
									
									Alert.show(commonFunction.getMessages('lowerPoint') +" D "+commonFunction.getMessages('cannotBeSmall')+" D- ",commonFunction.getMessages('error'),4,null,null,errorIcon);
									return false;
								}
							}
							else{
								
								Alert.show(commonFunction.getMessages('lowerPoint') +" C- "+commonFunction.getMessages('cannotBeSmall')+" D ",commonFunction.getMessages('error'),4,null,null,errorIcon);
								return false;
							}
						}
						else{
							
							Alert.show(commonFunction.getMessages('lowerPoint') +" C "+commonFunction.getMessages('cannotBeSmall')+" C- ",commonFunction.getMessages('error'),4,null,null,errorIcon);
							return false;
						}
					}
					else{
						
						Alert.show(commonFunction.getMessages('lowerPoint') +" B- "+commonFunction.getMessages('cannotBeSmall')+" C ",commonFunction.getMessages('error'),4,null,null,errorIcon);
						return false;
					}
				}
				else{
					
					Alert.show(commonFunction.getMessages('lowerPoint') +" B "+commonFunction.getMessages('cannotBeSmall')+" B- ",commonFunction.getMessages('error'),4,null,null,errorIcon);
					return false;
				}
			}
			else{
				
				Alert.show(commonFunction.getMessages('lowerPoint') +" A- "+commonFunction.getMessages('cannotBeSmall')+" B ",commonFunction.getMessages('error'),4,null,null,errorIcon);
				return false;
			}
		}
		else{
			
			Alert.show(commonFunction.getMessages('lowerPoint') +" A "+commonFunction.getMessages('cannotBeSmall')+" A- ",commonFunction.getMessages('error'),4,null,null,errorIcon);
			return false;
		}
	}
	else{
		return false;
	}

}


//works if confirm yes for insert
private function insertOrNot(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		insertCourseGrade.send(params);
		makeUnEditable(courseGrid);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}




/**************************************************
 * function works to make column editable
 * @author:Ashish Mohan
 * ***********************************************/
public static function makeEditable(datagrid:DataGrid):void{
	var columns:Array=datagrid.columns;
	for each(var dataColumn:DataGridColumn in columns){
		if(dataColumn.dataField=="aGrade" || dataColumn.dataField=="amGrade" || dataColumn.dataField=="bGrade" || dataColumn.dataField=="bmGrade"
		 || dataColumn.dataField=="cGrade" || dataColumn.dataField=="cmGrade" || dataColumn.dataField=="dGrade" || dataColumn.dataField=="dmGrade" || dataColumn.dataField=="eGrade"
		  || dataColumn.dataField=="emGrade" || dataColumn.dataField=="fGrade" )
		{dataColumn.editable=true;}
	}
	datagrid.columns=columns;
}

/**************************************************
 * function works to make column Uneditable
 * @author:Ashish Mohan
 * ***********************************************/
public static function makeUnEditable(datagrid:DataGrid):void{
	var columns:Array=datagrid.columns;
	for each(var dataColumn:DataGridColumn in columns){
		dataColumn.editable=false;
	}
	datagrid.columns=columns;
}


// works on insert request success
private function getInsertCourseGradeSuccess(event:ResultEvent):void{
	var result:XML=event.result as XML;
	Mask.close();
	if(String(result.Details.list_values)=="insert"){
		Alert.show(resourceManager.getString('Messages','successOnInsert'),resourceManager.getString('Messages','success'),0,this,onOk,successIcon);
		
	}
	else if(String(result.Details.list_values)=="update"){
		Alert.show(resourceManager.getString('Messages','recordUpdatedSuccessfully'),resourceManager.getString('Messages','success'),0,this,onOk,successIcon);
		
	}
	else{
		Alert.show(""+result,(commonFunction.getMessages('error')),4,null,null,errorIcon);	
	}
}

//works when clicking on OK button to refresh grid
private function onOk(event:CloseEvent):void{
	onExamChange();
}


/**************************************************
 * function works to disable other rows of 
 * editable column and enable only selected row 
 * @author:Ashish Mohan
 * ***********************************************/
protected function disableRows(event:DataGridEvent):void{
    if(event.rowIndex!=courseGrid.selectedIndex){
          event.preventDefault();
    }
}

//method to check marks according to exam type
protected function matchMarks(a:Number,index:Number):Boolean{
	
	var externalMarks:Number=Number(courseDetails.Details[index].marksEndSemester);
	var totalMarks:Number=Number(courseDetails.Details[index].totalMarks);
	var internalMarks:Number=totalMarks-externalMarks;
	
    if((examType.text.charAt(0).toUpperCase()=='E') && (a>externalMarks)){
         Alert.show(commonFunction.getMessages('exceedExt')+ courseGrid.selectedItem.courseCode,(commonFunction.getMessages('info')),4,null,null,infoIcon)
         return false;
    }
    else if((examType.text.charAt(0).toUpperCase()=='I') && (a>internalMarks) && Number(courseDetails.Details[index].internalActive)==1){
    	Alert.show(commonFunction.getMessages('exceedInt')+ courseGrid.selectedItem.courseCode,(commonFunction.getMessages('info')),4,null,null,infoIcon)
        return false;
    }
    else if((examType.text.charAt(0).toUpperCase()=='I') && (a>totalMarks) && Number(courseDetails.Details[index].internalActive)==0){
    	Alert.show(commonFunction.getMessages('exceedInt')+ courseGrid.selectedItem.courseCode,(commonFunction.getMessages('info')),4,null,null,infoIcon)
        return false;
    }
    else {
        return true;
    }
}

