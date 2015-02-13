/**
 * Created by Fredrik on 2015-02-13.
 */
var BattleshipController = function(battleshipView,model){



    $(".square").click(function(event){
        var idString=event.target.id;
        var idSplitted=idString.split('x');
        var x_coordinate=parseInt(idSplitted[0]);
        var y_coordinate=parseInt(idSplitted[1]);

        model.attackSquare(x_coordinate,y_coordinate);
        battleshipView.showStatus();
        if(model.checkIfVictory()){
            battleshipView.victory();
            model.reset();
            battleshipView.reset();
        }
    });

    $("#reset").click(function (event) {
        model.reset();
        battleshipView.reset();
    })
};