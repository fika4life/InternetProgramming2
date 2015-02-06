$( function() {
    var rows=9;
    var columns=9;

    var matrix = [];
    for(var k=0; k<rows; k++) {
        matrix[k] = [];
        for(var m=0; m<columns; m++) {
            matrix[k][m] = false;
        }
    }




    for(var i=0;i<rows;i++){
        var row='<div class="row">';
        for(j=0;j<columns;j++){
            row+='<div class="square" id="'+i+"x"+j+'" />';
        }
        row+='</div>';
        $("#board").append(row);
        $(".square").click(function(event){
            var idString=event.target.id;
            $("#"+idString).css("background-color", "#00FF00");
            $("#text-info").html(idString);
        });
    }

});

