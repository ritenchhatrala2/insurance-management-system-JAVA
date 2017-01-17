//for Customer Id
function customerid(id)
{
	var alphaexp = /^[a-zA-Z\s]+$/;
	if(id==null||id=="")
	{
		document.getElementById("cid_msg").innerHTML="Customer Id must be filled.";
		customerIdform.custid.focus();
	}
	else if(isNaN(id) || id.match(alphaexp))
	{
		document.getElementById("cid_msg").innerHTML="Customer Id must be in NUMBER only.";
		customerIdform.custid.focus();
	}
	else
		document.getElementById("cid_msg").innerHTML="";
}

//for Customer Name
function customername(id)
{
	var str=/^[a-zA-Z]+\s*[a-zA-Z]*$/;
	if(id==null||id=="")
	{
		document.getElementById("cname_msg").innerHTML="Customer name must be filled.";
		customerform.cname.focus();
	}
	else if(!id.match(str))
	{
		document.getElementById("cname_msg").innerHTML="Customer name must be alphabets only.";
		customerform.cname.focus();
	}
	else
		document.getElementById("cname_msg").innerHTML="";
}

//for Customer Date of Birth
function customerdob(id) {
	if(id==null||id=="")
	{
		document.getElementById("cdob_msg").innerHTML="Customer date of birth must be filled.";
		customerform.cdob.focus();
	}
	else if(!isPastDate(id))
	{
		document.getElementById("cdob_msg").innerHTML="Customer date of birth must be in past.";
		customerform.cdob.focus();
	}
	else
		document.getElementById("cdob_msg").innerHTML="";
}

//check for past date(current and future dates are not allowed)
function isPastDate(value) {
    var now = new Date;
    var target = new Date(value);

    if (target.getFullYear() < now.getFullYear()) {
        return true;
    } else if (target.getMonth() < now.getMonth()) {
        return true;
    } else if (target.getDate() < now.getDate()) {
        return true;
    }

    return false;
}

//for Customer Email-ID
function customeremail(id)
{
	var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    if(id==null||id=="")
	{
		document.getElementById("cemail_msg").innerHTML="Customer email must be filled.";
		customerform.cemail.focus();
	}
    else if(!id.match(pattern))
	{
		document.getElementById("cemail_msg").innerHTML="Customer email must be in correct format.";
		customerform.cemail.focus();
	}
	else
		document.getElementById("cemail_msg").innerHTML="";
}

//for Customer Address
function customeraddress(id)
{
	if(id==null||id=="")
	{
		document.getElementById("caddress_msg").innerHTML="Customer address must be filled.";
		customerform.caddress.focus();
	}
	else
		document.getElementById("caddress_msg").innerHTML="";
}

//for Customer Contact Number
function customercontact(id)
{
	var testPattern = /[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]/;
	if(id==null||id=="")
	{
		document.getElementById("ccontact_msg").innerHTML="Customer contact must be filled.";
		customerform.ccontact.focus();
	}
	else if(!id.match(testPattern))
	{
		document.getElementById("ccontact_msg").innerHTML="Customer contact must be valid.";
		customerform.ccontact.focus();
	}
	else if(isNaN(id))
	{
		document.getElementById("ccontact_msg").innerHTML="Customer contact must be in NUMBER only.";
		customerform.ccontact.focus();
	}
	else if(id.length!=10)
	{
		document.getElementById("ccontact_msg").innerHTML="Customer contact must be 10 digits.";
		customerform.ccontact.focus();
	}
	else
		document.getElementById("ccontact_msg").innerHTML="";
}

//for Customer Photo Proof Type
function customerphotoproof(id)
{
	if(id=="default")
	{
		document.getElementById("cphotoproof_msg").innerHTML="Customer photo proof must be selected.";
		customerform.cphotoproof.focus();
	}
	else
		document.getElementById("cphotoproof_msg").innerHTML="";
	
}

//for Customer Photo Proof Number
function customerphotoproofno(id)
{
	if(id==null||id=="")
	{
		document.getElementById("cphotoproofno_msg").innerHTML="Customer photo proof number must be filled.";
		customerform.cphotoproofno.focus();
	}
	else
		document.getElementById("cphotoproofno_msg").innerHTML="";
}

//for Customer Address Proof Type
function customeraddressproof(id)
{
	if(id=="default")
	{
		document.getElementById("caddressproof_msg").innerHTML="Customer photo proof must be selected.";
		customerform.caddressproof.focus();
	}
	else
		document.getElementById("caddressproof_msg").innerHTML="";
	
}

//for Start Date
function startdate(id) {
	if(id==null||id=="")
	{
		document.getElementById("sdate_msg").innerHTML="Start date must be filled.";
		viewform.sdate.focus();
	}
	else
		document.getElementById("sdate_msg").innerHTML="";
}

//for End Date
function enddate(id) {
	if(id==null||id=="")
	{
		document.getElementById("edate_msg").innerHTML="End date must be filled.";
		viewform.edate.focus();
	}
	else
		document.getElementById("edate_msg").innerHTML="";
}

function registrationMandatory()
{
	var status = true;
	var id=document.customerform.cname.value;
	if(id==null||id=="")
	{
		document.getElementById("cname_msg").innerHTML="Customer name must be filled.";
		status = false;
	}
	id=document.customerform.cdob.value;
	if(id==null||id=="")
	{
		document.getElementById("cdob_msg").innerHTML="Customer date of birth must be filled.";
		status = false;
	}
	id=document.customerform.cemail.value;
	if(id==null||id=="")
	{
		document.getElementById("cemail_msg").innerHTML="Customer email must be filled.";
		status = false;
	}
	id=document.customerform.caddress.value;
	if(id==null||id=="")
	{
		document.getElementById("caddress_msg").innerHTML="Customer address must be filled.";
		status = false;
	}
	id=document.customerform.ccontact.value;
	if(id==null||id=="")
	{
		document.getElementById("ccontact_msg").innerHTML="Customer contact must be filled.";
		status = false;
	}
	id=document.customerform.cphotoproof.value;
	if(id=="default")
	{
		document.getElementById("cphotoproof_msg").innerHTML="Customer photo proof must be selected.";
		status = false;
	}
	id=document.customerform.cphotoproofno.value;
	if(id==null||id=="")
	{
		document.getElementById("cphotoproofno_msg").innerHTML="Customer photo proof number must be filled.";
		status = false;
	}
	id=document.customerform.caddressproof.value;
	if(id=="default")
	{
		document.getElementById("caddressproof_msg").innerHTML="Customer photo proof must be selected.";
		status = false;
	}
	return status;
}

function idMandatory()
{
	var status = true;	
	var id=document.customerIdform.custid.value;
	if(id==null||id=="")
	{
		document.getElementById("cid_msg").innerHTML="Customer Id must be filled.";
		status = false;
	}
	return status;
}

function dateMandatory()
{
	var status = true;	
	var id=document.viewform.sdate.value;
	if(id==null||id=="")
	{
		document.getElementById("sdate_msg").innerHTML="Start date must be filled.";
		status = false;
	}
	id=document.viewform.edate.value;
	if(id==null||id=="")
	{
		document.getElementById("edate_msg").innerHTML="End date must be filled.";
		status = false;
	}
	return status;
}