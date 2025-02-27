package cn.lili.modules.file.serviceimpl;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.security.AuthUser;
import cn.lili.common.utils.PageUtil;
import cn.lili.common.utils.StringUtils;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.SearchVO;
import cn.lili.modules.file.entity.File;
import cn.lili.modules.file.entity.dto.FileOwnerDTO;
import cn.lili.modules.file.mapper.FileMapper;
import cn.lili.modules.file.plugin.FileManagerPlugin;
import cn.lili.modules.file.service.FileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理业务层实现
 *
 * @author Chopper
 * @date 2020/11/26 17:50
 */
@Service
@Transactional
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private FileManagerPlugin fileManagerPlugin;

    @Override
    public void batchDelete(List<String> ids) {

        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(File::getId, ids);

        List<File> files = this.list(queryWrapper);
        List<String> keys = new ArrayList<>();
        files.forEach(item -> keys.add(item.getFileKey()));
        fileManagerPlugin.deleteFile(keys);
        this.remove(queryWrapper);
    }

    @Override
    public void batchDelete(List<String> ids, AuthUser authUser) {
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(File::getId, ids);

        queryWrapper.eq(File::getUserEnums, authUser.getRole().name());
        //操作图片属性判定
        switch (authUser.getRole()) {
            case MEMBER:
                queryWrapper.eq(File::getOwnerId, authUser.getId());
                break;
            case STORE:
                queryWrapper.eq(File::getOwnerId, authUser.getStoreId());
                break;
            case MANAGER:
                break;
            default:
                throw new ServiceException(ResultCode.USER_AUTHORITY_ERROR);
        }
        List<File> files = this.list(queryWrapper);
        List<String> keys = new ArrayList<>();
        files.forEach(item -> keys.add(item.getFileKey()));
        fileManagerPlugin.deleteFile(keys);
        this.remove(queryWrapper);
    }

    @Override
    public IPage<File> customerPage(File file, SearchVO searchVO, PageVO pageVo) {
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(file.getName()), File::getName, file.getName())
                .like(StringUtils.isNotEmpty(file.getFileKey()), File::getFileKey, file.getFileKey())
                .like(StringUtils.isNotEmpty(file.getFileType()), File::getFileType, file.getFileType())
                .between(StringUtils.isNotEmpty(searchVO.getStartDate()) && StringUtils.isNotEmpty(searchVO.getEndDate()),
                        File::getCreateTime, searchVO.getStartDate(), searchVO.getEndDate());
        IPage<File> page = this.page(PageUtil.initPage(pageVo), queryWrapper);
        return page;
    }

    @Override
    public IPage<File> customerPageOwner(FileOwnerDTO ownerDTO, File file, SearchVO searchVO, PageVO pageVo) {
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(ownerDTO.getOwnerId()), File::getOwnerId, ownerDTO.getOwnerId())
                .eq(File::getUserEnums, ownerDTO.getUserEnums())
                .like(StringUtils.isNotEmpty(file.getName()), File::getName, file.getName())
                .like(StringUtils.isNotEmpty(file.getFileKey()), File::getFileKey, file.getFileKey())
                .like(StringUtils.isNotEmpty(file.getFileType()), File::getFileType, file.getFileType())
                .between(StringUtils.isNotEmpty(searchVO.getStartDate()) && StringUtils.isNotEmpty(searchVO.getEndDate()),
                        File::getCreateTime, searchVO.getStartDate(), searchVO.getEndDate());
        IPage<File> page = this.page(PageUtil.initPage(pageVo), queryWrapper);
        return page;
    }
}