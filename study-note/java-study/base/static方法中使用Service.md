### 静态方式中使用Service对象
1.在工具类上加一个@Component注解，把它交给spring去管理
```Java
@Component
public class EasyExcelUtil {
  ...
}
```
2.把需要调用的service注入进来，然后在用静态的注入一次

```Java
@Component
public class EasyExcelUtil {
  @Autowired
   private SysExcelService excelService;

   @Autowired
   private SysDictService dictService;
}
```

3.@PostConstruct注解在完成依赖注入之后执行

```Java
@Component
public class EasyExcelUtil {
  @Autowired
   private SysExcelService excelService;

   @Autowired
   private SysDictService dictService;

   public static EasyExcelUtil easyExcelUtil;
   @PostConstruct
   public void init() {
       easyExcelUtil = this;
       easyExcelUtil.excelService = this.excelService;
       easyExcelUtil.dictService = this.dictService;
   }
}
```

4. 在static方法中使用
```Java
@Component
public class EasyExcelUtil {
  @Autowired
   private SysExcelService excelService;

   @Autowired
   private SysDictService dictService;

   public static EasyExcelUtil easyExcelUtil;
   @PostConstruct
   public void init() {
       easyExcelUtil = this;
       easyExcelUtil.excelService = this.excelService;
       easyExcelUtil.dictService = this.dictService;
   }

   public static void exportData(String filename,
                                  String sheetName,
                                  String entityName,
                                  String excelName,
                                  QueryVO query) throws Exception {
        SysExcel sysExcel = easyExcelUtil.excelService.getByName(entityName, excelName);

      ...
    }
}
```
