package com.example.projectda.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

  private int mItemOffset;

  private int spanCount = 3;

  public ItemOffsetDecoration(int itemOffset) {
    mItemOffset = itemOffset;
  }

  public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
    this(context.getResources().getDimensionPixelSize(itemOffsetId));
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);

    int position = parent.getChildLayoutPosition(view);

    GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();

    outRect.top = mItemOffset;
    outRect.right = mItemOffset;
    outRect.left = mItemOffset;
    outRect.bottom = mItemOffset;
  }
}