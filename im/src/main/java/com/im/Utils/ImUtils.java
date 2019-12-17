package com.im.Utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.lang.reflect.Field;
import java.util.UUID;

public class ImUtils {

    /**
     * 获取UUID
     */
    public static  String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 将文字转为汉语拼音
     * @param chineselanguage 要转成拼音的中文
     */
     public String toHanyuPinyin(String chineseLanguage) {
         char[] cl_chars = chineseLanguage.trim().toCharArray();
         String hanyupinyin = "";
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
         defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
         try {
             for (int i = 0; i < cl_chars.length; i++) {
                 if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                     hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                     hanyupinyin += cl_chars[i];
                 }
             }
         }catch (BadHanyuPinyinOutputFormatCombination e) {
             System.out.println("字符不能转成汉语拼音");
         }
         return hanyupinyin;
     }

    /**
     * 用于获取结果集的映射关系
     */
    @Deprecated
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }
}
