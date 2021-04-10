package com.mp.mprbac;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.platform.commons.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); // TODO 当前项目的物理根目录
        System.out.println("projectPath：" + projectPath);
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("seven"); // 生成的类里加入作者
        gc.setOpen(false);   // 生成代码之后不打开文件夹
        gc.setServiceName("%sService"); // %s为占位符，这里设置接口是xxxxxService，默认的是这样的xxxxxIService
        // gc.setSwagger2(true); // TODO 实体属性 Swagger2 注解
        // gc.setDateType(DateType.ONLY_DATE); // TODO 设置日期类型
        mpg.setGlobalConfig(gc);

        // TODO 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://121.5.242.163:4316/project?useUnicode=true&characterEncoding=utf-8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1g5~9fth0");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));  // TODO 模块名（作为参数动态传入），当前项目这里输入mprbac
        pc.setParent("com.mp");  // 包
        mpg.setPackageInfo(pc); // 最终生成的包是: 包 + 模块名 = com.mp.mprbac

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        // TODO 是否允许覆盖已经存在的文件
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                return true; // TODO true表示允许
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);


        // TODO 并非所有实体类都有公共的字段，这里需要进行判断，通过接收的输入参数进行判断
        if (scanner("是否需要继承基类？是请输入y，不是请输入n").equalsIgnoreCase("y")) {
            /**
             * TODO 设置实体类的基类（公共参数放在基类里），如果不使用，参与的步骤全部注释掉即可
             * 步骤1 设置基类的全限定类名
             */
            strategy.setSuperEntityClass("com.mp.mprbac.entity.BaseEntity");
            /**
             * TODO 设置实体类的基类（公共参数放在基类里），如果不使用，参与的步骤全部注释掉即可
             * 步骤2 设置写于父类中的公共字段
             */
            strategy.setSuperEntityColumns("create_time", "modified_time", "create_account_id", "modified_account_id", "deleted");

        }

        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false); // TODO 当前项目是前后端不分离的，所以不使用RestController，不然全返回json数据而不是html模板页面

        // TODO 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");


        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_"); // TODO 表名前缀


        strategy.setLogicDeleteFieldName("deleted"); // TODO 设置逻辑删除字段
        // TODO 设置自动填充字段
        ArrayList<TableFill> tableFills = new ArrayList<>();
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill modifiedTime = new TableFill("modified_time", FieldFill.UPDATE);
        TableFill createAccountId = new TableFill("create_account_id", FieldFill.INSERT);
        TableFill modifiedAccountId = new TableFill("modified_account_id", FieldFill.UPDATE);
        tableFills.add(createTime);
        tableFills.add(modifiedTime);
        tableFills.add(createAccountId);
        tableFills.add(modifiedAccountId);
        strategy.setTableFillList(tableFills);


        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine()); // 使用Freemarker模板引擎
        mpg.execute();
    }

}
