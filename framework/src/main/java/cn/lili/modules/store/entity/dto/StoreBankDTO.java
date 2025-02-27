package cn.lili.modules.store.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 店铺-银行信息
 *
 * @author Bulbasaur
 * @date 2020/12/7 15:54
 */
@Data
public class StoreBankDTO {

    //结算银行信息
    @Size(min = 1, max = 200)
    @NotBlank(message = "结算银行开户行名称不能为空")
    @ApiModelProperty(value = "结算银行开户行名称")
    private String settlementBankAccountName;

    @Size(min = 1, max = 200)
    @NotBlank(message = "结算银行开户账号不能为空")
    @ApiModelProperty(value = "结算银行开户账号")
    private String settlementBankAccountNum;

    @Size(min = 1, max = 200)
    @NotBlank(message = "结算银行开户支行名称不能为空")
    @ApiModelProperty(value = "结算银行开户支行名称")
    private String settlementBankBranchName;

    @Size(min = 1, max = 50)
    @NotBlank(message = "结算银行支行联行号不能为空")
    @ApiModelProperty(value = "结算银行支行联行号")
    private String settlementBankJointName;

    @NotBlank(message = "开户银行许可证电子版不能为空")
    @ApiModelProperty(value = "开户银行许可证电子版")
    private String settlementBankLicencePhoto;

}
