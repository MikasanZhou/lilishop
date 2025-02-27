package cn.lili.modules.goods.entity.enums;

/**
 * 商品类型
 *
 * @author Bulbasaur
 * @date: 2021/5/28 4:23 下午
 */
public enum GoodsTypeEnum {


    PHYSICAL_GOODS("实物商品"),

    VIRTUAL_GOODS("虚拟商品"),

    E_COUPON("电子卡券");


    private final String description;

    GoodsTypeEnum(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

}
