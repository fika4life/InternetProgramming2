/**
 * Created by Fredrik on 2015-02-09.
 */

var BattleShipModel = function(rows,columns){

    this._grid = new Array(this._rows);

    this._rows = rows;
    this._columns = columns;

    this._shipLengths = [2,3,3,4,5];
    this._directions = [[1,0],[0,1]];




    this._randomNumber=function(min,max){
        return (Math.floor(Math.random() * (max - min + 1)) + min);
    };

    this._populate=function(){
        for (var index in this._shipLengths){
            shipLength=this._shipLengths[index];
            var canBePlaced=false;
            while(!canBePlaced) {
                canBePlaced = true;
                var xCoord = this._randomNumber(0, this._rows-1);
                var yCoord = this._randomNumber(0, this._columns-1);
                var directionNr = this._randomNumber(0, this._directions.length-1);
                var directionToGo = this._directions[directionNr];

                var xDirection = directionToGo[0];
                var yDirection = directionToGo[1];


                for (var i = 0; i < shipLength; i++) {
                    var xChecked = xCoord+xDirection*i;
                    var yChecked = yCoord + yDirection*i;
                    if (xChecked<0||xChecked>this._rows-1||yChecked<0||yChecked>this._columns-1||this._grid[xChecked][yChecked].getHasShip()) {
                        canBePlaced=false;
                        break;
                    }
                }
            }
            for (var i = 0; i < shipLength; i++) {
                xChecked = xCoord + xDirection * i;
                yChecked = yCoord + yDirection * i;
                this._grid[xChecked][yChecked].addShip();
            }
        }
    };

    this.reset=function(){
        this._createGrid();
        this._populate();
    }

    this._createGrid=function(){
        for (var i = 0; i < this._rows; i++) {
            this._grid[i] = new Array(this._columns);
            for(var j=0;j<this._columns;j++){
                this._grid[i][j] = new BattleshipSquare();
            }
        }
    };

    this.checkHasShip=function(xCoord,yCoord){
        if(xCoord>this._rows-1){
            throw "X-coordinate out of bounds: "+xCoord;
        }
        if(yCoord>this._columns-1){
            throw  "Y-coordinate out of bounds: "+yCoord;
        }
        return this._grid[xCoord][yCoord].getHasShip();
    };

    this.attackSquare=function(xCoord,yCoord){
        if(xCoord>this._rows-1){
            throw "X-coordinate out of bounds: "+xCoord;
        }
        if(yCoord>this._columns-1){
            throw  "Y-coordinate out of bounds: "+yCoord;
        }
        this._grid[xCoord][yCoord].setAttacked();
    };

    this.checkAttacked=function(xCoord,yCoord){
        if(xCoord>this._rows-1){
            throw "X-coordinate out of bounds: "+xCoord;
        }
        if(yCoord>this._columns-1){
            throw  "Y-coordinate out of bounds: "+yCoord;
        }
        return this._grid[xCoord][yCoord].getAttacked();
    };

    this.checkIfVictory=function(){
        for(var i=0;i<rows;i++){
            for(var j=0;j<columns;j++){
                var square=this._grid[i][j];
                if((!square.getAttacked())&&square.getHasShip()){
                    return false;
                }
            }
        }
        return true;
    };


    this._createGrid();
    this._populate();




};