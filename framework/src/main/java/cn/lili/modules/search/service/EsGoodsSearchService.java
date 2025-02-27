package cn.lili.modules.search.service;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.search.entity.dos.EsGoodsIndex;
import cn.lili.modules.search.entity.dos.EsGoodsRelatedInfo;
import cn.lili.modules.search.entity.dto.EsGoodsSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * ES商品搜索业务层
 * @author paulG
 * @date 2020/10/15
 **/
public interface EsGoodsSearchService {

    /**
     * 商品搜索
     *
     * @param searchDTO 搜索参数
     * @param pageVo    分页参数
     * @return 搜索结果
     */
    Page<EsGoodsIndex> searchGoods(EsGoodsSearchDTO searchDTO, PageVO pageVo);

    /**
     * 获取热门关键词
     *
     * @param start 查询范围开始位置
     * @param end   查询范围结束位置
     * @return
     */
    List<String> getHotWords(Integer start, Integer end);

    /**
     * 获取筛选器
     *
     * @param goodsSearch 搜索条件
     * @param pageVo      分页参数
     * @return Map
     */
    EsGoodsRelatedInfo getSelector(EsGoodsSearchDTO goodsSearch, PageVO pageVo);

    List<EsGoodsIndex> getEsGoodsBySkuIds(List<String> skuIds);
}
