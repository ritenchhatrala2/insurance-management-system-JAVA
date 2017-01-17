//----------------------------------health Registration Policy------------------------------------------------------

//--------------Customer ID----------------//
function custId(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("custid_msg").innerHTML="Customer Id must be in NUMBER only.";
		healthpolicyform.custid.focus();
	}
	else{
		document.getElementById("custid_msg").innerHTML="";
	}
}


//--------------Policy ID---------------//
function PolicyId(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("policyId_msg").innerHTML="Policy Id must be in NUMBER only.";
		healthpolicyform.policyId.focus();
	}
	else{
		document.getElementById("policyId_msg").innerHTML="";
	}
}


//--------------------Tenure--------------------//
function Tenure(id)
{
	var str=/^[1-9]+[0-9]*$/;
	if(id==null||id=="")
	{
		document.getElementById("tenure_msg").innerHTML="Tenure must be filled.";
		healthpolicyform.tenure.focus();
	}
	else if(!id.match(str))
	{
		document.getElementById("tenure_msg").innerHTML="Tenure must be NUMBER only.";
		healthpolicyform.tenure.focus();
	}
	else
		document.getElementById("tenure_msg").innerHTML="";
}


//--------------Policy Commencement Date-------------//
function psDate(id) 
{
	
	//var date = new Date();
	if(id==null||id=="")
	{
		document.getElementById("psdate_msg").innerHTML="Commencement date must be filled.";
		healthpolicyform.psdate.focus();
	}
	else
		document.getElementById("psdate_msg").innerHTML="";
}


//----------------------------premium amount--------------------------//
function Premium(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("pamount_msg").innerHTML="Premium Amount must be in NUMBER only.";
		healthpolicyform.pamount.focus();
	}
	else
		document.getElementById("pamount_msg").innerHTML="";
}


//-------------------------------Payment Frequency--------------------------//
function PaymentFrequency(id)
{
	//alert(id);
	if(id.match("default"))
	{
		document.getElementById("paymentfrequency_msg").innerHTML="Payment frequency must be selected.";
		healthpolicyform.paymentfrequency.focus();
	}
	else
		document.getElementById("paymentfrequency_msg").innerHTML="";
}



//----------all mandatory fields----------------//

function health_register_mandatory() {
	var status = true;
	var custid = document.healthpolicyform.custid.value;
	var policyId = document.healthpolicyform.policyId.value;
	var tenure = document.healthpolicyform.tenure.value;
	var psdate = document.healthpolicyform.psdate.value;
	var paymentfrequency = document.healthpolicyform.paymentfrequency;
	var pamount = document.healthpolicyform.pamount.value;
	var pmode = document.getElementsByName("pmode");
	
	if(custid== "" || custid == null) 
	{
		document.getElementById("custid_msg").innerHTML = "please enter Customer Id.";
		status = false;
	}
	
	if (policyId == "" || policyId == null)
	{
		document.getElementById("policyId_msg").innerHTML = "please enter policy Id.";
		status = false;
	}
	
	if (tenure == "" || tenure == null)
	{
		document.getElementById("tenure_msg").innerHTML = "please enter policy tenure.";
		status = false;
	}
	
	if (psdate == "" || psdate == null) 
	{
		document.getElementById("psdate_msg").innerHTML = "please select policy commencement date.";
		status = false;
	}

	if(paymentfrequency.selectedIndex==0)
	{
		document.getElementById("paymentfrequency_msg").innerHTML = "please select payment frequency."
		status = false;
	}
	
	if (pamount == "" || pamount == null) 
	{
		document.getElementById("pamount_msg").innerHTML = "please enter premium amount.";
		status = false;
	}
	
	for (var i = 0; i < pmode.length; i++)
	{
		if (!pmode[i].checked)
			status = false;
		else{
			status = true;
			break;
		}
	}
	
	if (status == false)
	{
		document.getElementById("pmode_msg").innerHTML = "please select payment mode.";
	}
	
	return status;
}


// ----------------------------------Vehicle Registration Policy----------------------------------------//

//-----------------customer id----------------//

//-------------------policy id------------------//
function pId(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("policyId_msg").innerHTML="Policy Id must be in NUMBER only.";
		vehiclepolicyform.policyId.focus();
	}
	else
		document.getElementById("policyId_msg").innerHTML="";
}


//------------------vehicle number------------------//
function vno(id)
{
	var str=/^[a-zA-Z]+[\\s-]*[a-zA-Z0-9]+$/;
	if(!id.match(str))
	{
		document.getElementById("vehicleno_msg").innerHTML="Vehicle number must be alphanumeric only.";
		vehiclepolicyform.vehicleno.focus();
	}
	else
		document.getElementById("vehicleno_msg").innerHTML="";
}


//----------------------license number---------------------//
function lno(id)
{
	var str=/^[a-zA-Z]+[\\s-]*[a-zA-Z0-9]+$/;
	if(!id.match(str))
	{
		document.getElementById("licenseno_msg").innerHTML="License number must be alphanumeric only.";
		vehiclepolicyform.licenseno.focus();
	}
	else
		document.getElementById("licenseno_msg").innerHTML="";
}


//-----------------------vehicle type---------------------//
function vtype(id)
{
	if(id.match("default"))
	{
		document.getElementById("vehicletype_msg").innerHTML="Vehicle type must be filled.";
		vehiclepolicyform.vehicletype.focus();
	}
	else
		document.getElementById("vehicletype_msg").innerHTML="";
}


