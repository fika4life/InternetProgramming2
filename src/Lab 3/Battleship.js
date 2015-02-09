var rows=9;
var columns=9;

var model=new BattleShipModel(rows,columns);


function showStatus(){
    for(var i=0;i<rows;i++){
        for(j=0;j<columns;j++){
            if(model.checkAttacked(i,j)){
                var square=$("#"+i+"x"+j);
                if(model.checkHasShip(i,j)) {
                    if (!square.hasClass("attacked")) {
                        square.addClass("attacked");
                    }
                }
                else {
                    if (!square.hasClass("missed")) {
                        square.addClass("missed");
                    }
                }
            }
        }
    }
}

$(function() {

    for(var i=0;i<rows;i++){
        var row='<div class="row">';
        for(j=0;j<columns;j++){
            row+='<div class="square"; id="'+i+"x"+j+'" />';
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

