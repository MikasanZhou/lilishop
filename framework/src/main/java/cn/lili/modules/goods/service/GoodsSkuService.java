package cn.lili.modules.goods.service;

import cn.lili.common.cache.CachePrefix;
import cn.lili.modules.goods.entity.dos.Goods;
import cn.lili.modules.goods.entity.dos.GoodsSku;
import cn.lili.modules.goods.entity.dto.GoodsSearchParams;
import cn.lili.modules.goods.entity.dto.GoodsSkuStockDTO;
import cn.lili.modules.goods.entity.vos.GoodsSkuSpecVO;
import cn.lili.modules.goods.entity.vos.GoodsSkuVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品sku业务层
 *
 * @author pikachu
 * @date 2020-02-24 16:18:56
 */
public interface GoodsSkuService extends IService<GoodsSku> {

    static String getCacheKeys(String id) {
        return CachePrefix.GOODS_SKU.getPrefix() + id;
    }

    static String getStockCacheKey(String id) {
        return CachePrefix.SKU_STOCK.getPrefix() + id;
    }

    /**
     * 添加商品sku
     *
     * @param skuList sku列表
     * @param goods   商品信息
     */
    void add(List<Map<String, Object>> skuList, Goods goods);

    /**
     * 更新商品sku
     *
     * @param skuList            sku列表
     * @param goods              商品信息
     * @param regeneratorSkuFlag 是否是否重新生成sku
     */
    void update(List<Map<String, Object>> skuList, Goods goods, Boolean regeneratorSkuFlag);

    /**
     * 更新商品sku
     *
     * @param goodsSku sku信息
     */
    void update(GoodsSku goodsSku);

    /**
     * 从redis缓存中获取商品SKU信息
     *
     * @param id SkuId
     * @return 商品SKU信息
     */
    GoodsSku getGoodsSkuByIdFromCache(String id);

    /**
     * 获取商品sku详情
     *
     * @param goodsId 商品ID
     * @param skuId   skuID
     * @return 商品sku详情
     */
    Map<String, Object> getGoodsSkuDetail(String goodsId, String skuId);

    /**
     * 根据商品分组商品sku及其规格信息
     *
     * @param goodsId 商品id
     * @return 分组后的商品sku及其规格信息
     */
    List<GoodsSkuSpecVO> groupBySkuAndSpec(String goodsId);

    /**
     * 批量从redis中获取商品SKU信息
     *
     * @param ids SkuId集合
     * @return 商品SKU信息集合
     */
    List<GoodsSku> getGoodsSkuByIdFromCache(List<String> ids);

    /**
     * 获取goodsId下所有的goodsSku
     *
     * @param goodsId 商品id
     * @return goodsSku列表
     */
    List<GoodsSkuVO> getGoodsListByGoodsId(String goodsId);

    /**
     * 获取goodsId下所有的goodsSku
     *
     * @param goodsId 商品id
     * @return goodsSku列表
     */
    List<GoodsSku> getGoodsSkuListByGoodsId(String goodsId);

    /**
     * 根据goodsSku组装goodsSkuVO
     *
     * @param list 商品id
     * @return goodsSku列表
     */
    List<GoodsSkuVO> getGoodsSkuVOList(List<GoodsSku> list);

    /**
     * 根据goodsSku组装goodsSkuVO
     *
     * @param goodsSku 商品规格
     * @return goodsSku列表
     */
    GoodsSkuVO getGoodsSkuVO(GoodsSku goodsSku);

    /**
     * 分页查询商品sku信息
     *
     * @param searchParams 查询参数
     * @return 商品sku信息
     */
    IPage<GoodsSku> getGoodsSkuByPage(GoodsSearchParams searchParams);

    /**
     * 更新商品sku状态
     *
     * @param goods 商品信息(Id,MarketEnable/IsAuth)
     */
    void updateGoodsSkuStatus(Goods goods);

    /**
     * 更新SKU库存
     *
     * @param goodsSkuStockDTOS sku库存修改实体
     */
    void updateStocks(List<GoodsSkuStockDTO> goodsSkuStockDTOS);

    /**
     * 更新SKU库存
     *
     * @param skuId    SKUId
     * @param quantity 设置的库存数量
     */
    void updateStock(String skuId, Integer quantity);

    /**
     * 获取商品sku库存
     *
     * @param skuId 商品skuId
     * @return 库存数量
     */
    Integer getStock(String skuId);

    /**
     * 修改商品库存字段
     *
     * @param goodsSkus
     */
    void updateGoodsStuck(List<GoodsSku> goodsSkus);

    /**
     * 更新SKU评价数量
     *
     * @param skuId    SKUId
     */
    void updateGoodsSkuCommentNum(String skuId);
}