/**
 * Created by Fredrik on 2015-02-13.
 */
var BattleshipView = function(board,model,rows,columns){

    this.board=board;
    this.model=model;
    this.rows=rows;
    this.columns=columns;


    for(var i=0;i<rows;i++) {
        var row='<div class="row">';
        for (j = 0; j < columns; j++) {
            row += '<div class="square"; id="' + i + "x" + j + '" />';
        }
        row += '</div>';
        this.board.append(row);
    }



    this.victory=function(){
        alert("YOU FOUND ALL THE SHIPS. CONGRATULATIONS!");
    };

    this.reset=function(){
        for(var i=0;i<this.rows;i++) {
            for (j = 0; j < this.columns; j++) {
                var square=this.board.find("#"+i+"x"+j);
                square.removeClass("attacked");
                square.removeClass("missed");
            }
        }
    };

    this.showStatus=function(){
        for(var i=0;i<this.rows;i++){
            for(j=0;j<this.columns;j++){
                if(this.model.checkAttacked(i,j)){
                    var square=this.board.find("#"+i+"x"+j);
                    if(this.model.checkHasShip(i,j)) {
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
};