package com.example.akil.recyclerswipable;

/**
 * Created by akil on 1/2/17.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPostion, int toPosition);
    void onItemDimiss(int Position);
}
