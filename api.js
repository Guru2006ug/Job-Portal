export const BASEURL="http://localhost:8056/";
export function callApi(reqmethod, url, data, responseHandler) 
{ 
    var option; 
    if(reqmethod  === "GET" || reqmethod==="DELETE") 
        option={method: reqmethod, headers: {'content-type':'application/json'}}; 
    else 
        option={method: reqmethod, headers:{'content-type':'application/json'},body:data}; 
    fetch(url,option) 
        .then(response => { 
            if(!response.ok) 
            throw new Error(response.status +" "+response.statusText); 
        return response.text(); 
        }) 
        .then(data=>responseHandler(data)) 
        .catch(error=>alert(error)); 
         
}

export function setSession(sesname,sesvalue,expday)
{
    let d=new Date();
    d.setTime(d.getTime()+expday*24*60*60*1000);
    document.cookie=`${sesname}=${sesvalue};expires=${d.toUTCString()};path=/;secure`;
}

export function getSession(sesname)
{
    let decodedCookie=decodeURIComponent(document.cookie);
    let cookieData=decodedCookie.split(';');
    for(let x in cookieData)
        if(cookieData[x].includes(sesname))
            return cookieData[x].substring(cookieData[x].indexOf(sesname)+sesname.length+1);

    return ""; 
}
