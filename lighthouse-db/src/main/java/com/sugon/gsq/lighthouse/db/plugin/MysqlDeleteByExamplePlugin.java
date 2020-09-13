package com.sugon.gsq.lighthouse.db.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/*
 * ClassName: MysqlDeleteByExamplePlugin
 * Author: gsq
 * Date: 2019/9/25 12:30
 */
public class MysqlDeleteByExamplePlugin extends PluginAdapter {


    /**
     *
     * delete from table语句增加别名
     * 改为delete alias from table
     */
    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();
        String tableNameALias = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        String[] strings = tableNameALias.trim().split("\\s+");

        if (strings.length > 1) {
            int index = 0;
            for (Element cur : elements) {
                String content = cur.getFormattedContent(0);
                if (content.contains("delete from")) {
                    String alias = strings[1];
                    String deleteSql = "delete " + alias + " from " + tableNameALias;
                    TextElement deleteElement = new TextElement(deleteSql);
                    elements.remove(cur);
                    element.addElement(index, deleteElement);
                    break;
                }
                index++;
            }

        }
        return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }

}
