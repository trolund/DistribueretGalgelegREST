//Wait for onload event from window
window.onload = function(){
    //Attach clickHandler
    document.getElementById("testbutton").onclick = function(){
        //wait for response from fetch
        fetch("/rest/test").then(function(response){
            //Wait for response to be parsed as text
            response.text().then(function(text){
                document.getElementById("outputDiv").innerHTML = text;
            })
        })

    };
    document.getElementById("testjsonbutton").onclick = function(){
        //wait for response from fetch
        fetch("/rest/test/json").then(function(response){
            //Wait for response to be parsed as json
            response.json().then(function(json){
                console.log(json);
                document.getElementById("outputDiv").innerHTML = json.text;
            })
        })

    };

};