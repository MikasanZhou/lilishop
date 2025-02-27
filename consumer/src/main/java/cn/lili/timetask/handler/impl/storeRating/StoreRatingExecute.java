package cn.lili.timetask.handler.impl.storeRating;

import cn.lili.common.enums.SwitchEnum;
import cn.lili.modules.member.entity.dos.MemberEvaluation;
import cn.lili.modules.member.entity.vo.StoreRatingVO;
import cn.lili.modules.member.mapper.MemberEvaluationMapper;
import cn.lili.modules.store.entity.dos.Store;
import cn.lili.modules.store.entity.enums.StoreStatusEnum;
import cn.lili.modules.store.service.StoreService;
import cn.lili.timetask.handler.EveryDayExecute;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 店铺评分
 *
 * @author Chopper
 * @date 2021/3/15 5:30 下午
 */
@Component
public class StoreRatingExecute implements EveryDayExecute {
    //店铺
    @Autowired
    private StoreService storeService;
    //会员评价
    @Autowired
    private MemberEvaluationMapper memberEvaluationMapper;


    @Override
    public void execute() {
        //获取所有开启的店铺
        List<Store> storeList = storeService.list(new LambdaQueryWrapper<Store>().eq(Store::getStoreDisable, StoreStatusEnum.OPEN.name()));
        for (Store store : storeList) {
            //店铺所有开启的评价
            LambdaQueryWrapper<MemberEvaluation> QueryWrapper = Wrappers.lambdaQuery();
            QueryWrapper.eq(MemberEvaluation::getStoreId, store.getId());
            QueryWrapper.eq(MemberEvaluation::getStatus, SwitchEnum.OPEN.name());
            StoreRatingVO storeRatingVO = memberEvaluationMapper.getStoreRatingVO(QueryWrapper);

            if (storeRatingVO != null) {
                //保存评分
                LambdaUpdateWrapper<Store> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
                lambdaUpdateWrapper.eq(Store::getId, store.getId());
                lambdaUpdateWrapper.set(Store::getDescriptionScore, storeRatingVO.getDescriptionScore());
                lambdaUpdateWrapper.set(Store::getDeliveryScore, storeRatingVO.getDeliveryScore());
                lambdaUpdateWrapper.set(Store::getServiceScore, storeRatingVO.getServiceScore());
                storeService.update(lambdaUpdateWrapper);
            }

        }


    }
}
