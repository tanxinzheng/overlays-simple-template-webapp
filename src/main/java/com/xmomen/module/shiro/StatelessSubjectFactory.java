package com.xmomen.module.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by tanxinzheng on 17/6/11.
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    /**
     * Create a stateless WebSubject
     * @param context
     * @return
     */
    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
