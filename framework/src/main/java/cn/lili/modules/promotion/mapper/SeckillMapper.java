package cn.lili.modules.promotion.mapper;

import cn.lili.modules.promotion.entity.dos.Seckill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * 秒杀活动数据处理层
 *
 * @author Chopper
 * @date 2020/8/21
 */
public interface SeckillMapper extends BaseMapper<Seckill> {

    @Update("UPDATE li_seckill SET goods_num =( SELECT count( id ) FROM li_seckill_apply WHERE seckill_id = #{seckillId} ) WHERE id = #{seckillId}")
    void updateSeckillGoodsNum(String seckillId);
}