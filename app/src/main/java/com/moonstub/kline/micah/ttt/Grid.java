package com.moonstub.kline.micah.ttt;

/**
 * Created by Micah on 4/5/2016.
 */

enum SQUARE_TYPE{
    X,O,B
}
public class Grid {

    private int x,y,mIndex;
    private int resId;
    private SQUARE_TYPE mType;

    public Grid(int x, int y){
        mType = SQUARE_TYPE.B;
    }

    public Grid(int index) {
        mType = SQUARE_TYPE.B;
        mIndex = index;
    }

    public boolean setType(SQUARE_TYPE type){
        if(mType == SQUARE_TYPE.B){
            mType = type;
            return true;
        }
        return false;
    }

    public SQUARE_TYPE getType(){
        return mType;
    }


    //return index based on a grid size of three
    public int convertFrom(int x, int y){
        return y * 3 + x;
    }

    //Returns Array Location based on index
    public int[] convertTo(int index){
        //Not yet Implementec
     return new int[]{0,0};
    }



}
