package com.example.baopengjian.ray_dailywork.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	public CommonAdapter(Context context, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mItemLayoutId = itemLayoutId;
	}

	public void refreshData(List<T> data) {
		this.mDatas = data;
		notifyDataSetChanged();
	}

	public List<T> getData() {
		return this.mDatas;
	}

	public void addData(List<T> data) {
		if(this.mDatas == null){
			this.mDatas = data;
		}else {
			this.mDatas.addAll(data);
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas == null || mDatas.size() == 0 ? null : mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setList(List<T> list) {
		this.mDatas = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = getViewHolder(convertView, parent);
		viewHolder.setPosition(position);
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder helper, T item);

	public ViewHolder getViewHolder(View convertView,
									ViewGroup parent) {
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId);
	}

}
