package com.xmomen.module.authorization.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.fss.FileStoreService;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.framework.utils.UUIDGenerator;
import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreterService;
import com.xmomen.module.attachment.model.Attachment;
import com.xmomen.module.attachment.service.AttachmentService;
import com.xmomen.module.authorization.mapper.UserMapper;
import com.xmomen.module.authorization.model.AttachmentGroupEnmu;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.model.UserQuery;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.model.SelectIndex;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
import com.xmomen.framework.utils.PasswordHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:53
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService, DictionaryInterpreterService, SelectService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    FileStoreService fileStoreService;

    /**
     * 新增数据字典
     *
     * @param userModel 新增数据字典对象参数
     * @return UserModel    数据字典领域对象
     */
    @Override
    @Transactional
    public UserModel createUser(UserModel userModel) {
        String password = UUIDGenerator.getInstance().getUUID(6);
        String salt = UUIDGenerator.getInstance().getUUID(32);
        String realPassword = PasswordHelper.encryptPassword(password, salt);
        userModel.setPassword(realPassword);
        userModel.setSalt(salt);
        User user = createUser(userModel.getEntity());
        if(user != null){
            return getOneUserModel(user.getId());
        }
        return null;
    }

    /**
     * 新增数据字典实体对象
     *
     * @param user 新增数据字典实体对象参数
     * @return User 数据字典实体对象
     */
    @Override
    @Transactional
    public User createUser(User user) {
        userMapper.insertSelective(user);
        return user;
    }

    /**
    * 批量新增数据字典
    *
    * @param userModels 新增数据字典对象集合参数
    * @return List<UserModel>    数据字典领域对象集合
    */
    @Override
    @Transactional
    public List<UserModel> createUsers(List<UserModel> userModels) {
        List<UserModel> userModelList = null;
        for (UserModel userModel : userModels) {
            userModel = createUser(userModel);
            if(userModel != null){
                if(userModelList == null){
                    userModelList = new ArrayList<>();
                }
                userModelList.add(userModel);
            }
        }
        return userModelList;
    }

    /**
    * 更新数据字典
    *
    * @param userModel 更新数据字典对象参数
    * @param userQuery 过滤数据字典对象参数
    */
    @Override
    @Transactional
    public void updateUser(UserModel userModel, UserQuery userQuery) {
        userMapper.updateSelectiveByQuery(userModel.getEntity(), userQuery);
    }

    /**
     * 更新数据字典
     *
     * @param userModel 更新数据字典对象参数
     */
    @Override
    @Transactional
    public void updateUser(UserModel userModel) {
        updateUser(userModel.getEntity());
    }

    /**
     * 更新数据字典实体对象
     *
     * @param user 新增数据字典实体对象参数
     * @return User 数据字典实体对象
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        userMapper.updateSelective(user);
    }

    /**
     * 删除数据字典
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteUser(String[] ids) {
        userMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除数据字典
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteUser(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询数据字典领域分页对象（带参数条件）
     *
     * @param userQuery 查询参数
     * @return Page<UserModel>   数据字典参数对象
     */
    @Override
    public Page<UserModel> getUserModelPage(UserQuery userQuery) {
        PageInterceptor.startPage(userQuery);
        userMapper.selectModel(userQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询数据字典领域集合对象（带参数条件）
     *
     * @param userQuery 查询参数对象
     * @return List<UserModel> 数据字典领域集合对象
     */
    @Override
    public List<UserModel> getUserModelList(UserQuery userQuery) {
        return userMapper.selectModel(userQuery);
    }

    /**
     * 查询数据字典实体对象
     *
     * @param id 主键
     * @return User 数据字典实体对象
     */
    @Override
    public User getOneUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据username查询用户
     *
     * @param username
     * @return
     */
    @Override
    public UserModel getOneUserModelByUsername(String username) {
        return userMapper.selectModelByUsername(username);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return UserModel 数据字典领域对象
     */
    @Override
    public UserModel getOneUserModel(String id) {
        return userMapper.selectModelByPrimaryKey(id);
    }

    @Cacheable(cacheNames = DictionaryIndex.DICTIONARY_CACHE_NAME_KEY)
    @Override
    public User getOneUserCache(String id) {
        return getOneUser(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param userQuery 数据字典查询参数对象
     * @return UserModel 数据字典领域对象
     */
    @Override
    public UserModel getOneUserModel(UserQuery userQuery) {
        List<UserModel> userModelList = userMapper.selectModel(userQuery);
        if(CollectionUtils.isEmpty(userModelList)){
            return null;
        }
        if(userModelList.size() > 1){
            throw new BusinessException();
        }
        return userModelList.get(0);
    }

    @Autowired
    AttachmentService attachmentService;

    /**
     * 更换用户头像
     *
     * @param userId
     * @param multipartFile
     */
    @Transactional
    @Override
    public void updateAvatar(String userId, MultipartFile multipartFile) {
        if(multipartFile.isEmpty()){
            throw new BusinessException("请选择有效的图片");
        }
        String fileKey = UUIDGenerator.getInstance().getUUID();
        String filePath = userId + File.separator + fileKey;
        Attachment attachment = new Attachment();
        attachment.setAttachmentGroup(AttachmentGroupEnmu.USER_AVATAR.name());
        attachment.setAttachmentKey(fileKey);
        attachment.setAttachmentPath(userId);
        attachment.setAttachmentSize(multipartFile.getSize());
        attachment.setUploadTime(new Date());
        attachment.setUploadUserId(userId);
        attachment.setAttachmentSuffix(multipartFile.getContentType());
        attachment.setOriginName("avatar.png");
        attachment.setIsPrivate(false);
        attachmentService.createAttachment(attachment);
        User user = getOneUser(userId);
        if(user != null && StringUtils.isNotBlank(user.getAvatar())){
            // 删除旧头像
            attachmentService.deleteAttachmentByKey(user.getAvatar());
        }
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setAvatar(fileKey);
        updateUser(updateUser);
        try {
            fileStoreService.newFile(filePath, multipartFile.getInputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException("图片上传失败，错误原因：" + e.getMessage());
        }
    }

    /**
     * 翻译
     *
     * @param dictionaryType 字典类型
     * @param dictionaryCode 字典代码
     * @return
     */
    @Override
    public Object translateDictionary(DictionaryIndex dictionaryType, String dictionaryCode) {
        UserModel user = getOneUserModel(dictionaryCode);
        if(user == null){
            return null;
        }
        return user;
    }

    /**
     * 字典索引
     *
     * @return
     */
    @Override
    public DictionaryIndex getDictionaryIndex() {
        return DictionaryIndex.USER_ID;
    }

    /**
     * 查询option数据
     *
     * @param selectOptionQuery
     * @return
     */
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        UserQuery userQuery = new UserQuery();
        List<UserModel> userModelList = getUserModelList(userQuery);
        if(CollectionUtils.isEmpty(userModelList)){
            return Lists.newArrayList();
        }
        int i = 0;
        List<SelectOptionModel> selectOptionModelList = Lists.newArrayList();
        for (UserModel userModel : userModelList) {
            SelectOptionModel selectOptionModel = new SelectOptionModel(
                    DictionaryIndex.USER_ID.name(),
                    "用户",
                    userModel.getId(),
                    userModel.getNickname(),
                    i++
            );
            selectOptionModelList.add(selectOptionModel);
        }
        return selectOptionModelList;
    }

    /**
     * 获取select index
     *
     * @return
     */
    @Override
    public SelectIndex getSelectIndex() {
        return SelectIndex.USER;
    }

}
