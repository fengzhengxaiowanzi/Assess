package com.zhang.dao;

import com.zhang.common.DictCode;
import com.zhang.common.DictDb;
import com.zhang.common.ReturnValue;
import com.zhang.common.ServiceResult;
import com.zhang.mapper.BMemberLevelMapper;
import com.zhang.pojo.BMemberLevel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang
 * @Description: com.yihu.dao
 * @Date：Created in 15:18 2021/2/7
 */
@Repository
public class BMemberLevelDao {
    @Resource
    private BMemberLevelMapper memberLevelMapper;
    public ServiceResult<List<BMemberLevel>> getMemberLevel(){
        try {
            List<BMemberLevel> lists = new ArrayList<>();
            lists = memberLevelMapper.selectAllLevel();
             return new ServiceResult<List<BMemberLevel>>(DictCode.SUCCESS,"查询成功").setResult(lists);
        }catch (DataAccessException e){
            return new ServiceResult<>(DictCode.SQL_EXCEPTION,"查询异常");
        }
    }
    public ServiceResult<BMemberLevel> addMemberLevel(BMemberLevel memberLevel){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        memberLevel.setInsertTime(now);
        memberLevel.setLastModify(now);
        memberLevel.setStatus(DictDb.Status.NORMAL);
        try {
            memberLevelMapper.insertLevel(memberLevel);
            return new ServiceResult<BMemberLevel>(DictCode.SUCCESS, "成功").setResult(memberLevel);
        } catch (DataAccessException e) {
            return new ServiceResult<>(DictCode.SQL_EXCEPTION, "新增异常");
        }
    }
    public ReturnValue updateMemberLevel(BMemberLevel memberLevel){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        memberLevel.setLastModify(now);
        try {
             memberLevelMapper.updateLevel(memberLevel);
            return new ReturnValue(DictCode.SUCCESS, "成功");
        } catch (DataAccessException e) {
            return new ReturnValue(DictCode.SQL_EXCEPTION, "修改异常");
        }
    }
//    public ReturnValue deleteMemberLevel(Integer levelId){
//        BMemberLevel bMemberLevel = new BMemberLevel();
//        bMemberLevel.setLevelId(levelId);
//        bMemberLevel.setStatus(DictDb.Status.UN_NORMAL);
//        return updateMemberLevel(bMemberLevel);
//    }
}
