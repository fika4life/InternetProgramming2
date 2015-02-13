








$(function() {
    var rows=9;
    var columns=9;

    var model=new BattleShipModel(rows,columns);
    var view=new BattleshipView($('#board'),model,rows,columns);
    var controller=new BattleshipController(view,model);
});

