package com.sugon.gsq.lighthouse.db.mapper;

import com.sugon.gsq.lighthouse.db.entity.LhSysUser;
import com.sugon.gsq.lighthouse.db.entity.LhSysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LhSysUserMapper {
    long countByExample(LhSysUserExample example);

    int deleteByExample(LhSysUserExample example);

    int deleteByPrimaryKey(String username);

    int insert(LhSysUser record);

    int insertSelective(LhSysUser record);

    List<LhSysUser> selectByExample(LhSysUserExample example);

    LhSysUser selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") LhSysUser record, @Param("example") LhSysUserExample example);

    int updateByExample(@Param("record") LhSysUser record, @Param("example") LhSysUserExample example);

    int updateByPrimaryKeySelective(LhSysUser record);

    int updateByPrimaryKey(LhSysUser record);
}