package cn.lili.modules.distribution.serviceimpl;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.security.enums.UserEnums;
import cn.lili.common.utils.PageUtil;
import cn.lili.modules.distribution.entity.dos.Distribution;
import cn.lili.modules.distribution.entity.dos.DistributionGoods;
import cn.lili.modules.distribution.entity.dto.DistributionGoodsSearchParams;
import cn.lili.modules.distribution.entity.vos.DistributionGoodsVO;
import cn.lili.modules.distribution.mapper.DistributionGoodsMapper;
import cn.lili.modules.distribution.service.DistributionGoodsService;
import cn.lili.modules.distribution.service.DistributionService;
import cn.lili.modules.goods.entity.dos.GoodsSku;
import cn.lili.modules.goods.service.GoodsSkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 分销商品接口实现
 *
 * @author pikachu
 * @date 2020-03-24 23:04:56
 */
@Service
@Transactional
public class DistributionGoodsServiceImpl extends ServiceImpl<DistributionGoodsMapper, DistributionGoods> implements DistributionGoodsService {

    //分销员
    @Autowired
    private DistributionService distributionService;
    //规格商品
    @Autowired
    private GoodsSkuService goodsSkuService;

    @Override
    public IPage<DistributionGoodsVO> goodsPage(DistributionGoodsSearchParams searchParams) {
        //获取商家的分销商品列表
        if (UserContext.getCurrentUser().getRole().equals(UserEnums.STORE)) {
            return this.baseMapper.getDistributionGoodsVO(PageUtil.initPage(searchParams), searchParams.storeQueryWrapper());
        } else if (UserContext.getCurrentUser().getRole().equals(UserEnums.MEMBER)) {
            //判断当前登录用户是否为分销员
            Distribution distribution = distributionService.getDistribution();
            if (distribution != null) {
                //判断查看已选择的分销商品列表
                if (searchParams.isChecked()) {
                    return this.baseMapper.selectGoods(PageUtil.initPage(searchParams), searchParams.distributionQueryWrapper(), distribution.getId());
                } else {
                    return this.baseMapper.notSelectGoods(PageUtil.initPage(searchParams), searchParams.distributionQueryWrapper(), distribution.getId());
                }
            }
            throw new ServiceException(ResultCode.DISTRIBUTION_NOT_EXIST);
        }
        //如果是平台则直接进行查询
        return this.baseMapper.getDistributionGoodsVO(PageUtil.initPage(searchParams), searchParams.distributionQueryWrapper());
    }

    @Override
    public DistributionGoods distributionGoodsVO(String id) {

        return this.getById(id);
    }

    @Override
    public DistributionGoods distributionGoodsVOBySkuId(String skuId) {
        return this.getOne(new LambdaUpdateWrapper<DistributionGoods>().eq(DistributionGoods::getSkuId,skuId));
    }

    @Override
    public DistributionGoods checked(String skuId, Double commission) {

        //检查分销功能开关
        distributionService.checkDistributionSetting();

        //判断是否存在分销商品，如果存在不能添加
        QueryWrapper queryWrapper = Wrappers.query().eq("sku_id", skuId);

        if (this.getOne(queryWrapper) != null) {
            throw new ServiceException(ResultCode.DISTRIBUTION_GOODS_DOUBLE);
        }
        GoodsSku goodsSku = goodsSkuService.getGoodsSkuByIdFromCache(skuId);
        DistributionGoods distributionGoods = new DistributionGoods(goodsSku, commission);
        this.save(distributionGoods);
        return distributionGoods;
    }

}