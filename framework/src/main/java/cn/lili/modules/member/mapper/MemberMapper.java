package cn.lili.modules.member.mapper;


import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.vo.MemberDistributionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会员数据处理层
 *
 * @author Bulbasaur
 * @date 2020-02-25 14:10:16
 */
public interface MemberMapper extends BaseMapper<Member> {

    @Select("select m.mobile from li_member m")
    List<String> getAllMemberMobile();

    @Select("select client_enum,count(0) as num from li_member group by client_enum")
    List<MemberDistributionVO> distribution();
}