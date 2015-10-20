package tang.di.xiaozhu.data;

import java.util.UUID;

public class XiaoZhuData {

	private String xiao_zhu_chu_sheng_ri_qi;
	private String xiao_zhu_mai_chu_ri_qi;
	private String xiao_zhu_jia_ge;
	private String xiao_zhu_mai_chu_zhi_shu;
	private String xiao_zhu_suo_zai_wei_zhi;
	private String xiao_zhu_xian_zai_zhi_shu;
	private UUID id;

	public XiaoZhuData() {
		id = UUID.randomUUID();
	}

	public String getXiao_zhu_xian_zai_zhi_shu() {
		return xiao_zhu_xian_zai_zhi_shu;
	}

	public void setXiao_zhu_xian_zai_zhi_shu(String xiao_zhu_xian_zai_zhi_shu) {
		this.xiao_zhu_xian_zai_zhi_shu = xiao_zhu_xian_zai_zhi_shu;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getXiao_zhu_chu_sheng_ri_qi() {
		return xiao_zhu_chu_sheng_ri_qi;
	}

	public String getXiao_zhu_suo_zai_wei_zhi() {
		return xiao_zhu_suo_zai_wei_zhi;
	}

	public void setXiao_zhu_suo_zai_wei_zhi(String xiao_zhu_suo_zai_wei_zhi) {
		this.xiao_zhu_suo_zai_wei_zhi = xiao_zhu_suo_zai_wei_zhi;
	}

	public void setXiao_zhu_chu_sheng_ri_qi(String xiao_zhu_chu_sheng) {
		this.xiao_zhu_chu_sheng_ri_qi = xiao_zhu_chu_sheng;
	}

	public String getXiao_zhu_mai_chu_ri_qi() {
		return xiao_zhu_mai_chu_ri_qi;
	}

	public void setXiao_zhu_mai_chu_ri_qi(String xiao_zhu_mai_chu) {
		this.xiao_zhu_mai_chu_ri_qi = xiao_zhu_mai_chu;
	}

	public String getXiao_zhu_jia_ge() {
		return xiao_zhu_jia_ge;
	}

	public void setXiao_zhu_jia_ge(String xiao_zhu_jia_ge) {
		this.xiao_zhu_jia_ge = xiao_zhu_jia_ge;
	}

	public String getXiao_zhu_mai_chu_zhi_shu() {
		return xiao_zhu_mai_chu_zhi_shu;
	}

	public void setXiao_zhu_mai_chu_zhi_shu(String xiao_zhu_mai_chu_zhi_shu) {
		this.xiao_zhu_mai_chu_zhi_shu = xiao_zhu_mai_chu_zhi_shu;
	}
	
	//

}
