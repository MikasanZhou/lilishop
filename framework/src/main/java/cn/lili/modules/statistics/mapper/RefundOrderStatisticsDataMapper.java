package cn.lili.modules.statistics.mapper;

import cn.lili.modules.order.order.entity.dos.StoreFlow;
import cn.lili.modules.statistics.model.vo.RefundOrderStatisticsDataVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 退款统计数据处理层
 *
 * @author Bulbasaur
 * @date 2020/12/10 11:22
 */
public interface RefundOrderStatisticsDataMapper extends BaseMapper<StoreFlow> {

    @Select("SELECT refund_sn,store_name,member_name,name,specs,final_price FROM li_store_flow ${ew.customSqlSegment}")
    IPage<RefundOrderStatisticsDataVO> getRefundStatisticsData(IPage<RefundOrderStatisticsDataVO> page, @Param(Constants.WRAPPER) Wrapper<RefundOrderStatisticsDataVO> queryWrapper);
}
