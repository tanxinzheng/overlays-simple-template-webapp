package com.xmomen.maven.plugins.mybatis.generator.context;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.config.PluginConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承类
 * Created by tanxinzheng on 2015/1/11.
 */
public class IntrospectedTableMyBatis3Impl4Xmomen extends IntrospectedTableMyBatis3Impl {

    public IntrospectedTableMyBatis3Impl4Xmomen() {
        super();
    }

    /**
     * 修复重新生成xml无法覆盖原xml文件的BUG
     * @return
     */
    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();

        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                    getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(),
                    context.getSqlMapGeneratorConfiguration().getTargetProject(),
                    false, context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answer.add(gxf);
            }
        }

        return answer;
    }
}
