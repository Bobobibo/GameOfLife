package org.example;

public class Cell {

int x;
int y;

boolean isAlive;

public Cell(int x, int y, boolean isAlive){

    this.x=x;
    this.y=y;
    this.isAlive=isAlive;


}

public void revive(){

    this.isAlive= true;
}

public void kill(){

    this.isAlive=false;
}
/*public String print(){
    if(isAlive){
        return "x";
    }
    return " ";
}*/

//Cell cell= new Cell(1,1,true);

public void iterate(int neighbours){
    if(neighbours ==3){
        this.revive();

    }if (neighbours<2||neighbours>3){
        this.kill();
    }
}
    public char print() {
        return isAlive ? 'x' : '○';
    }

























}
