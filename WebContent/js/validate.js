//for customer id

function mandatoryClaimId()
{
	var claimId=document.claimform.claimid.value;
	var status=true;
	if(claimId=="")
	{
		document.getElementById("claim_Msg").innerHTML="claim Id must not be null .";
		status=false;
	}
	return status;
}

function claimId(id)
{   
	var str= /^[1-9][0-9]*$/;
	if(id=="")
	{
		document.getElementById("claim_Msg").innerHTML="claim Id must not be null .";
		claimform.claimid.focus();
	}
	else if(!id.match(str))
	{
		document.getElementById("claim_Msg").innerHTML="claim Id must be in NUMBER only.";
		claimform.claimid.focus();
	}
	else{
		document.getElementById("claim_Msg").innerHTML="";
		
	}
}

function bAddress(id)
{
	var str=/^/;
	var str1=/\s+/;
	//var str="\d{1,5}\s\w.\s(\b\w*\b\s){1,2}\w*\."
	if(id.match(str1))
	{
		document.getElementById("baddress_msg").innerHTML="Beneficiary Address should not contain space only";
		
		claimform.baddress.focus();
		
	}
	else if(id.length>150)
	{
		document.getElementById("baddress_msg").innerHTML="Beneficiary Address should contains 150 character only.";
		claimform.baddress.focus();
		
	}
	else if(id=="")
	{
		document.getElementById("baddress_msg").innerHTML="please enter Beneficiary Address.";
		claimform.baddress.focus();
		
	}
	else{
		document.getElementById("baddress_msg").innerHTML="";
		
	}	
}

//for policy reference number
function policyN(id)
{
	var str= /^[1-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("policy_msg").innerHTML="Policy reference number must be in NUMBER only.";
		claimform.prefno.focus();
	}
	else
		document.getElementById("policy_msg").innerHTML="";
}

//claim amount
function claimA(id)
{
//	 var str=/^\d+(\.\d+)?$/;
	 var str=/^[1-9][0-9]*\.[0-9]*$/;
	 var str1=/^[1-9][0-9]*$/;
	if((!id.match(str) || id.match(str1)) && (id.match(str) || !id.match(str1)))
	{
		document.getElementById("claimAmu_msg").innerHTML="Claim amount must be greater than 0 or number only.";
		claimform.camount.focus();
	}
	else
		document.getElementById("claimAmu_msg").innerHTML="";
	
	
	
}

//beneficiary name
function bName(id)
{
	
	var str=/^[a-zA-Z]+\s*[a-zA-Z]*$/;
	if(!id.match(str))
	{
		document.getElementById("bname_msg").innerHTML="Beneficiary name must be alphabets only.";
		claimform.bname.focus();
	}
	else
		document.getElementById("bname_msg").innerHTML="";
}

//FOR email only
function bEmail(id)
{
	var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	if(id==null||id=="")
	{
	 document.getElementById("bemail_msg").innerHTML="Beneficiary email must not be null.";
	 claimform.bemail.focus();
	}
	else if(!id.match(pattern))
		{
		 document.getElementById("bemail_msg").innerHTML="Beneficiary email must not be in correct format.";
	      claimform.bemail.focus();
		}
  else
	  document.getElementById("bemail_msg").innerHTML="";	  
}


//contact number
function bCon(id)
{
	var str=/^[7-9][0-9]*$/;
	if(!id.match(str))
	{
		document.getElementById("bcon_msg").innerHTML="contact nummber must be Start with 7,8,9 and  NUMBER only.";
		claimform.bcontact .focus();
	}
	else if(id.length!=10)
		{
		document.getElementById("bcon_msg").innerHTML="contact nummber must be 10 digits only.";
		claimform.bcontact.focus();
		}
	else
		document.getElementById("bcon_msg").innerHTML="";
}

/*function password(id)
{
	var str=/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})^/;
	if(!id.match(str))
	{
		document.getElementById("password").innerHTML="Password invalid format.";
		claimform.bname.focus();
	}
	else
		document.getElementById("password").innerHTML="";
}*/

function loginUserId(id)
{
	var str= /^[1-9][0-9]*$/;
	if(id=="")
	{
		document.getElementById("username").innerHTML="User Id must not be null .";
		login.userid.focus();
	}
	else if(!id.match(str))
	{
		document.getElementById("username").innerHTML="User Id must be in NUMBER only.";
		login.userid.focus();
	}
	else{
		document.getElementById("username").innerHTML="";
		
	}
}
function loginPassword(id)
{
	
	if(id=="")
	{
		document.getElementById("password").innerHTML="Password must not be null .";
		login.userid.focus();
	}
	else{
		document.getElementById("password").innerHTML="";
		
	}
}


function mandatoryLogin()
{
	var username=document.login.userid.value;
	var password=document.login.password.value;
	var status=true;
	
	if(username=="")
	{
		document.getElementById("username").innerHTML="please enter Username";
		status=false;
	}
	if(password=="")
	{
		document.getElementById("password").innerHTML="please enter Password";
		status=false;
	}
	
	return status;
	
}



function mandatory()
{
	
	var pref=document.claimform.prefno.value;
	var camount=document.claimform.camount.value;
	var bname=document.claimform.bname.value;
	var baddress=document.claimform.baddress.value;
	var bemail=document.claimform.bemail.value;
	var bcontact=document.claimform.bcontact.value;
		
	var status=true;
	
	if(pref=="")
	{
		document.getElementById("policy_msg").innerHTML="please enter Policy Reference Number.";
		status=false;
	}
	if(camount=="")
	{
		document.getElementById("claimAmu_msg").innerHTML="please enter Claim amount.";
		status=false;
	}
	if(bname=="")
	{
		document.getElementById("bname_msg").innerHTML="please enter Beneficiary Name.";
		status=false;
	}
	if(baddress=="")
	{
		document.getElementById("baddress_msg").innerHTML="please enter Beneficiary Address.";
		status=false;
	}
	if(bemail=="")
	{
		document.getElementById("bemail_msg").innerHTML="please enter Beneficiary Email.";
		status=false;
	}
	if(bcontact=="")
	{
		document.getElementById("bcon_msg").innerHTML="please enter Beneficiary Contact Number.";
		status=false;
	}
	return status;
}