//--------------------commencement Date--------------------//
/*function psDate(id) 
{
	if(id==null||id=="")
	{
		document.getElementById("psdate_msg").innerHTML="Commencement date must be filled.";
		vehiclepolicyform.psdate.focus();
	}
	else
		document.getElementById("psdate_msg").innerHTML="";
}
*/
//---------------------premium amount---------------------//
function pAMOUNT(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("pamount_msg").innerHTML="Premium amount must be in NUMBER only.";
		vehiclepolicyform.pamount.focus();
	}
	else
		document.getElementById("pamount_msg").innerHTML="";
}


//------------------payment frequency------------------//
function pfrncy(id)
{
	if(id.match("default"))
	{
		document.getElementById("paymentfrequency_msg").innerHTML="Payment frequency must be filled.";
		vehiclepolicyform.paymentfrequency.focus();
	}
	else
	{
		document.getElementById("paymentfrequency_msg").innerHTML="";
	}
}


//--------------------all mandatory---------------------//

function vehicle_register_mandatory()
{
	var custId = document.vehiclepolicyform.custid.value;
	var policyId=document.vehiclepolicyform.policyId.value;
	var vehicleno=document.vehiclepolicyform.vehicleno.value;
	var licenseno=document.vehiclepolicyform.licenseno.value;
	var vehicletype=document.vehiclepolicyform.vehicletype;
	var psdate=document.vehiclepolicyform.psdate.value;
	var paymentfrequency = document.vehiclepolicyform.paymentfrequency;
	var pamount = document.vehiclepolicyform.pamount.value;
	var pmode = document.getElementsByName("pmode");
	var status=true;
	
	if(custId==""||custId==null)
	{   
		document.getElementById("custid_msg").innerHTML="please enter Customer ID.";
		status=false;
	}
	
	if(policyId==""||policyId==null)
	{ 
		document.getElementById("policyId_msg").innerHTML="please enter Policy ID.";
		status=false;
	}
	
	if(vehicleno==""||vehicleno==null)
	{
		document.getElementById("vehicleno_msg").innerHTML="please enter Vehicle no.";
		status=false;
	}
	
	if(licenseno==""||licenseno==null)
	{
		document.getElementById("licenseno_msg").innerHTML="please enter License no.";
		status=false;
	}
	
	if(vehicletype.selectedIndex==0)
	{
		document.getElementById("vehicletype_msg").innerHTML = "please select Vehicle type."
		status = false;
	}
	
	if(psdate==""||psdate==null)
	{
		document.getElementById("psdate_msg").innerHTML="please enter Policy commencement date.";
		status=false;
	}
	
	if(paymentfrequency.selectedIndex==0)
	{
		document.getElementById("paymentfrequency_msg").innerHTML = "please select payment frequency."
		status = false;
	}
	
	if(pamount==""||pamount==null)
	{
		document.getElementById("pamount_msg").innerHTML="please enter Premium amount.";
		status=false;
	}
	
	for (var i = 0; i < pmode.length; i++)
	{
		if (!pmode[i].checked)
		{
			status = false;
		}
		else{
			status = true;
			break;
		}
	}
	
	if (status == false)
	{
		document.getElementById("pmode_msg").innerHTML = "please select payment mode.";
	}
	
	return status;
}



// ---------------------------------- update Policy------------------------------------//

//--------------------Policy Reference number-----------------------//
function PolicyRef(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("policyReferenceNo_msg").innerHTML="Policy Reference number must be in NUMBER only.";
		viewpolicyform.policyReferenceNo.focus();
	}
	else
	{
		document.getElementById("policyReferenceNo_msg").innerHTML="";
	}
}

function update_policy_mandatory() {
	var status = true;
	var policyReferenceNo = document.viewpolicyform.policyReferenceNo.value;
	if(policyReferenceNo == "" || policyReferenceNo == null) 
	{
		document.getElementById("policyReferenceNo_msg").innerHTML = "please enter policy Reference Number.";
		status = false;
	}
	return status;
}


// ---------------------------------- View Policy------------------------------------------------------

//--------------------customer id--------------------//
function CustId(id)
{
	var str=/^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("custid_msg").innerHTML="Customer Id must be in NUMBER only.";
		viewpolicyform1.custid.focus();
	}
	else
	{
		document.getElementById("custid_msg").innerHTML="";
	}
}


function View_policy_mandatory()
{
	var status1 = true;
	var custid1 = document.viewpolicyform1.custid.value;
	if (custid1 == "")
	{
		document.getElementById("custid_msg").innerHTML = "please enter customer Id.";
		status1 = false;
	}
	
	return status1;
}


//--------------------------------------------------terminate Policy-------------------------------





//-----------mandatory--------------//
function remove_policy_mandatory() 
{
	var status = true;
	var custid = document.removepolicyform.custid.value;
	if (custid == "" || custid == null) 
	{
		document.getElementById("custid_msg").innerHTML = "please enter customer Id.";
		status = false;
	}
	return status;
}


//----------------check box for termination----------------//
function checkbox1()
{
	var select=document.Terminatepolicyform.select;
	var status=true;	
	for (var i = 0; i < select.length; i++)
	{ 
		if (!select[i].checked)
			status = false;
		else
		{
			status = true;
			break;
		}
	}

	if (status == false)
	{
		alert('please select atleast one policy ');
	}
	
	return status;
}