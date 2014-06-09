package com.example.lifehelper.adapter;

import java.util.ArrayList;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class CityListAdapter extends BaseAdapter implements SectionIndexer{
	private Context mContext;
	private ArrayList<String> City;
	private String [] mIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private String [][] cityName = {{"阿坝","阿拉善","阿里","安康","安庆","鞍山","安顺","安阳","澳门"},
			{"北京","白银","保定","宝鸡","保山","包头","巴中","北海","蚌埠","本溪","毕节","滨州","百色","亳州"},
			{"重庆","成都","长沙","长春","沧州","常德","昌都","长治","常州","巢湖","潮州","承德","郴州","赤峰","池州","崇左","楚雄","滁州","朝阳"},
			{"大连","东莞","大理","丹东","大庆","大同","大兴安岭","德宏","德阳","德州","定西","迪庆","东营"},
			{"鄂尔多斯","恩施","鄂州"},
			{"福州","防城港","佛山","抚顺","抚州","阜新","阜阳"},
			{"广州","桂林","贵阳","甘南","赣州","甘孜","广安","广元","贵港","果洛"},
			{"杭州","哈尔滨","合肥","海口","呼和浩特","海北","海东","海南","海西","邯郸","汉中","鹤壁","河池","鹤岗","黑河","衡水","衡阳","河源","贺州","红河","淮安","淮北","怀化","淮南","黄冈","黄南","黄山","黄石","惠州","葫芦岛","呼伦贝尔","湖州","菏泽"},
			{"无"},
			{"济南","佳木斯","吉安","江门","焦作","嘉兴","嘉峪关","揭阳","吉林","金昌","晋城","景德镇","荆门","荆州","金华","济宁","晋中","锦州","九江","酒泉"},
			{"昆明","开封"},
			{"兰州","拉萨","来宾","莱芜","廊坊","乐山","凉山","连云港","聊城","辽阳","辽源","丽江","临沧","临汾","临夏","临沂","林芝","丽水","六安","六盘水","柳州","陇南","龙岩","娄底","漯河","洛阳","泸州","吕梁"},
			{"马鞍山","茂名","眉山","梅州","绵阳","牡丹江"},
			{"南京","南昌","南宁","宁波","南充","南平","南通","南阳","那曲","内江","宁德","怒江"},
			{"无"},
			{"盘锦","攀枝花","平顶山","平凉","萍乡","莆田","濮阳"},
			{"青岛","黔东南","黔南","黔西南","庆阳","清远","秦皇岛","钦州","齐齐哈尔","泉州","曲靖","衢州"},
			{"日喀则","日照"},
			{"上海","深圳","苏州","沈阳","石家庄","三门峡","三明","三亚","商洛","商丘","上饶","山南","汕头","汕尾","韶关","绍兴","邵阳","十堰","朔州","四平","绥化","遂宁","随州","宿迁","宿州"},
			{"天津","太原","泰安","泰州","台州","唐山","天水","铁岭","铜川","通化","通辽","铜陵","铜仁","台湾"},
			{"无"},
			{"无"},
			{"武汉","乌鲁木齐","无锡","威海","潍坊","文山","温州","乌海","芜湖","乌兰察布","武威","梧州"},
			{"厦门","西安","西宁","襄樊","湘潭","湘西","咸宁","咸阳","孝感","邢台","新乡","信阳","新余","忻州","西双版纳","宣城","许昌","徐州","香港","锡林郭勒","兴安"},
			{"银川","雅安","延安","延边","盐城","阳江","阳泉","扬州","烟台","宜宾","宜昌","宜春","营口","益阳","永州","岳阳","榆林","运城","云浮","玉树","玉溪","玉林"},
			{"中山"},};
	//private ViewHolder mHolder;
	private CityGridAdapter adapter;
	
	public CityListAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mIndex.length-1;
	}

	@Override
	public Object getItem(int position) {
		return mIndex[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder mHolder = null;
		adapter = new CityGridAdapter(mContext, cityName[position]);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.city_list, null);
		}
		if (null == mHolder) {
			mHolder = new ViewHolder();
			mHolder.indexLetter = (TextView) convertView.findViewById(R.id.index_letter);
			mHolder.gridView = (GridView) convertView.findViewById(R.id.city);
			convertView.setTag(mHolder);
		}else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.indexLetter.setText(mIndex[position]);
		mHolder.gridView.setAdapter(adapter);
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView indexLetter;
		GridView gridView;
	}

	@Override
	public int getPositionForSection(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSectionForPosition(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

}
