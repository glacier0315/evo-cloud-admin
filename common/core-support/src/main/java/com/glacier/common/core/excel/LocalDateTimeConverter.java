package com.glacier.common.core.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-06 19:49
 */
public class LocalDateTimeConverter implements Converter<LocalDate> {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Class<LocalDate> supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDate convertToJavaData(
            CellData cellData,
            ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return LocalDate.parse(cellData.getStringValue(),
                    DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
        }
        return LocalDate.parse(cellData.getStringValue(),
                DateTimeFormatter.ofPattern(
                        contentProperty.getDateTimeFormatProperty()
                                .getFormat()));
    }

    @Override
    public CellData<?> convertToExcelData(
            LocalDate value,
            ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return new CellData<>(
                    DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS)
                            .format(value));
        }
        return new CellData<>(
                DateTimeFormatter.ofPattern(
                        contentProperty.getDateTimeFormatProperty()
                                .getFormat())
                        .format(value));

    }
}
