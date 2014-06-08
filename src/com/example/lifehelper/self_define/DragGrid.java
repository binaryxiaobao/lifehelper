package com.example.lifehelper.self_define;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lifehelper.adapter.DateAdapter;

/**
 * 自定义的可拖拽GridView
 * 
 * @author Administrator
 * 
 */
public class DragGrid extends GridView {
	/** 长按选项的位置 **/
	private int dragPosition;
	/** 终止是选项的位置 **/
	private int dropPosition;

	/** 长按选项开始的位置 **/
	private int startPosition;

	private int holdPosition;
	private int specialPosition = -1;
	private int leftBottomPosition = -1;

	private int nColumns = 2;

	/** 总行数 **/
	private int nRows;
	/** 最后行的个数 **/
	private int Remainder;

	/** 获取选项总数 **/
	private int itemTotalCount;
	/** 获取选项一半的宽度 **/
	private int halfItemWidth;

	/** 窗体拖动img **/
	private ImageView dragImageView = null;
	private ViewGroup dragItemView = null;

	private WindowManager windowManager = null;
	private WindowManager.LayoutParams windowParams = null;

	/** 位置坐标s **/
	private int mLastX, xtox;
	private int mLastY, ytoy;

	/** 拖动Item的Y坐标 **/
	private int specialItemY;
	private int leftBtmItemY;

	private String LastAnimationID;

	private boolean isCountXY = false;
	/** 是否移动 **/
	private boolean isMoving = false;

	// private ArrayList<ViewGroup> mItemViewList ;

	public DragGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DragGrid(Context context) {
		super(context);
	}

	boolean flag = false;

	

