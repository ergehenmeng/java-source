package com.eghm;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.freemarker.FreemarkerEngine;


public class FreemarkerTest {
	public static void main(String[] args) {
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setCustomEngine(FreemarkerEngine.class);
		TemplateEngine engine = TemplateUtil.createEngine();
		Template template = engine.getTemplate("sgfdsfsd ${user.name}");
		System.out.println(template.render(Dict.create().set("user.name", "sdfsdf")));
	}
}
