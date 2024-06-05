package mybatis.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.service.FactoryService;
import mybatis.vo.BbsVO;

public class BbsDAO {

	public static BbsVO[] getList(String bname, int begin, int end) {
		BbsVO[] b_ar = null;
		
		SqlSession ss = FactoryService.getFactory().openSession();
		
		HashMap<String, String> b_map = new HashMap<>();
		
		b_map.put("bname", bname);
		b_map.put("begin", String.valueOf(begin));
		b_map.put("end", String.valueOf(end));
		
		List<BbsVO> b_list = ss.selectList("bbs.list",b_map);
		
		if(b_list != null && b_list.size()>0) {
			b_ar = new BbsVO[b_list.size()];
			
			b_list.toArray(b_ar);
		}
		
		return b_ar;
	}
	
	public static void add(String title, String writer, String content, String fname, String oname, String ip, String b_name) {
		
		SqlSession ss = FactoryService.getFactory().openSession();
		
		HashMap<String, String> b_map = new HashMap<>();
		
		b_map.put("subject", title);
		b_map.put("writer", writer);
		b_map.put("content", content);
		b_map.put("file_name", fname);
		b_map.put("ori_name", oname);
		b_map.put("ip", ip);
		b_map.put("bname", b_name);
		
		int chk = ss.insert("bbs.add",b_map);
		
		if(chk == 1) {
			ss.commit();
		} else {
			ss.rollback();
		}
		ss.close();
		
		
		return;
		
	}
	
	
}