	public boolean setOnItemLongClickListener(final MotionEvent ev) {
		this.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// 控制父控件拦截触摸事件
				getParent().requestDisallowInterceptTouchEvent(true);
				// 获取屏幕坐标并赋值
				int x = (int) ev.getX();
				int y = (int) ev.getY();
				// 获取位置
				mLastX = x;
				mLastY = y;
				// 赋值长按位置
				startPosition = dragPosition = dropPosition = arg2;
				// 如果是无效位置(超出边界，分割线等位置)，返回
				// 　AdapterView.INVALID_POSITION 代表无效的位置。有效值的范围是 0 到当前适配器项目数减 1
				// 。
				if (dragPosition == AdapterView.INVALID_POSITION) {
					return false;
				}

				// 将dragPosition与GridView当前显示的序号对应
				// getChildAt(int position)显示display在界面的position位置的View
				// getFirstVisiblePosition()返回第一个display在界面的view在adapter的位置position，可能是0，也可能是4
				ViewGroup itemView = (ViewGroup) getChildAt(dragPosition
						- getFirstVisiblePosition());

				// 初始化长按选项,宽,高,行数信息
				if (!isCountXY) {
					// 获取选项一半的宽度
					halfItemWidth = itemView.getWidth() / 2;
					int rows;
					itemTotalCount = getCount();
					rows = itemTotalCount / nColumns;
					Remainder = itemTotalCount % nColumns;
					// 计算一共的行数（包括最后一行不完整）
					nRows = Remainder == 0 ? rows : rows + 1;

					specialPosition = itemTotalCount - 1 - Remainder;//最后一个的position

					// if(Remainder!=1)
					leftBottomPosition = nColumns * (nRows - 1);//左下角的position
					// if(Remainder == 0 || nRows == 1)
					// specialPosition = -1;
					isCountXY = true;
				}

				if (specialPosition != dragPosition && dragPosition != -1) {
					specialItemY = getChildAt(specialPosition).getTop();
				} else {
					specialItemY = -1;
				}

				if (leftBottomPosition != dragPosition && dragPosition != -1) {
					leftBtmItemY = getChildAt(leftBottomPosition).getTop();
				} else {
					leftBtmItemY = -1;
				}

				dragItemView = itemView;
				// 每次都销毁一次cache，重新生成一个bitmap
				itemView.destroyDrawingCache();
				// 设置Drawingcache为true，获得选中项的影像bm，就是后面我们拖动的哪个头像
				itemView.setDrawingCacheEnabled(true);
				itemView.setDrawingCacheBackgroundColor(0xff6DB7ED);
				Bitmap bm = itemView.getDrawingCache(true);
				Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0,bm.getWidth() - 8, bm.getHeight() - 8);
				startDrag(bitmap, x, y);   //生成悬浮窗
				hideDropItem();   //隐藏该项
				itemView.setVisibility(View.INVISIBLE);
				isMoving = false;
				return false;
			};
		});
		return super.onInterceptTouchEvent(ev);
	}

	public void GetItemShadow(int x, int y) {

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 拦截按下动作
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			return setOnItemLongClickListener(ev);
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (dragImageView != null
				&& dragPosition != AdapterView.INVALID_POSITION) {
			// 控制父控件拦截事件
			getParent().requestDisallowInterceptTouchEvent(true);
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			switch (ev.getAction()) {
			case MotionEvent.ACTION_MOVE:
				if (!isCountXY) {
					// 移动到哪个位置
					xtox = x - mLastX;
					ytoy = y - mLastY;
					isCountXY = true;
				}
				onDrag(x-120, y+20);
				if (!isMoving)
					OnMove(x, y);
				break;
			case MotionEvent.ACTION_UP:
				stopDrag();//销毁悬浮窗体
				onDrop(x, y);
				break;
			}
		}
		return super.onTouchEvent(ev);
	}

	private void startDrag(Bitmap bm, int x, int y) {
		stopDrag();
		windowParams = new WindowManager.LayoutParams();
		windowParams.gravity = Gravity.TOP | Gravity.LEFT;
		
//		windowParams.x = x;
//		windowParams.y = y;
		
		windowParams.x = dragItemView.getLeft()+20;
		windowParams.y = dragItemView.getTop()+halfItemWidth-10;
		Log.d("TAG", windowParams.x+"+"+windowParams.y);
		windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.alpha = 0.8f;

		ImageView iv = new ImageView(getContext());
		iv.setImageBitmap(bm);
		windowManager = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		// 把影像ImagView添加到当前视图中
		windowManager.addView(iv, windowParams);

		dragImageView = iv;
	}

	/**
	 * 控制选项的移动位置
	 * 
	 * @param x
	 * @param y
	 */
	public void OnMove(int x, int y) {
		getParent().requestDisallowInterceptTouchEvent(true);
//		int TempPosition = pointToPosition(x, y);
//		int sOffsetY = specialItemY == -1 ? y - mLastY : y - specialItemY;
//				//- halfItemWidth;
//		int lOffsetY = leftBtmItemY == -1 ? y - mLastY : y - leftBtmItemY;
//				//- halfItemWidth;
//		if (TempPosition != AdapterView.INVALID_POSITION
//				&& TempPosition != dragPosition) {
//			dropPosition = TempPosition;
//		} else if (specialPosition != -1 && dragPosition == specialPosition
//				&& sOffsetY >= halfItemWidth) {
//			dropPosition = (itemTotalCount - 1);
//		} else if (leftBottomPosition != -1
//				&& dragPosition == leftBottomPosition
//				&& lOffsetY >= halfItemWidth) {
//			dropPosition = (itemTotalCount - 1);
//		}
//		if (dragPosition != startPosition)
//			dragPosition = startPosition;
//		int MoveNum = dropPosition - dragPosition;
//		if (dragPosition != startPosition && dragPosition == dropPosition)
//			MoveNum = 0;
//		if (MoveNum != 0) {
//			int itemMoveNum = Math.abs(MoveNum);
//			float Xoffset, Yoffset;
//			for (int i = 0; i < itemMoveNum; i++) {
//				if (MoveNum > 0) {
//					holdPosition = dragPosition + 1;
//					Xoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? (-1) : (nColumns - 1);
//					Yoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? 0 : (-1);
//				} else {
//					holdPosition = dragPosition - 1;
//					Xoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? 1 : (-(nColumns - 1));
//					Yoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? 0 : 1;
//				}
//				ViewGroup moveView = (ViewGroup) getChildAt(holdPosition);
//				Animation animation = getMoveAnimation(Xoffset, Yoffset);
//				moveView.startAnimation(animation);
//				dragPosition = holdPosition;
//				if (dragPosition == dropPosition)
//					LastAnimationID = animation.toString();
//				final DateAdapter adapter = (DateAdapter) this.getAdapter();
//				animation
//						.setAnimationListener(new Animation.AnimationListener() {
//
//							@Override
//							public void onAnimationStart(Animation animation) {
//								// TODO Auto-generated method stub
//								isMoving = true;
//							}
//
//							@Override
//							public void onAnimationRepeat(Animation animation) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onAnimationEnd(Animation animation) {
//								// TODO Auto-generated method stub
//								String animaionID = animation.toString();
//								if (animaionID
//										.equalsIgnoreCase(LastAnimationID)) {
//									adapter.exchange(startPosition,
//											dropPosition);
//									startPosition = dropPosition;
//									isMoving = false;
//								}
//							}
//						});
//			}
//		}
		int TempPosition = pointToPosition(x,y);
		int sOffsetY = specialItemY == -1 ? y - mLastY : y - specialItemY - halfItemWidth;
		int lOffsetY = leftBtmItemY == -1 ? y - mLastY : y - leftBtmItemY - halfItemWidth;
		if(TempPosition != AdapterView.INVALID_POSITION && TempPosition != dragPosition){
			dropPosition = TempPosition;
		}else if(specialPosition != -1 && dragPosition == specialPosition && sOffsetY >= halfItemWidth){
			dropPosition = (itemTotalCount - 1);
		}else if(leftBottomPosition != -1 && dragPosition == leftBottomPosition && lOffsetY >= halfItemWidth){
			dropPosition = (itemTotalCount - 1);
		}	
		if(dragPosition != startPosition)
			dragPosition = startPosition;
		int MoveNum = dropPosition - dragPosition;
		if(dragPosition != startPosition && dragPosition == dropPosition)
			MoveNum = 0;
		if(MoveNum != 0){
			System.out.println("moveNum="+MoveNum);
			int itemMoveNum = Math.abs(MoveNum);
			float Xoffset,Yoffset;
			for (int i = 0;i < itemMoveNum;i++){
				System.out.println("itemMoveNum=" + itemMoveNum);
				if(MoveNum > 0){
					holdPosition = dragPosition + 1;
//					Xoffset = (dragPosition/nColumns == holdPosition/nColumns) ? (-1) : (nColumns -1);
					Xoffset = 1;
					Yoffset = (dragPosition/nColumns == holdPosition/nColumns) ? 0 : (-1);
				}else{
					holdPosition = dragPosition - 1;
					Xoffset = (dragPosition/nColumns == holdPosition/nColumns) ? 1 : (-(nColumns-1));
					Yoffset = (dragPosition/nColumns == holdPosition/nColumns) ? 0 : 1;
				}
				ViewGroup moveView = (ViewGroup)getChildAt(holdPosition);				
				Animation animation = getMoveAnimation(Xoffset,Yoffset);
				moveView.startAnimation(animation);
				dragPosition = holdPosition;
				if(dragPosition == dropPosition)
					LastAnimationID = animation.toString();
				final DateAdapter adapter = (DateAdapter)this.getAdapter();
				animation.setAnimationListener(new Animation.AnimationListener() {


					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						isMoving = true;
					}


					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}


					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						String animaionID = animation.toString();
						if(animaionID.equalsIgnoreCase(LastAnimationID)){
							adapter.exchange(startPosition, dropPosition);
							startPosition = dropPosition;
							isMoving = false;
						}					
					}
				});	
			}
		}
	}

	/**
	 * 更新适配器
	 * 
	 * @param x
	 * @param y
	 */
	private void onDrop(int x, int y) {
		final DateAdapter adapter = (DateAdapter) this.getAdapter();
		adapter.showDropItem(true);
		adapter.notifyDataSetChanged();
	}

	/**
	 * 根据你手指移动来改变windowmanager的位置
	 * 
	 * @param x
	 * @param y
	 */
	private void onDrag(int x, int y) {
		// 计算位置，通过windowManager不停地更新View
		if (dragImageView != null) {
			windowParams.alpha = 0.8f;
//			windowParams.gravity=Gravity.LEFT|Gravity.TOP;
			windowParams.x = (x );
			windowParams.y = (y );
//					+ (int) (45 * Configure.screenDensity
			windowManager.updateViewLayout(dragImageView, windowParams);
		}
	}

	/**
	 * 从windowManager 移除ImageView 内存释放
	 */
	private void stopDrag() {
		if (dragImageView != null) {
			windowManager.removeView(dragImageView);
			dragImageView = null;
		}
	}

	/**
	 * 隐藏点击选项
	 */
	private void hideDropItem() {
		final DateAdapter adapter = (DateAdapter) this.getAdapter();
		adapter.showDropItem(false);
	}

	public Animation getMoveAnimation(float x, float y) {
		TranslateAnimation go = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, x,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, y);
		go.setFillAfter(true);
		go.setDuration(300);
		return go;
	}

}
