package tang.di.xiaozhu.data;

import java.util.ArrayList;

public class XiaoZhuDataList {

	private ArrayList<XiaoZhuData> list;

	private static XiaoZhuDataList instance;

	public static XiaoZhuDataList getInstance() {
		if (instance == null) {
			instance = new XiaoZhuDataList();
		}
		return instance;
	}

	private XiaoZhuDataList() {
		list = new ArrayList<XiaoZhuData>();
	}

	public void add(XiaoZhuData data) {
		list.add(data);
	}

	public ArrayList<XiaoZhuData> getListForXiaoZhu() {
		return list;
	}


}
