/**
 * Created by Fredrik on 2015-02-09.
 */
var BattleshipSquare = function(){

    this._hasShip=false;
    this._attacked=false;

    this.getHasShip=function(){
        return this._hasShip;
    };

    this.getAttacked=function(){
        return this._attacked;
    };

    this.addShip=function(){
        this._hasShip=true;
    };

    this.setAttacked=function(){
        this._attacked=true;
    };
};