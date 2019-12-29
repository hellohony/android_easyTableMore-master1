package adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<E> extends BaseAdapter implements
		OnClickCallback {
	private static String tag = CommonAdapter.class.getSimpleName();// ��ȡtag,ͨ���ǵ�ǰ�������
	private List<E> data = new ArrayList<E>();// /**********************************
	private Context context;
	private LayoutInflater inflater;
	private int layoutId;// ��Ҫ���Ĳ���id

	public CommonAdapter(List<E> data, Context context, int layoutId) {
		if (data != null) {
			this.data.addAll(data);
		}
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public E getItem(int position) {
		if (position > -1 && position < getCount()) {
			return data.get(position);
		}
		Log.e(tag, "error>>>>> position is incorrect");
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseViewHolder holder;
		if (convertView != null) {
			holder = (BaseViewHolder) convertView.getTag();
			// convertView = holder.getConvertView();

		} else {
			holder = new BaseViewHolder(parent, context, layoutId, this);
			setListener(holder);
		}
		// ��������
		holder.setPosition(position);

		System.out.println("hint>> " + position);
		setData(holder, getItem(position));
		return holder.getConvertView();
	}

	public void append(List<E> more) {
		if (more != null && !more.isEmpty()) {
			data.addAll(more);
			notifyDataSetChanged();
		}
	}

	public void querryAppend(List<E> more) {
		data.clear();// �ȶ�֮ǰ�����ݽ�������������������򵱲�ѯ�������ݵ�ʱ�򣬽���ᱣ����һ�εĽ������
		data.addAll(more);
		notifyDataSetChanged();
		
		
//		if (more != null && !more.isEmpty()) {//ȥ���ж����������������ڲ�ѯ��0�����ݵ�ʱ��Ҳ���ˢ��
//			data.addAll(more);
//			notifyDataSetChanged();
//		}
	}

	public void update(Object object) {// ר������ɾ���ʼǺ������½���ķ���
		data.remove(object);
		notifyDataSetChanged();
	}
	

	@Override
	public void onClick(View v, int position, BaseViewHolder viewHolder) {
		onClickCallback(v, position, viewHolder);
	}

	@Override
	public boolean onLongClick(View v, int position, BaseViewHolder viewHolder) {
		return onLonClickCallback(v, position, viewHolder);
	}

	// ���ü���
	public abstract void setListener(BaseViewHolder holder);

	// չʾ����
	public abstract void setData(BaseViewHolder holder, E item);

	// �̰��ص�
	public abstract void onClickCallback(View v, int position,
			BaseViewHolder viewHolder);

	// �����ص�
	public abstract boolean onLonClickCallback(View v, int position,
			BaseViewHolder viewHolder);

}
